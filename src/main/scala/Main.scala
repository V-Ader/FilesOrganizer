import config.Config
import processor.FilesProcessor

object Main {

  def main(args: Array[String]): Unit = {
    if (args.length < 3) {
      println("Usage: Main <source-directory> <destination-directory> <error-directory> <snapshot-path>")
      println("Usage example: Main .\\user\\data\\ .\\user\\parsed\\ .\\user\\error\\ .\\user\\data\\snap.data")
      System.exit(1)
    }

    val config = Config.apply(
      sourcePath = args(0), destinationPath = args(1),
      errorPath = args(2))

    val filesProcessor = FilesProcessor.apply(config)
    val results = filesProcessor.processDirectory()
    printf("Successed: %d, Skipped: %d, Errors: %d", results.succeeded, results.skipped, results.errors)
  }
}