package processor

import result.ProcessingOutcome

import scala.util.Try

trait FileProcessor {
  def processFile(processedFile: ProcessedFile): Try[ProcessingOutcome]
}
