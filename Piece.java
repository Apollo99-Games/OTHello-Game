/**
 * Represents a tile on the board.
 */
enum Tile {
    EMPTY,
    BLACK,
    WHITE
}
/**
 * Represents a piece on the board.
 */
public class Piece {
    Tile pieceType;

    /**
     * Creates an empty piece.
     */
    public Piece() {
        this.pieceType = Tile.EMPTY;
    }

    /**
     * Creates a piece of a given type.
     *
     * @param piece The type of piece to create.
     */
    public Piece(Tile piece) {
        this.pieceType = piece;
    }

    /**
     * Sets the type of this piece.
     *
     * @param piece The type of piece to set this piece to.
     */
    public void setPiece(Tile piece) {
        this.pieceType = piece;
    }

    /**
     * Gets the type of this piece.
     *
     * @return The type of this piece.
     */
    public Tile getType() {
        return pieceType;
    }

    /**
     * Checks if this piece is black.
     *
     * @return True if this piece is black, false otherwise.
     */
    public boolean isBlack() {
        return pieceType == Tile.BLACK;
    }

    /**
     * Checks if this piece is white.
     *
     * @return True if this piece is white, false otherwise.
     */
    public boolean isWhite() {
        return pieceType == Tile.WHITE;
    }

    /**
     * Checks if this piece is empty.
     *
     * @return True if this piece is empty, false otherwise.
     */
    public boolean isEmpty() {
        return pieceType == Tile.EMPTY;
    }

    /**
     * Checks if this piece is opposite to another given piece.
     *
     * @param other The other given piece to check against.
     * @return True if this piece is opposite to another given piece, false otherwise.
     */
    public boolean isOppositeTo(Piece other) {
        return (this.isBlack() && other.isWhite()) || (this.isWhite() && other.isBlack());
    }

    /**
     * Gets the opposite of this piece (black becomes white and vice versa).
     *
     * @return The opposite of this piece (black becomes white and vice versa).
     */
    public Piece getOpposite() {
        if (this.isBlack()) {
            return new Piece(Tile.WHITE);
        }
        else if (this.isWhite()) {
            return new Piece(Tile.BLACK);
        }
        else {
            return new Piece();
        }
    }

    /**
     * Returns a string representation of this object.
     *
     * @return A string representation of this object.
     */
    public String toString() {
        switch (pieceType) {
            case BLACK:
                return " o ";
            case WHITE:
                return " @ ";
            default:
                return "   ";
        }
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for the object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pieceType == null) ? 0 : pieceType.hashCode());
        return result;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The reference object with which to compare.
     * @return True if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Piece other = (Piece) obj;
        if (pieceType != other.pieceType)
            return false;
        return true;
    }
}
