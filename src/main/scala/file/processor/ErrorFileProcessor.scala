package file.processor

import file.{FileOperator, ProcessedFile}

import java.nio.file.Path
import scala.util.{Failure, Success, Try}

class ErrorFileProcessor(val directory: Path)  {

  def processFile(processedFile: ProcessedFile): Try[ProcessingOutcome] = {
    if(! FileOperator.equals(
        processedFile.currentPath,
        processedFile.destinationPath)) {
      println(processedFile.errorPath)
      FileOperator.copy(processedFile.currentPath, processedFile.errorPath) match {
        case Success(_) => return Success(OutcomeSaved)
        case Failure(exception) => return Failure(exception)
      }
    }
    OutcomeSkipped.asInstanceOf
  }
}

object ErrorFileProcessor  {
  def apply(directory: Path) = new ErrorFileProcessor(directory)
}

