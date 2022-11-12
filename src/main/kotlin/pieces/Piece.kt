package my.qualified.packagename.pieces

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.Move
import my.qualified.packagename.model.MoveSet
import my.qualified.packagename.model.PlayerType

abstract class Piece(val playerType: PlayerType) {
    private var moveHistory = mutableListOf<Move>()

    private var coordinate: Coordinate = Coordinate(0, 0)

    fun storeMove(move: Move) {
        moveHistory.add(move)
        setCurrentCoordinate(move.target)
    }
    fun getMoveCount(): Int {
        return moveHistory.size
    }
    fun setCurrentCoordinate(coordinate: Coordinate) {
        this.coordinate = coordinate
    }
    fun getCurrentCoordinate(): Coordinate {
        return this.coordinate
    }

    abstract fun getMoves(coordinate: Coordinate, board: Board): List<MoveSet>

    abstract fun getTypeId(): Int
}
