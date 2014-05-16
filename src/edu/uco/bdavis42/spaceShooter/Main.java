package edu.uco.bdavis42.spaceShooter;


import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
// Spaceship art done by Smileymensch from http://opengameart.org
public class Main extends JFrame
        implements ActionListener, MouseListener, KeyListener {
    int x =0;
    private GamePanel gamePanel;
    final GameData gameData;
    private Animator animator;
    private JButton startButton;
    private JButton quitButton;
    private Ship user;
    private Enemy enemy;
    public static boolean leftPressed = false;
    public static boolean rightPressed = false;
    public static boolean spacePressed = false;
    public static ConcreteObserver scoreKeeper = new ConcreteObserver();
    public static JLabel score = new JLabel("Score:     " + scoreKeeper.getScore());

    public Main() {
        setSize(615, 480);
        setLocation(100, 100);
        
        
        Container c = getContentPane();
        animator = new Animator();
        gameData = new GameData();
        gamePanel = new GamePanel(animator, gameData);
        animator.setGamePanel(gamePanel);
        animator.setGameData(gameData);
        
        gameData.registerObserver(scoreKeeper);
        c.add(gamePanel, "Center");
        
        
        c.add(score, "North");


        JPanel southPanel = new JPanel();
        startButton = new JButton("Start");

        southPanel.add(startButton);
        quitButton = new JButton("Quit");
        southPanel.add(quitButton);
        c.add(southPanel, "South");

        gamePanel.addMouseListener(this);
        startButton.setFocusable(false); // "Start" button click should not receive keyboard data
        gamePanel.setFocusable(true); // receives keyboard data
        gamePanel.addKeyListener(this);
        startButton.addActionListener(this);
        quitButton.addActionListener(this);

        user = user.getInstance(); // launcher
        if(GameData.figures.size()>1){
        enemy = (Enemy) GameData.figures.get(1);
        }
        updateScore();
       
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == startButton) {
            if(x==0){
             
            gamePanel.startGame();
            x++;
            
            }
            
            else{
                Animator.gameOver = false;
                gameData.reset();
                score.setText("Score     " + "0");
            }
            
           
             
             
        } else if (ae.getSource() == quitButton) {
            animator.running = false;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        float x = user.getX();
        Color color;
        color = Color.green;
        Missile f = new Missile(user.getXofMissileShoot(), user.getYofMissileShoot(), color);
        f.setTarget(x, 10);
        int size = (int) (Math.random() * 100) + 5; // min = 5 max = 105
        f.setExplosionMaxSize(size);

        synchronized (GameData.figures) {
            GameData.figures.add(f);

        }

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = true;
                break;
        }
        doInput();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = false;
                break;
        }
        doInput();
    }

    public void doInput() {
        if (leftPressed == true && user.getX() > 19) {
            user.setX(user.getX() - 20);

        }
        if (rightPressed == true && user.getX() < 560) {
            user.setX(user.getX() + 20);
        }
        if (spacePressed == true) {
            float x = user.getX();
            Color color = Color.green;


            Missile f = new Missile(user.getXofMissileShoot(), user.getYofMissileShoot(), color);
            f.setTarget(x, 10);
            int size = (int) (Math.random() * 10) + 2;; // min = 5 max = 105
            f.setExplosionMaxSize(size);

            synchronized (GameData.figures) {
                GameData.figures.add(f);


            }

        }
    }
     private void updateScore(){
        final Timer timer = new Timer(500,null);
        ActionListener listener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(Animator.gameOver == false){
                    score.setText("Score     " + scoreKeeper.getScore());
                }
            }
        };
        timer.addActionListener(listener);
        timer.start();
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    public static void main(String[] args) {
        JFrame game = new Main();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
        
    }
}
