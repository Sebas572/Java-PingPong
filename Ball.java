import java.lang.Math;

public class Ball extends Thread {
	public static float x = 0f;
	public static float y = 0f;
	public static double ANG = 45;
	public static float addEjeX = 5 * (float) Math.sin(ANG);
	public static float addEjeY = - 5 * (float) Math.cos(ANG);
	public static int width = 20;
	public static int height = 20;

	public void run() {
		System.out.println("Ball iniciado");
	}

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void collision(Player player) {
		if(this.x > 500-this.width) {
			System.out.println("ball right");
			this.addEjeX = -5 * (float) Math.sin(ANG);
		}
		if(this.x < 0+this.width) {
			System.out.println("ball left");
			this.addEjeX = 5 * (float) Math.sin(ANG);
		}

		if((this.y >= player.y-player.height-10)&&(this.y <= player.y+player.height)&&(this.x >= player.x)&&(this.x <= player.x+player.width)) {
			this.addEjeY = -5 * (float) Math.cos(ANG);
		}
		if(this.y < 0+this.height) {
			this.addEjeY = 5 * (float) Math.cos(ANG);
		}
	}

	public void addCoordinated() {
		this.x += this.addEjeX;
		this.y += this.addEjeY;
	}
}