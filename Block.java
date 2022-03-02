public class Block extends Thread {
	public int x = 0;
	public int y = 0;
	public int width = 80;
	public int height = 30;

	public void run() {
		System.out.println("Block-Thread Subprocess init");
	}

	Block(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean collision(Ball ball) {
		if((ball.x >= this.x)&&(ball.x <= this.x+this.width)&&(ball.y >= this.y+15)&&(ball.y <= this.y+this.height+ball.height)) {
			//Up
			this.x = -150;
			ball.addEjeY = 5;
			System.out.println(1);
			return true;
		}else if((ball.x >= this.x)&&(ball.x <= this.x+this.width)&&(ball.y >= this.y-20)&&(ball.y <= this.y)) {
			//Down
			this.x = -150;
			ball.addEjeY = -5;
			System.out.println(2);
			return true;
		}else if((ball.x >= this.x-20)&&(ball.x <= this.x+ball.width)&&(ball.y >= this.y)&&(ball.y <= this.y+this.height)) {
			//Left
			this.x = -150;
			ball.addEjeX = -5;
			System.out.println(3);
			return true;
		}else if((ball.x >= this.x+this.width-20)&&(ball.x <= this.x+this.width+ball.width)&&(ball.y >= this.y)&&(ball.y <= this.y+this.height)) {
			//Right
			this.x = -150;
			ball.addEjeX = 5;
			System.out.println(4);
			return true;
		}
		return false;
	}
}