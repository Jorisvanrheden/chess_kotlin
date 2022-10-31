package my.qualified.packagename.pieces

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.Direction
import my.qualified.packagename.model.PlayerType
import my.qualified.packagename.moves.getMovesInDirection

class Bishop(playerType: PlayerType) : Piece(playerType) {
    override fun getMoves(coordinate: Coordinate, board: Board): List<Coordinate> {
        var coordinates = mutableListOf<Coordinate>()
        val directions = listOf(
            Direction(1, 1),
            Direction(-1, -1),
            Direction(-1, 1),
            Direction(1, -1)
        )
        for (direction in directions) {
            coordinates.addAll(
                getMovesInDirection(direction, coordinate, board)
            )
        }
        return coordinates
    }

    override fun getTypeId(): Int {
        return 3
    }
}
