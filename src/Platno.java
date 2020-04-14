import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class Platno extends JPanel {

	private static final long serialVersionUID = -4476094085092247106L;
	// AI VŠEOBECNÉ 
	static Image pozadi, zem, walk1, walk2, jump, duck1, duck2, sud, flaska; // 56X56, skrèení 56X33
	private int kroky = 0;
	public static int width = 1024; // statika kvùli pøístupu z ostatních tøíd, napø. IntroPlatno
	public static int height = 683; // _||_
	private int urovenZeme = 50;
	private int elevation = urovenZeme; // Slouží pro konstruktor zemì po zvýšení
	// AI OHLEDNÌ ZEMÌ
	private Zeme[] poleZ = new Zeme[(width/sirkaZemeImg) + 2];
	private boolean elevNext = false;
	private int pocetSchodu = 0;
	private int pocetSchoduMax = Level.schody.length - 1;
	private static int sirkaZemeImg = 100;
	private int posunZemeDefault = -sirkaZemeImg;
	private int posunZeme = posunZemeDefault;
	// AI K POSTAVÌ
	private Postava character = new Postava();
	private int posledniPosun = 0;
	// AI K PØEKÁŽKÁM
	private Prekazka[] poleSud = new Prekazka[Level.sudy.length];
	private Prekazka[] poleFlasek = new Prekazka[Level.flasky.length];
	//TODO DALŠÍ AI
	
	public Platno() {
		// VYTVOØENÍ ZEMÌ
		for(int i = 0; i < poleZ.length; i++) { 
			poleZ[i] = new Zeme(i * sirkaZemeImg, height - elevation);
		}
		// VYTVOØENÍ PØEKÁŽEK
		for(int i = 0; i < poleSud.length; i++) {
			poleSud[i] = new Prekazka(Level.sudy[i], height);
			poleSud[i].setY(poleSud[i].getY() - poleSud[i].getHeight()); // Øádek navíc, protože to kvùli zapouzdøení v koknstruktoru nešlo
		}
		for(int i = 0; i < poleFlasek.length; i++) {
			poleFlasek[i] = new Prekazka(Level.flasky[i], height, true);
			poleFlasek[i].setY(poleFlasek[i].getY() - poleFlasek[i].getHeight());
		}
	}
	
	// MI
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// INICIALIZACE OBRÁZKÙ A OSTATNÍCH INSTANCÍ
		pozadi = Toolkit.getDefaultToolkit().getImage("bg1.jpg");
		zem = Toolkit.getDefaultToolkit().getImage("gnd.png");
		walk1 = Toolkit.getDefaultToolkit().getImage("walk1.png");
		walk2 = Toolkit.getDefaultToolkit().getImage("walk2.png");
		jump = Toolkit.getDefaultToolkit().getImage("jump.png");
		duck1 = Toolkit.getDefaultToolkit().getImage("duck1.png");
		duck2 = Toolkit.getDefaultToolkit().getImage("duck2.png");
		sud = Toolkit.getDefaultToolkit().getImage("sud.png");
		flaska = Toolkit.getDefaultToolkit().getImage("flaska.png");
		// POZADÍ
		g2.drawImage(pozadi, 0, 0, null);
		// ZEMÌ
		for(Zeme Z : poleZ) {
			g2.drawImage(zem, Z.getX(), Z.getY(), null);
		}
		if(kroky == Level.schody[pocetSchodu]) {
			elevation += Level.vysky[pocetSchodu];
			elevNext = true; 
			if(pocetSchoduMax > pocetSchodu) {
				pocetSchodu++;
			}
		}
		for(Zeme z : poleZ) {
			if(z.isElev()) {
				if(zemeKolize(character.getX(), character.getX() + character.getWidth(), z.getX()) == true) {
					if(character.getY() + character.getHeight() > z.getY()) Hra.pokracuj = false; //TODO -> PODMÍNKA PRO KONEC HRY
					urovenZeme += Level.vysky[posledniPosun];
					z.setElev(false);
					character.posunPriSkoku = Level.vysky[posledniPosun];
					character.setyPos(character.getyPos() - character.posunPriSkoku); //?
					if(posledniPosun < Level.vysky.length) posledniPosun++;
				}
			}
		}
			// 2. POKUS
		/*for(int i = -posunZeme + posunZemeDefault; i < width; i += sirkaZemeImg) { 
			g2.drawImage(zem, i, height - urovenZeme, null);
		}
		if(kroky == Level.schody[pocetSchodu]) {
			urovenZeme += Level.vysky[pocetSchodu];
			if(pocetSchoduMax > pocetSchodu)pocetSchodu++;
		}
		*/
			// 1. POKUS
		/*for(int i = -posunZeme + posunZemeDefault; i < width; i += sirkaZemeImg) {
			g2.drawImage(zem, i, height - urovenZeme, null);
		}*/
		posunZeme += 2; 
		if(posunZeme >= 0) posunZeme = posunZemeDefault;
		// POSTAVA
		int x = character.getX();
		// int y = height - urovenZeme - character.getHeight() - character.getyPos();
		character.setY(height - urovenZeme - character.getHeight() - character.getyPos());
		int duckY = height - urovenZeme - character.getDuckHeight();
		switch(Postava.getStav()) {
			default: 
				break;
			case 0:
				if(character.getPom() < 5) {
					g2.drawImage(walk1, x, character.getY(), null);
					character.prehodPom();
				}
				else {
					g2.drawImage(walk2, x, character.getY(), null);
					character.prehodPom();
				}
				break;
			case 1:
				character.setyPos(character.getyPos() + Postava.jumpArray[character.fazeSkoku]); // inkrementace yPos pro normální pøípad
				g2.drawImage(jump, x, character.getY(), null);
				character.fazeSkoku++;
				if(character.fazeSkoku == 73) { // SKOK DOKONÈEN
					character.fazeSkoku = 0;
					character.setyPos(0);
					Postava.setStav(0);
					character.posunPriSkoku = 0;
				}
				break;
			case 2:
				if(character.getPom() < 5) {
					g2.drawImage(duck1, x, duckY, null);
					character.prehodPom();
				}
				else {
					g2.drawImage(duck2, x, duckY, null);
					character.prehodPom();
				}
				break;
		}
		g2.drawString(Integer.toString(character.getY()), 500, 500);
		g2.drawString(Integer.toString(character.posunPriSkoku), 500, 400);
		// PØEKÁŽKA/Y
			// SUDY
		for(Prekazka p : poleSud) {
			if(kroky == p.getKde()) {
				p.setX(width);
				p.setAktivni(true);
				p.setY(p.getY() - elevation);
			}
			if(p.isAktivni()) {
				g2.drawImage(sud, p.getX(), p.getY(), null);
				p.update();
				if(kolize(character.getX(), character.getY(), character.getWidth(), character.getHeight(), p.getX(), p.getY(), p.getWidth(), p.getHeight()) == true) Hra.pokracuj = false;
				if(p.getX() < 0 - p.getWidth()) p = null;
			}			
		}
			// FLAŠKY
		for(Prekazka p : poleFlasek) {
			if(kroky == p.getKde()) {
				p.setX(width);
				p.setAktivni(true);
				p.setY(p.getY() - elevation);
			}
			if(p.isAktivni()) {
				g2.rotate(p.getAngle(), p.getX() + p.getSize()/2, p.getY() + p.getSize()/2);//
				g2.drawImage(flaska, p.getX(), p.getY(), null);
				g2.setTransform(AffineTransform.getScaleInstance(1, 1));//
				p.update();
				if(kolize(character.getX(), character.getY(), character.getWidth(), character.getHeight(), p.getX(), p.getY(), p.getWidth(), p.getHeight()) == true) Hra.pokracuj = false;
				if(p.getX() < 0 - p.getWidth()) p = null;
			}
		}
		//TODO další
		// UPDATE 
		kroky++;
		for(int i = 0; i < poleZ.length; i++) {
			poleZ[i].update();
			if(poleZ[i].getX() <= - sirkaZemeImg) {
				poleZ[i] = new Zeme((poleZ.length - 1) * sirkaZemeImg, height - elevation);
				if(elevNext == true) {
					elevNext = false;
					poleZ[i].setElev(true);
				}
			}
		}
		if(true); // 
	}
	
	public static boolean kolize(int poX, int poY, int poW, int poH, int prX, int prY, int prW, int prH) { 
		boolean b = false;
		boolean xKol = false;
		boolean yKol = false;
		if(Postava.getStav() == 2) poY += Postava.walkDuckRozdil;
		// TEST PRO X-OVÉ SOUØADNICE
		int poPom = poX + poW;
		int prPom = prX + prW;
		if(prX > poPom == false) {
			if(poX > prPom == false) {
				xKol = true;
			}
		}
		// TEST PRO Y-OVÉ SOUØADNICE
		poPom = poY + poH;
		prPom = prY + prH;
		if(prY > poPom == false) {
			if(poY > prPom == false) {
				yKol = true;
			}
		}
		if(xKol == true && yKol == true) b = true;
		return b;
	}
	
	public static boolean zemeKolize(int po1, int po2, int ze) { 
		boolean b = false;
		if(ze > po1 && ze < po2) b = true;
		return b;
	}
	// GETTERY A SETTERY
	public static int getSirkaZemeImg() {return sirkaZemeImg;}
}