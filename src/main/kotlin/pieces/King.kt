package my.qualified.packagename.pieces

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.Direction
import my.qualified.packagename.model.PlayerType

class King(playerType: PlayerType) : Piece(playerType) {
    override fun getMoves(coordinate: Coordinate, board: Board): List<Coordinate> {
        var coordinates = mutableListOf<Coordinate>()
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
                coordinates.add((targetCoordinate))
            }
        }
        return coordinates
    }

    override fun getTypeId(): Int {
        return 1
    }
}
