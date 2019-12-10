package explorer.world.block;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import explorer.world.World;

/**
 * @Author: Me
 * @Description: The 'Building Blocks' of the World
 * 
 */

public class Block {
	private static final String path = "/explorer/assets/textures/";

	private Texture texture;

	private boolean isSolid;
	
	private int x, y, z;

	/**
	 * Constructor Method
	 * 
	 */
	public Block(String textureName, int x, int y, int z, boolean solid) {

		//Initializes the Texture
		try {

			texture = TextureLoader.getTexture("PNG", getClass()
					.getResourceAsStream(path + textureName + ".png"));
		} catch (Exception e) {

			//Loads a Backup if the Intended One Isn't There
			try {

				texture = TextureLoader.getTexture("PNG", getClass()
						.getResourceAsStream(path + "default" + ".png"));
			} catch (Exception e2) {

				e2.printStackTrace();
			}

			e.printStackTrace();
		}

		//Initializes the Variables
		this.x = x;
		this.y = y - 2;
		this.z = z;
		this.isSolid = solid;
	}

	/**
	 * Updates the Block
	 * 
	 */
	public void tick(World w) {

		render(w);
		
		if(isSolid){
			
			collision(w);
		}
	}
	
	/**
	 * Is the player in the box
	 * 
	 */
	
	public boolean collision(World w){
		
		float x = -w.player.x;
		float y = -w.player.y;
		float z = -w.player.z;
		
		//Collision Stuff
		if(x > this.x - 0.4 && x < this.x + 1.4){
			
			if(y > this.y + 0.8 && y < this.y + 3.4){
				
				if(z > this.z - 0.4 && z < this.z + 1.4){
					
					w.player.x = (w.player.prevX);
					w.player.y = (w.player.prevY);
					w.player.z = (w.player.prevZ);
					
					return true;
				}else{
					
					return false;
				}
			}else{
				
				return false;
			}
		}
		
		return false;
	}

	/**
	 * Draws the block in the world
	 * 
	 */
	public void render(World w) {

		glPushMatrix();
		{
			//Moves the Blocks to its Position
			glTranslatef(this.x, this.y, this.z);

			texture.bind();

			glBegin(GL_QUADS);
			{

				// Draw Bottom

				if (y > -2) {

					if (!w.isBlockAt(x, y + 1, z)) {

						glTexCoord2f(0, 0);
						glVertex3f(0, 0, 0);
						glTexCoord2f(0, 1);
						glVertex3f(1, 0, 0);
						glTexCoord2f(1, 1);
						glVertex3f(1, 0, 1);
						glTexCoord2f(1, 0);
						glVertex3f(0, 0, 1);
					}
				}

				// Draw Top

				if (y < 1) {

					if (!w.isBlockAt(x, y + 3, z)) {

						glTexCoord2f(0, 0);
						glVertex3f(0, 1, 0);
						glTexCoord2f(0, 1);
						glVertex3f(1, 1, 0);
						glTexCoord2f(1, 1);
						glVertex3f(1, 1, 1);
						glTexCoord2f(1, 0);
						glVertex3f(0, 1, 1);
					}
				}

				// DrawRight

				if (!w.isBlockAt(x, y + 2, z + 1)) {

					glTexCoord2f(0, 1);
					glVertex3f(0, 0, 1);
					glTexCoord2f(1, 1);
					glVertex3f(1, 0, 1);
					glTexCoord2f(1, 0);
					glVertex3f(1, 1, 1);
					glTexCoord2f(0, 0);
					glVertex3f(0, 1, 1);
				}

				// DrawLeft
				if (z > 0) {

					if (!w.isBlockAt(x, y + 2, z - 1)) {

						glTexCoord2f(0, 1);
						glVertex3f(0, 0, 0);
						glTexCoord2f(1, 1);
						glVertex3f(1, 0, 0);
						glTexCoord2f(1, 0);
						glVertex3f(1, 1, 0);
						glTexCoord2f(0, 0);
						glVertex3f(0, 1, 0);

					}
				}

				// DrawBack

				if (!w.isBlockAt(x + 1, y + 2, z)) {

					glTexCoord2f(1, 1);
					glVertex3f(1, 0, 0);
					glTexCoord2f(1, 0);
					glVertex3f(1, 1, 0);
					glTexCoord2f(0, 0);
					glVertex3f(1, 1, 1);
					glTexCoord2f(0, 1);
					glVertex3f(1, 0, 1);
				}

				// DrawFront
				if (x > 0) {

					if (!w.isBlockAt(x - 1, y + 2, z)) {

						glTexCoord2f(1, 1);
						glVertex3f(0, 0, 0);
						glTexCoord2f(1, 0);
						glVertex3f(0, 1, 0);
						glTexCoord2f(0, 0);
						glVertex3f(0, 1, 1);
						glTexCoord2f(0, 1);
						glVertex3f(0, 0, 1);
					}
				}
			}
			glEnd();
		}
		glPopMatrix();
	}

	/**
	 * Getters and Setters
	 * 
	 */
	public float getX() {

		return x;
	}

	public float getY() {

		return y;
	}

	public float getZ() {

		return z;
	}

}