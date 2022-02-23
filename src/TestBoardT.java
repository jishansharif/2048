
/**
 * Author: Jishan Sharif
 * Revised: 12/04/2021
 *
 * Description: Testing Board model here
 */



package src;

import org.junit.*;
import static org.junit.Assert.*;

public class TestBoardT
{
    int size = 4;
    BoardT board = new BoardT();
    BoardT board1 = new BoardT();
    BoardT board2 = new BoardT();
    BoardT board3 = new BoardT();
    BoardT countBoard = new BoardT();
    BoardT countBoard2 = new BoardT();
    BoardT countBoard3 = new BoardT();
    TileT[][] newBoard = new TileT[size][size];
    TileT[][] newBoard1 = new TileT[size][size];
    TileT[][] newBoard2 = new TileT[size][size];
    TileT[][] newBoard3 = new TileT[size][size];

    @Before
    public void setUp()
    {
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                newBoard[i][j] = new TileT(0);
                newBoard1[i][j] = new TileT(0);
                newBoard2[i][j] = new TileT(0);
                newBoard3[i][j] = new TileT(0);
            }
        }
        board.setBoard(newBoard);
        board1.setBoard(newBoard1);
        board2.setBoard(newBoard2);
        board3.setBoard(newBoard3);
    }

    @After
    public void tearDown() {
        board = null;
        board1 = null;
        board2 = null;
        board3 = null;
        newBoard = null;
        newBoard1 = null;
        newBoard2 = null;
        newBoard3 = null;
        countBoard = null;
        countBoard2 = null;
        countBoard3 = null;
    }

    @Test
    public void testCount()
    {
        int count = 0;
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < countBoard.size; i ++){
            for (int j = 0; j < countBoard.size; j++){
                if (countBoard.board[i][j].getValue() != 0){
                    count += 1;
                }
            }
        }

        for (int i = 0; i < countBoard2.size; i ++){
            for (int j = 0; j < countBoard2.size; j++){
                if (countBoard2.board[i][j].getValue() != 0){
                    count1 += 1;
                }
            }
        }

        for (int i = 0; i < countBoard3.size; i ++){
            for (int j = 0; j < countBoard3.size; j++){
                if (countBoard3.board[i][j].getValue() != 0){
                    count2 += 1;
                }
            }
        }
        assertTrue((count1 == count2) && (count == 2));
        assertTrue(count1 == count);

    }

    @Test
    public void testGetBoard()
    {
        board.board[3][0].setValue(4);
        board.board[2][1].setValue(2);
        board1.board[2][3].setValue(69);
        assertTrue((board.getBoard()[3][0].getValue() == 4));
        assertTrue((board.getBoard()[2][1].getValue() == 2));
        assertTrue((board1.getBoard()[2][3].getValue() == 69));

    }

    @Test
    public void testMoveRight()
    {
        board.board[0][2].setValue(4);
        board.board[0][3].setValue(4);
        board.moveRight();
        assertTrue((board.getBoard()[0][3].getValue() == 8));
        board2.board[0][1].setValue(8);
        board2.moveRight();
        assertTrue((board2.getBoard()[0][3].getValue() == 8));
    }

    @Test
    public void testMoveLeft()
    {
        board.board[0][2].setValue(4);
        board.board[0][3].setValue(4);
        board.moveLeft();
        assertTrue((board.getBoard()[0][0].getValue() == 8));
        board2.board[0][1].setValue(8);
        board2.moveLeft();
        System.out.println(((board2.getBoard()[0][0].getValue() == 8)));
    }


    @Test
    public void testMoveUp()
    {
        board2.board[0][0].setValue(4);
        board2.board[1][0].setValue(4);
        board2.moveUp();
        assertTrue((board2.getBoard()[0][0].getValue() == 8));
        board3.board[0][0].setValue(8);
        board3.moveUp();
        assertTrue((board3.getBoard()[0][0].getValue() == 8));
    }


    @Test
    public void testMoveDown()
    {
        board.board[0][0].setValue(4);
        board.board[3][0].setValue(4);
        board.moveDown();
        board2.board[0][0].setValue(8);
        board2.moveDown();
        assertTrue((board2.getBoard()[3][0].getValue() == 8));
    }

    @Test
    public void testGameOver()
    {
       board3.board[0][0].setValue(2);
       board3.board[0][1].setValue(3);
       board3.board[0][2].setValue(4);
       board3.board[0][3].setValue(5);
       board3.board[1][0].setValue(6);
       board3.board[1][1].setValue(7);
       board3.board[1][2].setValue(8);
       board3.board[1][3].setValue(9);
       board3.board[2][0].setValue(10);
       board3.board[2][1].setValue(11);
       board3.board[2][2].setValue(12);
       board3.board[2][3].setValue(13);
       board3.board[3][0].setValue(14);
       board3.board[3][1].setValue(15);
       board3.board[3][2].setValue(16);
       board3.board[3][3].setValue(17);
       assertTrue(board3.noMoreMoves(board3.board));
    }
}
