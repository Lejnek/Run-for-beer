public class Level {
	public static int pom = 1;
	
	public static int delka;
	public static int[] schody;
	public static int[] vysky;
	public static int[] sudy;
	public static int[] flasky;
	public static int[] flaskyY;
	
	public static void init(){
		switch(pom) {
		case 1:
			int[] schody1 = {700, 1500, 2500};
			schody = schody1;
			int[] vysky1 = {30, 45, 35};
			vysky = vysky1;
			int[] sudy1 = {0, 400, 1100, 1800, 2300};
			sudy = sudy1;
			int[] flasky1 = {150, 800, 1400, 2000};
			flasky = flasky1;
			int[] flaskyY1 = {90, 80, 10, 20};
			flaskyY = flaskyY1;
			delka = 2500 + Platno.width / 2;
			break;
		case 2:
			int[] schody2 = {100, 200, 750, 1500, 2000, 2300};
			schody = schody2;
			int[] vysky2 = {10, 50, 30, 25, 40, 30};
			vysky = vysky2;
			int[] sudy2 = {30, 400, 600, 850, 1100, 1300, 1650, 1800, 2150};
			sudy = sudy2;
			int[] flasky2 = {300, 1000, 1200, 1400, 2000};
			flasky = flasky2;
			int[] flaskyY2 = {90, 80, 10, 20, 80};
			flaskyY = flaskyY2;
			delka = 2300 + Platno.width / 2;
			break;
		case 3:
			int[] schody3 = {40, 550, 750, 1050, 1400, 1650};
			schody = schody3;
			int[] vysky3 = {40, 30, 130, 30, 20, 20};
			vysky = vysky3;
			int[] sudy3 = {25, 35, 120, 350, 430, 500, 600, 1000, 1150, 1550};
			sudy = sudy3;
			int[] flasky3 = {170, 190, 500, 830, 930, 1300, 1300, 1600};
			flasky = flasky3;
			int[] flaskyY3 = {120, 80, 60, 90, 90, 0, 60, 110};
			flaskyY = flaskyY3;
			delka = 1700 + Platno.width / 4; 
			break;
		case 4:
			int[] schody4 = {0, 310, 370, 520};
			schody = schody4;
			int[] vysky4 = {50, 50, 60, 140};
			vysky = vysky4;
			int[] sudy4 = {160, 171, 182, 295, 393, 450, 480};
			sudy = sudy4;
			int[] flasky4 = {40, 100, 240, 460, 470};
			flasky = flasky4;
			int[] flaskyY4 = {25, 80, 90, 50, 50};
			flaskyY = flaskyY4;
			delka = 520 + Platno.width / 2;
			break;
		}
		pom++;
		Prekazka.setID(0);
	}
}