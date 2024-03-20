package config

case class Config(mediaTypes: Set[String])

object Config {

  def apply() = new Config(getMediaTypes)

  def getMediaTypes: Set[String] = {
    Set("jpg", "jpeg")
  }
}
