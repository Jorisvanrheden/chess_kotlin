package my.qualified.packagename.logic

import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.PlayerType
import my.qualified.packagename.pieces.Bishop
import my.qualified.packagename.pieces.King
import my.qualified.packagename.pieces.Knight
import my.qualified.packagename.pieces.Piece
import my.qualified.packagename.pieces.Queen
import my.qualified.packagename.pieces.Rook

class Board(private val sizeX: Int, private val sizeY: Int) {
    private var matrix: Array<Array<Piece?>> = Array(sizeX) {
        Array(sizeY) {
            null
        }
    }

    init {
        matrix[0][0] = Rook(PlayerType.WHITE)
        matrix[0][1] = Knight(PlayerType.WHITE)
        matrix[0][2] = Bishop(PlayerType.WHITE)
        matrix[0][3] = Queen(PlayerType.WHITE)
        matrix[0][4] = King(PlayerType.WHITE)

//        matrix[0] = Array(sizeX) {
//            Rook(PlayerType.WHITE)
//            Knight(PlayerType.WHITE)
//            Bishop(PlayerType.WHITE)
//            Queen(PlayerType.WHITE)
//            King(PlayerType.WHITE)
//            Bishop(PlayerType.WHITE)
//            Knight(PlayerType.WHITE)
//            Rook(PlayerType.WHITE)
//        }
//
//        matrix[1] = Array(sizeX) {
//            Pawn(PlayerType.WHITE)
//            Pawn(PlayerType.WHITE)
//            Pawn(PlayerType.WHITE)
//            Pawn(PlayerType.WHITE)
//            Pawn(PlayerType.WHITE)
//            Pawn(PlayerType.WHITE)
//            Pawn(PlayerType.WHITE)
//            Pawn(PlayerType.WHITE)
//        }
//
//        matrix[7] = Array(sizeX) {
//            Rook(PlayerType.BLACK)
//            Knight(PlayerType.BLACK)
//            Bishop(PlayerType.BLACK)
//            Queen(PlayerType.BLACK)
//            King(PlayerType.BLACK)
//            Bishop(PlayerType.BLACK)
//            Knight(PlayerType.BLACK)
//            Rook(PlayerType.BLACK)
//        }
//
//        matrix[6] = Array(sizeX) {
//            Pawn(PlayerType.BLACK)
//            Pawn(PlayerType.BLACK)
//            Pawn(PlayerType.BLACK)
//            Pawn(PlayerType.BLACK)
//            Pawn(PlayerType.BLACK)
//            Pawn(PlayerType.BLACK)
//            Pawn(PlayerType.BLACK)
//            Pawn(PlayerType.BLACK)
//        }
    }

    fun getBoardRepresentation(): Array<Array<Int>> {
        var map: Array<Array<Int>> = Array<Array<Int>>(sizeX) {
            Array(sizeY) { 0 }
        }

        for (i in 0 until sizeX) {
            for (j in 0 until sizeY) {
                val piece = matrix[i][j]
                if (piece != null) {
                    var typeId = piece.getTypeId()
                    console.log(typeId)
                    if (piece.playerType == PlayerType.BLACK) typeId += 6
                    map[i][j] = typeId
                } else {
                    map[i][j] = 0
                }
            }
        }

        return map
    }

    fun isValidCoordinate(coordinate: Coordinate): Boolean {
        if (coordinate.x < 0 || coordinate.x >= sizeX) return false
        if (coordinate.y < 0 || coordinate.y >= sizeY) return false
        return true
    }

    fun getPiece(coordinate: Coordinate): Piece? =
        matrix[coordinate.x][coordinate.y]

    fun containsPiece(coordinate: Coordinate): Boolean =
        matrix[coordinate.x][coordinate.y] != null

    fun isEqualPlayerType(originCoordinate: Coordinate, targetCoordinate: Coordinate): Boolean {
        val originPiece = matrix[originCoordinate.x][originCoordinate.y]
        val targetPiece = matrix[targetCoordinate.x][targetCoordinate.y]

        if (originPiece != null && targetPiece != null) {
            return originPiece.playerType == targetPiece.playerType
        }
        return false
    }

    fun isValidMove(originCoordinate: Coordinate, targetCoordinate: Coordinate): Boolean {
        // check move validity
        if (!isValidCoordinate(targetCoordinate)) {
            return false
        }

        // process move
        return if (containsPiece(targetCoordinate)) {
            // only if the piece is not of the same type, the move is valid
            !isEqualPlayerType(originCoordinate, targetCoordinate)
        } else {
            true
        }
    }
}
