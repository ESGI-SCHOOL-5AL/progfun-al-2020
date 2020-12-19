package projetal2020

class Lawnmower(val gridWidth: Int, val gridHeight: Int) {
    var orientation = Orientation.North
    var positionX: Int = 0
    var positionY: Int = 0
    
    def move(m: Move.Move) = m match {
        case Move.RotateLeft => orientation = Orientation.fromInt(orientation.id - 1)
        case Move.RotateRight => orientation = Orientation.fromInt(orientation.id + 1)
        case Move.Forward => this.forward()
    }
    
    def forward() = {
        orientation match {
            case Orientation.North => this.positionY -= 1
            case Orientation.East => this.positionX += 1
            case Orientation.South => this.positionY += 1
            case Orientation.West => this.positionX -= 1
        }
        this.keepInBounds()
    }
    
    def keepInBounds() = {
        this.positionX = clamp(this.positionX, 0, gridWidth - 1)
        this.positionY = clamp(this.positionY, 0, gridHeight - 1)
    }

    override def toString(): String = "[O: " + orientation + ", X: " + positionX + ", Y: " + positionY + "]"
}
