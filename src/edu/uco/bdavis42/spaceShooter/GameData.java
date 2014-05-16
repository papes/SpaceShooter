package edu.uco.bdavis42.spaceShooter;


import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class GameData implements Subject {

    public static final List<GameFigure> figures = Collections.synchronizedList(new ArrayList<GameFigure>());
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    int score;
    EnemyFactory spawn;
    Ship user = Ship.getInstance();

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public GameData() {


        figures.add(user);
        figures.add(new EnemyShip((float) (Math.random() * GamePanel.PWIDTH),
                (float) (Math.random() * (GamePanel.PHEIGHT / 3))));
        figures.add(new Alien((float) (Math.random() * GamePanel.PWIDTH),
                (float) (Math.random() * (GamePanel.PHEIGHT / 3))));
        figures.add(new EnemyShip((float) (Math.random() * GamePanel.PWIDTH),
                (float) (Math.random() * (GamePanel.PHEIGHT / 3))));
        figures.add(new Alien((float) (Math.random() * GamePanel.PWIDTH),
                (float) (Math.random() * (GamePanel.PHEIGHT / 3))));
        figures.add(new EnemyShip((float) (Math.random() * GamePanel.PWIDTH),
                (float) (Math.random() * (GamePanel.PHEIGHT / 3))));
        figures.add(new Alien((float) (Math.random() * GamePanel.PWIDTH),
                (float) (Math.random() * (GamePanel.PHEIGHT / 3))));


    }

    public void update() {
        List<GameFigure> remove = new ArrayList<GameFigure>();
        GameFigure f;
        int enemyObjects = 0;
        synchronized (figures) {
            for (int i = 0; i < figures.size(); i++) {
                f = figures.get(i);
                if ("enemy".equals(f.toString())) {
                    enemyObjects++;
                }


                f.update();
                if (f.getState() == GameFigure.STATE_DONE) {
                    if ("enemy".equals(f.toString())) {
                        score += 100;
                        notifyObservers();
                    }
                    remove.add(f);
                }
            }
            figures.removeAll(remove);
            while (enemyObjects < 7) {
                spawn = new EnemyFactory((int) (Math.random() * 10));
                figures.add(spawn.spawnEnemy());
                enemyObjects++;

            }
        }
    }

    public List<GameFigure> getFigures() {
        return figures;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer ob : observers) {
            ob.update(this.score);
           
        }
    }
    
    public void reset(){
        figures.removeAll(figures);
        score = 0;
        notifyObservers();
        user.setX(GamePanel.PWIDTH / 2 - 30);
        figures.add(user);
        figures.add(new EnemyShip((float) (Math.random() * GamePanel.PWIDTH),
                (float) (Math.random() * (GamePanel.PHEIGHT / 3))));
        figures.add(new Alien((float) (Math.random() * GamePanel.PWIDTH),
                (float) (Math.random() * (GamePanel.PHEIGHT / 3))));
        figures.add(new EnemyShip((float) (Math.random() * GamePanel.PWIDTH),
                (float) (Math.random() * (GamePanel.PHEIGHT / 3))));
        figures.add(new Alien((float) (Math.random() * GamePanel.PWIDTH),
                (float) (Math.random() * (GamePanel.PHEIGHT / 3))));
        figures.add(new EnemyShip((float) (Math.random() * GamePanel.PWIDTH),
                (float) (Math.random() * (GamePanel.PHEIGHT / 3))));
        figures.add(new Alien((float) (Math.random() * GamePanel.PWIDTH),
                (float) (Math.random() * (GamePanel.PHEIGHT / 3))));


    }
    
}
