package my.qualified.packagename.pieces

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.Direction
import my.qualified.packagename.model.PlayerType

class King(playerType: PlayerType, private val connectedPieces: List<Piece>) : Piece(playerType) {
    override fun getMoves(coordinate: Coordinate, board: Board): List<Coordinate> {
        var moves = mutableListOf<Coordinate>()
        moves.addAll(getDefaultMoves(coordinate, board))
        moves.addAll(getCastlingMoves(coordinate, board))
        return moves
    }

    private fun getDefaultMoves(coordinate: Coordinate, board: Board): List<Coordinate> {
        var moves = mutableListOf<Coordinate>()
        val directions = listOf(
            Direction(1, 0),
            Direction(-1, 0),
            Direction(0, 1),
            Direction(0, -1),
            Direction(1, 1),
            Direction(-1, -1),
            Direction(-1, 1),
            Direction(1, -1)
        )
        for (direction in directions) {
            val targetCoordinate = Coordinate(
                coordinate.x + direction.x,
                coordinate.y + direction.y
            )
            if (board.isValidMove(coordinate, targetCoordinate)) {
                moves.add((targetCoordinate))
            }
        }
        return moves
    }

    private fun getCastlingMoves(coordinate: Coordinate, board: Board): List<Coordinate> {
        var moves = mutableListOf<Coordinate>()
        // check if the king has moved
        if (getMoveCount() > 0) return emptyList()

        // for each of the connected piece, only process the move if that piece hasnt moved yet
        for (connectedPiece in connectedPieces) {
            if (connectedPiece.getMoveCount() > 0) continue
            if (containsPieceBetweenCoordinates(coordinate, connectedPiece.getCurrentCoordinate(), board)) continue

            // get direction between pieces
            val direction = getDirectionBetweenCoordinates(coordinate, connectedPiece.getCurrentCoordinate())

            // move this piece x units in the direction
            moves.add(Coordinate(coordinate.x + direction.x * CASTLING_MOVE_DISTANCE, coordinate.y))

            // move the interacting piece one unit next to this piece on the other side
            // TODO: requires extension of the default moveset data structure
        }

        return moves
    }

    private fun getDirectionBetweenCoordinates(origin: Coordinate, target: Coordinate): Direction {
        if (target.x - origin.x > 0) return Direction(1, 0)
        if (target.x - origin.x < 0) return Direction(-1, 0)
        if (target.y - origin.y > 0) return Direction(0, 1)
        if (target.y - origin.y < 0) return Direction(0, -1)
        return Direction(0, 0)
    }

    private fun containsPieceBetweenCoordinates(origin: Coordinate, target: Coordinate, board: Board): Boolean {
        // don't process the start and end position, but only coordinates in between
        for (i in origin.x + 1 until target.x) {
            if (board.getPiece(Coordinate(i, origin.y)) != null) return true
        }
        return false
    }

    override fun getTypeId(): Int {
        return 1
    }

    private companion object {
        val CASTLING_MOVE_DISTANCE = 2
    }
}
