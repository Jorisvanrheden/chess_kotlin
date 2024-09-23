import my.qualified.packagename.logic.Board
import my.qualified.packagename.logic.MoveHandler
import my.qualified.packagename.model.Coordinate

val board = Board()
val moveHandler = MoveHandler(board)

fun main() {
    println(board.toString())
    println(board.getMoves(Coordinate(4, 6)))
    moveHandler.playMove(Coordinate(4, 6), Coordinate(4, 5))
    println(board.toString())
    println(board.getMoves(Coordinate(4, 5)))
//    moveHandler.playMove(Coordinate(4, 6), Coordinate(4, 5))
}
