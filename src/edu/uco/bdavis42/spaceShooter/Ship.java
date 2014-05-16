package edu.uco.bdavis42.spaceShooter;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Ship implements GameFigure{
    
    private Image launcherImage;
    private float x, y;
    private int state;
    // 'static': created before any 'thread' is created, thread-safe
    private static Ship user =
            new Ship();

    // 'private': no public access to this contructor
    private Ship() {
         
        x = GamePanel.PWIDTH / 2 - 30;
        y = GamePanel.PHEIGHT - 60;
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
                + "spaceship3.png");
    }

public static Ship getInstance() {
        return user;
    }
    
    public float getX() {
        return x;
    }
    
    public void setX(float z) {
        x = z;
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
        g.drawImage(launcherImage, (int)x, (int)y, null);
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
        return( new Rectangle((int)this.x,(int)this.y,25, 25));
    
     }
    public String toString(){
        return "user";
    }
}
