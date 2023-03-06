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

    fun getMoveSets(origin: Coordinate): List<MoveSet> {
        val piece = getPiece(origin) ?: return emptyList()
        val moves = piece.getMoves(origin, this)

        // Validate all moves based on game logic
        return moves.filter { !leavesKingInCheck(it, piece.playerType) }
    }

    fun getMoves(coordinate: Coordinate): List<Coordinate> =
        getMoveSets(coordinate).map {
            Coordinate(it.moves[0].to.x, it.moves[0].to.y)
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

        // Set the targets back to the pieces that were located there
        for (target in move.targets) {
            setPieceAt(target.getCurrentCoordinate(), target)
        }

        // Undo the piece history update
        move.activePiece?.removeLastCoordinate()
    }

    private fun applyMove(move: Move) {
        // set the origin to null
        setPieceAt(move.from, null)

        // set all targets to null
        for (target in move.targets) {
            setPieceAt(target.getCurrentCoordinate(), null)
        }

        // set the active piece end location
        setPieceAt(move.to, move.activePiece)

        // Store the move for each piece
        move.activePiece?.storeMove(move)
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
        if (!isCoordinateWithinBounds(targetCoordinate)) {
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

    private fun createCopy() = this
    private fun leavesKingInCheck(moveSet: MoveSet, playerType: PlayerType): Boolean {
        createCopy().let {
            it.applyMoveSet(moveSet)
            return getEnemyTargets(playerType).contains(getKing(playerType)?.getCurrentCoordinate())
        }
    }

    private fun getEnemyTargets(type: PlayerType): List<Coordinate> {
        var moves = mutableListOf<Coordinate>()

        val enemyPieces = getEnemyPieces(type)
        for (piece in enemyPieces) {
            val moveSets = piece.getMoves(piece.getCurrentCoordinate(), this)
            for (moveSet in moveSets) {
                moves.addAll(moveSet.moves.map { it.to })
            }
        }
        return moves
    }

    private fun getEnemyPieces(type: PlayerType): List<Piece> {
        var pieces = mutableListOf<Piece>()
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix[0].size) {
                val piece = getPiece(Coordinate(i, j)) ?: continue

                if (piece.playerType == type)continue
                pieces.add(piece)
            }
        }
        return pieces
    }

    private fun getKing(type: PlayerType): Piece? {
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix[0].size) {
                val piece = getPiece(Coordinate(i, j)) ?: continue

                if (piece.playerType == type && piece.getTypeId() == PieceType.KING) {
                    return piece
                }
            }
        }
        return null
    }
}
