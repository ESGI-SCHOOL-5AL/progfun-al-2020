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

  test("Error test size") {
    assertThrows[DonneesIncorectesException] {
      ConfigurationParser.parseFile(
        "test_inputs/sample_files/sample_error_1.txt"
      )
    }
  }

  test("Error test position") {
    assertThrows[DonneesIncorectesException] {
      ConfigurationParser.parseFile(
        "test_inputs/sample_files/sample_error_2.txt"
      )
    }
  }

  test("Error test instructions") {
    assertThrows[DonneesIncorectesException] {
      ConfigurationParser.parseFile(
        "test_inputs/sample_files/sample_error_3.txt"
      )
    }
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
