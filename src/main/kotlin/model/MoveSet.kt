package my.qualified.packagename.model

data class Move(val origin: Coordinate, val target: Coordinate)
data class MoveSet(val moves: List<Move>)
