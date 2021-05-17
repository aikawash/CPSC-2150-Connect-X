package cpsc2150.connectX;

/**
 * Created by aikaw on 2/24/20.
 */

/**
 * Initialization ensures that an instance of GameBoard will be created.
 * It also ensures that the connectBoard's dimensions are
 * COLNUM x ROWNUM.
 * It also ensures that the functions of this interface will work
 * on an instance of GameBoard.
 */
public interface IGameBoard {
    public final static int COLNUM = 0;
    public final static int ROWNUM = 0;
    public final static int WIN_NUM = 0;

    char[][] connectBoard= new char[ROWNUM][COLNUM];

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
    public boolean checkIfFree(int c);



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
    public boolean checkForWin(int c);

    /**
     * places token in lowest available row
     * @param p player token character
     * @param c column number
     * @return n/a
     * @pre c >= 0 and [p is a char]
     * @post [token successfully placed in lowest available row]
     *
     */
    public void placeToken(char p, int c);

    /**
     * checks for a horizontal win
     * @param BoardPosition holds [row][column] position
     * on the board
     * @param p player token character
     * @return true if player has 4 tokens in a row horizontally,
     * false otherwise
     * @pre [p is a char] ana [BoardPosition is a valid position]
     * @post [true results in a player win, false not a win]
     *
     */
    public boolean checkHorizWin(BoardPosition pos, char p);

    /**
     * checks for vertical win
     * @param BoardPosition holds [row][column] position
     * on the board
     * @param p player token character
     * @return true if player has 4 tokens in a row vertically,
     * false otherwise
     * @pre [p is a char] ana [BoardPosition is a valid position]
     * @post [true results in a player win, false not a win]
     *
     */
    public boolean checkVertWin(BoardPosition pos, char p);

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
    public boolean checkDiagWin(BoardPosition pos, char p);

    /**
     * returns if a char si in given position on board,
     * otherwise returns " " (blank space)
     * @param BoardPosition holds [row][column] position
     * on the board
     * @return char in given position, else " " if char does not match
     * @pre [BoardPosition is a valid position]
     * @post [char in the position is known]
     *
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * returns true is player token in a specific position on the board
     * @param BoardPosition holds [row][column] position
     * on the board
     * @param player char of player token
     * @return true if player char is at given BoardPosition
     * @pre [BoardPosition is a valid position] and [player is a char]
     * @post [true if given char and given position match]
     *
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player);

    /**
     * overrides default toString to return a formatted current game board
     * @return a formatted game board
     * @post returns game board in current state
     *
     */
    public String toString();

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
    public boolean checkTie();



    /**
     *
     * @return getNumRows() = ROWNUM;
     */
    public int getNumRows();

    /**
     *
     * @return getNumColumns() = COLNUM;
     */
    public int getNumColumns();

    /**
     *
     * @return getNumToWin() = WIN_NUM;
     */
    public int getNumToWin();
}
