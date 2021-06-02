# Chess

Welcome to my terminal-based, 2 player chess game.

Instructions:

1. Create a build folder with "rmdir build"  
2. Compile the .java files into the build folder with "javac -d build src/*.java".  
2. Run the file named Driver.java with "java -cp ./build Driver".  
3. The game will begin.  

Notes:  

- ChessGame.java: The main logic of the game is handled here, such as taking turns,
handling check etc.  
- Board.java: Used to set pieces on the board and physically move them around.  
- Move.java: Handles the format for an actual move. A move is specified as a
string such as "e2 e4".  
- Cell.java: Handles the format for a square on the board.  
- Piece.java: An abstract class to set up the structure for each individual piece.  
- The rest of the files are the specific for each piece type, detailing their valid
moves and any other unique attributes/methods.  

Future areas of development:  

- Checkmate algorithm: Currently have no way to end the game automatically on checkmate,
even if there are no valid moves. Current thinking is to make an algorithm that runs
every possible move to see if there is one that gets out of check (taking the
threatening piece, moving king, defending king with another piece). This is quite
brute force but unsure how to optimise it yet.  

- Make it into an interactive webpage: Build into a web app with click and drag
functionality  
