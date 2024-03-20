package media

import config.Config

import java.nio.file.Path

object MediaTypeChecker {

  private val mediaTypes: Set[String] = Config.getMediaTypes

  def isMediaType(filePath: Path): Boolean = {
    filePath.toString.toLowerCase.split("\\.").lastOption
      .map(_.toLowerCase())
      .exists(mediaTypes.contains)
  }
}
