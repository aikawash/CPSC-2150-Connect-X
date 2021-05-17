package cpsc2150.connectX;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by aikaw on 4/9/20.
 */
public class TestGameBoardMem {

    // GameBoard constructor for test functions
    private IGameBoard CreateGameBoard(){

        IGameBoard testBoard = new GameBoardMem(5,5,3);
        return testBoard;
    }


    private IGameBoard CreateGameBoard_different_size(int row, int col, int numToWin){
        IGameBoard testBoard = new GameBoardMem(row,col, numToWin);
        return testBoard;
    }

    // private helper method to create expected version of GameBoard to compare
    private String compareString(char [][] board, int row, int col){
        String compare = "";

        for(int i = 0; i < col; i++)
        {
            if(i > 9)
                compare += "|" + i;
            else compare+= "| " + i;
        }
        compare += "|\n";

        for(int i = row-1; i > -1; i--) {
            compare += "|";

            for(int j = 0; j < col; j++)
                compare += " " + board[i][j] + "|";
            compare+= "\n";
        }
        return compare;
    }


    // minimum board size
    @Test
    public void test_constructor(){
        IGameBoard gb = CreateGameBoard();
        char [][] test_arr = new char[5][5];
        for(int i =0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                test_arr[i][j] = ' ';
        assertEquals(gb.toString(), compareString(test_arr,5,5));
    }

    // Tests if constructor works on the minimum possible_value
    @Test
    public void constructor_min_size(){
        IGameBoard gb = CreateGameBoard_different_size(3,3,3);
        char [][] test_arr = new char[3][3];
        for(int i =0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                test_arr[i][j] = ' ';
        assertEquals(gb.toString(), compareString(test_arr,3,3));
    }

    // Tests if constructor works on the maximum possible value
    @Test
    public void constructor_max_size(){
        IGameBoard gb = CreateGameBoard_different_size(100,100,25);
        char [][] test_arr = new char[100][100];
        for(int i =0; i < 100; i++)
            for(int j = 0; j < 100; j++)
                test_arr[i][j] = ' ';
        assertEquals(gb.toString(), compareString(test_arr,100,100));
    }

    // Tests if checkIfFree works when the column is full
    @Test
    public void checkIfFree_column_full(){
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 5; i++)
            board.placeToken('X', 0);
        assertTrue(!board.checkIfFree(0));
    }

    // Tests if checkIfFree works when the column is empty
    @Test
    public void checkIfFree_column_empty(){
        IGameBoard board = CreateGameBoard();
        assertTrue(board.checkIfFree(0));
    }

    // Tests if checkIfFree works when the column is partially full
    @Test
    public void checkIfFree_column_not_full(){
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 4; i++)
            board.placeToken('X', 0);
        assertTrue(board.checkIfFree(0));
    }

    // Tests checkHorizWin at beginning of a horizontal
    @Test
    public void checkHorizWin_row0_beginning(){
        IGameBoard board = CreateGameBoard();
        BoardPosition pos = new BoardPosition (0,0);
        for (int i = 0; i < 3; i++)
            board.placeToken('X', i);
        assertTrue(board.checkHorizWin(pos, 'X'));
    }


    // Tests checkHorizWin at beginning of a row
    @Test
    public void checkHorizWin_beginnining_of_row(){
        BoardPosition pos = new BoardPosition (0,0);
        IGameBoard board = CreateGameBoard();
        for (int i = 0; i < 5; i++) {
            board.placeToken('X', i);
        }
        assertTrue(board.checkHorizWin(pos, 'X'));
    }

    // Tests checkHorizWin on horizontal that doesnt exist from beginning of row
    @Test
    public void checkHorizWin_row0_false_beginning(){
        BoardPosition pos = new BoardPosition (0,0);
        IGameBoard board = CreateGameBoard();
        for (int i = 0; i < 2; i++)
            board.placeToken('X', i);
        for (int i = 2; i < 4; i++)
            board.placeToken('O', i);
        assertTrue(!board.checkHorizWin(pos, 'X'));
    }

    // Tests checkHorizWin on horizontal that doesnt exist from end of row
    @Test
    public void checkHorizWin_row0_false_end(){
        BoardPosition pos = new BoardPosition (0,4);
        IGameBoard board = CreateGameBoard();
        for (int i = 1; i < 3; i++)
            board.placeToken('X', i);
        for (int i = 3; i < 5; i++)
            board.placeToken('O', i);
        assertTrue(!board.checkHorizWin(pos, 'O'));
    }

    // Tests a checkVertWin for row 0 from the top, with none below
    @Test
    public void checkVertWin_from_top_none_below(){
        BoardPosition pos = new BoardPosition (2,0);
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 3; i++)
            board.placeToken('X', 0);
        assertTrue(board.checkVertWin( pos, 'X'));
    }


    // Tests a checkVertWin for row 0 from empty spot above top spot,
    // with spots below
    @Test
    public void checkVertWin_false_with_empty_spots_below(){
        BoardPosition pos = new BoardPosition (4,0);
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 2; i++)
            board.placeToken('O', 0);
        for(int i = 2; i < 4; i++)
            board.placeToken('X', 0);
        assertTrue(!board.checkVertWin( pos, 'X'));

    }

    // Tests a checkVertWin for row 0 from top spot, with spots below
    @Test
    public void checkVertWin_false_top_with_spots_below(){
        BoardPosition pos = new BoardPosition (3,0);
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 2; i++)
            board.placeToken('O', 0);
        for(int i = 2; i < 4; i++)
            board.placeToken('X', 0);
        assertTrue(!board.checkVertWin( pos, 'X'));
    }

    // Tests checkVertWin for row 0 from the top, with none below
    // checkVertWin should not  check non existing spots which would cause a null ptr exception
    @Test
    public void checkVertWin_false_with_empty_board(){
        BoardPosition pos = new BoardPosition (0,0);
        IGameBoard board = CreateGameBoard();
        assertTrue(!board.checkVertWin( pos, 'X'));
    }

    // Tests checkDiagWin for row 0 from the top, with none below
    // checkVertWin should not  check non existing spots which would cause a null ptr exception
    @Test
    public void checkDiagWin_false_with_empty_board(){
        BoardPosition pos = new BoardPosition (0,0);
        IGameBoard board = CreateGameBoard();
        assertTrue(!board.checkDiagWin( pos, 'X'));
    }

    // Tests diagonal bottom left to top right
    @Test
    public void checkDiagWin_bottom_left_to_top_right(){
        BoardPosition pos = new BoardPosition (2,2);
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 3; i++)
            board.placeToken('X', i);
        for(int i = 1; i < 3; i++)
            board.placeToken('X', i);
        board.placeToken('X', 2);
        assertTrue(board.checkDiagWin(pos,'X'));
    }

    // Tests diagonal bottom right to top left
    @Test
    public void checkDiagWin_bottom_right_to_top_left(){
        BoardPosition pos = new BoardPosition (0,0);
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 3; i++)
            board.placeToken('X', i);
        for(int i = 0; i < 2; i++)
            board.placeToken('X', i);
        board.placeToken('X', 0);
        assertTrue(board.checkDiagWin(pos,'X'));
    }

    // Tests diagonal bottom left to top right with spots filled under it
    @Test
    public void checkDiagWin_bottom_left_to_top_right_filled_under(){
        BoardPosition pos = new BoardPosition (4,4);
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 5; i++){
            board.placeToken('O', i);
            board.placeToken('O', i);
        }
        for(int i = 2; i < 5; i++)
            board.placeToken('X', i);
        for(int i = 3; i < 5; i++)
            board.placeToken('X', i);
        board.placeToken('X', 4);
        assertTrue(board.checkDiagWin(pos,'X'));
    }


    // makes sure the bottom left to top right check doesn't work on not enough chars
    @Test
    public void checkDiagWin_false_bottom_left_to_top_right_insufficient_chars(){
        BoardPosition pos = new BoardPosition (0,0);
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 2; i++) {
            board.placeToken('X', i);
        }
        board.placeToken('X', 1);
        assertFalse(board.checkDiagWin(pos, 'X'));
    }

    // makes sure the bottom left to top right check doesn't work on not enough chars
    @Test
    public void checkDiagWin_false_top_left_to_bottom_right_insufficient_chars(){
        IGameBoard board = CreateGameBoard();
        for(int i = 1; i < 3; i++)
            board.placeToken('X', i);
        board.placeToken('X', 1);
        BoardPosition pos = new BoardPosition (1,1);
        assertTrue(!board.checkDiagWin(pos, 'X'));
    }

    // checkDiagWin should be false in the event of a full board (tie) without diagonals
    @Test
    public void checkDiagWin_false_full_tied_board()
    {
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++) {
                if (i%2 == 1)
                    board.placeToken('X', j);
                else
                    board.placeToken('O', j);
            }
        BoardPosition pos = new BoardPosition (2,2);
        assertTrue(!board.checkDiagWin(pos, 'O'));
    }

    //  checkTie should be TRUE for afull board
    @Test
    public void checkTie_true_full_board(){
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                board.placeToken('X', j);
        assertTrue(board.checkTie());
    }

    // checkTie should be false for an empty board
    @Test
    public void checkTie_false_empty_board(){
        IGameBoard board = CreateGameBoard();
        assertTrue(!board.checkTie());
    }

    // check checkTie for a board that has some columns full
    @Test
    public void checkTie_some_full_columns(){
        IGameBoard board = CreateGameBoard();
        for(int j = 0; j < 3; j++)
            for(int i = 0; i < 5; i++)
                board.placeToken('X', j);
        assertTrue(!board.checkTie());
    }

    // test checkTie for a full board of alternating chars
    @Test
    public void checkTie_full_alternating_board()
    {
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++) {
                if (i%2 == 1)
                    board.placeToken('X', j);
                else
                    board.placeToken('O', j);
            }
        assertTrue(board.checkTie());
    }

    // tests WhatsAtPos on an empty board for an empty space
    @Test
    public void WhatsAtPos_empty_space_empty_board(){
        IGameBoard board = CreateGameBoard();
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals(' ', board.whatsAtPos(pos));
    }

    // tests WhatsAtPos on board with one row full on an empty space
    @Test
    public void WhatsAtPos_one_full_row_empty_space(){
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 5; i++)
            board.placeToken('X', i);
        BoardPosition pos = new BoardPosition(1,0);
        assertEquals(' ', board.whatsAtPos(pos));
    }

    // tests WhatsAtPos on an empty space on an almost full board
    @Test
    public void WhatsAtPos_almost_full_board_empty_space(){
        IGameBoard board = CreateGameBoard();
        for(int j = 0; j < 4; j++)
            for(int i = 0; i < 5; i++)
                board.placeToken('X', i);
        for(int i = 0; i < 4; i++)
            board.placeToken('X', i);
        BoardPosition pos = new BoardPosition(4,4);
        assertEquals(' ', board.whatsAtPos(pos));
    }

    // Tests WhatsAtPos on the only spot on the board with a character
    @Test
    public void WhatsAtPos_one_char_on_board(){
        BoardPosition pos = new BoardPosition(0,3);
        IGameBoard board = CreateGameBoard();
        board.placeToken('X', 3);
        assertEquals('X', board.whatsAtPos(pos));
    }


    // Tests WhatsAtPos on empty spot surrounded by chars
    @Test
    public void WhatsAtPos_empty_spot_surrounded_by_chars(){
        IGameBoard board = CreateGameBoard();
        board.placeToken('O', 0);
        board.placeToken('X', 0);
        board.placeToken('O', 0);
        BoardPosition pos = new BoardPosition(1,0);
        assertEquals('X', board.whatsAtPos(pos));
    }

    // test isPlayerAtPos should be false on an empty spot on an empty  board
    @Test
    public void isPlayerAtPos_false_empty_spot_empty_board() {
        IGameBoard board = CreateGameBoard();
        BoardPosition pos = new BoardPosition(0,0);
        assertFalse(board.isPlayerAtPos(pos, 'X'));

    }

    // test isPlayerAtPos on a board with only one char on the board
    @Test
    public void isPlayerAtPos_one_char_on_board() {
        IGameBoard board = CreateGameBoard();
        board.placeToken('O',4);
        BoardPosition pos = new BoardPosition(0,4);
        assertTrue( board.isPlayerAtPos(pos, 'O'));

    }

    // test isPlayerAtPos on a board with only one full row
    @Test
    public void isPlayerAtPos_one_filled_row() {
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 5; i++)
            board.placeToken('X', i);
        BoardPosition pos = new BoardPosition(0,1);
        assertTrue( board.isPlayerAtPos(pos, 'X'));

    }


    // test isPlayerAtPos on a almost full board with one empty space
    @Test
    public void isPlayerAtPos_false_almost_full_board_empty_space() {
        IGameBoard board = CreateGameBoard();
        for(int j = 0; j < 4; j++)
            for(int i = 0; i < 5; i++)
                board.placeToken('X', i);
        for(int i = 0; i < 4; i++)
            board.placeToken('X', i);
        BoardPosition pos = new BoardPosition(4,4);
        assertFalse(board.isPlayerAtPos(pos, 'O'));

    }

    // test isPlayerAtPos on a almost full board with one empty space
    @Test
    public void isPlayerAtPos_full_board() {
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                board.placeToken('X', j);
        BoardPosition pos = new BoardPosition(4,4);
        assertTrue(board.isPlayerAtPos(pos,'X'));

    }




    // test placeToken on empty board
    @Test
    public void placeToken_on_empty_board(){
        IGameBoard board = CreateGameBoard();
        board.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals('X', board.whatsAtPos(pos));
    }

    // test placeToken on partly filled column
    @Test
    public void placeToken_in_partly_filled_column(){
        IGameBoard board = CreateGameBoard();
        board.placeToken('O', 0);
        board.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(1,0);
        assertEquals('X', board.whatsAtPos(pos));
    }

    // test placeToken to fill up a column completely
    @Test
    public void placeToken_fill_up_column(){
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 4; i++)
            board.placeToken('O', 0);
        board.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(4,0);
        assertEquals('X', board.whatsAtPos(pos));
    }

    // tests placeToken to fill up a row completely
    @Test
    public void placeToken_to_fill_up_row(){
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 4; i++)
            board.placeToken('O', i);
        board.placeToken('X', 4);
        BoardPosition pos = new BoardPosition(0,4);
        assertEquals('X', board.whatsAtPos(pos));
    }

    // tests placeToken to fill up a board completely
    @Test
    public void placeToken_to_fill_board(){
        IGameBoard board = CreateGameBoard();
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 4 && j == 4)
                    board.placeToken('X', 4);
                else
                    board.placeToken('O', j);
            }
        }
        BoardPosition pos = new BoardPosition(4,4);
        assertEquals('X', board.whatsAtPos(pos));
    }
}