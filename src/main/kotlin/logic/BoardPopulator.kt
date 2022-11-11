package my.qualified.packagename.logic

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
        matrix[0][0] = Rook(PlayerType.BLACK)
        matrix[1][0] = Knight(PlayerType.BLACK)
        matrix[2][0] = Bishop(PlayerType.BLACK)
        matrix[3][0] = Queen(PlayerType.BLACK)
        matrix[4][0] = King(PlayerType.BLACK)
        matrix[5][0] = Bishop(PlayerType.BLACK)
        matrix[6][0] = Knight(PlayerType.BLACK)
        matrix[7][0] = Rook(PlayerType.BLACK)
        for (i in 0 until SIZE_X) {
            matrix[i][1] = Pawn(PlayerType.BLACK, Direction(0, 1))
        }

        matrix[0][7] = Rook(PlayerType.WHITE)
        matrix[1][7] = Knight(PlayerType.WHITE)
        matrix[2][7] = Bishop(PlayerType.WHITE)
        matrix[3][7] = Queen(PlayerType.WHITE)
        matrix[4][7] = King(PlayerType.WHITE)
        matrix[5][7] = Bishop(PlayerType.WHITE)
        matrix[6][7] = Knight(PlayerType.WHITE)
        matrix[7][7] = Rook(PlayerType.WHITE)
        for (i in 0 until SIZE_X) {
            matrix[i][6] = Pawn(PlayerType.WHITE, Direction(0, -1))
        }

        return matrix
    }

    private companion object {
        val SIZE_X = 8
        val SIZE_Y = 8
    }
}
