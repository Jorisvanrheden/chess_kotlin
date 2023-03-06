package my.qualified.packagename.pieces

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.Move
import my.qualified.packagename.model.MoveSet
import my.qualified.packagename.model.PieceType
import my.qualified.packagename.model.PlayerType

abstract class Piece(val playerType: PlayerType) {
    protected fun getTargets(target: Coordinate, board: Board): List<Piece> {
        val targetPiece = board.getPiece(target) ?: return emptyList()
        return listOf(targetPiece)
    }

    var hasMoved = false

    abstract fun getMoves(coordinate: Coordinate, board: Board): List<MoveSet>

    abstract fun getTypeId(): PieceType

    fun Move.toMoveSet() =
        MoveSet(
            listOf(this),
        )
    fun Coordinate.toMove(board: Board) =
        Move(
            board.getPiece(Coordinate(0, 0)),
            getTargets(this, board),
            Coordinate(0, 0),
            this,
        )

    override fun toString(): String {
        return if (playerType == PlayerType.BLACK) {
            (getTypeId().id + 6).toString()
        } else {
            getTypeId().id.toString()
        }
    }
}
