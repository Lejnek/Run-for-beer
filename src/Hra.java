import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Hra extends JFrame implements KeyListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = -6082477813618084477L;
	private static boolean paused = false;
	public static boolean pokracuj = true;
	public static boolean levelComplete = false;
	public static boolean gameComplete = false;
	private static boolean pokracujIntro = true;

	public Hra() {
		super("Run for beer!");
	}
	
	public static void main(String[] args) throws InterruptedException {
		// INICIALIZACE
		Hra instance = new Hra();
		instance.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		instance.setLayout(new BorderLayout());
		instance.addKeyListener(instance);
		instance.addMouseListener(instance);
		instance.addMouseMotionListener(instance);
		// INTRO
		IntroPlatno ip = new IntroPlatno();
		instance.getContentPane().add(ip);
		instance.pack();
		instance.setVisible(true);
		Thread.sleep(1000);
		ip.repaint();
		while(pokracujIntro == true) {
			ip.repaint();
			Thread.sleep(10);
		}
		if(IntroPlatno.getChoice() == true) {Platno.zlejsek();}
		else Platno.brko();
		instance.removeMouseMotionListener(instance);
		instance.removeMouseListener(instance);
		ip = null; 
		// HRA 
		Platno p;
		while(pokracuj == true) {
			Level.init();
			p = new Platno();
			instance.getContentPane().add(p);
			instance.pack();
			instance.setVisible(true);
			levelComplete = false;
			while(levelComplete == false) {
				while(paused == true) {Thread.sleep(5);}
				Thread.sleep(9);
				p.repaint();
			}
			// PRECHOD MEZI LEVELY
			if(pokracuj == true) {
				MeziLvlPlatno mlp = new MeziLvlPlatno();
				instance.getContentPane().add(mlp);
				instance.setVisible(true);//
				paused = true;
				while(paused == true) {
					mlp.repaint();
					Thread.sleep(5);
				}
			}
		}
		// PROHRA
		if(gameComplete == false) {
			MeziLvlPlatno.setEnd();
			MeziLvlPlatno gameover = new MeziLvlPlatno();
			instance.getContentPane().add(gameover);
			instance.setVisible(true);
			paused = true;
			while(paused == true) {
				Thread.sleep(5);
				gameover.repaint();
			}
		} 
		// VYHRA
		if(gameComplete == true) {
			while(EndingScreen.getPom() < 3) {
				EndingScreen es = new EndingScreen();
				instance.getContentPane().add(es);
				instance.setVisible(true);
				paused = true; 
				while(paused) {
					Thread.sleep(5);
					es.repaint();
				}
				if(EndingScreen.getPom() < 2)EndingScreen.next(); // PODMINKA ZABRANI ArrayIndexOutOfBoundsException
				else System.exit(0);
			}
		}
		System.exit(0);
	}

	// LISTENERY
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 38 && Postava.getStav() == 0) Postava.setStav(1);
		if(e.getKeyCode() == 40 && Postava.getStav() == 0) Postava.setStav(2);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 40 && Postava.getStav() == 2) Postava.setStav(0);
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		if(arg0.getKeyChar() == ' ') {
			if(paused == true) {paused = false;}
			else {paused = true;}
		}
	}

	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// pokracujIntro = false; // Casto funguje az naponekolikate
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		pokracujIntro = false;
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		if(arg0.getX() > Platno.width / 2 && IntroPlatno.getChoice() == true) {
			IntroPlatno.switchChoice();
		}
		if(arg0.getX() <= Platno.width / 2 && IntroPlatno.getChoice() == false) {
			IntroPlatno.switchChoice();
		}
	}
	// GETTERY A SETTERY
	public static boolean isPaused() {return paused;}
}
