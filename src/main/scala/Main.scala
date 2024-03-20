import file.FilesProcessor

object Main {


  def main(args: Array[String]): Unit = {
    if (args.length < 4) {
      println("Usage: Main <source-directory> <destination-directory> <error-directory>")
      println("Usage example: Main .\\user\\data\\ .\\user\\parsed\\ .\\user\\error\\")
      System.exit(1)
    }

    val filesProcessor = FilesProcessor.apply(src=args(0), dist=args(1), error=args(2))
    filesProcessor.processDirectory()
  }
}