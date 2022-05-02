import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class GrafikTE20 extends Canvas {
    private final int height = 600;
    private final int width = 800;
    int treeX = 200;
    int treeY = 200;
    int cloudX = 500;
    int cloudY = 100;
    int houseX = 350;
    int houseY = 220;
    int sunX = 50;
    int sunY = 50;
    int skyX = 0;
    int skyY = 0;
    int moonX = width-100;
    int moonY = height-100;

    int marioX = 0;
    int marioY = 0;
    int marioVX = 0;
    int marioVY = 0;

    private BufferedImage marioimg;

    public GrafikTE20() {
        JFrame frame = new JFrame("Del 1");
        this.setSize(width, height);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addKeyListener(new KL());

        try {
            marioimg = ImageIO.read(new File("supermario2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {

        if (sunY == 50 && sunX < 700) {
            sunX += 5;
            moonX -= 5;
        }
        if (sunX == 700) {
            sunY += 5;
            moonY -= 5;
        }
        if (sunY == 500) {
            sunX -= 5;
            moonX += 5;
        }
        if (sunX == 50) {
            sunY -= 5;
            moonY += 5;
        }

        if (sunY > 260) {
            skyY = 260;
        } else {
            skyY = 0;
        }

        if (marioX + marioVX < width+4*marioimg.getWidth() && marioX + marioVX > 0)
            marioX += marioVX;
        //if (marioY + marioVY < height && marioY + marioVY > 250-4*marioimg.getHeight())
            marioY += marioVY;

        cloudX += 2;
        if (cloudX > width){
            cloudX = 0;
        }
        draw(g);
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();

    }

    public void draw(Graphics g) {
        drawNightsky(g, 0, 0);
        drawSky(g, skyX, skyY);
        drawSun(g, sunX, sunY);
        drawMoon(g, moonX, moonY);
        drawGround(g, 0, 260);

        drawTree(g, treeX,treeY);
        drawTree(g, treeX+30,treeY);
        drawTree(g, treeX+60,treeY);
        drawHouse(g, houseX, houseY);
        drawCloud(g, cloudX, cloudY);
        g.drawImage(marioimg, marioX, marioY, 4*marioimg.getWidth(), 4*marioimg.getHeight(),null);
    }

    private void drawCloud(Graphics g, int x, int y) {
        g.setColor(Color.white);
        g.fillOval(x, y, 30, 30);
        g.fillOval(x+20, y+10, 30, 30);
        g.fillOval(x+30, y-5, 30, 30);
    }

    private void drawHouse(Graphics g, int x, int y) {
        g.setColor(Color.red);
        g.fillRect(x, y, 70, 40);
        g.setColor(Color.black);
        g.fillArc(x, y-20, 70, 40, 0, 180);
    }

    private void drawSun(Graphics g, int x, int y) {
        g.setColor(Color.yellow);
        g.fillOval(x, y, 50, 50);
    }

    private void drawMoon(Graphics g, int x, int y) {
        g.setColor(Color.white);
        g.fillOval(x, y, 50, 50);
    }

    private void drawSky(Graphics g, int x, int y) {
        g.setColor(new Color(0x00DDFF));
        g.fillRect(x, y, width, 260);
    }

    private void drawNightsky(Graphics g, int x, int y) {
        g.setColor(new Color(0x888888));
        g.fillRect(x, y, width, 260);
    }

    private void drawGround(Graphics g, int x, int y) {
        g.setColor(Color.green);
        g.fillRect(x, y, width, height);
    }

    private void drawTree(Graphics g, int x, int y) {
        g.setColor(new Color(0x00DD33));
        g.fillRect(x,y,20,40);
        g.setColor(Color.black);
        g.fillRect(x+8,y+40,4,20);
    }


    public static void main(String[] args) {
        GrafikTE20 exempel = new GrafikTE20();

    }

    private class KL implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyChar() == 'a') {
                marioVX = -5;
            }
            if (keyEvent.getKeyChar() == 'd') {
                marioVX = 5;
            }
            if (keyEvent.getKeyChar() == 'w') {
                marioVY = -5;
            }
            if (keyEvent.getKeyChar() == 's') {
                marioVY = 5;
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            if (keyEvent.getKeyChar() == 'a') {
                marioVX = 0;
            }
            if (keyEvent.getKeyChar() == 'd') {
                marioVX = 0;
            }
            if (keyEvent.getKeyChar() == 'w') {
                marioVY = 0;
            }
            if (keyEvent.getKeyChar() == 's') {
                marioVY = 0;
            }
        }
    }
}
