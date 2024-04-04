package config

case class Config(mediaTypes: Set[String],
                  sourcePath: String, destinationPath: String, errorPath: String)

object Config {
  def apply(sourcePath: String, destinationPath: String, errorPath: String) = new Config(
    getMediaTypes,sourcePath, destinationPath, errorPath)

  def getMediaTypes: Set[String] = {
    Set("jpg", "jpeg")
  }
}
