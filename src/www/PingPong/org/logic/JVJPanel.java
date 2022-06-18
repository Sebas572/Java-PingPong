package www.PingPong.org.logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class JVJPanel extends JPanel implements ActionListener {
	static final int WIDTH = 500;
	static final int HEIGHT = 515;

	static Player player = new Player(200, 480);
	Thread playerProcess = new Thread(player);

	static Ball ball = new Ball(250, 480);
	Thread ballProcess = new Thread(ball);

	public static ArrayList<Block> list = new ArrayList<Block>();

	private static int score = 0;

	Timer timer = new Timer(50, this);

	public static void generatedMap() {
		for(int iy = 0; iy<=4; iy++) {
			for(int ix = 0; ix<=4; ix++) {
				Block block = new Block((ix*100)+10, (iy*40)+10);
				list.add(block);
				Thread blockProcess = new Thread(block);
				blockProcess.start();
			}
		}
	}

	JVJPanel() {
		System.out.println("JVJPanel init");
		ballProcess.start();
		playerProcess.start();
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		timer.start();

		generatedMap();
	}

	public void paintComponent(Graphics ctx) {
		super.paintComponent(ctx);

		ctx.setColor(Color.white);
		ctx.fillOval((int) Ball.x, (int) Ball.y, Ball.width, Ball.height);

		ctx.setFont(new Font("Monospaced", Font.BOLD, 18));
		ctx.drawString("Score: " + score, 10, 510);
		ctx.drawString("Sebastián Balebona", 290, 510);
		
		ctx.setColor(Color.red);

		for(Block block : list) {
			ctx.fillRect(block.x, block.y, block.width, block.height);
		}

		ctx.setColor(Color.white);
		ctx.fillRect(Player.x, Player.y, Player.width, Player.height);
	}

	public static void reset() {
		try {
			Player.x = 200;
			Player.y = 480;

			Ball.x = 250;
			Ball.y = 480;
			Ball.addEjeX = Math.abs(Ball.addEjeX);
			list.clear();

			score = 0;
			Thread.sleep(1000);

			generatedMap();
		}catch (Exception e) {
			System.out.println("JVJPanel-reset: " + e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(ball.collision(player)) {
			reset();
		}
		ball.addCoordinated();
		for(Block i : list) {
			if(i.collision(ball)) {
				score++;
				if(score == 25) {
					int confirm = JOptionPane.showConfirmDialog(null, "Win!!!", "Win", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(confirm == JOptionPane.YES_NO_OPTION) {
						reset();
					}else {
						System.exit(0);
					}
				}
			}
		}
		repaint();
	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT: player.left();break;
				case KeyEvent.VK_RIGHT: player.right();break;
			}
		}
	}
}