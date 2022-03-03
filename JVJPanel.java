import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.lang.Math;
import java.lang.Thread;
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
				//creamos nuevo objecto bloke
				Block block = new Block((ix*100)+10, (iy*40)+10);
				list.add(block);	//adding list
				Thread blockProcess = new Thread(block);	//iniciamos proceso
				blockProcess.start();						//de multiples hilo
			}
		}
	}

	JVJPanel() {
		System.out.println("JVJPanel iniciado");
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
		ctx.fillOval((int) ball.x, (int) ball.y, ball.width, ball.height);

		ctx.setFont(new Font("Monospaced", Font.BOLD, 18));
		ctx.drawString("Score: " + score, 10, 510);
		ctx.drawString("Sebastián Balebona", 290, 510);
		
		ctx.setColor(Color.red);

		for(int i = 0; i<list.size(); i++) {
			ctx.fillRect(list.get(i).x, list.get(i).y, list.get(i).width, list.get(i).height);
		}

		ctx.setColor(Color.white);
		ctx.fillRect(player.x, player.y, player.width, player.height);
	}

	public static void reset() {
		try {
			player.x = 200;
			player.y = 480;

			ball.x = 250;
			ball.y = 480;
			ball.addEjeX = Math.abs(ball.addEjeX);
			
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
			switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					System.out.println("Left");
					player.left();
					break;
				case KeyEvent.VK_RIGHT:
					System.out.println("Right");
					player.right();
					break;
			}
		}
	}
}