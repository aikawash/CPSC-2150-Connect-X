package cpsc2150.connectX;

/**
 * @invariant 0 <= column < MAX_SIZE
 * @invariant 0 <= row < MAX_SIZE
 */
public class BoardPosition {

    private final int row;
    private final int column;

    /**
     * Constructor that creates BoardPosition to hold row
     * and column positions
     * @param r holds column number
     * @param c holds row number
     *
     * @post row = r and column = c
     */
    public BoardPosition(int r, int c){
        row = r;
        column = c;

    }

    /**
     * returns the row position
     * @return number of the row
     *
     */
    public int getRow(){
        return row;
    }

    /**
     * returns the column position
     * @return number of the column
     *
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
    public boolean equals(Object pos){
        if(pos == this)
            return true;

        if(pos instanceof BoardPosition ){
            BoardPosition pos3 = (BoardPosition) pos;

            if(column == pos3.column && row == pos3.row){
                return true;
            }
        }

        return false;
    }

    /**
     * outputs a formatted BoardPosition
     * @param row holds the row variable
     * @param col holds the column variable
     * @return a formatted gameboard
     */
    public String toString(int row, int col){
        String format = row + ", " + col;
        return format;
    }

}
