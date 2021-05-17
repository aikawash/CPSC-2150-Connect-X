package cpsc2150.connectX;

import java.util.Arrays;

/**
 * Created by aikaw on 2/9/20.
 */
public class GameBoard implements IGameBoard{

    // row and column numbers
    public final static int COLNUM = 7;
    public final static int ROWNUM = 6;
    public final static int WIN_NUM = 4;

    /**
     * @invariant
     * connectBoard array positions cannot be null
     */

    // variable representing game board
    public final char[][] connectBoard;


    public GameBoard(){

        connectBoard = new char[ROWNUM][COLNUM];
        for(char[] newBoard : connectBoard){
            Arrays.fill(newBoard , ' ');
        }
    }
    /**
     * returns true if column c can accept another token
     * @param c column number
     * @return true or false boolean depending on column fullness
     * @pre
     * c >= 0 and c < 7
     * @post
     * [if true column can accept another token]
     *
     */
    public boolean checkIfFree(int c){
        if(c > COLNUM -1 || c < 0 ){

            return false;
        }

        int count = 0;

        for(int i = 0; i < ROWNUM; i++){
            if(connectBoard[i][c] == ' ' ){
                count++;

            }
        }

        if(count <= 6 && count != 0){

            return true;
        }

        else {
            return false;
        }


    }

    /**
     * returns true if last token placed in column c
     * results in a player win
     * @param c column number
     * @return true or false boolean depending on if last token
     * creates a win condition
     * @pre
     * c >= 0 and c < 7
     * @post
     * [if true, game is won by last player]
     *
     */
    public boolean checkForWin(int c){
        int col = c;

        int count = 0;

        for(int i = 0; i < ROWNUM ; i++){
            if(connectBoard[i][c] != ' ' ){
                count++;

            }
        }




        BoardPosition temp = new BoardPosition(count, col);
        char player = whatsAtPos(temp);

        if(checkHorizWin(temp, player) || checkVertWin(temp, player) || checkDiagWin(temp, player) )
            return true;


        else{
            return false;
        }

    }

    /**
     * places token in lowest available row
     * @param p player token character
     * @param c column number
     * @return n/a
     * @pre c >= 0 and [p is a char] and checkIfFree() == true
     * @post [token successfully placed in lowest available row]
     *
     */
    public void placeToken(char p, int c){
        for(int i = ROWNUM-1; i >= 0; i--){
            if(connectBoard[i][c] == ' ' ){
                connectBoard[i][c] = p;
                return;
            }
        }



    }

    /**
     * checks for a horizontal win
     * @param BoardPosition holds [row][column] position
     * on the board
     * @param p player token character
     * @return true if player has 4 tokens in a row horizontally,
     * false otherwise
     * @pre [p is a char] and [BoardPosition is a valid position and last token placed]
     * @post [true results in a player win, false not a win]
     *
     */
    public boolean checkHorizWin(BoardPosition pos, char p){
        if(p == connectBoard[pos.getRow()][pos.getColumn()]){
            int rowInc = 1;
            int colInc = 0;

            int count1 =0;
            int count2 =0;

            int counter = 1;

            int startCol = pos.getColumn();
            int startRow = pos.getRow();

            do {
                startRow= startRow + rowInc;
                startCol= startCol + colInc;
            }
            // inside while checks for out of bounds
            while (!(startRow >= ROWNUM || startCol >= COLNUM || startRow < 0 || startCol < 0)
                    //count char to win is on board
                    && (connectBoard[pos.getRow()][pos.getColumn()] == p)
                    && ++count1 < WIN_NUM);

            counter = counter + count1;


            do {
                startRow= startRow + rowInc;
                startCol= startCol + colInc;
            }
            // inside while checks for out of bounds
            while (!(-startRow >= ROWNUM || -startCol >= COLNUM || -startRow < 0 || -startCol < 0)
                    //count char to win is on board
                    && (connectBoard[pos.getRow()][pos.getColumn()] == p)
                    && ++count2 < WIN_NUM);


            counter = counter + count2;


            return counter == WIN_NUM/2;
        }
        return false;
    }


    /**
     * checks for vertical win
     * @param BoardPosition holds [row][column] position
     * on the board
     * @param p player token character
     * @return true if player has 4 tokens in a row vertically,
     * false otherwise
     * @pre [p is a char] ana [BoardPosition is a valid position and last token placed]
     * @post [true results in a player win, false not a win]
     *
     */
    public boolean checkVertWin(BoardPosition pos, char p){
        if(p == connectBoard[pos.getRow()][pos.getColumn()]){
            int rowInc = 0;
            int colInc = 1;

            int count1 =0;
            int count2 =0;

            int counter = 1;

            int startCol = pos.getColumn();
            int startRow = pos.getRow();

            do {
                startRow= startRow + rowInc;
                startCol= startCol + colInc;
            }
            // inside while checks for out of bounds
            while (!(startRow >= ROWNUM || startCol >= COLNUM || startRow < 0 || startCol < 0)
                    //count char to win is on board
                    && (connectBoard[pos.getRow()][pos.getColumn()] == p)
                    && ++count1 < WIN_NUM);

            counter = counter + count1;

            do {
                startRow= startRow + rowInc;
                startCol= startCol + colInc;
            }
            // inside while checks for out of bounds
            while (!(-startRow >= ROWNUM || -startCol >= COLNUM || -startRow < 0 || -startCol < 0)
                    //count char to win is on board
                    && (connectBoard[pos.getRow()][pos.getColumn()] == p)
                    && ++count2 < WIN_NUM);

            counter = counter + count2;


            return counter == WIN_NUM/2;
        }

        return false;
    }
    /**
     * checks for a diagonal win
     * @param BoardPosition holds [row][column] position
     * on the board
     * @param p player token character
     * @return true if player has 4 tokens in a row diagonally,
     * false otherwise
     * @pre [p is a char] ana [BoardPosition is a valid position]
     * @post [true results in a player win, false not a win]
     *
     */
    public boolean checkDiagWin(BoardPosition pos, char p){
        int rowInc = 1;

        int colInc = 1;

        int count1 =0;
        int count2 =0;

        int counter = 1;

        int startCol = pos.getColumn();
        int startRow = pos.getRow();

        do {
            startRow= startRow + rowInc;
            startCol= startCol + colInc;
        }
        // inside while checks for out of bounds
        while (!(startRow >= ROWNUM || startCol >= COLNUM || startRow < 0 || startCol < 0)
                //count char to win is on board
                && (connectBoard[pos.getRow()][pos.getColumn()] == p)
                && ++count1 < WIN_NUM);

        counter = counter + count1;

        do {
            startRow= startRow + rowInc;
            startCol= startCol + colInc;
        }
        // inside while checks for out of bounds
        while (!(-startRow >= ROWNUM || -startCol >= COLNUM || -startRow < 0 || -startCol < 0)
                //count char to win is on board
                && (connectBoard[pos.getRow()][pos.getColumn()] == p)
                && ++count2 < WIN_NUM);

        counter = counter + count2;

        boolean win1 = counter == WIN_NUM/2;

        rowInc = 1;

        colInc = -1;

        count1 =0;
        count2 =0;

        counter = 1;

        do {
            startRow= startRow + rowInc;
            startCol= startCol + colInc;
        }
        // inside while checks for out of bounds
        while (!(startRow >= ROWNUM || startCol >= COLNUM || startRow < 0 || startCol < 0)
                //count char to win is on board
                && (connectBoard[pos.getRow()][pos.getColumn()] == p)
                && ++count1 < WIN_NUM);

        counter = counter + count1;

        do {
            startRow= startRow + rowInc;
            startCol= startCol + colInc;
        }
        // inside while checks for out of bounds
        while (!(-startRow >= ROWNUM || -startCol >= COLNUM || -startRow < 0 || -startCol < 0)
                //count char to win is on board
                && (connectBoard[pos.getRow()][pos.getColumn()] == p)
                && ++count2 < WIN_NUM);

        counter = counter + count2;


        boolean win2 = counter == WIN_NUM/2;

        return win1 || win2;
    }
    /**
     * returns if a char is in given position on board,
     * otherwise returns " " (blank space)
     * @param BoardPosition holds [row][column] position
     * on the board
     * @return char in given position, else " " if char does not match
     * @pre [BoardPosition is a valid position]
     * @post [char in the position is known]
     *
     */
    public char whatsAtPos(BoardPosition pos){
        char playerAtPos;
        if( connectBoard[pos.getRow()][pos.getColumn()] != ' '){
            playerAtPos = connectBoard[pos.getRow()][pos.getColumn()];
            return playerAtPos;
        }

        else {
            return ' ';
        }
    }

    /**
     * returns true if player token in a specific position on the board
     * @param BoardPosition holds [row][column] position
     * on the board
     * @param player char of player token
     * @return true if player char is at given BoardPosition
     * @pre [BoardPosition is a valid position] and [player is a char]
     * @post [true if given char and given position match]
     *
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player){

        return connectBoard[pos.getRow()][pos.getColumn()] == player;

    }




    /**
     * overrides default toString to return a formatted current game board
     * @return a formatted game board
     * @post returns game board in current state
     *
     */
    public String toString(){
        int [] nums = new int [COLNUM];

        String gameString = "|";

        for(int j =0; j < COLNUM ; j++){
            int temp;
            temp = j;
            nums[j] = temp;

        }

        // top number row
        for(int i = 0; i < COLNUM ; i++){
            if(i == 0){
                //gameString = gameString.concat("|");
                gameString = gameString.concat(Integer.toString(nums[i]));
                gameString = gameString.concat("|");
            }
            if(i != 0){
                gameString = gameString.concat(Integer.toString(nums[i]));
                gameString = gameString.concat("|");
            }
        }

        gameString = gameString.concat("\n");

        for(int k = 0; k < ROWNUM; k++){
            for(int i = 0; i < COLNUM; i++){
                if(i == 0){
                    gameString = gameString.concat("|");
                    gameString = gameString.concat(Character.toString(connectBoard[k][i]));
                    gameString = gameString.concat("|");
                }

                if((i < COLNUM ) && i != 0 ){
                    gameString = gameString.concat(Character.toString(connectBoard[k][i]));
                    gameString = gameString.concat("|");
                }





            }

            gameString = gameString.concat("\n");
        }
        gameString = gameString.concat("\n");
        return gameString;
    }

    /**
     * returns true if the game board results in a tie game
     * @return true or false boolean depending on if the game
     * ends in a tie (no winner)
     * @pre
     * checkIfFree(int c) on columns 0-6 must return false
     * @post
     * [if true, game is won by neither player]
     *
     */
    public boolean checkTie(){

        for(int i = 0; i < COLNUM; i++){
            if(checkIfFree(i) == false){
                return false;
            }
        }

        return true;
    }


    public int getNumRows() {
        return ROWNUM;
    }


    public int getNumColumns() {
        return COLNUM;
    }


    public int getNumToWin() {
        return WIN_NUM;
    }

}
