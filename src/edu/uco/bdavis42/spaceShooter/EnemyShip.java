package edu.uco.bdavis42.spaceShooter;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class EnemyShip extends Enemy {
    Ship user = Ship.getInstance();

    public EnemyShip(float x, float y) {
        this.x = x;
        this.y = y;
        state = STATE_TRAVELING;
        String imagePath = System.getProperty("user.dir");
        // separator: Windows '\', Linux '/'
        String separator = System.getProperty("file.separator");

        // put images in 'images' folder, which is on the top level of
        // the NetBeans project folder.
        // Using "Files" tab of the NetBeans explorer window, right click on
        // the project folder name, and create a folder named "image"
        // You cannot see "images" folder in 'Project' tab, though
        launcherImage = getImage(imagePath + separator + "images" + separator
                + "spaceship1.png");
    }

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
        return x + 30;
    }

    public float getYofMissileShoot() {
        return y + 20;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(launcherImage, (int) x, (int) y, null);
    }

    @Override
    public void update() {
        
            this.doCycle();
            if(this.y >= GamePanel.PHEIGHT-60){
                Animator.gameOver = true;
            }
        
    }

    @Override
    public int getState() {
        return state;
    }

    public void setState(int x) {
        this.state = x;
    }

    

    public String toString() {
        return "enemy";
    }

    public void move() {
        int pivot = 1 + (int) (Math.random() * 100);

        if (pivot > 60 && this.x < 540) {
            this.x += 20;


        } else if (pivot < 40 && this.x > 19) {
            this.x -= 20;
        } else {
            this.y += 1;
        }
    }

    public void shoot() {
        int cooldown = 1 + (int) (Math.random() * 100);
        if(cooldown <= 2){

        
        Missile f = new Missile(this.x, this.y, color);
        f.setTarget(user.getX(), GamePanel.PHEIGHT);
        int size = (int) (Math.random() * 10) + 2; // min = 5 max = 105
        f.setExplosionMaxSize(size);

        synchronized (GameData.figures) {
            GameData.figures.add(f);
        }
        }
    }
}
