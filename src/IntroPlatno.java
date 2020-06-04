import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class IntroPlatno extends JPanel {
	private static final long serialVersionUID = 1L;
	private static boolean choice = true; // True -> Zlejsek, False -> Brk
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
		zlejsek = Toolkit.getDefaultToolkit().getImage("walk1.png");
		brko = Toolkit.getDefaultToolkit().getImage("walk3.png");
		// POZADI
		bg = Toolkit.getDefaultToolkit().getImage("bgIntroMale.jpg");
		g2.drawImage(bg, 0, 0, Platno.width, Platno.height, null);
		// HL. TEXT
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		g2.drawString("VYBER SI POSTAVU!", (Platno.width / 2) - 250, (Platno.height / 2) - 250);
		g2.setColor(Color.RED);
		
		// VOLBA BRKA
		if(choice == false) { 
			g2.drawString("Jiøí Zlejsek", (Platno.width / 2) - 350, (Platno.height / 2) - 100);
			g2.drawImage(zlejsek, 264, 280, null);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 75));
			g2.setColor(Color.GREEN);
			g2.drawString("Tom Brk", (Platno.width / 2) + 100, (Platno.height / 2) - 100);
			g2.drawImage(brko, 694, 280, 101, 163, null);
		}
		
		// VOLBA ZLEJSKA
		if(choice == true) {
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 75));
			g2.drawString("Jiøí Zlejsek", (Platno.width / 2) - 350 - 60, (Platno.height / 2) - 100 + 9);
			g2.drawImage(zlejsek, 244, 280, 93, 174, null);
			g2.setColor(Color.GREEN);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			g2.drawString("Tom Brk", (Platno.width / 2) + 100 + 48, (Platno.height / 2) - 100 - 9); 
			g2.drawImage(brko, 714, 280, null);
		}
	}
	public static void switchChoice() {
		if(choice == true) choice = false;
		else choice = true;
	}
	public static boolean getChoice() {return choice;}
}