package edu.uco.bdavis42.spaceShooter;


public class ConcreteObserver implements Observer {
 private int score;

    public int getScore() {
        return score;
    }
    @Override
    public void update(int score) {
        this.score = score;
        
        
    }
    
}
