public class Player extends Thread {
	public static int x = 0;
	public static int y = 0;
	public static int width = 100;
	public static int height = 10;

	public void run() {
		System.out.println("Player init");
	}
	
	Player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void left() {
		this.x -= 5;
	}

	public void right() {
		this.x += 5;
	}
}