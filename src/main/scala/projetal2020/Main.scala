package projetal2020

object Main extends App {
  if (args.length == 0) {
    println("File path required")
    sys.exit(1)
  }

  val fileContents = ConfigurationParser.configurationFileToString(args(0))
  val split = ConfigurationParser.splitByBlock(fileContents)
  val config = ConfigurationParser.parse(split)

  val grid = new Grid(config.sizeGrid(0), config.sizeGrid(0), config.lawnmowers)
  grid.print()

  val lawnmowers = config.lawnmowers.zipWithIndex.map {
    case (lawnmower, index) => {
      config.instructions(index).foldLeft[Lawnmower](lawnmower) { (a, m) =>
        a.move(m)
      }
    }
  }.toList

  val gridResult = new Grid(config.sizeGrid(0), config.sizeGrid(0), lawnmowers)
  gridResult.print()
}
