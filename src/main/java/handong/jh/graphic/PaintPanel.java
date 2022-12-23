package handong.jh.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener {

    int width, height;
    Point beforePoint;
    Point afterPoint;
    Color currentColor = Color.black;
    float currentStroke = 5f;

    Graphics g = getGraphics();
    ArrayList<Geometry> drawing = new ArrayList<>();

    public static int mode = 0; // 0: Line, 1: Rect, 2: Circle, 3: Sketch

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        System.out.println("TRY");
        for(int i = 0 ; i < drawing.size() ; i++) {
            int x1 = (int)drawing.get(i).getBeginPointX();
            int y1 = (int)drawing.get(i).getBeginPointY();
            int x2 = (int)drawing.get(i).getEndPointX();
            int y2 = (int)drawing.get(i).getEndPointY();

            int minX = Math.min(x1, x2);
            int minY = Math.min(y1, y2);
            int distX = abs(x1 - x2);
            int distY = abs(y1 - y2);

            System.out.println("i : " + i);
            System.out.println("mode : " + drawing.get(i).getMode());

            switch (drawing.get(i).getMode()) {
                case 0 -> g.drawLine(x1, y1, x2, y2);
                case 1 -> g.drawRect(minX, minY, distX, distY);
                case 2 -> g.drawArc(minX, minY, distX, distY, 0, 360);
            }
        }
    }

    public PaintPanel(int width, int height)
    {
        this.width = width;
        this.height = height - 50;
        setBounds(0, 50, this.width, this.height);
        setBackground(Color.WHITE);

        beforePoint = new Point(0, 0);
        afterPoint = new Point(0, 0);

        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        beforePoint.setLocation(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("x : " + e.getX() + " y : " + e.getY());
        afterPoint.setLocation(e.getX(), e.getY());

        drawing.add(new Geometry(beforePoint, afterPoint, mode, currentColor, currentStroke));
        repaint();
        beforePoint = new Point();
        afterPoint = new Point();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
