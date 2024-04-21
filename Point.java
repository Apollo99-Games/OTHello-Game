/**
 * Represents a point with a x and y component.
 */
class Point {
    private int x;
    private int y;

    /**
     * Initializes a Point object with the given x and y coordinates.
     * 
     * @param X The x-coordinate of the point.
     * @param Y The y-coordinate of the point.
     */
    public Point(int X, int Y) {
        this.x = X;
        this.y = Y;
    }

    /**
     * Initializes a Point object with the given character-based x and integer-based y coordinates.
     * 
     * @param X The character-based x-coordinate of the point.
     * @param Y The integer-based y-coordinate of the point.
     */
    public Point(char X, int Y) {
        this.x = Character.toUpperCase(X) - 'A';
        this.y = Y;
    }

    /**
     * Returns a new Point object that is the sum of this point and another given point.
     * 
     * @param other The Point object to add to this point.
     * @return The resulting Point object after addition.
     */
    public Point add(Point other) {
        return new Point(x + other.x, y + other.y);
    }

    /**
     * Returns a new Point object that is the difference between this point and another given point.
     * 
     * @param other The Point object to subtract from this point.
     * @return The resulting Point object after subtraction.
     */
    public Point subtract(Point other) {
        return new Point(x - other.x, y - other.y);
    }

    /**
     * Returns a reference to this Point object.
     * 
     * @return This Point object.
     */
    public Point clone() {
        return this;
    }

    /**
     * Returns the x-coordinate of this Point object.
     * 
     * @return The x-coordinate of this Point object.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this Point object.
     * 
     * @return The y-coordinate of this Point object.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of this Point object to a new value.
     * 
     * @param newX The new value for the x-coordinate of this Point object.
     */
    public void setX(int newX) {
        x = newX;
    }

    /**
     * Sets the y-coordinate of this Point object to a new value.
     * 
     * @param newY The new value for the y-coordinate of this Point object.
     */
    public void setY(int newY) {
        x = newY;
    }

    /**
     * Returns a string representation of this Point object in the form "(x, y)".
     * 
     * @return A string representation of this Point object.
     */
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    /**
     * Returns true if the given string is in the format of a Point object.
     * 
     * @param point The string to check for Point format.
     * @return True if the string is in Point format, false otherwise.
     */
    public static boolean isPointFormat(String point) {
        return point.strip().matches("[A-Za-z](\\s+)?[0-9]");
    }

    /**
   *
   *@param point A string representation of a point in format "A 1"
   *@return A new instance of class Point with coordinates from input string
   *@throws IllegalArgumentException if input string has invalid format
   */
    public static Point valueOf(String point) throws IllegalArgumentException {
        String pointString = point.strip();
        if (isPointFormat(point)) {
            return new Point(pointString.charAt(0), Integer.valueOf(pointString.substring(pointString.length() - 1, pointString.length())));
        }
        throw new IllegalArgumentException("Invalid Point Format");
    }

    /**
     * Returns a hash code value for this Point object.
     * 
     * @return A hash code value for this Point object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    /**
     * Returns true if this Point object is equal to another given object.
     * 
     * @param obj The object to compare with this Point object.
     * @return True if the two objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    public static void main(String[] args) {
        Point boby = Point.valueOf("              E                      5          ");
        System.out.println(boby);
    }
}
