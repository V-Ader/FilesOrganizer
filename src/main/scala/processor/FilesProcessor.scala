package processor

import config.Config
import media.MediaTypeChecker
import result.{OutcomeError, ProcessingOutcome, ProcessingResult}

import java.nio.file.{FileAlreadyExistsException, FileSystems, Files, Path}
import scala.jdk.CollectionConverters.IteratorHasAsScala
import scala.util.{Failure, Success}

class FilesProcessor(sourceDirectory: Path, fileProcessor: DefaultFileProcessor, errorFileProcessor: ErrorFileProcessor) {

  def processDirectory(): ProcessingResult = {
    val processingResults = Files.find(sourceDirectory, Int.MaxValue, (_, fileAttr) => fileAttr.isRegularFile)
      .iterator()
      .asScala
      .filter(MediaTypeChecker.isMediaType)
      .map(file => ProcessedFile(file, fileProcessor.directory, errorFileProcessor.directory))
      .map(processFile)
      .foldLeft(ProcessingResult()) { (acc, outcome) =>
        acc.update(outcome)
      }

    processingResults
  }
  private def processFile(processedFile: ProcessedFile): ProcessingOutcome = {
    fileProcessor.processFile(processedFile) match {
      case Success(value) => value
      case Failure(_: FileAlreadyExistsException) => errorFileProcessor.processFile(processedFile).get
      case Failure(exception) => println("Unexpected exception occurred for file: ", processedFile.currentPath,
        "Exception:", exception); OutcomeError
    }
  }
}

object FilesProcessor {
  def apply(config: Config): FilesProcessor = new FilesProcessor(
    FileSystems.getDefault.getPath(config.sourcePath),
    new DefaultFileProcessor(FileSystems.getDefault.getPath(config.destinationPath)),
    new ErrorFileProcessor(FileSystems.getDefault.getPath(config.errorPath)))
}
