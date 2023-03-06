package my.qualified.packagename.logic

import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.Direction
import my.qualified.packagename.model.PlayerType
import my.qualified.packagename.pieces.Bishop
import my.qualified.packagename.pieces.King
import my.qualified.packagename.pieces.Knight
import my.qualified.packagename.pieces.Pawn
import my.qualified.packagename.pieces.Piece
import my.qualified.packagename.pieces.Queen
import my.qualified.packagename.pieces.Rook

class BoardPopulator() {
    private var matrix: Array<Array<Piece?>> = Array(SIZE_X) {
        Array(SIZE_Y) {
            null
        }
    }

    fun getSizeX(): Int = SIZE_X
    fun getSizeY(): Int = SIZE_Y

    fun generateMatrix(): Array<Array<Piece?>> {
        val blackRookLong = Rook(PlayerType.BLACK)
        val blackRookShort = Rook(PlayerType.BLACK)
        setPieceAtCoordinate(blackRookLong, Coordinate(0, 0))
        setPieceAtCoordinate(Knight(PlayerType.BLACK), Coordinate(1, 0))
        setPieceAtCoordinate(Bishop(PlayerType.BLACK), Coordinate(2, 0))
        setPieceAtCoordinate(Queen(PlayerType.BLACK), Coordinate(3, 0))
        setPieceAtCoordinate(King(PlayerType.BLACK, listOf(blackRookLong, blackRookShort)), Coordinate(4, 0))
        setPieceAtCoordinate(Bishop(PlayerType.BLACK), Coordinate(5, 0))
        setPieceAtCoordinate(Knight(PlayerType.BLACK), Coordinate(6, 0))
        setPieceAtCoordinate(blackRookShort, Coordinate(7, 0))
        for (i in 0 until SIZE_X) {
            setPieceAtCoordinate(Pawn(PlayerType.BLACK, Direction(0, 1)), Coordinate(i, 1))
        }

        val whiteRookLong = Rook(PlayerType.WHITE)
        val whiteRookShort = Rook(PlayerType.WHITE)
        setPieceAtCoordinate(whiteRookLong, Coordinate(0, 7))
        setPieceAtCoordinate(Knight(PlayerType.WHITE), Coordinate(1, 7))
        setPieceAtCoordinate(Bishop(PlayerType.WHITE), Coordinate(2, 7))
        setPieceAtCoordinate(Queen(PlayerType.WHITE), Coordinate(3, 7))
        setPieceAtCoordinate(King(PlayerType.WHITE, listOf(whiteRookLong, whiteRookShort)), Coordinate(4, 7))
        setPieceAtCoordinate(Bishop(PlayerType.WHITE), Coordinate(5, 7))
        setPieceAtCoordinate(Knight(PlayerType.WHITE), Coordinate(6, 7))
        setPieceAtCoordinate(whiteRookShort, Coordinate(7, 7))
        for (i in 0 until SIZE_X) {
            setPieceAtCoordinate(Pawn(PlayerType.WHITE, Direction(0, -1)), Coordinate(i, 6))
        }

        return matrix
    }

    private fun setPieceAtCoordinate(piece: Piece, coordinate: Coordinate) {
        matrix[coordinate.x][coordinate.y] = piece
        piece.setCurrentCoordinate(coordinate)
    }

    private companion object {
        val SIZE_X = 8
        val SIZE_Y = 8
    }
}
