package file

import java.nio.file.{Files, Path, Paths, StandardCopyOption}
import scala.util.Try
import java.math.BigInteger
import java.security.MessageDigest


object FileOperator {
  def copy(filePath: Path, newFilePath: Path): Try[Unit] = Try {
    Files.createDirectories(newFilePath.getParent)
    Files.copy(filePath, newFilePath, StandardCopyOption.COPY_ATTRIBUTES)
  }

  def getHash(filePath: Path): String = {
    val bytes = Files.readAllBytes(Paths.get(filePath.toUri))
    val hash = MessageDigest.getInstance("MD5").digest(bytes)
    new BigInteger(1, hash).toString(16)
  }

  def equals(file1: Path, file2: Path): Boolean = {
    val hash1 = FileOperator.getHash(file1)
    val hash2 = FileOperator.getHash(file2)
    hash1 == hash2
  }
}
