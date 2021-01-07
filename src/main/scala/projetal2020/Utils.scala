package projetal2020

object Utils {
  def clamp(x: Int, lower: Int, upper: Int): Int = (lower max x) min upper
}
