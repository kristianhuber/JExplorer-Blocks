package explorer.world;

import explorer.world.block.Block;

/**
 * @Author: Me
 * @Description: This Class Creates a Room for the World
 * 
 */

public class Room {
	public static final String VIKING = "norse";
	public static final String GREEK = "greek";
	public static final String EGYPT = "egypt";
	public static final String AZTEC = "aztec";
	public static final String CELT = "celt";

	/**
	 * This Methods Creates a Room Depending on the Type
	 * 
	 */
	public static void generateRoom(Block[][][] block, String type, int x, int y) {
		int width = 15, length = 27;
		
		block[x + 10][1][y + 10] = new Block("wall_" + type, x + 10, 1, y + 10, true);
		
		//Draws the Floor
		for (int i = x; i < x + length; i++) {

			for (int j = y; j < y + width; j++) {

				block[i][0][j] = new Block("ground_" + type, i, 0, j, true);
			}
		}

		//Draws the Walls for the X Axis
		for (int i = x; i < x + length; i++) {

			for (int j = 1; j < World.HEIGHT + 1; j++) {

				block[i][j][y] = new Block("wall_" + type, i, j, y, true);
				block[i][j][y + (width - 1)] = new Block("wall_" + type, i, j,
						y + (width - 1), true);
			}
		}

		//Draws the Walls for the Z Axis
		for (int i = y; i < y + width; i++) {

			for (int j = 1; j < World.HEIGHT + 1; j++) {

				block[x][j][i] = new Block("wall_" + type, x, j, i, true);
				block[x + length][j][i] = new Block("wall_" + type, x + length, j,
						i, true);
			}
		}
	}
}