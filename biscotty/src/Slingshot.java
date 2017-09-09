import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Slingshot extends JLabel {
	private int power;
	private Level level;

	public Slingshot(ImageIcon i, int width, int height, Level l) {
		super(i);
		level = l;
		setPreferredSize(new Dimension(width, height));
		setVisible(true);
		setBounds(level.getWidth()/2, level.getHeight() - height, width, height);
	}

    //TESTING
    public Slingshot(String s, int width, int height, Level l) {
		super(s);
		level = l;
		setPreferredSize(new Dimension(width, height));
		setVisible(false);
	}

    public void move(int vx) {
		if(getX()+vx >= 0 && getX()+getWidth()+vx <= level.getWidth())
			setBounds(getX() + vx, getY(), getWidth(), getHeight());
	}

	public void incPower() {
		setPower(getPower() + 1);
		if(power >= 100) {
			setPower(100);
			shoot();
		}
	}

	public void setPower(int p) {
		power = p;
	}

	public int getPower() {
		return power;
	}

	public Projectile shoot() {
		power = 5;
		Projectile p = new Projectile(new ImageIcon(getScaledImage(new ImageIcon("C:\\Users\\musuf\\workspace\\biscotty\\img\\dogbone.png").getImage(), 100, 100)), 100, 100, power);
		setPower(0);
		add(p);
		return p;
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
}
