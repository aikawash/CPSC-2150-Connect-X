package cpsc2150.connectX;

/**
 * Created by aikaw on 2/9/20.
 */
import java.util.Scanner;
import static java.lang.System.*;
import java.lang.String;
import java.util.Arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @invariant
 * [GameBoard must show the current game status for each player's placed tokens] and
 * [playerTurn tells what turn it is depending on the state of the boolean] and
 * [gameInPlay is true when the board is not full and a win or tie not found] and
 * [ playerX and playerO must represent the tokens of the two players] and
 * [lastCol gives the last successfully placed column]
 *
 *
 */

public class GameScreen {

    private GameBoard connectXBoard = new GameBoard();
    // player names
    private final char playerX = 'X';
    // letter O
    private final char playerO = 'O';

    // player X = true, player O = false
    private boolean playerTurn = true;
    private boolean gameInPlay = true;
    private int lastCol;
    private int moves;

        public static void main(String[] args){

            GameScreen newGame = new GameScreen();
            System.out.print(newGame.connectXBoard);

            while(newGame.gameInPlay == true){
                newGame.placeMarker();
                newGame.checkWin();

                if(newGame.playerTurn){
                    newGame.playerTurn = false;
                    newGame.moves++;
                }


                else{
                    newGame.playerTurn = true;
                    newGame.moves++;
                }

                newGame.checkTie();


            }










    }


    /**
     * receives input from player to get column
     * location to place token
     * @param col column number
     * @pre
     * col >= 0 and col < 7
     * @post
     * [if within bounds, will accept the column number.
     * if out of bounds, will prompt player again. ]
     *
     */
    public boolean getColumn(int col){

        if(playerTurn){
            if( col < 0 ){
                System.out.println("\nColumn cannot be less than 0");
                System.out.print("Player "+playerX+ ", what column do you want to place your token in?\n");
                return false;

            }

            if( col >= connectXBoard.getNumColumns() ){
                System.out.println("\nColumn cannot be greater than 6");
                System.out.print("Player "+playerX+ ", what column do you want to place your token in?\n");

                return false;

            }

            if(connectXBoard.checkIfFree(col) == false){
                System.out.println("\nColumn is full");
                return false;
            }

            return true;
        }

        if(!playerTurn){
            if( col < 0 ){
                System.out.println("\nColumn cannot be less than 0");
                System.out.print("Player "+playerO+ ", what column do you want to place your token in?\n");
                return false;

            }

            if( col >= connectXBoard.getNumColumns() ){
                System.out.println("\nColumn cannot be greater than 6");
                System.out.print("Player "+playerO+ ", what column do you want to place your token in?\n");

                return false;

            }

            if(connectXBoard.checkIfFree(col) == false){
                System.out.println("\nColumn is full");
                return false;
            }

            return true;
        }
        return false;
    }

    /**
     * checks for a win from either player, if there is
     * a win print a congratulatory message and be prompted
     * to choose to play again
     * @post
     * [if player wins, they will receive a message and prompt
     * to play again]
     */
    public void checkWin(){
        if(connectXBoard.checkForWin(lastCol) == true){
            if(playerTurn == true){
                System.out.println("Player X won!");
                if(playAgain()== true){
                    connectXBoard = new GameBoard();
                }

            }

            if(playerTurn == false){
                System.out.println("Player O won!");
                playAgain();
            }

        }

    }

    /**
     * checks for a tie between the two players, if there
     * is a tie will print a tie message and prompt the player
     * to play again
     * @post
     * [in the case of a tie, player will be prompted to play again]
     */
    public boolean checkTie(){

        int maxMoves = connectXBoard.getNumRows()*connectXBoard.getNumColumns();

        if(moves == maxMoves ){
            return true;
        }

        return false;

        //if(connectXBoard.checkTie() ==


    }

    /**
     * prompts player to place marker on board
     *
     */
    public void placeMarker(){
        if(playerTurn){

            System.out.print("Player "+playerX+ ", what column do you want to place your token in?\n");
            int column = 0;
            boolean test = false;

            while(test == false){
                Scanner in = new Scanner(System.in);
                column = in.nextInt();
                test= getColumn(column);
            }



            lastCol = column;
            connectXBoard.placeToken(playerX,column);
            System.out.print(connectXBoard);
        }

        if(!playerTurn){
            System.out.println("Player "+playerO+ ", what column do you want to place your token in?");

            int column = 0;
            boolean test = false;

            while(test == false){
                Scanner in = new Scanner(System.in);
                column = in.nextInt();
                test= getColumn(column);
            }



            lastCol = column;
            connectXBoard.placeToken(playerO,column);
            System.out.print(connectXBoard);
        }


    }

    /**
     * resets the game if a player chooses to play again
     * @post
     * [board and positions will be reset when after the
     * player chooses to play again (true) , otherwise
     * game will end (false)]
     */
    public boolean playAgain(){

        System.out.println("Would you like to play again? Y/N");
        Scanner in = new Scanner(System.in);
        char choice = in.next().charAt(0);


        if(choice == 'Y' || choice == 'y'){

            return true;
        }

        if(choice == 'n' || choice == 'N'){
            gameInPlay = false;
            return false;
        }

        return false;
    }


}