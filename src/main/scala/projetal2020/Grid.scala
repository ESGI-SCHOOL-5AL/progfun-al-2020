package projetal2020

class Grid(val width: Int, val height: Int, val lawnmowerCount: Int) {
    val lawnmowers = Array.fill[Lawnmower](lawnmowerCount)(new Lawnmower(width, height))
  
    def debug() = lawnmowers foreach println
    
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
      for( x <- 0 to this.width - 1) {
        val l: Option[Lawnmower] = this.lawnmowers.find(l => l.positionX == x && l.positionY == y)
        if (l != None) {
          values(x) = l.get.orientation match {
            case Orientation.North => "↑"
            case Orientation.East => "→"
            case Orientation.South => "↓"
            case Orientation.West => "←"
          }
        }
      }
      "│" + values.mkString("│") + "│\n"
    }
  
    def printRows() = {
      val rows = Array.fill[String](this.height)("")
      for (y <- 0 to this.height - 1) {
        rows(y) = buildRow(y)
      }
      println(rows.mkString(buildRowSeparator() + "\n"))
    }
}
