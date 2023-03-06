import my.qualified.packagename.logic.Board
import my.qualified.packagename.logic.MoveHandler
import my.qualified.packagename.model.Coordinate

val board = Board()
val moveHandler = MoveHandler(board)

fun main() {
    print(board.toString())
    print(board.getMoves(Coordinate(4, 6)))
}

fun processMove(xOrigin: Int, yOrigin: Int, xTarget: Int, yTarget: Int) {
    moveHandler.processMove(Coordinate(xOrigin, yOrigin), Coordinate(xTarget, yTarget))
}

fun undoMove() {
    moveHandler.undoMove()
}
