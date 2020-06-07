import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class MeziLvlPlatno extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image bg;
	private static boolean end = false;
	
	public Dimension getPreferredSize() {
		return new Dimension(Platno.width, Platno.height);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// POZADÍ
		g2.setColor(Color.WHITE);
		bg = Toolkit.getDefaultToolkit().getImage("gameover.png");
		if(end == false) {           
			bg = Toolkit.getDefaultToolkit().getImage("mezilvl.png");
			g2.setColor(Color.BLACK);
		}
		g2.drawImage(bg, 0, 0, Platno.width, Platno.height, null);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g2.drawString("STISKNI", Platno.width - 150, 30);
		g2.drawString("MEZERNÍK", Platno.width - 165, 60);
	}
	public static void setEnd() {end = true;}
}