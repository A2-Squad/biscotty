import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Level extends JPanel implements MouseListener {
	private Slingshot shooter;
	private ArrayList<Projectile> projectiles;
	private Target[] targets;
	private String backgroundImage;
	private final int SHOOTER_WIDTH = 150; //TODO set value based on image
	private final int SHOOTER_HEIGHT = 300; //TODO set value based on image
	private final int LEVEL_WIDTH = 2000; //TODO set this value
	private final int LEVEL_HEIGHT = 1500; //TODO set this value
	private int shooterX, shooterY;
	private JFrame frame;
	
	public Level(int numTargets, int targetSize, String backgroundPath, JFrame f) {
		int targetWidth = targetSize;
		int targetHeight = targetSize;
		//shooter = new Slingshot(new ImageIcon(""), SHOOTER_WIDTH, SHOOTER_HEIGHT); //TODO replace empty string with path to slingshot image
        this.setLayout(new SpringLayout());
		shooter = new Slingshot(new ImageIcon(getScaledImage(new ImageIcon("C:\\Users\\musuf\\workspace\\biscotty\\img\\cannon.png").getImage(), SHOOTER_WIDTH, SHOOTER_HEIGHT)), SHOOTER_WIDTH, SHOOTER_HEIGHT, this);
		this.add(shooter);
		shooterX = LEVEL_WIDTH/2 - SHOOTER_WIDTH/2;
		shooterY = LEVEL_HEIGHT - SHOOTER_HEIGHT - 50;
		shooter.setBounds(shooterX, shooterY, SHOOTER_WIDTH, SHOOTER_HEIGHT);
		projectiles = new ArrayList<Projectile>();
		targets = new Target[numTargets];
		for(int i = 0; i < numTargets; i++) {
			targets[i] = new Target(new ImageIcon(getScaledImage(new ImageIcon("C:\\Users\\musuf\\workspace\\biscotty\\img\\scottie.png").getImage(), targetWidth, targetHeight)), targetWidth, targetHeight, this); //TODO replace empty string with target image file path
			targets[i].setVelocity((int) (Math.random() * 5) + 5, (int) (Math.random() * 5) + 5);
			this.add(targets[i]);
			targets[i].setBounds(((int)(Math.random() * this.getWidth())), ((int)(Math.random() * (this.getHeight() - 500))), targetWidth, targetHeight);
		}
		this.setPreferredSize(new Dimension(LEVEL_WIDTH, LEVEL_HEIGHT));
        // Commented out for testing purposes only
		JLabel background = new JLabel(new ImageIcon(getScaledImage(new ImageIcon(backgroundPath).getImage(), LEVEL_WIDTH, LEVEL_HEIGHT)));
		background.setPreferredSize(this.getPreferredSize());
		background.setBounds(0, 0, LEVEL_WIDTH, LEVEL_HEIGHT);
		this.add(background);
		frame = f;
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	public void mouseClicked(MouseEvent e) {
		Projectile p = shooter.shoot();
		add(p);
		p.setLocation(shooterX, shooterY);
		projectiles.add(p);
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

    public void update() {
    	for(Target t: targets) {
    		t.move();
    	}
        for (Projectile p: projectiles) {
            p.move();
        }
    }

	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    shooterX = MouseInfo.getPointerInfo().getLocation().x - frame.getLocation().x;
	    //System.out.println(shooterX + ", " + shooterY);
	    shooter.setLocation(shooterX, shooterY);
	    shooter.repaint();
	    for(Projectile p : projectiles)
	    	p.repaint();
	    for(Target t : targets)
	    	t.repaint();
	}
}
