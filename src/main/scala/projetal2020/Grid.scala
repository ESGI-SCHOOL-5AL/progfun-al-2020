package projetal2020

import play.api.libs.json._

class Grid(val width: Int, val height: Int, val lawnmowers: List[Lawnmower]) {

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
      buildRow(height - 1 - i)
    }
    println(rows.mkString(buildRowSeparator() + "\n"))
  }
}

object JSONGrid {
  def toJSON(config: Configuration, lawnmowerList: List[Lawnmower]): JsValue = {
    Json.toJson(
      JsObject(
        Seq[(String, JsValue)](
          "limite" -> JsObject(
            Seq[(String, JsNumber)](
              "x" -> JsNumber(config.sizeGrid(0)),
              "y" -> JsNumber(config.sizeGrid(1))
            )
          ),
          "tondeuses" -> JsArray(config.lawnmowers.zipWithIndex.map {
            case (lawnmower, index) => {
              JsObject(
                Seq[(String, JsValue)](
                  "debut" -> JsObject(
                    Seq[(String, JsValue)](
                      "point" -> JsObject(
                        Seq[(String, JsNumber)](
                          "x" -> JsNumber(lawnmower.positionX),
                          "y" -> JsNumber(lawnmower.positionY)
                        )
                      ),
                      "direction" -> JsString(
                        Orientation.toChar(lawnmower.orientation)
                      )
                    )
                  ),
                  "instructions" -> JsArray(
                    config.instructions(index).map { m =>
                      JsString(Move.toChar(m))
                    }
                  ),
                  "fin" -> JsObject(
                    Seq[(String, JsValue)](
                      "point" -> JsObject(
                        Seq[(String, JsNumber)](
                          "x" -> JsNumber(lawnmowerList(index).positionX),
                          "y" -> JsNumber(lawnmowerList(index).positionY)
                        )
                      ),
                      "direction" -> JsString(
                        Orientation.toChar(
                          lawnmowerList(index).orientation
                        )
                      )
                    )
                  )
                )
              )
            }
          })
        )
      )
    )
  }
}
