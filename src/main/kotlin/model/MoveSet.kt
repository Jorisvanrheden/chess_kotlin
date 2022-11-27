package my.qualified.packagename.model

import my.qualified.packagename.pieces.Piece

data class Move(
    val activePiece: Piece?,
    val targets: List<Piece>,
    val from: Coordinate,
    val to: Coordinate
)
data class MoveSet(val moves: List<Move>)
