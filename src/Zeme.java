public class Zeme {
	private int width = Platno.getSirkaZemeImg();
	private int x, y; // Y SE NEMENI!
	private int krok = 4;
	private boolean elev = false;
	public int indexZeme;
	public static int index = 0;
	
	public Zeme(int x, int y) {
		this.x = x;
		this.y = y;
		this.indexZeme = index;
		index++;
	}
	
	public void update() {
		this.x -= krok;
	}

	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public boolean isElev() {
		return elev;
	}
	public void setElev(boolean elev) {
		this.elev = elev;
	}
}