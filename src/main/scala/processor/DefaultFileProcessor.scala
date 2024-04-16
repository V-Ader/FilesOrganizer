package processor

import file.FileOperator
import result.{OutcomeSaved, ProcessingOutcome}

import java.nio.file.Path
import scala.util.{Failure, Success, Try}

class DefaultFileProcessor(val directory: Path) extends FileProcessor{

  def processFile(file: ProcessedFile): Try[ProcessingOutcome] = {
    FileOperator.copy(file.currentPath, file.destinationPath) match {
      case Success(_) => Success(OutcomeSaved)
      case Failure(exception) => Failure(exception)
    }
  }
}

object DefaultFileProcessor {
  def apply(directory: Path) = new DefaultFileProcessor(directory)
}
