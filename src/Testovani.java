import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Testovani extends JFrame implements KeyListener{
	
	public Testovani() {
		super("Bezvýznamné okno pro testování detailù");
	}
	public static void main(String[] args) throws InterruptedException {
		Testovani t = new Testovani();
		t.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		t.setLayout(new BorderLayout());
		t.addKeyListener(t);
		TestPlatno p = new TestPlatno();
		t.getContentPane().add(p);
		t.pack();
		t.setVisible(true);
		Thread.sleep(1000);
		p.repaint();
		
		while(true) {
			Thread.sleep(10);
			p.repaint();
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}