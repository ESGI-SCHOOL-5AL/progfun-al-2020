package projetal2020

class Grid(val width: Int, val height: Int, val lawnmowerCount: Int) {
    val lawnmowers = Array.fill[Lawnmower](lawnmowerCount)(new Lawnmower(width, height))
  
    def debug() = lawnmowers foreach println
}