package my.qualified.packagename

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate

fun main() {
    console.log("Initializing module")

    val coord = Coord(0, 0)
    val board = Wrapper(10, 10)
}

@JsExport
class Coord(val x: Int, val y: Int)

@JsExport
class Wrapper(val sizeX: Int, val sizeY: Int) {
    private val board: Board = Board(sizeX, sizeY)

    init {
        getMoves(0, 0)
    }

    fun processMove(xOrigin: Int, yOrigin: Int, xTarget: Int, yTarget: Int) {
        board.processMove(xOrigin, yOrigin, xTarget, yTarget)
    }

    fun getMoves(x: Int, y: Int): Array<Coord> {
        val coordinate = Coordinate(x, y)
        val piece = board.getPiece(coordinate) ?: return emptyArray()
        val moves = piece.getMoves(coordinate, board)

        var movePairs = mutableListOf<Coord>()
        for (m in moves) {
            movePairs.add(Coord(m.x, m.y))
        }
        return movePairs.toTypedArray()
    }

    fun getBoardRepresentation(): Array<Array<Int>> {
        return board.getBoardRepresentation()
    }
}
