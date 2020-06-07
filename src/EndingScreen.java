import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class EndingScreen extends JPanel {
	private static final long serialVersionUID = 1L;

	static Image obr1 = Toolkit.getDefaultToolkit().getImage("end1.png");
	static Image obr2 = Toolkit.getDefaultToolkit().getImage("end2.png");
	static Image obr3 = Toolkit.getDefaultToolkit().getImage("end3.png");
	private  static Image[] images = {obr1, obr2, obr3};
	private static int pom = 0;
	public Dimension getPreferredSize() {
		return new Dimension(Platno.width, Platno.height);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(images[pom], 0, 0, null);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g2.drawString("STISKNI", Platno.width - 150, 30);
		g2.drawString("MEZERNÍK", Platno.width - 165, 60);
	}
	public static void next() {
		pom++;
	}
	public static int getPom() {
		return pom;
	}
}