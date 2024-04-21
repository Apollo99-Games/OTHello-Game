import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a game board.
 */
class Board {
    private Piece gameBoard[][];

    private HashMap<Point, Piece> blackPieces = new HashMap<Point, Piece>();
    private HashMap<Point, Piece> whitePieces = new HashMap<Point, Piece>();

    /**
     * Constructor for Board class.
     */
    public Board() {
        gameBoard = new Piece[8][8];
        SetUpBoard();
    }

    /**
     * Sets up the board with pieces in their initial positions.
     */
    private void SetUpBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                gameBoard[i][j] = new Piece();
            }
        }

        this.setPiece(new Piece(Tile.WHITE), new Point(3, 3));
        this.setPiece(new Piece(Tile.BLACK), new Point(4, 3));
        this.setPiece(new Piece(Tile.WHITE), new Point(4, 4));
        this.setPiece(new Piece(Tile.BLACK), new Point(3, 4));
    }

    /**
     * Returns a string representation of the board with valid moves.
     *
     * @param validMoves A HashMap containing valid moves for each piece on the board.
     * @return A string representation of the board with valid moves.
     */
    public String toString(HashMap<Point, ArrayList<Point>> validMoves) {
        String str = "-+---+---+---+---+---+---+---+---+\n";

        for (int y = 0; y < gameBoard.length; y++) {

            // Creates the number list on the left Y-axis
            str += Integer.toString(y) + "|";


            for (int x = 0; x < gameBoard.length; x++) {

                // If this is a valid move, a number will be printed on the tile
                // It will represent the number of points the player will get playing that move
                if (validMoves.containsKey(new Point(x, y))) {
                    str += " " + validMoves.get(new Point(x, y)).size() + " ";
                }
                else {
                    str += gameBoard[y][x].toString();
                }

                // Adds the vertical divider to create the grid
                str += "|";
            }
            
            // Adds the horizontal divider to create the grid
            str += "\n-+---+---+---+---+---+---+---+---|\n";
        }

        str += " | A | B | C | D | E | F | G | H |";
        str += "\n    Black(o): " + getNumberOfBlackPieces() + "    White(@): " + getNumberOfWhitePieces();
        
        return str;
    }

    /**
     * Returns a string representation of the board without valid moves.
     *
     * @return A string representation of the board without valid moves.
     */
    public String toString() {
        return this.toString(new HashMap<Point, ArrayList<Point>>());
    }

    /**
     * Returns the piece at a given point on the board.
     *
     * @param point The point on the board to get the piece from.
     * @return The piece at the given point on the board.
     */
    public Piece getPiece(Point point) {
        return gameBoard[point.getY()][point.getX()];
    }

    /**
     * Returns whether a given point is on the board or not.
     *
     * @param point The point to check if it's on the board or not.
     * @return True if the point is on the board, false otherwise.
     */
    public boolean isPointOnBoard(Point point) {
        if (point.getX() < 8 && point.getY() < 8) {
            if (point.getX() >= 0 && point.getY() >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Flips all pieces in a list of coordinates to their opposite color.
     *
     * @param points The list of coordinates to flip pieces at.
     */
    public void flipAllPieces(ArrayList<Point> points) {
        for (Point point : points) {
            this.setPiece(this.getPiece(point).getOpposite(), point);
        }
    }

    /**
     * Sets a piece at a given point on the board.
     *
     * @param piece The piece to set at the given point.
     * @param point  The point on the board to set the piece at.
     */
    public void setPiece(Piece piece, Point point) {
        updatePieces(piece, point);
        gameBoard[point.getY()][point.getX()] = piece;
    }

    /**
     * Updates the blackPieces and whitePieces HashMaps with a new piece at a given point.
     *
     * @param piece The new piece to update the HashMaps with.
     * @param point  The point on the board where the new piece is located.
     */
    private void updatePieces(Piece piece, Point point) {
        if (piece.isBlack()) {
            if (whitePieces.containsKey(point)) {
                whitePieces.remove(point);
            }
            if (!blackPieces.containsKey(point)) {
                blackPieces.put(point, piece);
            }
        }

        if (piece.isWhite()) {
            if (!whitePieces.containsKey(point)) {
                whitePieces.put(point, piece);
            }
            if (blackPieces.containsKey(point)) {
                blackPieces.remove(point);
            }
        }

        if (piece.isEmpty()) {
            if (whitePieces.containsKey(point)) {
                whitePieces.remove(point);
            }
            if(blackPieces.containsKey(point)) {
                blackPieces.remove(point);
            }
        }
    }

    /**
     * Returns the number of black pieces on the board.
     *
     * @return The number of black pieces on the board.
     */
    public int getNumberOfBlackPieces() {
        return blackPieces.size();
    }

    /**
     * Returns the number of white pieces on the board.
     *
     * @return The number of white pieces on the board.
     */
    public int getNumberOfWhitePieces() {
        return whitePieces.size();
    }

    /**
     * Returns a HashMap containing all black pieces on the board and their locations.
     *
     * @return A HashMap containing all black pieces on the board and their locations.
     */
    public HashMap<Point, Piece> getBlackPieces() {
        return blackPieces;
    }
    
    /**
     * Returns a HashMap containing all white pieces on the board and their locations.
     *
     * @return A HashMap containing all white pieces on the board and their locations.
     */
    public HashMap<Point, Piece> getWhitePieces() {
        return whitePieces;
    }
}