import java.io.File
import javax.imageio.ImageIO

@OptIn(ExperimentalUnsignedTypes::class)
fun main() {
    val img = ImageIO.read(File("test1.jpg"))
    val imgc = ImageIO.read(File("test2.jpg"))
    val imgD = DHash.fromImage(img)
    val imgCD = DHash.fromImage(imgc)
    println(imgD.distanceTo(imgCD))
}

