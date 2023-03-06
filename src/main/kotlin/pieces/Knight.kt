package my.qualified.packagename.pieces

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.Move
import my.qualified.packagename.model.MoveSet
import my.qualified.packagename.model.PieceType
import my.qualified.packagename.model.PlayerType

class Knight(playerType: PlayerType) : Piece(playerType) {
    override fun getMoves(coordinate: Coordinate, board: Board): List<MoveSet> =
        listOf(
            Coordinate(coordinate.x - 1, coordinate.y + 2),
            Coordinate(coordinate.x + 1, coordinate.y + 2),
            Coordinate(coordinate.x - 1, coordinate.y - 2),
            Coordinate(coordinate.x + 1, coordinate.y - 2),
            Coordinate(coordinate.x - 2, coordinate.y + 1),
            Coordinate(coordinate.x - 2, coordinate.y - 1),
            Coordinate(coordinate.x + 2, coordinate.y + 1),
            Coordinate(coordinate.x + 2, coordinate.y - 1),
        ).filter {
            board.isCoordinateWithinBounds(it)
        }.map {
            it.toMove(board, it).toMoveSet()
        }

    override fun getTypeId(): PieceType {
        return PieceType.KNIGHT
    }
}
