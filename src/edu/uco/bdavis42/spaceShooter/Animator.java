package edu.uco.bdavis42.spaceShooter;


public class Animator implements Runnable {
    public static boolean gameOver;
    boolean running;
    GamePanel gamePanel = null;
    GameData gameData = null;
    
    public Animator() {
    }
    
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }
   

    @Override
    public void run() {
        running = true;
        gameOver = false;
        while (running) {
            while(gameOver == false){
            
            gameData.update();
            gamePanel.gameRender();
            gamePanel.printScreen();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                
            }
            }
            
            gamePanel.gameOver();
             gameData.reset();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                
            }
            
        }
        System.exit(0);
    }
    
}
