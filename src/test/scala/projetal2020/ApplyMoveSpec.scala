package projetal2020

import org.scalatest.funsuite.AnyFunSuite
import play.api.libs.json._

class ApplyMoveSpec extends AnyFunSuite {

  test("Sample test 1") {
    testSample("1")
  }

  test("Sample test 2") {
    testSample("2")
  }

  test("Sample test 3") {
    testSample("3")
  }

  def testSample(id: String) = {
    val config = ConfigurationParser.parseFile(
      "test_inputs/sample_files/sample" + id + ".txt"
    )
    val lawnmowers =
      Utils.applyInstructions(config.lawnmowers, config.instructions)

    assert(
      scala.io.Source
        .fromFile("test_inputs/sample_files/expected_" + id + ".json")
        .mkString === Json.stringify(JSONGrid.toJSON(config, lawnmowers))
    )
  }

}
