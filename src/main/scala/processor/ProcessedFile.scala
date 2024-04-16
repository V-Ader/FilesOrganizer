package processor

import file.PathResolver

import java.nio.file.Path

case class ProcessedFile(currentPath: Path, destinationPath: Path, errorPath: Path)

object ProcessedFile {
  def apply(filePath: Path, destinationDirectory: Path, errorDirectory: Path): ProcessedFile = {
    val organizedDestination = PathResolver.getDestinationPath(filePath, destinationDirectory)
    val organizedError = PathResolver.getErrorPath(filePath, errorDirectory)
    new ProcessedFile(filePath, organizedDestination, organizedError)
  }
}