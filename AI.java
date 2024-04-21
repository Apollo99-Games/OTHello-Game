import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class AI {

    private static Random random = new Random();

    /**
    * Returns a corner move from the valid AI moves that gives the most points.
    *
    * @param validAIMoves a HashMap of the valid AI moves
    * @return if a corner move is possible returns a point, else null
    */
    public static Point cornerMove(HashMap<Point, ArrayList<Point>> validAIMoves) {
        Point cornerList[] = {new Point('A', 0), new Point('A', 8), new Point('H', 0), new Point('H', 8)};

        Point bestCorner = null;
        int bestCornerPoints = 0;
        
        for (Point corner : cornerList) {
            // loops through to find the corner move that will get the most points
            if (validAIMoves.containsKey(corner)) {

                int points = validAIMoves.get(corner).size();
                if (bestCornerPoints < points) {
                    bestCorner = corner;
                    bestCornerPoints = points;
                }
            }
        }
        
        return bestCorner;
    }

    /**
    * Returns a random valid move from the valid white moves.
    *
    * @param validAIMoves a HashMap of the valid AI moves
    * @return a random valid move from the valid AI move.
    */
    public static Point randomValidMove(HashMap<Point, ArrayList<Point>> validAIMoves) {
        ArrayList<Point> validPoints = new ArrayList<>(validAIMoves.keySet());
        Point randomPoint = validPoints.get(random.nextInt(validPoints.size()));
        return randomPoint;
    }

    /**
    * Returns the move that minimizes the number of points agained by the AI
    *
    * @param validAIMoves the valid AI moves
    * @return the move that minimizes the number of points agained by the AI
    */
    public static Point minimizePointsMove(HashMap<Point, ArrayList<Point>> validAIMoves) {
        Point minimizeMove = null;
        int pieceCounter = Integer.MAX_VALUE;

        for (Map.Entry<Point, ArrayList<Point>> entry : validAIMoves.entrySet()) {
            Point point = entry.getKey();
            if (validAIMoves.get(point).size() < pieceCounter) {
                pieceCounter = entry.getValue().size();
                minimizeMove = point;
            }
        }
        return minimizeMove;
    }

    /**
    * Returns the move that maximizes the number of points agained by the AI
    *
    * @param validAIMoves the valid AI moves
    * @return the move that maximizes the number of points agained by the AI
    */
    public static Point maximizePointsMove(HashMap<Point, ArrayList<Point>> validAIMoves) {
        Point maxMove = null;
        int pieceCounter = 0;

        for (Map.Entry<Point, ArrayList<Point>> entry : validAIMoves.entrySet()) {
            Point point = entry.getKey();
            if (validAIMoves.get(point).size() > pieceCounter) {
                pieceCounter = entry.getValue().size();
                maxMove = point;
            }
        }
        return maxMove;
    }

    /**
    * Returns the move that the AI should make based on the current state of the board.
    *
    * @param board the current state of the board
    * @param validMoves the valid AI moves
    * @return the move that the AI should make
    */
    public static Point makeMove(Board board, HashMap<Point, ArrayList<Point>> validMoves) {
        Point AIMove = null;

        if (cornerMove(validMoves) != null) {
            AIMove = cornerMove(validMoves);
        }
        else if (board.getNumberOfWhitePieces() + board.getNumberOfBlackPieces() < 24) {
            AIMove = minimizePointsMove(validMoves);
        }
        else if (board.getNumberOfWhitePieces() + board.getNumberOfBlackPieces() > 32) {
            AIMove = maximizePointsMove(validMoves);
        }
        else {
            AIMove = randomValidMove(validMoves);
        }
        return AIMove;
    }
}
