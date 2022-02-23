/**
 * Author: Jishan Sharif
 * Revised: 12/04/2021
 *
 * Description: Tile Object
 */



package src;

public class TileT {
    int value = 0;
    /**
     * @brief A tile can contain a positive even value or it can contain a value of 0
     * A board is made up of 16 tiles.
     * @param val of type int is the initial value given to the Tile, we initially give it a 0
     */

    public TileT(int val) {
        this.value = val;
    }

    /**
     * @brief This game will deal with a lot of updating the board.
     * This means we will have to update the Tile values. This method does exactly that
     * @param val of type int is the new value you wish to update the tile with
     */

    public void setValue(int val){
        this.value = val;
    }

    /**
     * @brief We would want to know how our tile looks like and what values each tile contains.
     * @return a value of type int representing the value of a specific tile.
     */
    public int getValue(){
        return this.value;
    }

    /**
     * @brief Print the values in a readable form
     * @return a string representing the value of a given tile
     */
    public String toString(){
        return Integer.toString(this.value);
    }

}
