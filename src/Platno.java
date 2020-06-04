import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class Platno extends JPanel {

	private static final long serialVersionUID = -4476094085092247106L;
	// AI VSEOBECNE 
	public static Image pozadi, zem, walk1, walk2, jump, duck1, duck2, sud, flaska; 
	private int kroky = 0;
	public static int width = 1024; // statika kvuli pristupu z ostatnich trid, napr. IntroPlatno
	public static int height = 683; // _||_
	private int urovenZeme = 50;
	private int elevation = urovenZeme; // Slouzi pro konstruktor zeme po zvyseni
	// AI OHLEDNE ZEME
	private Zeme[] poleZ = new Zeme[(width/sirkaZemeImg) + 2];
	private boolean elevNext = false;
	private int pocetSchodu = 0;
	private int pocetSchoduMax = Level.schody.length - 1;
	private static int sirkaZemeImg = 100;
	private int posunZemeDefault = -sirkaZemeImg;
	private int posunZeme = posunZemeDefault;
	// AI K POSTAVE
	private Postava character = new Postava();
	private int posledniPosun = 0;
	private static boolean konecSkoku = false;
	// AI K PREKAZKAM
	private Prekazka[] poleSud = new Prekazka[Level.sudy.length];
	private Prekazka[] poleFlasek = new Prekazka[Level.flasky.length];

	
	public Platno() {
		// VYTVORENI ZEME
		for(int i = 0; i < poleZ.length; i++) { 
			poleZ[i] = new Zeme(i * sirkaZemeImg, height - elevation);
		}
		// VYTVORENI PREKAZEK
		for(int i = 0; i < poleSud.length; i++) {
			poleSud[i] = new Prekazka(Level.sudy[i], height);
			poleSud[i].setY(poleSud[i].getY() - poleSud[i].getHeight()); // Radek navic, protoze to kvuli zapouzdreni v koknstruktoru neslo
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
		// INICIALIZACE OBRAZKU A OSTATNICH INSTANCI JE VE TRIDE Hra
		// POZADI
		g2.drawImage(pozadi, 0, 0, null);
		if(Hra.isPaused() == true && Hra.levelComplete == false) {
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 75));
			g2.setColor(Color.BLACK);
			g2.drawString("PAUZA", width / 2 - 100, height / 2);
		}
		// ZEME
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
					if(character.getY() + character.getHeight() > z.getY()) {
						Hra.pokracuj = false;
						Hra.levelComplete = true; 
					}
					urovenZeme += Level.vysky[posledniPosun];
					z.setElev(false);
					character.posunPriSkoku = Level.vysky[posledniPosun];
					character.setyPos(character.getyPos() - character.posunPriSkoku); 
					if(posledniPosun < Level.vysky.length) posledniPosun++;
				}
			}
		}
		posunZeme += 2; 
		if(posunZeme >= 0) posunZeme = posunZemeDefault;
		// POSTAVA
		int x = character.getX();
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
				if(Platno.height - (character.getY() + character.getHeight() + Postava.jumpArray[character.fazeSkoku]) < urovenZeme && character.posunPriSkoku > 0) {
					konecSkoku = true;
					character.fazeSkoku = 73;
				}
				if(konecSkoku == false) {
					character.setyPos(character.getyPos() + Postava.jumpArray[character.fazeSkoku]); // inkrementace yPos pro normalni pripad
					g2.drawImage(jump, x, character.getY(), null);
					character.fazeSkoku++;
				}
				if(character.fazeSkoku == 73) { // SKOK DOKONCEN
					character.fazeSkoku = 0;
					character.setyPos(0);
					Postava.setStav(0);
					character.posunPriSkoku = 0;
					g2.drawImage(jump, x, character.getY(), null); 
					konecSkoku = false;
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
		// PREKAZKY
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
				if(kolize(character.getX(), character.getY(), character.getWidth(), character.getHeight(), p.getX(), p.getY(), p.getWidth(), p.getHeight()) == true) {
					Hra.pokracuj = false;
					Hra.levelComplete = true;
				}
				if(p.getX() < 0 - p.getWidth()) p.setAktivni(false);
			}			
		}
			// FLASKY
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
				if(kolize(character.getX(), character.getY(), character.getWidth(), character.getHeight(), p.getX(), p.getY(), p.getWidth(), p.getHeight()) == true) {
					Hra.pokracuj = false;
					Hra.levelComplete = true;
				}
				if(p.getX() < 0 - p.getWidth()) p.setAktivni(false);
			}
		}
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
		// TEST KONCE LEVELU
		if(kroky > Level.delka) {
			boolean pom = true;
			for(Prekazka p : poleSud) {
				if(p.isAktivni()) pom = false;
			}
			for(Prekazka p : poleFlasek) {
				if(p.isAktivni()) pom = false;
			}
			if(pom == true) {
				Hra.levelComplete = true;
				if(Level.pom == 5) { 
					Hra.gameComplete = true;
					Hra.pokracuj = false;
				}
			}
		}
	}
	
	public static void zlejsek() {
		pozadi = Toolkit.getDefaultToolkit().getImage("bg1.jpg");
		zem = Toolkit.getDefaultToolkit().getImage("gnd.png");
		walk1 = Toolkit.getDefaultToolkit().getImage("walk1.png");
		walk2 = Toolkit.getDefaultToolkit().getImage("walk2.png");
		jump = Toolkit.getDefaultToolkit().getImage("jump.png");
		duck1 = Toolkit.getDefaultToolkit().getImage("duck1.png");
		duck2 = Toolkit.getDefaultToolkit().getImage("duck2.png");
		sud = Toolkit.getDefaultToolkit().getImage("sud.png");
		flaska = Toolkit.getDefaultToolkit().getImage("flaska.png");
	}
	public static void brko() {
		pozadi = Toolkit.getDefaultToolkit().getImage("bg1.jpg");
		zem = Toolkit.getDefaultToolkit().getImage("gnd.png");
		walk1 = Toolkit.getDefaultToolkit().getImage("walk3.png");
		walk2 = Toolkit.getDefaultToolkit().getImage("walk4.png");
		jump = Toolkit.getDefaultToolkit().getImage("jump2.png");
		duck1 = Toolkit.getDefaultToolkit().getImage("duck3.png");
		duck2 = Toolkit.getDefaultToolkit().getImage("duck4.png");
		sud = Toolkit.getDefaultToolkit().getImage("sud.png");
		flaska = Toolkit.getDefaultToolkit().getImage("flaska.png");
	}
	
	public static boolean kolize(int poX, int poY, int poW, int poH, int prX, int prY, int prW, int prH) { 
		boolean b = false;
		boolean xKol = false;
		boolean yKol = false;
		if(Postava.getStav() == 2) {
			poW = 100;
			poY += Postava.walkDuckRozdil;
		}
		// TEST PRO X-OVE SOURADNICE
		int poPom = poX + poW;
		int prPom = prX + prW;
		if(prX > poPom == false) {
			if(poX > prPom == false) {
				xKol = true;
			}
		}
		// TEST PRO Y-OVE SOURADNICE
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