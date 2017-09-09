import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game {

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
        Level level1 = new Level(20, 150, "C:\\Users\\musuf\\workspace\\biscotty\\img\\cut.jpg", frame);
        frame.add(level1);
        frame.setSize(2000, 1500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		level1.setFocusable(true);
		frame.addMouseListener(level1);

		while (true) {
			level1.requestFocusInWindow();
			level1.update();
			Thread.sleep(10);
		}
	}
}
