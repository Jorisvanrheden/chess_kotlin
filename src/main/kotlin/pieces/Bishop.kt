package my.qualified.packagename.pieces

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.Direction
import my.qualified.packagename.model.MoveSet
import my.qualified.packagename.model.PieceType
import my.qualified.packagename.model.PlayerType
import my.qualified.packagename.moves.getMovesInDirection

class Bishop(playerType: PlayerType) : Piece(playerType) {
    override fun getMoves(coordinate: Coordinate, board: Board): List<MoveSet> =
        DIRECTIONS.map {
            getMovesInDirection(it, coordinate, board)
        }
            .flatten()
            .filter {
                board.isCoordinateWithinBounds(it)
            }
            .map {
                it.toMove(board).toMoveSet()
            }

    override fun getTypeId(): PieceType {
        return PieceType.BISHOP
    }

    private companion object {
        val DIRECTIONS = listOf(
            Direction(1, 1),
            Direction(-1, -1),
            Direction(-1, 1),
            Direction(1, -1),
        )
    }
}
