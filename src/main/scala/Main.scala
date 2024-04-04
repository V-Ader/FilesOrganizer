import config.Config
import file.processor.FilesProcessor

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
    filesProcessor.processDirectory()
  }
}