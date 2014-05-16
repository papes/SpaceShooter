package edu.uco.bdavis42.spaceShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public abstract class Enemy implements GameFigure {
    
    Image launcherImage;
    float x, y;
    int state;
    Color color = Color.red;


    public float getX() {
        return x;
    }
    
    public static Image getImage(String fileName) {
        Image image = null;
        try {
            image = ImageIO.read(new File(fileName));
        } catch (Exception ioe) {
            System.out.println("Error: Cannot open image:" + fileName);
            JOptionPane.showMessageDialog(null, "Error: Cannot open image:" + fileName);
        }
        return image;
    }
    
    // Missile shoot location
    public float getXofMissileShoot() {
        return x+30;
    }
    
    public float getYofMissileShoot() {
        return y+20;
    }

    @Override
    public void render(Graphics g) {
        
    }

    @Override
    public void update() {
    }

    @Override
    public int getState() {
        return state;
    }
    
    public void setState(int x) {
        this.state =x;
    }
    
    @Override
    public Rectangle getBounds(){
        return( new Rectangle((int)x,(int)y + 2,10, 10));
    
     }
    public String toString(){
        return "enemy";
    }
    
    public abstract void move();
    public abstract void shoot();
    
    public void doCycle(){
        move();
        shoot();
    }
}
