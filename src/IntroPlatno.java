import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class IntroPlatno extends JPanel {
	private Image zlejsek, brko, bg;
	
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
		bg = Toolkit.getDefaultToolkit().getImage("bgIntro.png");
		g2.drawImage(bg, 0, 0, null);
	}
}