import java.awt.image.BufferedImage

@OptIn(ExperimentalUnsignedTypes::class)
inline class DHash(val raw: ULong) {
    fun distanceTo(other: DHash): Int {
        return (raw xor other.raw).countOneBits()
    }

    companion object {
        @OptIn(ExperimentalUnsignedTypes::class)
        fun fromImage(img: BufferedImage): DHash {
            val scl = BufferedImage(9, 8, BufferedImage.TYPE_BYTE_GRAY)
            scl.graphics.drawImage(img, 0, 0, scl.width, scl.height, 0, 0, img.width, img.height, null)
            var hash = 0UL
            for (row in 0 until 8) {
                for (col in 0 until 8) {
                    val risingGrad = scl.getRGB(col, row) <= scl.getRGB(col + 1, row)
                    hash = hash or if (risingGrad) 1UL else 0UL
                    if (row == 7 && col == 7) break // otherwise we lose the last digit.
                    hash = hash shl 1
                }
            }
            return DHash(hash)
        }
    }
}