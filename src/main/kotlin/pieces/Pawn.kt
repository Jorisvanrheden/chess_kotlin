package my.qualified.packagename.pieces

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.Direction
import my.qualified.packagename.model.Move
import my.qualified.packagename.model.MoveSet
import my.qualified.packagename.model.PlayerType
import kotlin.math.abs

class Pawn(playerType: PlayerType, private val direction: Direction) : Piece(playerType) {
    override fun getMoves(coordinate: Coordinate, board: Board): List<MoveSet> {
        var moves = mutableListOf<Coordinate>()
        moves.addAll(getDefaultMoves(coordinate, board))
        moves.addAll(getAttackingMoves(coordinate, board))
        moves.addAll(getEnPassantMoves(coordinate, board))
        return moves.map { MoveSet(listOf(Move(coordinate, it))) }
    }

    private fun getDefaultMoves(coordinate: Coordinate, board: Board): List<Coordinate> {
        var moves = mutableListOf<Coordinate>()
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
        return moves
    }

    private fun getAttackingMoves(coordinate: Coordinate, board: Board): List<Coordinate> {
        var moves = mutableListOf<Coordinate>()
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
        return moves
    }

    private fun getEnPassantMoves(coordinate: Coordinate, board: Board): List<Coordinate> {
        var moves = mutableListOf<Coordinate>()
        val lastMoveSet = board.getLastMove()
        if (lastMoveSet != null) {
            // target should be an enemy pawn
            val piece = board.getPiece(lastMoveSet.target) ?: return moves
            if (piece.playerType == playerType) return moves

            // piece should be a pawn
            if (piece.getTypeId() != getTypeId()) return moves

            // piece should have moved the large amount
            val pieceMoveDistance = (lastMoveSet.target.x - lastMoveSet.origin.x) +
                (lastMoveSet.target.y - lastMoveSet.origin.y)
            if (pieceMoveDistance != FIRST_MOVE_DISTANCE) return moves

            if (direction.x == 1 || direction.x == -1) {
                if (abs(lastMoveSet.target.y - coordinate.y) == 1) {
                    moves.add(
                        Coordinate(
                            coordinate.x + direction.x,
                            lastMoveSet.target.y
                        )
                    )
                }
            }
            if (direction.y == 1 || direction.y == -1) {
                // if the piece is right next to the current piece
                if (abs(lastMoveSet.target.x - coordinate.x) == 1) {
                    moves.add(
                        Coordinate(
                            lastMoveSet.target.x,
                            coordinate.y + direction.y
                        )
                    )
                }
            }
        }
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
        return if (getMoveCount() == 0) FIRST_MOVE_DISTANCE else DEFAULT_MOVE_DISTANCE
    }

    override fun getTypeId(): Int {
        return 6
    }

    private companion object {
        val FIRST_MOVE_DISTANCE = 2
        val DEFAULT_MOVE_DISTANCE = 1
    }
}
