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

        var moveSets = moves.map {
            MoveSet(
                listOf(
                    Move(
                        board.getPiece(coordinate),
                        getTargets(it, board),
                        coordinate,
                        it
                    )
                )
            )
        }.toMutableList()

        moveSets.addAll(
            getEnPassantMoves(coordinate, board)
        )
        return moveSets
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
            if (!board.isValidCoordinate(attackingCoordinate)) continue

            // Requires an enemy piece
            val piece = board.getPiece(attackingCoordinate) ?: continue
            if (piece.playerType != playerType) {
                moves.add(attackingCoordinate)
            }
        }
        return moves
    }

    private fun getEnPassantMoves(coordinate: Coordinate, board: Board): List<MoveSet> {
        var moveSets = mutableListOf<MoveSet>()

        val lastMoveSet = board.getLastMove() ?: return moveSets

        // target should be an enemy pawn
        val piece = board.getPiece(lastMoveSet.to) ?: return moveSets
        if (piece.playerType == playerType) return moveSets

        // piece should be a pawn
        if (piece.getTypeId() != getTypeId()) return moveSets

        // piece should have moved the large amount
        val pieceMoveDistance = (lastMoveSet.to.x - lastMoveSet.from.x) +
            (lastMoveSet.to.y - lastMoveSet.from.y)
        if (pieceMoveDistance != FIRST_MOVE_DISTANCE) return moveSets

        val targets = getEnPassantTargets(coordinate, lastMoveSet)
        for (target in targets) {
            moveSets.add(
                MoveSet(
                    listOf(
                        Move(
                            this,
                            getTargets(lastMoveSet.to, board),
                            coordinate,
                            target
                        )
                    )
                )
            )
        }
        return moveSets
    }

    private fun getEnPassantTargets(coordinate: Coordinate, lastMove: Move): List<Coordinate> {
        var targets = mutableListOf<Coordinate>()
        if (direction.x == 1 || direction.x == -1) {
            if (abs(lastMove.to.y - coordinate.y) == 1) {
                targets.add(
                    Coordinate(
                        coordinate.x + direction.x,
                        lastMove.to.y
                    )
                )
            }
        }

        if (direction.y == 1 || direction.y == -1) {
            // if the piece is right next to the current piece
            if (abs(lastMove.to.x - coordinate.x) == 1) {
                targets.add(
                    Coordinate(
                        lastMove.to.x,
                        coordinate.y + direction.y
                    )
                )
            }
        }

        return targets
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
