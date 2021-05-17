package cpsc2150.connectX;

/**
 * Created by aikaw on 2/24/20.
 */

/**
 * iGameBoard represents a 2D array that can have characters inside
 * Initialization ensures that an instance of GameBoard will be created
 * with the dimensions and
 * contain only blank characters.
 * It also ensures that the connectBoard's dimensions are
 * no larger than MAX_SIZE * MAX_SIZe or no smaller than
 * MIN_SIZE * MIN_SIZE.
 *Defines: [number of rows]: Z
 *          [number of columns] : Z
 *          [number in a row to win] : Z
 *
 */
public interface IGameBoard {

    int MAX_SIZE = 100;
    int MIN_SIZE = 0;
    int MAX_WIN_NUM = 25;
    int MAX_PLAYER = 10;
    int MIN_PLAYER = 2;

    /**
     * returns true if column c can accept another token
     * @param c number of last column entered
     * @return true or false boolean depending on column fullness
     * @pre
     * c >= 0 and c < getNumColumns()
     * @post
     * [if true column can accept another token]
     *
     */
    default boolean checkIfFree(int c){
        for(int i = 0; i < getNumRows(); i++ ){
            BoardPosition pos = new BoardPosition(i,c);
            if(whatsAtPos(pos) == ' '){
                return true;
            }
        }
        return false;
    }



    /**
     * returns true if last token placed in column c
     * results in a player win
     * @param c column number to be checked for a win
     * @return  true iff
     * [checkHorizWin || checkVertWin || checkDiagWin are true when
     * first index of column is not empty]
     * @pre
     * c >= 0 and c < getNumColumns()
     * @post
     * checkForWin(c) = true iff
     * [checkHorizWin || checkVertWin || checkDiagWin are true when
     * first index of column is not empty]
     * [if true, game is won by last player]
     *
     */
    default boolean checkForWin(int c){

        for(int i =0; i < getNumRows(); i++){
            BoardPosition pos2 = new BoardPosition(i,c);
            if(whatsAtPos(pos2) != ' '){
                if(checkHorizWin(pos2, whatsAtPos(pos2)) || checkVertWin(pos2, whatsAtPos(pos2)) ||
                checkDiagWin(pos2, whatsAtPos(pos2))){
                    return true;
                }
            }
        }
        return false;

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
    void placeToken(char p, int c);

    /**
     * checks for a horizontal win
     * @param pos holds [row][column] position
     * on the board
     * @param p player token character to check for win
     * @return true if player has getNumToWin() in a row horizontally,
     * false otherwise
     * @pre [p is a char] and [pos is a valid position]
     * pos.getRow < getNumRows() && pos.getRow > -1 and
     * pos.getCol < getNumColumns && pos.getCol > -1
     *
     * @post true iff [ there are getNumToWin() consecutive
     * of p horizontally on the board]
     *
     */
    default boolean checkHorizWin(BoardPosition pos, char p){
        int row = pos.getRow();
        int col = pos.getColumn();


        int leftmost= Math.max(0, col - (getNumToWin()-1));
        int rightmost = Math.min(getNumColumns() - 1, col + (getNumToWin()-1));

        boolean pass = true;
        int i, j;

        for(i = leftmost; i + getNumToWin() - 1 <= rightmost; i++){
            for(j = 0; j < getNumToWin(); j++) {
                BoardPosition pos2 = new BoardPosition(row, i + j);

                if (whatsAtPos(pos2) != p) {
                    pass = false;
                }
            }
                if(pass){
                    return true;
                }

                pass = true;

        }

        return false;
    };

    /**
     * checks for a vertical win
     * @param pos holds [row][column] position
     * on the board
     * @param p player token character to check for win
     * @return true if player has getNumToWin() in a row vertically,
     * false otherwise
     * @pre [p is a player char] and [pos is a valid position]
     * pos.getRow < getNumRows() && pos.getRow > -1 and
     * pos.getCol < getNumColumns && pos.getCol > -1
     *
     * @post true iff [ there are getNumToWin() consecutive
     * of p vertically on the board]
     *
     */
    default boolean checkVertWin(BoardPosition pos, char p){

        int row = pos.getRow();
        int col = pos.getColumn();
        int i;

        if(row - (getNumToWin() - 1) > -1){
            for(i = 0; i < getNumToWin(); i++){
                BoardPosition pos2 = new BoardPosition(row - i,col);
                if(whatsAtPos(pos2) != p){
                    return false;
                }
            }
            return true;
        }

        else return false;
    }

    /**
     * checks for a diagonal win
     * @param pos holds [row][column] position
     * on the board
     * @param p player token character to check for win
     * @return true if player has getNumToWin() tokens in a row diagonally,
     * false otherwise
     * @pre [p is a player char] and [pos is a valid position]
     * pos.getRow < getNumRows() && pos.getRow > -1 and
     * pos.getCol < getNumColumns && pos.getCol > -1
     *
     * @post true iff [ there are getNumToWin() consecutive
     * of p diagonally on the board]
     *
     */
    default boolean checkDiagWin(BoardPosition pos, char p){
        int row = pos.getRow();
        int col = pos.getColumn();
        boolean pass = true;

        int i, q;

        int rightmost = Math.min(getNumColumns() -1, col + (getNumToWin()-1));
        int topmost = Math.min(getNumRows()-1, row + (getNumToWin()-1));
        int bottommost = Math.max(0, row - (getNumToWin()-1));
        int leftmost =Math.max(0, col - (getNumToWin()-1));

        int inc = bottommost;
        for(i = leftmost; i + getNumToWin() - 1 <= rightmost && inc + getNumToWin() - 1 <= topmost; i++){
            for(q = 0; q < getNumToWin(); q++){
                BoardPosition pos2 = new BoardPosition(inc + q, i + q);
                if(whatsAtPos(pos2) != p) {
                    pass = false;
                }
            }

            if(pass){
                return true;
            }

            inc++;
        }

        inc = topmost;
        q = 0;
        i = 0;

        for(i = leftmost; i + getNumToWin() - 1 <= rightmost && inc - (getNumToWin() - 1) >= 0; i++){

            for(q = 0; q < getNumToWin(); q++) {
                BoardPosition pos2 = new BoardPosition(inc - q, i + q);
                if (whatsAtPos(pos2) != p) {
                    pass = false;
                }
            }
            if(pass){
                return true;
            }

            pass = true;
            inc--;




        }

        return false;
    };

    /**
     * returns if a char si in given position on board,
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
    public char whatsAtPos(BoardPosition pos);

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
    public boolean isPlayerAtPos(BoardPosition pos, char player);

    /**
     * overrides default toString to return a formatted current game board
     * @return a formatted game board
     * @post [returns string representation of game board in current state]
     *
     */
    public String toString();

    /**
     * returns true if the game board results in a tie game
     * @return return true iff
     * [the board is full]
     * @pre
     * [no previous move resulted in a win]
     * @post checkTie() == true iff
     * [the board is full]
     *
     */
    default boolean checkTie(){
        for(int i = 0; i < getNumColumns(); i++ ){
            BoardPosition pos2 = new BoardPosition(getNumRows()-1, i);
            if(whatsAtPos(pos2) == ' '){
                return false;
            }
        }

        return true;
    };



    /**
     * @pre [number of rows] <= MAX_SIZE && [number of rows] >= MIN_SIZE;
     * @post getNumRows() = [number of rows]
     * @return [number of rows]
     */
    public int getNumRows();

    /**
     * @pre [number of columns] <= MAX_SIZE && [number of columns] >= MIN_SIZE
     * @post getNumColumns() = [number of columns]
     * @return [number of columns]
     */
    public int getNumColumns();

    /**
     * @pre [number in a row to win] <= MAX_WIN_NUM && [number in a row to win] >= MIN_SIZE
     * @post getNumToWin() = [number in a row to win]
     * @return [number in a row to win]
     */
    public int getNumToWin();
}
