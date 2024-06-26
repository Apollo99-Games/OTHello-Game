# Othello-Game

This is an implementation of Othello in Java. Note that you will need some JDK and preferably an IDE to run the code, as a terminal is required to print the result (I used VS code). The game can be run from the "OTHelloGame.java" file.

You can run the game on [Replit](https://replit.com/@WahhajKhan/OTHello-Game?v=1).

### The Java Version used to test the code:

openjdk version "17.0.11" 2024-04-16
OpenJDK Runtime Environment Temurin-17.0.11+9 (build 17.0.11+9)
OpenJDK 64-Bit Server VM Temurin-17.0.11+9 (build 17.0.11+9, mixed mode, sharing)

# Features: 
- Single-player and 2-player modes
- Will check for valid moves
- Will tell players how many points they will gain for each possible move
- Will keep track of the score
- Can handle the end game and inform players of who won
- Comes with a basic AI player

# Example of the board on the terminal
The numbers represent the points you will get by making a move on that coordinate.
@ - white team     O - Black team

![OTHelloGame java - OthelloGame - Visual Studio Code 4_20_2024 3_16_29 PM](https://github.com/Apollo99-Games/OTHello-Game/assets/163193765/a84f1b6e-feeb-4bb2-b117-684747016228)

# Using this to build a Custom Interface
Although this project is built to interact with a terminal, it can easily be modified to work with other user interfaces, such as a GUI. 

# Basic Helper Classes
There are two small helper classes in this project.

## Point Class
A basic class that holds a 2D coordinate (x, y)
```java

// The point can be created with either two numbers or a letter as the x-axis
Point one = new Point(5, 6);
Point two = new Point('A', 5);

//Convert a string to a point in a format that has a letter for the x-axis and then a number for the y-axis
Point three = Point.valueOf("A5");

// simple getters and setters
three.setX(3);
three.setY(3);
int x  = three.getX();
int y = three.getY();

```

## Piece Class
A basic class that holds the colour of a piece on the board (BLACK, WHITE, EMPTY)
```java

// empty piece
Piece emptyPiece = new Piece();

// piece with a custom colour (BLACK, WHITE, EMPTY)
Piece whitePiece = new Piece(Tile.WHITE);
Piece blackPiece = new Piece(Tile.BLACK);

// some other helpful methods
whitePiece.isOppositeTo(blackPiece); // true
Piece whitePiece2 = blackPiece.getOpposite();

```

# Board Class
Contains an 8 by 8 board for the game. It also keeps track of the number of pieces each player has on it.
```java

private static Board board = new Board();

// get a piece on the board
Piece piece = board.getPiece(new Point('A', 0));

// set a piece on the board
board.setPiece(piece, new Point('A', 0));

// Get the number of player pieces
int black = board.getNumberOfBlackPieces();
int white = board.getNumberOfWhitePieces();

ArrayList<Point> piecesToFlip = new ArrayList<Point>();
piecesToFlip.add(new Point('A', 0));
piecesToFlip.add(new Point('A', 1));
piecesToFlip.add(new Point('A', 2));
piecesToFlip.add(new Point('A', 3));

// changes all pieces on the specified points to their opposite colour
// For example, if all the pieces in the specified points are white, they will become black
board.flipAllPieces(piecesToFlip);

```

# Vaild Moves Class
It is an easy-to-use class that gives you every valid move for a player. All it requires is passing in the current state of the board and the player's pieces' colour.
```java

// Create the valid moves class
private static VaildMoves checker = new VaildMoves(board);

// update it whenever the board changes
checker.updateBoard(board);

// Returns a hashmap
// The key is the coordinate on which the player can play a valid move
//The value contains a list of all the coordinates on which the pieces will be flipped to the current player's colour
// The size of each value's list also represents the number of points the player will get if they choose to move on the coordinate of that value's key
HashMap<Point, ArrayList<Point>> blackMoves = checker.getValidPlayerMoves(Tile.BLACK);

```

# AI Class
An easy-to-use computer player that will play against human players.

```java

// Create the valid moves class

// Pass in the current state of the board and the valid moves the AI can make to get the AI's move.
Point AIMove = AI.makeMove(board, vaildAIMoves);

```


