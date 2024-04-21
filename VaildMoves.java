import java.util.ArrayList;
import java.util.HashMap;

/**
 * Finds the valid moves given a board
 */
public class VaildMoves {
    private Board board;

    /**
     * Constructor for VaildMoves class.
     *
     * @param board The board object to get valid moves for.
     */
    public VaildMoves(Board board) {
        this.board = board;
    }

    /**
     * Updates the board object for this instance of VaildMoves.
     *
     * @param board The new board object to update this instance with.
     */
    public void updateBoard(Board board) {
        this.board = board;
    }

    /**
     * Returns a HashMap containing all valid moves for a given player color.
     *
     * @param colour The color of the player to get valid moves for.
     * @return A HashMap containing all valid moves for a given player color.
     */
    public HashMap<Point, ArrayList<Point>> getValidPlayerMoves(Tile colour) {
        HashMap<Point, ArrayList<Point>> AllValidMoves = new HashMap<Point, ArrayList<Point>>();

         // Compile and add valid moves for each piece depending on their colour.
        if (colour == Tile.WHITE) {
            for (Point point : board.getWhitePieces().keySet()) {
                AllValidMoves.putAll(compileValidMoves(point, AllValidMoves));
            }
        }
        else if (colour == Tile.BLACK) {
            for (Point point : board.getBlackPieces().keySet()) {
                AllValidMoves.putAll(compileValidMoves(point, AllValidMoves));
            }
        }
 
        return AllValidMoves;
    }

    /**
     * Compiles all valid moves for a given piece on the board.
     *
     * @param point The point on the board where the piece is located.
     * @param AllValidMoves A HashMap containing all valid moves so far.
     * @return A HashMap containing all valid moves for a given piece on the board.
     */
    private HashMap<Point, ArrayList<Point>> compileValidMoves(Point point, HashMap<Point, ArrayList<Point>> AllValidMoves) {
        // Get all valid moves for the piece located at the given point.
        HashMap<Point, ArrayList<Point>> validMovesForPiece = getValidMovesFrom(point);


        for (Point validPoints : validMovesForPiece.keySet()) {

            // If the Map that contains ALL vaild moves (not just this point) already contains the key for the current point
            // We will merely find the current move's key and add all the points that are affected by it
            // Otherwise we will have to create a new key for the point and then add all the points that are affected by it
            if (AllValidMoves.containsKey(validPoints)) {

                // Ensure that it actaully exists
                if (AllValidMoves.get(validPoints) != null) {
                    ArrayList<Point> holder = AllValidMoves.get(validPoints);
                    holder.addAll(validMovesForPiece.get(validPoints));
                    AllValidMoves.put(validPoints, holder);
                }
            }
            else if (validPoints != null) {
                AllValidMoves.put(validPoints, validMovesForPiece.get(validPoints));
            }
        }
        return AllValidMoves;
    }

    /**
     * Returns all valid moves from a given point on the board.
     *
     * @param currentPoint The point on the board to get valid moves from.
     * @return A HashMap containing all valid moves from a given point on the board.
     */
    private HashMap<Point, ArrayList<Point>> getValidMovesFrom(Point currentPoint) {

         // Initialize a HashMap to store valid moves in all directions.
        HashMap<Point, ArrayList<Point>> allDirections = new HashMap<Point, ArrayList<Point>>();
        
        // Iterate through 360 degrees with 45-degree increments to cover all directions.
        for (int degree = 0; degree <= 360; degree += 45) {

            // Calculate the direction vector based on the degree.
            Point direction = new Point((int)Math.round(Math.cos(Math.toRadians(degree))), (int)Math.round(Math.sin(Math.toRadians(degree))) * -1);

            // Find all pieces in the current direction from the given point.
            ArrayList<Point> piecesInDirection = findPieceInDirectionOf(currentPoint, direction);

            Point validMove = null;
            // The valid move will be the last point in the list as that is where the player's piece will jump to
            if (!piecesInDirection.isEmpty()) {
                validMove = piecesInDirection.remove(piecesInDirection.size() -1);
            }
            allDirections.put(validMove, piecesInDirection);
        }
        return allDirections;
    }

    /**
     * Finds all pieces in a given direction from a starting point on the board.
     *
     * @param currentPoint The starting point on the board to find pieces from.
     * @param vector      The direction to look for pieces in.
     * @return An ArrayList containing all pieces in a given direction from a starting point on the board.
     */
    private ArrayList<Point> findPieceInDirectionOf(Point currentPoint, Point vector) {
        Piece current = board.getPiece(currentPoint);

        // Determine the piece to look for based on the current piece.
        Piece pieceFound = current.getOpposite();
        
           // Initialize variables for tracking movement and storing coordinates.
        Point changeDirection = currentPoint.clone();  // Store coordinates of pieces found.
        ArrayList<Point> pointsToPiece = new ArrayList<Point>();
        ArrayList<Point> empty = new ArrayList<Point>(); // Empty list to return if no piece is found.

        // Loop until an opposite piece is no longer found.
        while (pieceFound.isOppositeTo(current)) {

            // Move to the next coordinate in the specified direction.
            if (board.isPointOnBoard(changeDirection.add(vector))) {
                changeDirection = changeDirection.add(vector);
            }
            else {
                // If the coordinate is off the board, return an empty list.
                return empty;
            }
            
            pointsToPiece.add(changeDirection);
            pieceFound = board.getPiece(changeDirection);

            // If the current piece matches the starting piece, return an empty list.
            if (pieceFound.equals(current)) return empty;
        }
        
        if (pieceFound.isEmpty() && pointsToPiece.size() > 1) {
            return pointsToPiece;
        }
        else {
            return empty;
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.setPiece(new Piece(Tile.BLACK), new Point('d', 2));
        board.setPiece(new Piece(Tile.BLACK), new Point('d', 3));
        board.setPiece(new Piece(Tile.BLACK), new Point('d', 4));
        board.setPiece(new Piece(Tile.WHITE), new Point('e', 2));
        board.setPiece(new Piece(Tile.WHITE), new Point('e', 3));
        board.setPiece(new Piece(Tile.WHITE), new Point('e', 4));
        VaildMoves check = new VaildMoves(board);

        HashMap<Point, ArrayList<Point>> piecesTest = check.getValidPlayerMoves(Tile.BLACK);

        System.out.println(board.toString(piecesTest));

        for (Point flip : piecesTest.get(new Point('f', 4))) {
            System.out.println(flip);
        }
    }
}
