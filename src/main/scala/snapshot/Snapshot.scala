package snapshot

import java.nio.file.Path
import scala.io.Source

case class Snapshot(snapPath: Path) {
  private val snapshots: Map[String, Long] = readKeyValuePairsFromFile(snapPath)

  def getSnapshot(key: String): Option[Long] = snapshots.get(key)

  private def readKeyValuePairsFromFile(filePath: Path): Map[String, Long] = {
    val keyValuePairs = scala.collection.mutable.Map[String, Long]()
    val source = Source.fromFile(filePath.toFile)
    try {
      for (line <- source.getLines()) {
        val Array(key, value) = line.split(" ")
        keyValuePairs.put(key.trim, value.trim.toLong)
      }
    } finally {
      source.close()
    }
    keyValuePairs.toMap
  }
}

object Snapshot {
}