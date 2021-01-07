package projetal2020

class Grid(val width: Int, val height: Int, val lawnmowers: Array[Lawnmower]) {

  def print() = {
    printHeader()
    printRows()
    printFooter()
  }

  def printHeader() = println(buildSeparator("┌", "┬", "┐"))
  def printFooter() = println(buildSeparator("└", "┴", "┘"))

  def buildRowSeparator() = buildSeparator("├", "┼", "┤")

  def buildSeparator(begin: String, mid: String, end: String) = {
    begin + Array.fill[String](this.width)("─").mkString(mid) + end
  }

  def buildRow(y: Int): String = {
    val values = Array.fill[String](this.width)(" ")
    for (x <- 0 to this.width - 1) {
      val l: Option[Lawnmower] =
        this.lawnmowers.find(l => l.positionX == x && l.positionY == y)
      values(x) = l match {
        case Some(l: Lawnmower) =>
          l.orientation match {
            case Orientation.North => "↑"
            case Orientation.East  => "→"
            case Orientation.South => "↓"
            case Orientation.West  => "←"
          }
        case _ => " "
      }
    }
    "│" + values.mkString("│") + "│\n"
  }

  def printRows() = {
    val rows = Array.tabulate(this.height) { i =>
      buildRow(i)
    }
    println(rows.mkString(buildRowSeparator() + "\n"))
  }
}
