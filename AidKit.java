import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class AidKit extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	
	private int step = 10;
    private boolean alive = true;
	
	public AidKit(int x, int y) {
		super(x, y, 10, 10);
		
	}

	@Override
	public void draw(Graphics2D g) {
        try {
            if(y < Y_TO_FADE)
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            else{
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
            (float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
            }
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
		} catch (Exception e) {
            
        }

	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
}