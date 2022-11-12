package my.qualified.packagename.logic

import my.qualified.packagename.model.Coordinate
import my.qualified.packagename.model.PlayerType

class MoveHandler(private var board: Board) {
    private val players = listOf(PlayerType.WHITE, PlayerType.BLACK)

    private var selectedPlayerIndex: Int = 0

    fun processMove(origin: Coordinate, target: Coordinate) {
        // Get the piece at the origin coordinate
        val piece = board.getPiece(origin)

        if (piece == null) {
            println("No piece found at coordinate $origin")
            return
        }

        // Find if the piece type is of the player whose turn it is
        if (piece.playerType != players[selectedPlayerIndex]) {
            println("The selected piece is not owned by the current player")
            return
        }

        // Check if the selected move is indeed part of the available moves
        // TODO: retrieve the moveset given the selected starting coordinate
        // The selected moveset itself can contain more than 1 move, which is not displayed in the target coordinate
        // This way the original coordinate can also be validated, verifying the target coordinate of the piece
        if (board.getMoves(origin).none { it.x == target.x && it.y == target.y }) {
            println("The selected move is not possible for the selected piece")
            return
        }

        // Process move
        board.processMove(origin, target)

        // Iterate selected player counter
        processMoveEnd()
    }

    private fun processMoveEnd() {
        selectedPlayerIndex++
        selectedPlayerIndex %= players.size
    }
}
