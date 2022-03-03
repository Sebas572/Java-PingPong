import javax.swing.JFrame;

public class JVJFrame extends JFrame {
	JVJFrame() {
		this.add(new JVJPanel());
		this.setTitle("Ping Pong");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
