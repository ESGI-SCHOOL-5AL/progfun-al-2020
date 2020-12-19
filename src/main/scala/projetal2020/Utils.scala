package projetal2020

import math.{min, max}

def clamp(x: Int, lower: Int, upper: Int): Int = (lower max x) min upper
