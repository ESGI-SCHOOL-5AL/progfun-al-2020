package projetal2020

import play.api.libs.json._

object Main extends App {
  if (args.length == 0) {
    println("File path required")
    sys.exit(1)
  } else {
    val config = ConfigurationParser.parseFile(args(0))

    val lawnmowers =
      Utils.applyInstructions(config.lawnmowers, config.instructions)

    if (args.length == 2 && args(1) == "pretty") {
      val grid =
        new Grid(config.sizeGrid(0), config.sizeGrid(0), config.lawnmowers)
      println("Initial state:")
      grid.print()
      println("End state:")
      val gridResult =
        new Grid(config.sizeGrid(0), config.sizeGrid(0), lawnmowers)
      gridResult.print()
    } else {
      println(Json.prettyPrint(JSONGrid.toJSON(config, lawnmowers)))
    }
  }
}
