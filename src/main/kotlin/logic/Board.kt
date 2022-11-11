package my.qualified.packagename.logic

import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.PlayerType
import my.qualified.packagename.pieces.Piece

class Board(private var sizeX: Int, private var sizeY: Int) {
    private val boardPopulator = BoardPopulator()
    private var matrix = boardPopulator.generateMatrix()

    init {
        sizeX = boardPopulator.getSizeX()
        sizeY = boardPopulator.getSizeY()
    }

    fun getBoardRepresentation(): Array<Array<Int>> {
        var map: Array<Array<Int>> = Array(sizeX) {
            Array(sizeY) { 0 }
        }
        for (i in 0 until sizeX) {
            for (j in 0 until sizeY) {
                val piece = matrix[i][j]
                if (piece != null) {
                    var typeId = piece.getTypeId()
                    if (piece.playerType == PlayerType.BLACK) typeId += 6
                    map[i][j] = typeId
                } else {
                    map[i][j] = 0
                }
            }
        }
        return map
    }

    fun getMoves(origin: Coordinate): List<Coordinate> {
        val piece = getPiece(origin) ?: return emptyList()
        val moves = piece.getMoves(origin, this)

        // Validate all moves based on game logic

        return moves
    }

    fun processMove(origin: Coordinate, target: Coordinate) {
        val piece = getPiece(origin)
        matrix[target.x][target.y] = piece
        matrix[origin.x][origin.y] = null

        // Store the move for each piece
        piece?.storeMove(origin, target)
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
