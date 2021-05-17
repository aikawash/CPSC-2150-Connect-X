default:
	javac cpsc2150/connectX/GameBoard.java cpsc2150/connectX/GameScreen.java cpsc2150/connectX/BoardPosition.java cpsc2150/connectX/IGameBoard.java cpsc2150/connectX/GameBoardMem.java

run: cpsc2150/connectX/GameBoard.java cpsc2150/connectX/GameScreen.java cpsc2150/connectX/BoardPosition.java cpsc2150/connectX/IGameBoard.java cpsc2150/connectX/GameBoardMem.java cpsc2150/connectX/AbsGameBoard.java
	java cpsc2150.connectX.GameScreen

clean:
	rm cpsc2150/connectX/*.class

test:
	java -cp .:/usr/share/java/junit4.jar cpsc2150/connectX/TestGameBoard.java cpsc2150/connectX/TestGameBoardMem.java

testGB:
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.connectX.TestGameBoard.java

testGBMem:
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.connectX.TestGameBoardMem.java
