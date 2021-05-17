package cpsc2150.connectX;

/**
 * @invariant 0 <= column < 7
 * @invariant 0 <= row < 6
 */
public class BoardPosition {

    private final int row;
    private final int column;

    /**
     * Constructor that creates BoardPosition to hold row
     * and column positions
     * @param r holds column number
     * @param c holds row number
     * @pre row = 0 and column = 0
     * @post row = r and column = c
     */
    public BoardPosition(int r, int c){
        row = r;
        column = c;

    }

    /**
     * returns the row position
     * @return number of the row
     * @pre
     * row >= 0
     *
     */
    public int getRow(){
        return row;
    }

    /**
     * returns the column position
     * @return number of the column
     * @pre
     * column >= 0
     *
     */
    public int getColumn(){
        return column;
    }

    /**
     * overrides default equals to return a boolean relaying
     * if two BoardPositions are equal (same row and column)
     * @param pos a BoardPosition with row and column variables
     * @param pos2 a BoardPosition with row and column variables
     * @return true or false
     * @post [returns true boolean if two BoardPositions are equal,
     * and false otherwise]
     *
     */
    public boolean equals(BoardPosition pos, BoardPosition pos2){
        if(pos.getRow() == pos2.getRow() && pos.getColumn() == pos2.getColumn()){
            return true;
        }

        return false;
    }

    public String toString(int row, int col){
        String format = row + ", " + col;
        return format;
    }

}
