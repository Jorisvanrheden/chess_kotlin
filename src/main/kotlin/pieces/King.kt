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
        // check if the king has moved
        if (getMoveCount() > 0) return emptyList()

        // for each of the connected piece, only process the move if that piece hasnt moved yet
        for (connectedPiece in connectedPieces) {
            if (connectedPiece.getMoveCount() > 0) continue

            // check if there are any pieces in between the king and and the connected piece
            // if there are pieces in between, don't process the move
//            val xRange = connectedPiece.
        }

        return emptyList()
    }

    override fun getTypeId(): Int {
        return 1
    }
}
