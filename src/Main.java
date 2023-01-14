import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JComponent implements ActionListener {
    static int winW = 810;
    static int winH = 730;
    static int centerX = winW /2;
    static int centerY = (winH-60)/2;
    Image sky = new ImageIcon("src/небо.jpg").getImage();
    Image sun = new ImageIcon("src/солнце2.png").getImage();
    Timer timer = new Timer(1, this);
    Planet earth = new Planet(16,16,120,0.018,"src/земля1.png");
    Planet mercury = new Planet(10,10,80,0.015,"src/меркурий.png");
    Planet venus = new Planet(15,15,100,0.02,"src/Венера.png");
    Planet mars = new Planet(13,13,140,0.012,"src/Марс.png");
    Planet neptun = new Planet(18,18,320,0.007,"src/Нептун.png");
    Planet saturn = new Planet(40,91,240,0.005,"src/Сатурн.png");
    Planet uran = new Planet(25,25,290,0.011,"src/Уран.png");
    Planet jupiter = new Planet(30,30,180,0.01,"src/Юпитер.png");

    public static void main(String[] args) {
        Main t = new Main();

        JFrame frame = new JFrame("Вращение планет вокруг Солнца");
        frame.setSize(winW, winH);
        frame.setLocation(334, 34);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.add(t);
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(sky, 0,0,winW,winH-30,null);
        g2d.drawImage(sun, centerX-75,centerY-75,150,150,null);
        Planet.setG2(g2d);
        earth.rotatePlanet();
        mercury.rotatePlanet();
        venus.rotatePlanet();
        mars.rotatePlanet();
        neptun.rotatePlanet();
        saturn.rotatePlanet();
        jupiter.rotatePlanet();
        uran.rotatePlanet();


        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }
}

class Planet extends JComponent {
    int x;
    int y;
    int h;
    int w;
    int radius;
    double delta;
    double speed;
    int winW = 810;
    int winH = 730;
    int centerX = winW /2;
    int centerY = (winH-60)/2;
    Image planetImage;
    static Graphics2D g2;

    public static void setG2(Graphics2D g2) {
        Planet.g2 = g2;
    }

    Planet (int h, int w, int radius, double speed, String imagePath) {
        this.planetImage = new ImageIcon(imagePath).getImage();
        this.h = h;
        this.w = w;
        this.radius = radius;
        this.speed = speed;

    }

    public void paintPlanet () {
        g2.drawImage(planetImage, x,y,w,h,null);
    }

    public void planetPosition() {
        x= centerX - w/2 + (int) (radius*(Math.cos(delta)));
        y= centerY - h/2 + (int) (radius*(Math.sin(delta)));
        delta += speed;
    }

    public void rotatePlanet () {
        planetPosition();
        paintPlanet();
    }
}