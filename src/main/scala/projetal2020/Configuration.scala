package projetal2020

final case class DonneesIncorectesException(val message: String)
    extends Exception(message)

class Configuration(
    val sizeGrid: List[Int],
    val lawnmowers: List[Lawnmower],
    val instructions: List[List[Move.Move]]
) {
  override def toString(): String =
    "[ Size: " + sizeGrid.toString + " | Lawnmowers: " + lawnmowers.toString + " | " + instructions.toString + " ]"
}

@SuppressWarnings(Array("org.wartremover.warts.Throw"))
object ConfigurationParser {

  def parseFile(fileName: String): Configuration =
    ConfigurationParser.parse(
      ConfigurationParser
        .splitByBlock(ConfigurationParser.configurationFileToString(fileName))
    )

  def parse(file: List[String]): Configuration = {
    val size = parseSize(file(0))
    val defs = parseLawnmowers(file.drop(1), size)
    val lawnmowers =
      defs.map(a => new Lawnmower(size(0), size(1), a._1._3, a._1._1, a._1._2))
    val instructions: List[List[Move.Move]] = defs.map(a => a._2)
    new Configuration(size, lawnmowers, instructions)
  }

  def configurationFileToString(filename: String): String =
    scala.io.Source.fromFile(filename).mkString

  def splitByBlock(rawData: String): List[String] = rawData.split("\n").toList

  def parseSize(line: String): List[Int] = {
    val size = line.split(" ").toList.take(2).map(_.toInt)
    size.length match {
      case 2 =>
        size.find(s => s < 1) match {
          case None => size
          case _ =>
            throw new DonneesIncorectesException("Size must be superior to 0")
        }
      case _ =>
        throw new DonneesIncorectesException("Malformed grid size definition")
    }
  }

  def parseLawnmowers(
      blocks: List[String],
      gridSize: List[Int]
  ): List[((Int, Int, Orientation.Orientation), List[Move.Move])] = {
    if (blocks.length % 2 == 0) {
      blocks
        .grouped(2)
        .toList
        .map(
          lawnmowerData => // List[String] 2
            (
              parseLawnmowerStartPosition(lawnmowerData(0), gridSize),
              parseLawnmowerMoveList(lawnmowerData(1))
            )
        )
    } else {
      throw new DonneesIncorectesException(
        "Invalid configuration data for the grid"
      )
    }
  }

  def parseLawnmowerStartPosition(
      startPosition: String,
      gridSize: List[Int]
  ): (Int, Int, Orientation.Orientation) = {
    val split = startPosition.split(" ")
    if (split.length == 3) {
      val x = split(0).toInt
      val y = split(1).toInt

      if (x >= gridSize(0) || y >= gridSize(1)) {
        throw new DonneesIncorectesException(
          "Lawnmover start position is out of bounds"
        )
      } else {
        (split(0).toInt, split(1).toInt, Orientation.fromString(split(2)))
      }
    } else {
      throw new DonneesIncorectesException("Lawnmover line is malformed")
    }
  }

  def parseLawnmowerMoveList(moveList: String): List[Move.Move] =
    moveList.map(c => Move.fromChar(c)).toList
}
