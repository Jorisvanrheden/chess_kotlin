package my.qualified.packagename.pieces

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.PlayerType

abstract class Piece(val playerType: PlayerType) {
    abstract fun getMoves(coordinate: Coordinate, board: Board): List<Coordinate>

    abstract fun getTypeId(): Int
}
