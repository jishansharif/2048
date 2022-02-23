
/**
 * Author: Jishan Sharif
 * Revised: 12/04/2021
 *
 * Description: Testing Tile object here
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;

public class TestTileT
{
    private TileT tileA;
    private TileT tileB;
    private TileT tileC;

    @Before
    public void setUp()
    {
        tileA = new TileT(2);
        tileB = new TileT(0);
        tileC = new TileT(4);

    }

    @After
    public void tearDown(){
        tileA = null;
        tileB = null;
        tileC = null;
    }



    @Test
    public void testGetValue()
    {
        assertTrue(tileA.getValue() == 2);
        assertTrue(tileB.getValue() == 0);
        assertTrue(tileC.getValue() == 4);
    }

    @Test
    public void testSetValue()
    {
        tileA.setValue(tileB.getValue());
        tileB.setValue(tileC.getValue());
        assertTrue(tileA.getValue() == 0);
        assertTrue(tileB.getValue() == 4);
        assertTrue(tileC.getValue() == 4);
    }

}