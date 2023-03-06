import my.qualified.packagename.logic.Board
import my.qualified.packagename.logic.MoveHandler
import my.qualified.packagename.model.Coordinate

val board = Board()
val moveHandler = MoveHandler(board)

fun main() {
    print(board.toString())
    moveHandler.playMove(Coordinate(4, 6), Coordinate(4, 5))
    print(board.toString())
    moveHandler.playMove(Coordinate(4, 6), Coordinate(4, 5))
}

fun processMove(xOrigin: Int, yOrigin: Int, xTarget: Int, yTarget: Int) {
    moveHandler.playMove(Coordinate(xOrigin, yOrigin), Coordinate(xTarget, yTarget))
}

fun undoMove() {
    moveHandler.undoMove()
}
