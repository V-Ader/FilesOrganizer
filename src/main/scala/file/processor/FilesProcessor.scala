package file.processor

import config.Config
import file.ProcessedFile
import media.MediaTypeChecker

import java.nio.file.{FileAlreadyExistsException, FileSystems, Files, Path}
import scala.util.{Failure, Success}

class FilesProcessor(sourceDirectory: Path, fileProcessor: DefaultFileProcessor, errorFileProcessor: ErrorFileProcessor) {

  def processDirectory(): Unit = {
    var succeed = 0
    var errors = 0
    var skipped = 0
    Files.find(sourceDirectory, Int.MaxValue, (_, fileAttr) => fileAttr.isRegularFile)
      .filter(MediaTypeChecker.isMediaType)
      .map(file  => ProcessedFile(file, fileProcessor.directory, errorFileProcessor.directory))
      .forEach(processedFile => {
        fileProcessor.processFile(processedFile) match {
          case Success(_) => succeed += 1
          case Failure(_: FileAlreadyExistsException) =>
            errorFileProcessor.processFile(processedFile) match {
              case Success(status) =>
                if (status == "SAVED") errors += 1
                if (status == "SKIPPED") skipped += 1
            }
          }
        }
      )
    println("saved: " + succeed, " errors saved: " + errors, " skipped: " + skipped)
  }
}

object FilesProcessor {
  def apply(config: Config) = new FilesProcessor(
    FileSystems.getDefault.getPath(config.sourcePath),
    new DefaultFileProcessor(FileSystems.getDefault.getPath(config.destinationPath)),
    new ErrorFileProcessor(FileSystems.getDefault.getPath(config.errorPath)))
}

