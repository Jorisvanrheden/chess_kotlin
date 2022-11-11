package my.qualified.packagename.pieces

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.Direction
import my.qualified.packagename.model.PlayerType

class Pawn(playerType: PlayerType, private val direction: Direction) : Piece(playerType) {
    override fun getMoves(coordinate: Coordinate, board: Board): List<Coordinate> {
        var moves = mutableListOf<Coordinate>()

        // Default squares
        val maxMoveSize = getMaxMoveSize()
        for (i in 1..maxMoveSize) {
            // Only if square is empty
            val updatedCoordinate = Coordinate(
                coordinate.x + direction.x * i,
                coordinate.y + direction.y * i
            )
            if (board.getPiece(updatedCoordinate) == null) {
                moves.add(updatedCoordinate)
            }
        }

        // Attacking squares
        val attackingCoordinates = getAttackingCoordinates(
            Coordinate(
                coordinate.x + direction.x,
                coordinate.y + direction.y
            )
        )
        for (attackingCoordinate in attackingCoordinates) {
            // Requires an enemy piece
            val piece = board.getPiece(attackingCoordinate) ?: continue
            if (piece.playerType != playerType) {
                moves.add(attackingCoordinate)
            }
        }

        // En Passant squares

        return moves
    }

    private fun getAttackingCoordinates(origin: Coordinate): List<Coordinate> {
        if (direction.x == 1 || direction.x == -1) {
            return listOf(Coordinate(origin.x, origin.y - 1), Coordinate(origin.x, origin.y + 1))
        }
        if (direction.y == 1 || direction.y == -1) {
            return listOf(Coordinate(origin.x - 1, origin.y), Coordinate(origin.x + 1, origin.y))
        }
        return emptyList()
    }

    private fun getMaxMoveSize(): Int {
        return if (moveCount == 0) FIRST_MOVE_DISTANCE else DEFAULT_MOVE_DISTANCE
    }

    override fun getTypeId(): Int {
        return 6
    }

    private companion object {
        val FIRST_MOVE_DISTANCE = 2
        val DEFAULT_MOVE_DISTANCE = 1
    }
}
