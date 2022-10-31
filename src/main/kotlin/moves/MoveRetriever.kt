package my.qualified.packagename.moves

import my.qualified.packagename.logic.Board
import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.Direction

fun getMovesInDirection(direction: Direction, coordinate: Coordinate, board: Board): List<Coordinate> {
    var coordinates = mutableListOf<Coordinate>()
    var activeCoordinate = coordinate

    while (true) {
        activeCoordinate = Coordinate(
            activeCoordinate.x + direction.x,
            activeCoordinate.y + direction.y
        )
        // check move validity
        if (!board.isValidCoordinate(activeCoordinate)) {
            break
        }

        // process move
        if (board.containsPiece(activeCoordinate)) {
            // only if the piece is not of the same type, the move is added
            if (!board.isEqualPlayerType(coordinate, activeCoordinate)) {
                coordinates.add(activeCoordinate)
            }
            break
        } else {
            coordinates.add(activeCoordinate)
        }
    }
    return coordinates
}
