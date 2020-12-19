package projetal2020

object Main extends App {
    println("Ici le programme principal")

    val grid = new Grid(2, 2)
    grid.debug()

    grid.grid(1)(1) = Option[Lawnmower](new Lawnmower("test"))
    grid.debug()
}
