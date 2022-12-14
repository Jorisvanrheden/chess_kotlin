package my.qualified.packagename

import my.qualified.packagename.logic.Board
import my.qualified.packagename.logic.MoveHandler
import my.qualified.packagename.model.Coordinate

fun main() {
    console.log("Initializing module")

    val coord = Coord(0, 0)
    val board = Wrapper(10, 10)
}

@JsExport
class Coord(val x: Int, val y: Int)

@JsExport
class Wrapper(private val sizeX: Int, private val sizeY: Int) {
    private val board: Board = Board(sizeX, sizeY)

    private var moveHandler: MoveHandler

    init {
        getMoves(0, 0)
        moveHandler = MoveHandler(board)
    }

    fun processMove(xOrigin: Int, yOrigin: Int, xTarget: Int, yTarget: Int) {
        moveHandler.processMove(Coordinate(xOrigin, yOrigin), Coordinate(xTarget, yTarget))
    }

    fun undoMove() {
        moveHandler.undoMove()
    }

    fun getMoves(x: Int, y: Int): Array<Coord> {
        return board.getMoveSets(
            Coordinate(x, y)
        ).map { Coord(it.moves[0].to.x, it.moves[0].to.y) }.toTypedArray()
    }

    fun getBoardRepresentation(): Array<Array<Int>> {
        return board.getBoardRepresentation()
    }
}
