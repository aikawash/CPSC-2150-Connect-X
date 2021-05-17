package cpsc2150.connectX;

/**
 * Created by aikaw on 3/21/20.
 */
public abstract class AbsGameBoard implements IGameBoard {
    /*
    * @return a string representation of the GameBoard
    * @requires that the current GameBoard != null
     */

    @Override

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


}
