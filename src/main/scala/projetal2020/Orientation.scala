package projetal2020

object Orientation extends Enumeration {
  type Orientation = Value
  val North, East, South, West = Value

  def fromInt(o: Int): Orientation = {
    if (o < 0)
      this.apply((o + 4) % 4)
    else
      this.apply(o % 4)
  }

  def fromString(l: Char): Orientation = l match {
    case "N" => North
    case "E" => East
    case "S" => South
    case "W" => West
    case _   => throw new DonneesIncorectesException("Invalid orientation")
  }
}

object Move extends Enumeration {
  type Move = Value
  val RotateLeft, RotateRight, Forward = Value

  def fromChar(c: Char): Move = c match {
    case "G" => RotateLeft
    case "D" => RotateRight
    case "A" => Forward
    case _   => throw new DonneesIncorectesException("Invalid move")
  }
}
