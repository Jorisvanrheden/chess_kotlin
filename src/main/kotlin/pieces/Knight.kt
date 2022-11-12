package my.qualified.packagename.pieces

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.Move
import my.qualified.packagename.model.MoveSet
import my.qualified.packagename.model.PlayerType

class Knight(playerType: PlayerType) : Piece(playerType) {
    override fun getMoves(coordinate: Coordinate, board: Board): List<MoveSet> {
        var coordinates = mutableListOf<Coordinate>()
        val targetCoordinates = listOf(
            Coordinate(coordinate.x - 1, coordinate.y + 2),
            Coordinate(coordinate.x + 1, coordinate.y + 2),
            Coordinate(coordinate.x - 1, coordinate.y - 2),
            Coordinate(coordinate.x + 1, coordinate.y - 2),
            Coordinate(coordinate.x - 2, coordinate.y + 1),
            Coordinate(coordinate.x - 2, coordinate.y - 1),
            Coordinate(coordinate.x + 2, coordinate.y + 1),
            Coordinate(coordinate.x + 2, coordinate.y - 1)
        )
        for (targetCoordinate in targetCoordinates) {
            if (board.isValidMove(coordinate, targetCoordinate)) {
                coordinates.add((targetCoordinate))
            }
        }
        return coordinates.map { MoveSet(listOf(Move(coordinate, it))) }
    }

    override fun getTypeId(): Int {
        return 4
    }
}
