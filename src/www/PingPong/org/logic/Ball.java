package www.PingPong.org.logic;

import javax.swing.*;

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

	public boolean collision(Player player) {
		if(this.x > 500-this.width) {
			this.addEjeX = -5 * (float) Math.sin(ANG);
		}
		if(this.x < 0+this.width) {
			this.addEjeX = 5 * (float) Math.sin(ANG);
		}

		if((this.y >= player.y-player.height-10)&&(this.y <= player.y+player.height)&&(this.x >= player.x)&&(this.x <= player.x+player.width)) {
			this.addEjeY = -5 * (float) Math.cos(ANG);
		}
		if(this.y < 0+this.height) {
			this.addEjeY = 5 * (float) Math.cos(ANG);
		}
		if(this.y > 520) {
			int confirm = JOptionPane.showConfirmDialog(null, "            Game over.\n¿Quieres volver a jugar?",   "Game over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(confirm == JOptionPane.YES_NO_OPTION) {
				return true;
			}else {
				System.exit(0);
			}
		}
		return false;
	}

	public void addCoordinated() {
		this.x += this.addEjeX;
		this.y += this.addEjeY;
	}
}