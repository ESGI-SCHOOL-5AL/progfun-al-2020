package projetal2020

object Utils {
  def clamp(x: Int, lower: Int, upper: Int): Int = (lower max x) min upper

  def applyInstructions(
      lawnmowers: List[Lawnmower],
      instructions: List[List[Move.Move]]
  ): List[Lawnmower] =
    lawnmowers.zipWithIndex.map {
      case (lawnmower, index) => {
        instructions(index).foldLeft[Lawnmower](lawnmower) { (a, m) =>
          a.move(m)
        }
      }
    }.toList
}
