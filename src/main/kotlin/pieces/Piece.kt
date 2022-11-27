package my.qualified.packagename.pieces

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.Move
import my.qualified.packagename.model.MoveSet
import my.qualified.packagename.model.PieceType
import my.qualified.packagename.model.PlayerType

abstract class Piece(val playerType: PlayerType) {
    private var moveHistory = mutableListOf<Move>()

    private var coordinate: Coordinate = Coordinate(0, 0)

    fun storeMove(move: Move) {
        moveHistory.add(move)
        setCurrentCoordinate(move.to)
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
    fun removeLastCoordinate() {
        if (moveHistory.size > 0) {
            moveHistory.removeLast()
        }
        if (moveHistory.size > 0) {
            setCurrentCoordinate(moveHistory[moveHistory.size - 1].to)
        }
    }

    protected fun getTargets(target: Coordinate, board: Board): List<Piece> {
        val targetPiece = board.getPiece(target) ?: return emptyList()
        return listOf(targetPiece)
    }

    abstract fun getMoves(coordinate: Coordinate, board: Board): List<MoveSet>

    abstract fun getTypeId(): PieceType
}
