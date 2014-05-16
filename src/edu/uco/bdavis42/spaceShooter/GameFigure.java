package edu.uco.bdavis42.spaceShooter;


import java.awt.Graphics;
import java.awt.Rectangle;

public interface GameFigure {
    public void render(Graphics g);
    public void update();
    public int getState();
    public void setState(int x);
    public Rectangle getBounds();
        
    static final int STATE_TRAVELING = 1;
    static final int STATE_EXPLODING = 2;
    static final int STATE_DONE = 0;
}
