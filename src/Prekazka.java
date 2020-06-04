import java.util.Random;

public class Prekazka {
	private static int id = 0; // Pro flasky
	private int x;
	private int y;
	private int kde;
	private boolean aktivni = false;
	private int width = 56;
	private int height = 56;
	private int size = 56; // JEN PRO PREHLEDNOST KODU VE TRIDE Platno
	private int krok = 4;
	private double angle, angleRate;
	
	public Prekazka(int kde, int y) { // Pro sudy
		this.setKde(kde);
		this.y = y;
	}
	public Prekazka(int kde, int y, boolean b) { // Pro flasky, u parametru booleanu neni dulezita hodnota, ale pritomnost
		this.setKde(kde);
		this.y = y - Level.flaskyY[id];
		id++;
		angleRate = 0.01;
		angleRate *= new Random().nextInt(30) - 15;
	}
	
	public void update() {
		this.x -= krok;
		setAngle(getAngle() + angleRate);
	}
	
	// GETTERY A SETTERY (NEVENOVAT POZORNOST)
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getKde() {
		return kde;
	}

	public void setKde(int kde) {
		this.kde = kde;
	}

	public boolean isAktivni() {
		return aktivni;
	}

	public void setAktivni(boolean aktivni) {
		this.aktivni = aktivni;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public double getAngleRate() {
		return angleRate;
	}
	public void setAngleRate(double angleRate) {
		this.angleRate = angleRate;
	}
	public int getSize() {
		return size;
	}
	public static void setID(int id) {
		Prekazka.id = id;
	}
}