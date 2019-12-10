package explorer.world.entity;

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

/**
 * @Author: Me
 * @Description: 'Living' Objects of the World
 * 
 */
public class Entity {

	private Texture texture;

	public float x, y, z, prevX, prevY, prevZ, pitchX;

	/**
	 * Constructor Method
	 * 
	 */
	public Entity(String ID) {

		//Temporary
		this.x = (int)(Math.random() * 9 + 1);
		this.z = (int)(Math.random() * 9 + 1);

		//Initializes the Texture
		try {

			texture = TextureLoader.getTexture("PNG", getClass()
					.getResourceAsStream(
							"/explorer/assets/entities/" + ID + ".png"));
		} catch (Exception e) {
			
			//Loads a Backup if the Intended One Isn't There
			try {

				texture = TextureLoader.getTexture("PNG", getClass()
						.getResourceAsStream(
								"/explorer/assets/entities/" + "default" + ".png"));
			} catch (Exception e2) {

				e2.printStackTrace();
			}

			e.printStackTrace();
		}

	}

	/**
	 * Simplified Movement
	 * 
	 */
	public void move(float amt, float dir) {

		x += (amt * Math.cos(Math.toRadians(pitchX + 90 * dir)));
		z += (amt * Math.sin(Math.toRadians(pitchX + 90 * dir)));
	}

	/**
	 * Updates the Entity
	 * 
	 */
	public void tick() {

		prevX = x;
		prevY = y;
		prevZ = z;

		render();
	}

	/**
	 * Renders the Entity
	 * 
	 */
	public void render() {

		glPushMatrix();
		{
			
			//Moves the Entity
			glTranslatef(this.x, this.y, this.z);

			texture.bind();

			glBegin(GL_QUADS);
			{

				glTexCoord2f(0, 1);
				glVertex3f(0, -1, 0);
				glTexCoord2f(1, 1);
				glVertex3f(-1, -1, 0);
				glTexCoord2f(1, 0);
				glVertex3f(-1, 2, 0);
				glTexCoord2f(0, 0);
				glVertex3f(0, 2, 0);
			}
			glEnd();
		}
		glPopMatrix();
	}
}
