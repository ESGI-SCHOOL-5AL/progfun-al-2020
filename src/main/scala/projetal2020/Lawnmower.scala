package projetal2020

class Lawnmower(
    val gridWidth: Int,
    val gridHeight: Int,
    val orientation: Orientation.Orientation,
    val positionX: Int,
    val positionY: Int
) {

  def move(m: Move.Move): Lawnmower = m match {
    case Move.RotateLeft =>
      new Lawnmower(
        gridWidth,
        gridHeight,
        Orientation.fromInt(orientation.id - 1),
        positionX,
        positionY
      )
    case Move.RotateRight =>
      new Lawnmower(
        gridWidth,
        gridHeight,
        Orientation.fromInt(orientation.id + 1),
        positionX,
        positionY
      )
    case Move.Forward => forward()
  }

  def forward(): Lawnmower = {
    orientation match {
      case Orientation.North =>
        new Lawnmower(
          gridWidth,
          gridHeight,
          orientation,
          positionX,
          Utils.clamp(positionY - 1, 0, gridHeight - 1)
        )
      case Orientation.East =>
        new Lawnmower(
          gridWidth,
          gridHeight,
          orientation,
          Utils.clamp(positionX + 1, 0, gridWidth - 1),
          positionY
        )
      case Orientation.South =>
        new Lawnmower(
          gridWidth,
          gridHeight,
          orientation,
          positionX,
          Utils.clamp(positionY + 1, 0, gridHeight - 1)
        )
      case Orientation.West =>
        new Lawnmower(
          gridWidth,
          gridHeight,
          orientation,
          Utils.clamp(positionX - 1, 0, gridWidth - 1),
          positionY
        )
    }
  }

}
