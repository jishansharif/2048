/**
 * Author: Jishan Sharif
 * Revised: 12/04/2021
 *
 * Description: Board Model
 */

package src;
import java.util.Random;

public class BoardT{
    int size = 4;
    int score = 0;
    TileT[][] board = new TileT[size][size];
    /**
     * @brief We initialize the board where each tile is of type TileT
     * we initialize a board of size 4 * 4. This is done by creating a 2-D array.
     * For each row and column, we first initialize each tile to 0. We then insert two
     * random values anywhere across the board.
     */

    public BoardT(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                board[i][j] = new TileT(0);
            }

        }
        // Now the array contains TileT elements in a 2-D array with values set to 0
        // We now need to randomly insert two values which is either 2 or 4 into the array.
        // We generate random row and column values
        // We generate random indexes to choose values 2 or 4
        generateValues(board);
    }

    /**
     * @brief This method gets called when we wish to view the score of the user
     * @return a value of type int representing the score of the user.
     */

    public int getScore(){
        return this.score;
    }

    /**
     * @brief This method gets called when we wish to view the board at the moment
     * @return a board which is a 2-D matrix of type TileT representing the board at the current moment.
     */

    public TileT[][] getBoard(){
        return this.board;
    }

    public void setBoard(TileT[][] board){
        this.board = board;
    }


    /**
     * @brief Simulating the event where we move the tiles right
     * @detail We need to move each non empty tile to the right.
     *         Iterate through each row, if you see a non empty tile, shift it to the
     *         end of that row. For example if a value is in 0,0 move it to 0,3, if 0,3 is taken,
     *         move it to 0,2,if 0,2 is taken, move it to 0,1. if All of them are taken. Don't move tile,
     *         Go to the next row.
     */

    public void moveRight(){
        int moves = 0;
        for (int i = 0; i < size; i++){
            int emptyPos = findEmptyRightPosition(i);
            for (int j = size - 1; j >= 0; j--){
                if (board[i][j].getValue() != 0 && emptyPos != -1 && j < emptyPos){
                    board[i][emptyPos].setValue(board[i][j].getValue());
                    board[i][j].setValue(0);
                    emptyPos = findEmptyRightPosition(i);
                    moves += 1;
                }
                canMergeRight(i);
            }
        }
        isValid(moves);
    }

    /**
     * @brief We want the user to know if their move is valid. If it is, we
     * insert a new value into the list, else, we send a message back to the user.
     * @param  moves of type int representing the no of moves done in a single moveFunction
     */

    private void isValid(int moves) {
        if (moves == 0){
            System.out.println("No moves possible! Try another command");
        } else {
            insertRandom(board);
        }
    }

    /**
     * @brief This checks if there are any more moves possible in the game
     * @detail The first thing we check here is if the board is completely filled.
     * If the board has a value 0, we return false, if the board is completely filled, we
     * check if any merges can be done.
     * @param board which is a 2-D list of type TileT representing the board
     * @return a boolean to determine whether there are any moves left, hence, if the game
     * is over
     */


    public boolean noMoreMoves(TileT[][] board) {
        boolean answer = true;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (board[i][j].getValue() == 0){
                    answer = false;
                }
            }
        }
        if (answer){
            if (!canMergeTiles(this.board)){
                answer = false;
            } else {
                answer = true;
            }
        }
        return answer;
    }

    /**
     * @brief This checks if there are any more moves possible in the game
     * @detail We check if there are any more tiles that can be merged. This has to be
     * done manually considering every row and every column and seeing if there are adjacent duplicate values.
     * @param board which is a 2-D list of type TileT representing the board
     * @return a boolean to determine whether there are any moves left, hence, if the game
     * is over
     */


    private boolean canMergeTiles(TileT[][] board) {
        int count = 0;
        // We need to see if there are any tiles which can be merged.
        for ( int i = 0; i < size; i++ )
        {
            for ( int j = 0; j < size; j++ )
            {
                // We only consider when the tiles are not empty
                if ( i == 0 && j == 0 )
                {
                    // Check if two 'up' values are the same.
                    if ( board[i][j].getValue() != board[i + 1][j].getValue()
                            && board[i][j].getValue() != board[i][j + 1].getValue() )
                    {
                        count++;
                    }
                }

                else if ( i == 3 && j == 3 )
                {
                    // Check if two down values are the same.
                    if ( board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i][j - 1].getValue() )
                    {
                        count++;
                    }
                }
                else if ( i == 3 && j == 0 )
                {
                    if ( board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i][j + 1].getValue() )
                    {
                        count++;
                    }
                }
                else if ( i == 0 && j == 3 )
                {
                    if ( board[i][j].getValue() != board[i + 1][j].getValue()
                            && board[i][j].getValue() != board[i][j - 1].getValue() )
                    {
                        count++;
                    }
                }
                else if ( i == 3 && ( j == 1 || j == 2 ) )
                {
                    if ( board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i][j + 1].getValue()
                            && board[i][j].getValue() != board[i][j - 1].getValue() )
                    {
                        count++;
                    }
                }
                else if ( i == 0 && ( j == 1 || j == 2 ) )
                {
                    if ( board[i][j].getValue() != board[i + 1][j].getValue()
                            && board[i][j].getValue() != board[i][j + 1].getValue()
                            && board[i][j].getValue() != board[i][j - 1].getValue() )
                    {
                        count++;
                    }
                }
                else if ( j == 3 && ( i == 1 || i == 2 ) )
                {
                    if ( board[i][j].getValue() != board[i][j - 1].getValue()
                            && board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i + 1][j].getValue() )
                    {
                        count++;
                    }
                }
                else if ( j == 0 && ( i == 1 || i == 2 ) )
                {
                    if ( board[i][j].getValue() != board[i][j + 1].getValue()
                            && board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i + 1][j].getValue() )
                    {
                        count++;
                    }
                }
                else
                {
                    if ( board[i][j].getValue() != board[i][j - 1].getValue()
                            && board[i][j].getValue() != board[i][j + 1].getValue()
                            && board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i + 1][j].getValue() )
                    {
                        count++;
                    }
                }
            }
        }
        // If count is 16, we know we have filled tiles which can no longer be merged.
        if ( count == 16 )
        {
            return true;
        }
        return false;
    }

    /**
     * @brief Simulating the event where we move the tiles left
     * @detail We need to move each non empty tile to the left.
     *         Iterate through each row, if you see a non empty tile, shift it to the
     *         beginning of that row. For example if a value is in 0,3 move it to 0,0, if 0,0 is taken,
     *         move it to 0,1,if 0,1 is taken, move it to 0,2. if All of them are taken. Don't move tile,
     *         Go to the next row.
     */

    public void moveLeft(){
        int moves = 0;
        for (int i = 0; i < size; i++){
            int emptyPos = findEmptyLeftPosition(i);
            for (int j = 0; j < size; j++){
                if (board[i][j].getValue() != 0 && emptyPos != -1 && j > emptyPos){
                    board[i][emptyPos].setValue(board[i][j].getValue());
                    board[i][j].setValue(0);
                    emptyPos = findEmptyLeftPosition(i);
                    moves += 1;
                }
            canMergeLeft(i);
            }

        }
        isValid(moves);
    }

    /**
     * @brief This method converts a board into a transposed board
     * @detail A transposed board is a board where the rows and columns are swapped.
     * Mathematically we know that transposing a board, moving left, and transposing again is the same
     * as moving up. The same applies with moving down but we simulate moving right.
     */
    public void Transpose(){

        BoardT temp_board = new BoardT();
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                temp_board.getBoard()[j][i] = this.board[i][j];
            }
        }

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                this.board[i][j] = (temp_board.getBoard()[i][j]);
            }
        }

    }

    /**
     * @brief Simulating the event where we move the tiles up
     * @detail We need to move each non empty tile upwards
     *         Iterate through each row, if you see a non empty tile, shift it
     *         to the top of the column. For example if you see a tile in 0,0. Don't do anything
     *         If you see a tile in 0,3, move it to 0,0. If 0,0 is taken, move it to 0,1. If 0,1 is taken
     *         move it to 0,2
     */


    public void moveUp(){
        this.Transpose();
        this.moveLeft();
        this.Transpose();
    }

    /**
     * @brief Simulating the event where we move the tiles down
     * @detail We need to move each non empty tile downwards
     *         Iterate through each row, if you see a non empty tile, shift it
     *         to the bottom of the column. For example if you see a tile in 0,0. Move it to 3,0
     *         If you see a tile in 0,3, Don't do anything. If 0,3 is taken, move it to 0,2. If 0,2 is taken
     *         move it to 0,1
     */

    public void moveDown(){
        this.Transpose();
        this.moveRight();
        this.Transpose();
    }

    /**
     * @brief For a given row we find an empty position in that row
     * @detail There may be two empty positions, we are looking for the right most
     * empty position
     * @param i of type int representing the index of the row
     * @return an int representing the index of the empty position
     */

    private int findEmptyRightPosition(int i) {
        TileT[] row = this.board[i];
        for (int j = size - 1; j >= 0; j--){
            if (row[j].getValue() == 0){
                return j;
            }
        }
        return -1;
    }

    /**
     * @brief For a given row we find an empty position in that row
     * @detail There may be two empty positions, we are looking for the right most
     * empty position
     * @param i of type int representing the index of the row
     * @return an int representing the index of the empty position
     */

    private int findEmptyLeftPosition(int i) {
        TileT[] row = this.board[i];
        for (int j = 0; j < size - 1; j++){
            if (row[j].getValue() == 0){
                return j;
            }
        }
        return -1;
    }

    /**
     * @brief For a given row we want to determine whether a merge can be done
     * @detail We want to add two values to the right most tile, and set the previous tile value to 0
     * @param i of type int representing the index of the row
     */

    private void canMergeRight(int i) {
        TileT[] row = this.board[i];
        for (int j = 0; j < (size - 1); j ++){
            if (row[j].getValue() !=0 && row[j].getValue() == row[j+1].getValue()){
                this.score += row[j].getValue() + row[j].getValue();
                row[j+1].setValue(row[j].getValue() + row[j].getValue());
                row[j].setValue(0);
            }

        }
    }

    /**
     * @brief For a given row we want to determine whether a merge can be done
     * @detail We want to add two values to the left most tile, and set the previous tile value to 0
     * @param i of type int representing the index of the row
     */
    private void canMergeLeft(int i) {
        TileT[] row = this.board[i];
        for (int j = 0; j < (size - 1); j ++){
            if (row[j].getValue() !=0 && row[j].getValue() == row[j+1].getValue()){
                this.score += row[j+1].getValue() + row[j+1].getValue();
                row[j].setValue(row[j+1].getValue() + row[j+1].getValue());
                row[j+1].setValue(0);
            }
        }
    }

    /**
     * @brief If a merge is successful and a move is considered valid, we add a new tile to the board
     * @param board which is a 2-D list of type TileT representing the board
     */

    private void insertRandom(TileT[][] board) {
        Random rand = new Random();
        int[] startValues = new int[]{2, 4};
        int rowIndex = rand.nextInt(size);
        int columnIndex = rand.nextInt(size);
        int possibleFirstIndex = rand.nextInt(2);
        if (board[rowIndex][columnIndex].getValue() != 0){
            insertRandom(board);
        }
        TileT firstVal = board[rowIndex][columnIndex];
        firstVal.setValue(startValues[possibleFirstIndex]);

    }

    /**
     * @brief We load the board with random values
     * @detail We now need to insert random values into the board. We do this
     * by making use of the Random library that Java has. We return two random values
     * less than four. These will represent column and row index . We insert only values
     * two and four into the board. This is done by creating a new list containing 2 and 4 and
     * randomly calling them. If we have the case where the row index and column index at both instances are
     * the same, we re run the function creating new random values.
     * @param board which is a 2D matrix of type TileT
     */

    private void generateValues(TileT[][] board) {
        Random rand = new Random();
        int[] startValues = new int[]{2, 4};
        int rowIndex = rand.nextInt(size);
        int columnIndex = rand.nextInt(size);
        int possibleFirstIndex = rand.nextInt(2);
        int possibleSecondIndex = rand.nextInt(2);
        int secondRowIndex = rand.nextInt(size);
        int secondColumnIndex = rand.nextInt(size);
        if (secondColumnIndex == columnIndex && rowIndex == secondRowIndex){
            generateValues(board);
        }
        TileT firstVal = board[rowIndex][columnIndex];
        TileT secondVal = board[secondRowIndex][secondColumnIndex];
        firstVal.setValue(startValues[possibleFirstIndex]);
        secondVal.setValue(startValues[possibleSecondIndex]);
    }
}