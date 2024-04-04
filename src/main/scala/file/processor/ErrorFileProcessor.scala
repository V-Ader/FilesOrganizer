package file.processor

import file.{FileOperator, ProcessedFile}

import java.nio.file.Path
import scala.util.{Failure, Success, Try}

class ErrorFileProcessor(val directory: Path)  {

  def processFile(processedFile: ProcessedFile): Try[String] = {
    if(! FileOperator.equals(
        processedFile.currentPath,
        processedFile.destinationPath)) {
      println(processedFile.errorPath)
      FileOperator.copy(processedFile.currentPath, processedFile.errorPath) match {
        case Success(_) => return Success("SAVED")
        case Failure(exception) => return Failure(exception)
      }
    }
    Success("SKIPPED")
  }
}

object ErrorFileProcessor  {
  def apply(directory: Path) = new ErrorFileProcessor(directory)
}

