import java.awt.*;
import javax.swing.*;

public class Target extends JLabel {
	private int vx;
	private int vy;
	private Level level;

	public Target(ImageIcon i, int width, int height, Level l) {
		super(i);
		setPreferredSize(new Dimension(width, height));
		setVisible(true);
		level = l;
	}

	public void setVelocity(int x, int y) {
		vx = x;
		vy = y;
	}

	public void move() {
		move(vx, vy);
	}

	public void move(int vx, int vy) {
		if (getX() + vx < 0 || getX() + getWidth() + vx > level.getWidth()) {
			this.vx *= -1;
			this.vy = (Math.abs(vy)/vy)*((int)(Math.random() * 5) + 5);
		} else if (getY() + vy < 0 || getY() + getHeight() + vy > level.getHeight() - 500) {
			this.vy *= -1;
			this.vx = (Math.abs(vx) / vx)*((int)(Math.random() * 5) + 5);
		} else {
			setBounds(getX() + vx, getY() + vy, getWidth(), getHeight());
		}
	}

	public void appear() {
		setVisible(true);
	}

	public void disappear() {
		setVisible(false);
	}

	public boolean isHit(Projectile p) {
		if ((getX() <= p.getX() + p.getWidth() && getX() + getWidth() >= p.getX())
				&& (getY() <= p.getY() + p.getHeight() && getY() + getHeight() >= p.getY())) {
			disappear();
			return true;
		} else {
			return false;
		}
	}
}
