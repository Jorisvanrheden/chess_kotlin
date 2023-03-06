package my.qualified.packagename.logic

import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.Move
import my.qualified.packagename.model.MoveSet
import my.qualified.packagename.model.PieceType
import my.qualified.packagename.model.PlayerType
import my.qualified.packagename.pieces.Piece

class Board() {
    private val boardPopulator = BoardPopulator()
    private var matrix: Array<Array<Piece?>> = boardPopulator.generateMatrix()

    private var history = mutableListOf<MoveSet>()

    private var sizeX: Int = boardPopulator.getSizeX()
    private var sizeY: Int = boardPopulator.getSizeY()

    override fun toString(): String {
        var board = ""
        for (i in 0 until sizeY) {
            for (j in 0 until sizeX) {
                board += matrix[j][i]?.toString() ?: "0"
                if (j < sizeX - 1) {
                    board += ",\t"
                }
            }
            board += "\n"
        }
        return board
    }

    fun getLastMove(): Move? {
        if (history.size > 0) {
            return history[history.size - 1].moves[0]
        }
        return null
    }

    fun getMoveSets(coordinate: Coordinate): List<MoveSet> {
        val piece = getPiece(coordinate) ?: return emptyList()
        return piece.getMoves(coordinate, this).filter {
            isMoveAllowed(it, piece.playerType)
        }
    }

    private fun getBasicMoveSets(coordinate: Coordinate): List<MoveSet> {
        val piece = getPiece(coordinate) ?: return emptyList()
        return piece.getMoves(coordinate, this).filter {
            isEqualPlayerType(it.moves[0].from, it.moves[0].to)
        }
    }

    fun undoLatestMoveSet(): Boolean {
        if (history.isEmpty()) return false

        val moveSet = history[history.size - 1]

        for (move in moveSet.moves) {
            undoMove(move)
        }

        history.removeLast()

        return true
    }

    fun applyMoveSet(moveSet: MoveSet) {
        for (move in moveSet.moves) {
            applyMove(move)
        }
        history.add(moveSet)
    }

    private fun undoMove(move: Move) {
        // To undo, the active piece must be set back to the from position
        setPieceAt(move.from, move.activePiece)

        // Set the target to null - it is important this happens before the targets are reset
        // In case no target, the square is still set properly, but otherwise targets are overwritten again
        setPieceAt(move.to, null)
    }

    private fun applyMove(move: Move) {
        // set the origin to null
        setPieceAt(move.from, null)

        // set the active piece end location
        setPieceAt(move.to, move.activePiece)
    }

    fun isCoordinateWithinBounds(coordinate: Coordinate): Boolean {
        if (coordinate.x < 0 || coordinate.x >= sizeX) return false
        if (coordinate.y < 0 || coordinate.y >= sizeY) return false
        return true
    }

    fun setPieceAt(coordinate: Coordinate, piece: Piece?) {
        matrix[coordinate.x][coordinate.y] = piece
    }

    fun getPiece(coordinate: Coordinate): Piece? =
        matrix[coordinate.x][coordinate.y]

    fun containsPiece(coordinate: Coordinate): Boolean =
        matrix[coordinate.x][coordinate.y] != null

    fun isEqualPlayerType(originCoordinate: Coordinate, targetCoordinate: Coordinate): Boolean =
        getPiece(originCoordinate)?.playerType == getPiece(targetCoordinate)?.playerType

    private fun isKingInCheckAfterMove(moveSet: MoveSet, playerType: PlayerType): Boolean {
        // get king
        this.let {
            it.applyMoveSet(moveSet)
            getEnemyAttackingCoordinates(playerType).contains(
                getKingCoordinate(playerType),
            )
        }
        return false
    }

    private fun isMoveAllowed(moveSet: MoveSet, playerType: PlayerType): Boolean {
        if (isEqualPlayerType(moveSet.moves[0].from, moveSet.moves[0].to)) return false
        if (isKingInCheckAfterMove(moveSet, playerType)) return false
        return true
    }

    private fun getEnemyAttackingCoordinates(playerType: PlayerType): List<Coordinate> =
        getEnemyCoordinates(playerType).map {
            getBasicMoveSets(it).map { moveSet -> moveSet.moves[0].to }
        }.flatten()

    private fun getEnemyCoordinates(playerType: PlayerType): List<Coordinate> =
        getBoardCoordinates().filter {
            getPiece(it)?.playerType != playerType
        }

    private fun getKingCoordinate(playerType: PlayerType): Coordinate =
        getBoardCoordinates().first {
            getPiece(it)?.playerType == playerType && getPiece(it)?.getTypeId() == PieceType.KING
        }

    private fun getBoardCoordinates(): List<Coordinate> {
        val coordinates = mutableListOf<Coordinate>()
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix[0].size) {
                coordinates.add(Coordinate(i, j))
            }
        }
        return coordinates
    }
}
