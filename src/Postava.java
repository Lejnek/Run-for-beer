public class Postava {
	private int width, height, duckHeight;
	public static int walkDuckRozdil;
	private static int stav = 0; 	// 0 walk, 1 jump, 2 duck
	public static int[] jumpArray = {9,9,9,9,9,9,9,9,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,3,3,3,3,3,3,3,3,3,
			-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-9,-9,-9,-9,-9,-9,-8};
	private int pom = 0; 	// 0-4 walk1; 5-9 walk2 (to sam� pro duck)
	private int x = 100; 	// Defaultn� X
	private int y; 			// defaultn� Y
	private int yPos; 		// Pro skok
	private int xPos; 		// Pro konec levelu
	public int urovenZeme = 50;
	public int fazeSkoku = 0;
	public int posunPriSkoku = 0;
	
	public Postava() { // KDY� AKTUALIZUJI 2.-4. PARAMETR, AKTUALIZUJI V�ECHNY 3 NAR�Z!
		this.width = 100;
		this.height = 100;
		this.duckHeight = 70;
		walkDuckRozdil = 30;
		yPos = 0;
	}
	
	public void prehodPom() {
		pom++;
		if(pom >= 10) pom = 0;
	}
	
	// GETTERY A SETTERY (NEV�NOVAT POZORNOST)
	public int getPom() {
		return pom;
	}
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
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getDuckHeight() {
		return duckHeight;
	}
	public static int getStav() {
		return Postava.stav;
	}
	public static void setStav(int stav) {
		Postava.stav = stav;
	}
	public int getFazeSkoku() {
		return fazeSkoku;
	}
	public void setFazeSkoku(int fazeSkoku) {
		this.fazeSkoku = fazeSkoku;
	}
}