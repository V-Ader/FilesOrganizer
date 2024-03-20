package file

import java.nio.file.Path
import java.util.{Calendar, Date}
import scala.util.Random

object PathResolver {

  def getDirectoryName(calendar: Calendar): String = {
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    s"$year-${"%02d".format(month)}"
  }

  def getNewDirectoriesForFile(filePath: Path): Path = {
    val file = filePath.toFile
    val newDestination = new Date(file.lastModified())
    val calendar = Calendar.getInstance()
    calendar.setTime(newDestination)
    val newDirName = getDirectoryName(calendar)
    Path.of(newDirName).resolve(file.getName)
  }

  def getFileExtension(filePath: Path): String = {
    filePath.toString.split("\\.").lastOption.get
  }

  def getFileName(filePath: Path): String = {
    filePath.toString.split("\\.").headOption.get
  }

  def generateUniqueFilename(filename: Path): String = {
    val rand = new Random
    val randomSuffix = rand.nextInt()
    s"${PathResolver.getFileName(filename)}_err_$randomSuffix.${PathResolver.getFileExtension(filename)}"
  }

}
