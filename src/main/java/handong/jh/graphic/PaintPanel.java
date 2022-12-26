package handong.jh.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener {

    int width, height;
    Point beforePoint;
    Point afterPoint;
    Point bandStartPoint = new Point();
    Point bandEndPoint = new Point();
    static Color currentColor = Color.black;
    static float currentStroke = 5f;

    boolean lineDraw = false;

    ArrayList<Geometry> drawing = new ArrayList<>();
    ArrayList<Geometry> reDraw = new ArrayList<>();

    public static int mode = 0; // 0: Line, 1: Rect, 2: Circle, 3: Sketch, 4: Erase, 5: Erase Object, 6: EraseAll

    public float dash1[] = {3, 3f};

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        for(int i = 0 ; i < drawing.size() ; i++) {
            ((Graphics2D) g).setStroke(new BasicStroke(drawing.get(i).getStrokeSize()));
            g.setColor(drawing.get(i).getStrokeColor());

            if(drawing.get(i).getMode() != 3) {
                int x1 = (int) drawing.get(i).getBeginPointX();
                int y1 = (int) drawing.get(i).getBeginPointY();
                int x2 = (int) drawing.get(i).getEndPointX();
                int y2 = (int) drawing.get(i).getEndPointY();

                int minX = Math.min(x1, x2);
                int minY = Math.min(y1, y2);
                int distX = abs(x1 - x2);
                int distY = abs(y1 - y2);

                switch (drawing.get(i).getMode()) {
                    case 0 -> g.drawLine(x1, y1, x2, y2);
                    case 1 -> g.drawRect(minX, minY, distX, distY);
                    case 2 -> g.drawOval(minX, minY, distX, distY);
                }
            }
            else if(drawing.get(i).getMode() == 3)
            {
                for(int j = 1 ; j < drawing.get(i).getLine().size() ; j++)
                {
                    g.drawLine(drawing.get(i).getLine().get(j - 1).x, drawing.get(i).getLine().get(j - 1).y, drawing.get(i).getLine().get(j).x, drawing.get(i).getLine().get(j).y);
                }
            }
        }

        ((Graphics2D) g).setStroke(new BasicStroke(currentStroke, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, dash1, 0));
        g.setColor(currentColor);

        // rubber band
        if(mode != 3)
        {
            int x1 = bandStartPoint.x;
            int y1 = bandStartPoint.y;
            int x2 = bandEndPoint.x;
            int y2 = bandEndPoint.y;

            int minX = Math.min(x1, x2);
            int minY = Math.min(y1, y2);
            int distX = abs(x1 - x2);
            int distY = abs(y1 - y2);
            switch (mode) {
                case 0 -> g.drawLine(x1, y1, x2, y2);
                case 1 -> g.drawRect(minX, minY, distX, distY);
                case 2 -> g.drawOval(minX, minY, distX, distY);
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
        addMouseMotionListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if(mode != 3) {
            beforePoint.setLocation(e.getX(), e.getY());
            bandStartPoint.setLocation(e.getX(), e.getY());
        }
        else
            drawing.add(new Geometry(currentColor, currentStroke));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(mode != 3) {
            System.out.println("x : " + e.getX() + " y : " + e.getY());
            afterPoint.setLocation(e.getX(), e.getY());

            drawing.add(new Geometry(beforePoint, afterPoint, mode, currentColor, currentStroke));
            repaint();
            beforePoint = new Point();
            afterPoint = new Point();

            bandStartPoint = new Point();
            bandEndPoint = new Point();
        }
        if(mode == 3) {
            lineDraw = false;
            System.out.println(drawing.get(drawing.size() - 1).getLine().size());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(mode == 3)
        {
            beforePoint.setLocation(e.getX(), e.getY());
            drawing.get(drawing.size() - 1).getLine().add(beforePoint);
            beforePoint = new Point();
            repaint();
        }
        else
        {
            bandEndPoint.setLocation(e.getX(), e.getY());
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}