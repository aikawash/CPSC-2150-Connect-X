package cpsc2150.connectX;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class GameScreen {
    public static void main(String [] args)
    {
        
        Scanner readIn = new Scanner(System.in);
        
        boolean playAgain = true;
        
        int numReadIn = -1;
        while(playAgain){
            
            System.out.println("How many playerNum?");
            int playerNum = readIn.nextInt();
            readIn.nextLine();

            while(playerNum < GameBoard.MIN_PLAYER ||
                    playerNum > GameBoard.MAX_PLAYER)
            {
                if(playerNum < GameBoard.MIN_PLAYER)
                    System.out.println("Must be at least 2 players");
                else if( playerNum > GameBoard.MAX_PLAYER)
                    System.out.println("Must be 10 playerNum or fewer");

                System.out.println("How many players?");
                playerNum = readIn.nextInt();
                readIn.nextLine();
            }

            List<Character> playerCharacter = new ArrayList<>();

            for(int i = 1; i <= playerNum; i++)
            {

                System.out.println("Enter the character to represent player "
                        + i);
                String g = readIn.nextLine();
                char player = g.charAt(0);

                while(playerCharacter.contains(player))
                {
                    System.out.println(player +
                            " is already taken as a player token!");
                    System.out.println(
                            "Enter the character to represent player " + i);
                    g = readIn.nextLine();
                    player = g.charAt(0);
                    player = Character.toUpperCase(player);
                }
                player = Character.toUpperCase(player);
                playerCharacter.add(player);
            }



            System.out.println("How many rows should be on the board?");

            int r = readIn.nextInt();
            while(r > GameBoard.MAX_SIZE ||  r < GameBoard.MIN_SIZE) {

                if (r > GameBoard.MAX_SIZE)
                    System.out.println("Can have at most "
                            + GameBoard.MAX_SIZE + " rows");

                else if (r < GameBoard.MIN_SIZE)
                    System.out.println("Must have at least "
                            + GameBoard.MIN_SIZE + " rows");

                System.out.println("How many rows should be on the board?");
                r = readIn.nextInt();
            }
            System.out.println("How many columns should be on the board?");
            int c = readIn.nextInt();

            while(c > GameBoard.MAX_SIZE || c < GameBoard.MIN_SIZE) {
                if (c > GameBoard.MAX_SIZE)
                    System.out.println("Can have at most "
                            + GameBoard.MAX_SIZE + " columns");

                else if (c < GameBoard.MIN_SIZE)
                    System.out.println("Must have at least "
                            + GameBoard.MIN_SIZE + " columns");
                System.out.println("How many columns should be on the board?");
                c = readIn.nextInt();
            }
            System.out.println("How many in a row to win?");
            int winNum = readIn.nextInt();
            readIn.nextLine();
            while(winNum > GameBoard.MAX_SIZE||
                    winNum < GameBoard.MIN_SIZE) {

                if (winNum > GameBoard.MAX_WIN_NUM)
                    System.out.println("Can have at most "
                            + GameBoard.MAX_SIZE+ " in a row to win");

                else if (winNum < GameBoard.MIN_SIZE)
                    System.out.println("Must have at least "
                            + GameBoard.MIN_SIZE + " in a row to win");

                System.out.println("How many in a row to win?");
                winNum = readIn.nextInt();
                readIn.nextLine();
            }

            System.out.println(
                    "Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
            String game = readIn.nextLine();
            char g = game.charAt(0);

            while(g != 'F' && g != 'f' && g != 'M' && g != 'm') {
                System.out.println("Please enter F or M");
                System.out.println(
                        "Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                game = readIn.nextLine();
                g = game.charAt(0);
            }

            IGameBoard gameBoard2;

            if(g == 'F' || g == 'f')
                gameBoard2 = new GameBoard(r,c,winNum);
            else
                gameBoard2 = new GameBoardMem(r,c,winNum);

            int current_player = -1;
            do {
                current_player++;
                System.out.println("Player " + playerCharacter.get(current_player%playerNum) +
                        ", what column do you want to place your marker in?");
                numReadIn = readIn.nextInt();
                while(numReadIn >= gameBoard2.getNumColumns() || numReadIn < 0
                        || !gameBoard2.checkIfFree(numReadIn)) {
                    if (numReadIn < 0)
                        System.out.println("Column cannot be less than 0");
                    else if (numReadIn >= gameBoard2.getNumColumns())
                        System.out.println("Column cannot be greater than "
                                + (gameBoard2.getNumColumns() - 1));
                    else if (!gameBoard2.checkIfFree(numReadIn))
                        System.out.println("Column is full");
                    System.out.println("Player " + playerCharacter.get(current_player%playerNum)+
                            ", what column do you want to place your marker in?");
                    numReadIn = readIn.nextInt();
                }
                gameBoard2.placeToken(playerCharacter.get(current_player%playerNum), numReadIn);
                System.out.println(gameBoard2);


            }while(!gameBoard2.checkForWin(numReadIn) && !gameBoard2.checkTie());
            if(gameBoard2.checkForWin(numReadIn))
                System.out.println("Player " +
                        playerCharacter.get(current_player%playerNum) + " won!");
            else if(gameBoard2.checkTie())
                System.out.println("Tie!");
            String again = readIn.nextLine();
            while(!again.equals("N") && !again.equals("n")
                    && !again.equals("Y") && !again.equals("y"))
            {
                System.out.println("Would you like to play again? Y/N");
                again = readIn.next();
            }

            if(again.equals("N") || again.equals("n"))
                playAgain = false;
        }
    }
}
