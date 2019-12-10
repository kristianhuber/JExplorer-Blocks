package explorer.gui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * @Author: Me
 * @Description: Class That Draws Font on the Screen
 * 
 */

public class Font {
	public static final String INDEX = "abcdefghijklmnopqrstuvwxyz ";

	private Texture letters;

	/**
	 * Constructor Method
	 * 
	 */
	public Font() {

		//Loads the Textures
		try {

			letters = TextureLoader.getTexture("PNG", getClass()
					.getResourceAsStream("/explorer/assets/gui/font.png"));
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * Draws Font at the Regular Size
	 * 
	 */
	public void drawFont(String text, int x, int y) {

		this.drawFont(text, x, y, 0.75F);
	}

	/**
	 * Draws Font at a Scale
	 * 
	 */
	public void drawFont(String text, int x, int y, float scale) {
		float xx = 0.0F;
		float yy = 0.0F;

		text = text.toLowerCase();

		//Runs Through Each Letter
		for (int i = 0; i < text.length(); i++) {

			//Gets the Index From INDEX
			for (int j = 0; j < INDEX.length(); j++) {

				//Sets the Coordinate on the Texture Map
				if (text.substring(i, i + 1).equals(INDEX.substring(j, j + 1))) {

					xx = (j % 8) * 0.125F;
					yy = (j / 8) * 0.125F;
					break;
				}
			}

			//Actually Renders the Font
			glPushMatrix();
			{

				letters.bind();

				float q = 32 * scale;
				glBegin(GL_QUADS);
				{
					glTexCoord2f(xx, yy);
					glVertex2f(x + (i * q), y);

					glTexCoord2f(xx + 0.125F, yy);
					glVertex2f(x + (i * q) + q, y);

					glTexCoord2f(xx + 0.125F, yy + 0.125F);
					glVertex2f((x + (i * q) + q), y + q);

					glTexCoord2f(xx, yy + 0.125F);
					glVertex2f(x + (i * q), y + q);
				}
				glEnd();
			}
			glPopMatrix();
		}
	}
}
