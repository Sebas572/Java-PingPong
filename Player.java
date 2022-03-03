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
		if(this.x > 0) {
			this.x -= 5;
		}else {
			System.out.println("Stop left");
		}
	}

	public void right() {
		if(this.x < 400) {
			this.x += 5;
		}else {
			System.out.println("Stop right");
		}
	}
}
