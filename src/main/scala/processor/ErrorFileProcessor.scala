package processor

import file.FileOperator
import result.{OutcomeSaved, OutcomeSkipped, ProcessingOutcome}

import java.nio.file.Path
import scala.util.{Failure, Success, Try}

class ErrorFileProcessor(val directory: Path) extends FileProcessor {

  override def processFile(processedFile: ProcessedFile): Try[ProcessingOutcome] = {
    if(!FileOperator.equals(
        processedFile.currentPath,
        processedFile.destinationPath)) {
      FileOperator.copy(processedFile.currentPath, processedFile.errorPath) match {
        case Success(_) => return Success(OutcomeSaved)
        case Failure(exception) => return Failure(exception)
      }
    }
    Success(OutcomeSkipped)
  }
}

object ErrorFileProcessor  {
  def apply(directory: Path) = new ErrorFileProcessor(directory)
}

