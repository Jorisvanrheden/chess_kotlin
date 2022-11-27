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
        // The selected moveset itself can contain more than 1 move, which is not displayed in the target coordinate
        // This way the original coordinate can also be validated, verifying the target coordinate of the piece
        val moveSets = board.getMoveSets(origin)
        if (moveSets.isEmpty()) {
            println("The selected move is not possible for the selected piece")
            return
        }

        // Loop through all move sets, finding the moveset of which the origin and target
        // are both contained in the first move of the set
        val filteredMoveSets = moveSets.filter {
            it.moves[0].from.x == origin.x &&
                it.moves[0].from.y == origin.y &&
                it.moves[0].to.x == target.x &&
                it.moves[0].to.y == target.y
        }
        if (filteredMoveSets.isEmpty()) {
            println("The selected move is not possible for the selected piece")
            return
        }

        // Process move
        board.applyMoveSet(filteredMoveSets[0])

        // Iterate selected player counter
        processMoveEnd()
    }

    private fun processMoveEnd() {
        selectedPlayerIndex++
        selectedPlayerIndex %= players.size
    }
}
