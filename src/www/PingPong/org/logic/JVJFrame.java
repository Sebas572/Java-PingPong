package www.PingPong.org.logic;

import javax.swing.*;

public class JVJFrame extends JFrame implements Runnable {

	public JVJFrame() {
		this.add(new JVJPanel());
		this.setTitle("Ping Pong");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.run();
	}

	@Override
	public void run() {
		while(true) this.repaint();
	}
}
