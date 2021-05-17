package cpsc2150.connectX;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


/**
 * @invariant MIN_SIZE <= width <=MAX_SIZE
 * @invariant MIN_SIZE <= height <= MAX_SIZE
 * @invariant MIN_SIZE < win_num < MAX_WIN_NUM
 *
 *
 * correspondence [number of rows] = height;
 * correspondence [number of columns] = column;
 * correspondence [in a row to win] = win_num;
 * correspondence this = Map<Character, List<BoardPosition>> gameMap = new HashMap<>();
 * */
public class GameBoardMem extends AbsGameBoard {

    // private variables for this class
    private int row;
    private int col;
    private int win_num;
    private Map<Character, List<BoardPosition>> gameMap;


    /*
    *
    * @post [new GameBoard is initialized] and
    * [GameBoard is initially filled with empty spaces]
    * @invariant height > 0 && width > 0;
     */


    public GameBoardMem(int r, int c, int winnum){
        row = r;
        col = c;
        win_num = winnum;
        gameMap = new HashMap<Character, List<BoardPosition>>();

;

    }

    /**
     * places token in lowest available row
     * @param p player token character
     * @param c column number to place p in
     * @return n/a
     * @pre c >= 0 and [p is a valid player char]
     * checkIfFree(c) == true and c < getNumColumns()
     * @post [using key p, position of token is stored in the
     * gameBoard map]
     *
     *
     */
    public void placeToken(char p, int c) {

        int i;
        gameMap.putIfAbsent(p, new ArrayList<>());
        BoardPosition place = new BoardPosition(0, c);
        for(i= 0; whatsAtPos(place) != ' ' ; i++) {

            place = new BoardPosition(i, c);

        }

            gameMap.get(p).add(place);

    }

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
    public char whatsAtPos(BoardPosition pos) {
        for(Map.Entry<Character, List<BoardPosition>> c : gameMap.entrySet()){
            if(c.getValue().contains(pos)) {
                return c.getKey();
            }
        }

        return ' ';
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
    public boolean isPlayerAtPos(BoardPosition pos, char player) {

        if(!gameMap.containsKey(player)){
            return false;
        }

        for(Map.Entry<Character, List<BoardPosition>> c : gameMap.entrySet() ){

            if(c.getKey().equals(player) && c.getValue().contains(pos))
                return true;
        }

        return false;
    }


    public int getNumRows() {
        return row;
    }


    public int getNumColumns() {
        return col;
    }


    public int getNumToWin() {
        return win_num;
    }
}
