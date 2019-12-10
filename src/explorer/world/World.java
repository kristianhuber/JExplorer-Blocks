package explorer.world;

import java.util.ArrayList;

import explorer.world.block.Block;
import explorer.world.entity.Entity;
import explorer.world.entity.Player;

/**
 * @Author: Me
 * @Description: Holds all of the Variables for Blocks and Entities
 * 
 */

public class World {
	public static final int WIDTH = 29, LENGTH = 17, HEIGHT = 3;

	private ArrayList<Entity> entity;
	private Block[][][] block;
	
	public Player player;

	/**
	 * Constructor Method
	 * 
	 */
	public World() {

		//Initializes the Variables
		player = new Player();
		
		block = new Block[WIDTH + 1][HEIGHT + 1][LENGTH + 1];
		entity = new ArrayList<Entity>();
		
		Room.generateRoom(block, Room.CELT, 0, 0);
	}

	/**
	 * Updates the World and its objects
	 * 
	 */
	public void tick() {
		
		//Updates the Player
		player.tick();
		
		//Displays all of the Blocks
		for (int i = 0; i <= WIDTH; i++) {

			for (int j = 0; j <= HEIGHT; j++) {

				for (int k = 0; k <= LENGTH; k++) {

					if (block[i][j][k] != null) {

						block[i][j][k].tick(this);
					}
				}
			}
		}
		
		//Displays all of the Entities
		for(int i = 0; i < entity.size(); i++){
			
			entity.get(i).tick();
		}
	}

	/**
	 * Returns Boolean for a Block at a Given Position
	 * 
	 */
	public boolean isBlockAt(int x, int y, int z) {

		//Decides if There is a Block at the Position
		if (block[x][y][z] == null) {

			return false;
		}

		//There is a Block Here
		return true;
	}
}
