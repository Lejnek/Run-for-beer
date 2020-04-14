import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Hra extends JFrame implements KeyListener {
	private static final long serialVersionUID = -6082477813618084477L;
	public static boolean pokracuj = true;

	public Hra() {
		super("Run for beer!");
	}
	
	public static void main(String[] args) throws InterruptedException {
		Hra instance = new Hra();
		instance.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		instance.setLayout(new BorderLayout());
		instance.addKeyListener(instance);
		Platno p = new Platno();
		instance.getContentPane().add(p);
		instance.pack();
		instance.setVisible(true);
		Thread.sleep(1000);
		p.repaint();
		while(pokracuj == true) {
			Thread.sleep(10);
			p.repaint();
		}
		System.out.println("GAME OVER!");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 38 && Postava.getStav() == 0) Postava.setStav(1);  //TODO
		if(e.getKeyCode() == 40 && Postava.getStav() == 0) Postava.setStav(2);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 40 && Postava.getStav() == 2) Postava.setStav(0);
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}