import java.awt.*;
import javax.swing.*;

public class Projectile extends JLabel {
	private int speed;

	public Projectile(ImageIcon i, int width, int height, int speed) {
		super(i);
		setPreferredSize(new Dimension(width, height));
	}

	public void move() {
		setBounds(getX(), getY() - speed, getWidth(), getHeight());
	}

}
