import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage bufferedImage;
	private Level level;
	Graphics2D bufferedImageGraphic;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel(Level level) {
		bufferedImage = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		bufferedImageGraphic = (Graphics2D) bufferedImage.getGraphics();
		bufferedImageGraphic.setBackground(Color.BLACK);
		this.level = level;
	}

	public void updateGameUI(GameReporter reporter){
		bufferedImageGraphic.clearRect(0, 0, 400, 600);
		
		bufferedImageGraphic.setColor(Color.WHITE);		
        bufferedImageGraphic.drawString(String.format("Score : %08d", reporter.getScore()), 300, 20);
		bufferedImageGraphic.drawString(String.format("LifePoint : %d", reporter.getLifePoint()), 300, 40);
		bufferedImageGraphic.drawString(String.format("Level : %d",level.getLevel() ), 300, 60);
        
		for(Sprite s : sprites){
			s.draw(bufferedImageGraphic);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D graphic2D = (Graphics2D) g;
		graphic2D.drawImage(bufferedImage, null, 0, 0);
	}

}
