package my.qualified.packagename.pieces

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.Direction
import my.qualified.packagename.model.Move
import my.qualified.packagename.model.MoveSet
import my.qualified.packagename.model.PlayerType
import my.qualified.packagename.moves.getMovesInDirection

class Queen(playerType: PlayerType) : Piece(playerType) {
    override fun getMoves(coordinate: Coordinate, board: Board): List<MoveSet> {
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
            coordinates.addAll(
                getMovesInDirection(direction, coordinate, board)
            )
        }
        return coordinates.map { MoveSet(listOf(Move(coordinate, it))) }
    }

    override fun getTypeId(): Int {
        return 2
    }
}
