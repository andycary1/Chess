# Chess

Welcome to my terminal-based, 2 player chess game.

Instructions:

1. Compiling the .java files into the build folder with "javac -d build src/*.java".
2. Run the file named Driver.java with "java -cp "./build" Driver".
3. The game will begin.

Future areas of development:
-Checkmate algorithm: Currently have no way to end the game automatically on checkmate,
even if there are no valid moves. Current thinking is to make an algorithm that runs
every possible move to see if there is one that gets out of check (taking the
threatening piece, moving king, defending king with another piece). This is quite
brute force but unsure how to optimise it yet.
-Make it into an interactive webpage: Build into a web app with click and drag
functionality
