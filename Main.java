import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Grasp a Fruit");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);
		frame.getContentPane().setLayout(new BorderLayout());
		
		Level levelGame = new Level(0, 0.05); 
        SpaceShip1 ship1 = new SpaceShip1(300, 550, 60, 20);
        SpaceShip2 ship2 = new SpaceShip2(150, 550, 60, 20);
        
		GamePanel gp = new GamePanel(levelGame);
		GameEngine engine = new GameEngine(gp, ship1, ship2, levelGame);
		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		
		engine.start();
	}
}
