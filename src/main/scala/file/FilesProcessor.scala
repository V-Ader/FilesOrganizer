package file

import media.MediaTypeChecker

import java.nio.file.{FileAlreadyExistsException, FileSystems, Files, Path, StandardCopyOption}
import scala.util.Random

case class FilesProcessor(sourceDirectory: Path, destinationDirectory: Path, errorDirectory: Path) {

  def processDirectory(): Unit = {
    Files.find(sourceDirectory, Int.MaxValue, (_, fileAttr) => fileAttr.isRegularFile)
      .filter(MediaTypeChecker.isMediaType)
      .forEach(processFile)
  }

  def processFile(filePath: Path): Unit = {
    val newFilePath = PathResolver.getNewDirectoriesForFile(filePath)
    copyFile(filePath, destinationDirectory, newFilePath)
  }

  def copyFile(filePath: Path, root: Path, newFilePath: Path): Unit = {
    try {
      val destFilePath = root.resolve(newFilePath)
      Files.createDirectories(destFilePath.getParent)
      Files.copy(filePath, destFilePath, StandardCopyOption.COPY_ATTRIBUTES)
    } catch {
      case _: FileAlreadyExistsException =>
        val newFilename = PathResolver.generateUniqueFilename(filePath.getFileName)
        val newErrorPath = errorDirectory.resolve(newFilePath.getParent).resolve(newFilename)
        copyFile(filePath, errorDirectory, newErrorPath)
      case e: Exception => println(s"Error moving file: $e")
    }
  }


}
object FilesProcessor {
  def apply(src: Path, dist: Path, error: Path) = new FilesProcessor(src, dist, error)
  def apply(src: String, dist: String, error: String) = new FilesProcessor(
    FileSystems.getDefault.getPath(src),
    FileSystems.getDefault.getPath(dist),
    FileSystems.getDefault.getPath(error))
}
