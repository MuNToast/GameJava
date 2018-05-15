import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip2 extends Sprite{

	int step = 20;
	
	public SpaceShip2(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		
	}

	public void moveX(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
    }
    
    public void moveY(int direction){
        y += (step * direction);

        if(y < 200)
            y = 200;
        if(y > 550 - height)
            y = 550 - height;

    }
}