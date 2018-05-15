import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
    private ArrayList<EnemyShip> enemies = new ArrayList<EnemyShip>();
    private ArrayList<AidKit> aidKits = new ArrayList<AidKit>();
    private SpaceShip1 ship1;	
    private SpaceShip2 ship2;
	
    private Timer timer;
    private Level levelGame;
	private long score = 0;
	private double difficulty = 0.05;
    private int lifePoint = 10;

    private int count = 0;
 
	public GameEngine(GamePanel gp, SpaceShip1 ship1, SpaceShip2 ship2,Level level) {
		this.gp = gp;
        this.ship1 = ship1;	
        this.ship2 = ship2;	
		this.levelGame = level;
        gp.sprites.add(ship1);
        gp.sprites.add(ship2);
		
		timer = new Timer(50, new ActionListener() {
            
            
			@Override
			public void actionPerformed(ActionEvent arg0) {
                process();
                count++;
                if(count >= 50) {
                    count = 0;
                    levelGame.increaseLevel();
                    levelGame.increaseDifficulty();
                    levelGame.getLevel();
                    levelGame.getDifficulty();
                }
			}
		});
		timer.setRepeats(true);
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
        EnemyShip e = new EnemyShip((int)(Math.random()*390), 30);
        gp.sprites.add(e);
		enemies.add(e);
    }
    
    private void generateAidKit(){
        AidKit a = new AidKit((int)(Math.random()*390), 30);
        gp.sprites.add(a);
        aidKits.add(a);
    }
	
	private void process(){
		if(Math.random() < difficulty){
            generateEnemy();
            generateAidKit();
		}
		
		Iterator<EnemyShip> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			EnemyShip e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
                lifePoint--;
				e_iter.remove();
                gp.sprites.remove(e);
			}
        }
        
        Iterator<AidKit> a_iter = aidKits.iterator();
		while(a_iter.hasNext()){
			AidKit a = a_iter.next();
			a.proceed();
			
        }

		gp.updateGameUI(this);

        Rectangle2D.Double ship1Check = ship1.getRectangle();
        Rectangle2D.Double ship2Check = ship2.getRectangle();
        
        Rectangle2D.Double enemyCheck2;
        Rectangle2D.Double aidCheck;
        
        Iterator<EnemyShip> e_iter2 = enemies.iterator();
		while(e_iter2.hasNext()){
            EnemyShip e = e_iter2.next();
            enemyCheck2 = e.getRectangle();
			if(enemyCheck2.intersects(ship1Check)){
                score += 50;
                e_iter2.remove();
                gp.sprites.remove(e);
				return;
            }
            if(enemyCheck2.intersects(ship2Check)){
                score += 50;
                e_iter2.remove();
                gp.sprites.remove(e);
                return;
            }
        }
        
        Iterator<AidKit> a_iter2 = aidKits.iterator();
		while(a_iter2.hasNext()){
			AidKit a = a_iter2.next();
            aidCheck = a.getRectangle();
            if(aidCheck.intersects(ship1Check)){
                lifePoint++;
                a_iter2.remove();
                gp.sprites.remove(a);
				return;
            }
            if(aidCheck.intersects(ship2Check)){
                lifePoint++;
                a_iter2.remove();
                gp.sprites.remove(a);
                return;
            }
			
        }

        if(lifePoint <= 0) 
            die();
	}
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {

		case KeyEvent.VK_LEFT:
			ship1.moveX(-1);
			break;
		case KeyEvent.VK_RIGHT:
			ship1.moveX(1);
            break;
        case KeyEvent.VK_UP:
            ship1.moveY(-1);
            break;
        case KeyEvent.VK_DOWN:
            ship1.moveY(1);
            break;

        case KeyEvent.VK_A:
            ship2.moveX(-1);
            break;
        case KeyEvent.VK_D:
            ship2.moveX(1);
            break;
        case KeyEvent.VK_W:
            ship2.moveY(-1);
            break;
        case KeyEvent.VK_S:
            ship2.moveY(1);
            break;
		}
    }

	public long getScore(){
		return score;
    }
    
    public int getLifePoint(){
        return lifePoint;
    }
    
    //public int getLevel(){
    //    return level;
    ///}

	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}