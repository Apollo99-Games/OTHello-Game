import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Represents an Othello game.
 */
class OTHelloGame {
    private static Board board = new Board();
    private static VaildMoves checker = new VaildMoves(board);

    /**
     * Gets valid input from the user and returns it as a Point object.
     *
     * @param input A Scanner object used to get user input from the console.
     * @param ValidMoves A HashMap containing valid moves for the current player.
     * @return A Point object representing the user's valid move.
     */
    private static Point getValidInput(Scanner input, HashMap<Point, ArrayList<Point>> ValidMoves) {
        Point playerMove = null;

        // Keep running this until the user enters a valid move
        while (true) {
            try {
                    // coonver player input to a point
                    playerMove = Point.valueOf(input.nextLine());

                    if (board.isPointOnBoard(playerMove) && ValidMoves.containsKey(playerMove)) {
                        break; 
                    }
                    else if (!board.isPointOnBoard(playerMove)) {
                        System.out.println("The coordinate is out of bounds");
                        System.out.println("Try again: ");
                    }
                    else {
                        System.out.println("Move is invalid as no pieces can be filped");
                        System.out.println("Try again: ");
                    }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Try again: ");
            }
        }
        return playerMove;
    }

    /**
     * Runs the game.
     */
    public static void runGame() {
        System.out.println("\n\nWelcome to Othello");
        System.out.println("To enter a coordinate type in (a letter for x axis, then a number for y axis)");
        System.out.println("Like this A5");

        Scanner input = new Scanner(System.in);

        boolean AIGame = false;
        switch (gameModeSelection(input)) {
            case 1:
                AIGame = false;
                break;
            case 2: 
                AIGame = true;
        }

        // The game will only end when not player has any valid moves left
        while(checker.getValidPlayerMoves(Tile.BLACK).size() > 0 || checker.getValidPlayerMoves(Tile.WHITE).size() > 0) {

            // Handle the black player's moves
            HashMap<Point, ArrayList<Point>> blackMoves = checker.getValidPlayerMoves(Tile.BLACK);
   
            if (blackMoves.size() > 0) {
                System.out.println("\n" + board.toString(blackMoves));

                System.out.println("\nBlack's Turn. Type in your coordinate: ");

                Point playerMove = getValidInput(input, blackMoves);

                board.flipAllPieces(blackMoves.get(playerMove));
                board.setPiece(new Piece(Tile.BLACK), playerMove);
                checker.updateBoard(board);
            }


            // Handle the white player's moves
            HashMap<Point, ArrayList<Point>> whiteMoves = checker.getValidPlayerMoves(Tile.WHITE);

            if (whiteMoves.size() > 0 && AIGame == false) {
                System.out.println("\n" + board.toString(whiteMoves));

                System.out.println("White's Turn. Type in your coordinate: ");

                Point playerMove = getValidInput(input, whiteMoves);

                board.flipAllPieces(whiteMoves.get(playerMove));
                board.setPiece(new Piece(Tile.WHITE), playerMove);
                checker.updateBoard(board);
            }
            // Handle the AI's moves
            else if (checker.getValidPlayerMoves(Tile.WHITE).size() > 0) {
                Point AIMove = AI.makeMove(board, whiteMoves);
                
                System.out.println("\nAI placed a white piece at " + AIMove.toString());

                board.flipAllPieces(whiteMoves.get(AIMove));
                board.setPiece(new Piece(Tile.WHITE), AIMove);
                checker.updateBoard(board);
            }
        }

        // Handle the end game
        System.out.println("\n" + board.toString());
        System.out.println("There are no more moves any player can play. Counting pieces...");
        if (board.getNumberOfBlackPieces() > board.getNumberOfWhitePieces()) {
            System.out.println("Black has more pieces. Black won!");
        }
        else if (board.getNumberOfBlackPieces() < board.getNumberOfWhitePieces()) {
            System.out.println("White has more pieces. White won!");
        }
        else {
            System.out.println("Both players have the same number of pieces. Tie game!");
        }
    }

    /**
     * Selects the game mode.
     *
     * @param input The scanner object to read user input from.
     * @return The selected game mode.
     */
    public static int gameModeSelection(Scanner input) {
        System.out.println("Select Game Mode:");
        System.out.println("  1. 2 Player Mode");
        System.out.println("  2. Vs AI Mode");
        while (true) {
            String userResponse = input.nextLine();
            if (userResponse.matches("(\\s+)?1(\\s+)?")) {
                return 1;
            }
            else if (userResponse.matches("(\\s+)?2(\\s+)?")) {
                return 2;
            }
        }
    }

    public static void main(String[] args) {
        runGame();
    }
}