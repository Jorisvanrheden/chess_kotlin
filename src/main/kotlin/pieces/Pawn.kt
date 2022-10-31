package my.qualified.packagename.pieces

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.PlayerType

class Pawn(playerType: PlayerType) : Piece(playerType) {
    override fun getMoves(coordinate: Coordinate, board: Board): List<Coordinate> {
        return listOf()
    }

    override fun getTypeId(): Int {
        return 6
    }
}
