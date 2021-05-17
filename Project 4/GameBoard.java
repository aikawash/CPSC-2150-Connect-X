package cpsc2150.connectX;

import java.util.Arrays;

/**
 * Created by aikaw on 2/9/20.
 */


/**
 * @invariant MIN_SIZE <= width <=MAX_SIZE
 * @invariant MIN_SIZE <= height <= MAX_SIZE
 * @invariant MIN_SIZE < win_num < MAX_WIN_NUM
 *
 *
 * correspondence [number of rows] = height;
 * correspondence [number of columns] = column;
 * correspondence [in a row to win] = win_num;
 */
public class GameBoard extends AbsGameBoard{

    // row and column numbers
    private int height;
    private int width;
    private int win_num;
    private char[][] connectBoard;


    /**
     * GameBoard constructor
     * @post [A new GameBoard is initialized filled with ' ']
     * @invariant height > 0 && width > 0
     */

    public GameBoard(int row, int col, int numWin){
        height = row;
        width = col;
        win_num = numWin;
        connectBoard = new char[row][col];
        for(char[] newBoard : connectBoard){
            Arrays.fill(newBoard , ' ');
        }
    }






    /**
     * places token in lowest available row
     * @param p player token character
     * @param c column number to place p in
     * @return n/a
     * @pre c >= 0 and [p is a valid player char]
     * checkIfFree(c) == true and c < getNumColumns()
     * @post [token p successfully placed in lowest available free spot
     * in column c]
     *
     */
    public void placeToken(char p, int c) {
        for (int i = 0; i < height; i++) {
            if (connectBoard[i][c] == ' ') {
                connectBoard[i][c] = p;
                return;
            }
        }
    }


    /**
     * returns if a char is in given position on board,
     * otherwise returns " " (blank space)
     * @param pos holds [row][column] position
     * on the board
     * @return char in given position, else " " if char does not match
     * @pre [BoardPosition is a valid position]
     * pos.getRow < getNumRows() && pos.getRow > -1 and
     * pos.getCol < getNumColumns && pos.getCol > -1
     * @post [returns char in the position pos.getRow, pos.getCol of
     * ' ' if unoccupied]
     *
     */
    public char whatsAtPos(BoardPosition pos){
        int row = pos.getRow();
        int col = pos.getColumn();

        return connectBoard[row][col];
    }

    /**
     * returns true is player token in a specific position on the board
     * @param pos holds [row][column] position
     * on the board
     * @param player char of player token
     * @return true if player char is at given BoardPosition
     * @pre [player is a valid player char] and [BoardPosition is a valid position]
     * pos.getRow < getNumRows() && pos.getRow > -1 and
     * pos.getCol < getNumColumns && pos.getCol > -1
     * @post [true if given char matches char at pos.getRow, pos.getCol
     * match]
     *
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player){

        return connectBoard[pos.getRow()][pos.getColumn()] == player;

    }




    /**
     * overrides default toString to return a formatted current game board
     * @return a formatted game board
     * @post [returns string representation of game board in current state]
     *
     */
    public String toString(){
        String board = "";

        for(int i =0; i < getNumColumns(); i++){
            if(i <= 9){
                board += "| " + i;

            }
            else{
                board += "|" + i;
            }
        }

        board += "|\n";

        for(int i = getNumRows()-1; i > -1; i--){
            board += "|";


            for(int j = 0; j < getNumColumns(); j++){
                BoardPosition pos2 = new BoardPosition(i, j);
                board += whatsAtPos(pos2) + " |";
            }
            board += "\n";
        }

        return board;
    }



    /**
     * @pre [number of rows] <= MAX_SIZE && [number of rows] >= MIN_SIZE;
     * @post getNumRows() = [number of rows]
     * @return [number of rows]
     */
    public int getNumRows() {
        return height;
    }

    /**
     * @pre [number of columns] <= MAX_SIZE && [number of columns] >= MIN_SIZE
     * @post getNumColumns() = [number of columns]
     * @return [number of columns]
     */
    public int getNumColumns() {
        return width;
    }

    /**
     * @pre [number in a row to win] <= MAX_WIN_NUM && [number in a row to win] >= MIN_SIZE
     * @post getNumToWin() = [number in a row to win]
     * @return [number in a row to win]
     */
    public int getNumToWin() {
        return win_num;
    }

}
