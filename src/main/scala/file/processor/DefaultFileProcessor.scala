package file.processor
import file.FileOperator.copy
import file.{FileOperator, PathResolver, ProcessedFile}

import java.nio.file.{FileAlreadyExistsException, Path}
import scala.util.{Failure, Success, Try}

class DefaultFileProcessor(val directory: Path) {

  def processFile(file: ProcessedFile): Try[ProcessingOutcome] = {
    println(file.destinationPath)
    FileOperator.copy(file.currentPath, file.destinationPath) match {
      case Success(_) => Success(OutcomeSaved)
      case Failure(exception) => Failure(exception)
    }
  }
}

object DefaultFileProcessor {
  def apply(directory: Path) = new DefaultFileProcessor(directory)
}
