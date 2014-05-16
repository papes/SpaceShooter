package edu.uco.bdavis42.spaceShooter;


public class EnemyFactory {
    
    private int type;
    
    public EnemyFactory(int type){
        this.type = type;
    }
    
    public Enemy spawnEnemy(){
        if(type >5){
            return new Alien( (float)(Math.random()* GamePanel.PWIDTH), 
                (float)(Math.random()* (GamePanel.PHEIGHT/3)));
        }
        else{
            return new EnemyShip((float)(Math.random()* GamePanel.PWIDTH), 
                (float)(Math.random()* (GamePanel.PHEIGHT/3)));
        }
    }
    
}