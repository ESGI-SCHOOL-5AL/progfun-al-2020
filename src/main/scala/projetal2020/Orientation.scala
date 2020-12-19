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
}

object Move extends Enumeration {
  type Move = Value
  val RotateLeft, RotateRight, Forward = Value
}
