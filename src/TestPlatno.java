import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class TestPlatno extends JPanel {

	private static final long serialVersionUID = -4476094085097247106L;
	private Image img, bg;
	int x = 0;
	int y = 0;
	int krokYDefault = 7;
	int krokY = 7;
	int faze = 1;
	int pom = 0;

	public Dimension getPreferredSize() {
		return new Dimension(1200, 800);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		img = Toolkit.getDefaultToolkit().getImage("walk1.png");
		bg = Toolkit.getDefaultToolkit().getImage("bg1.jpg");
	}
}