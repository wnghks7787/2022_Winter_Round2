package handong.jh.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener {

    // 기본정보
    // 가로길이, 세로길이
    int width, height;

    // Geometry class 에 들어갈 내용들
    // beforePoint == 시작점 | afterPoint == 끝점 | mode == 현재 모드 | currentColor == 현재 색상 | currentStroke == 현재 선 굵기
    Point beforePoint;
    Point afterPoint;
    public static int mode = 0; // 0: Line, 1: Rect, 2: Circle, 3: Sketch, 4: Erase, 5: Erase Object, 6: EraseAll
    static Color currentColor = Color.black;
    static float currentStroke = 5f;

    // RubberBand 를 하기 위한 시작점과 끝점
    Point bandStartPoint = new Point();
    Point bandEndPoint = new Point();
    // RubberBand 점선 표현을 위한 변수
    public float[] dash1 = {3, 3f};

    boolean lineDraw = false;
//    Point selectPoint = new Point();

    // drawing == 그림 그린 거 | reDraw == UNDO 를 위한 ArrayList
    ArrayList<Geometry> drawing = new ArrayList<>();
    ArrayList<Geometry> reDraw = new ArrayList<>();

    // Drag and Drop 을 위한 변수들
    Point dragStartPoint = new Point();
    Point dragEndPoint = new Point();
    static boolean clickOn = false;


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
                ((Graphics2D) g).setStroke(new BasicStroke(drawing.get(i).getStrokeSize(), BasicStroke.CAP_ROUND, 0));
                for(int j = 1 ; j < drawing.get(i).getLine().size() ; j++)
                {
                    g.drawLine(drawing.get(i).getLine().get(j - 1).x, drawing.get(i).getLine().get(j - 1).y, drawing.get(i).getLine().get(j).x, drawing.get(i).getLine().get(j).y);
                }
            }
        }

        if(!drawing.isEmpty() && drawing.get(drawing.size() - 1).getMode() != 3)
        {
            ((Graphics2D)g).setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, dash1, 0));
            g.setColor(Color.GRAY);

            g.drawRect((int)Math.min(drawing.get(drawing.size() - 1).getBeginPointX(), drawing.get(drawing.size() - 1).getEndPointX()), (int)Math.min(drawing.get(drawing.size() - 1).getBeginPointY(), drawing.get(drawing.size() - 1).getEndPointY()), (int)abs(drawing.get(drawing.size() - 1).getBeginPointX() - drawing.get(drawing.size() - 1).getEndPointX()), (int)abs(drawing.get(drawing.size() - 1).getBeginPointY() - drawing.get(drawing.size() - 1).getEndPointY()));
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
        this.height = height - 100;
        setBounds(0, 100, this.width, this.height);
        setBackground(Color.WHITE);

        beforePoint = new Point(0, 0);
        afterPoint = new Point(0, 0);

        addMouseListener(this);
        addMouseMotionListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if(clickOn)
//        {
//            System.out.println("x : " + e.getX() + " y : " + e.getY());
//            selectPoint = e.getPoint();
//            double x = selectPoint.getX();
//            double y = selectPoint.getY();
//            double x1 = 0, y1 = 0, x2 = 0, y2 = 0;
//
//            for(int i = 0 ; i < drawing.size() ; i++)
//            {
//                if(drawing.get(i).getMode() != 3)
//                {
//                    x1 = drawing.get(i).getBeginPointX();
//                    y1 = drawing.get(i).getBeginPointY();
//                    x2 = drawing.get(i).getEndPointX();
//                    y2 = drawing.get(i).getEndPointY();
//                }
//                switch (drawing.get(i).getMode())
//                {
//                    case 0:
//                        double gradient = (((y2 - y1) / (x2 - x1)) * (x - x1) - y1);
//                        if(y >= gradient - 15 && y <= gradient + 15)
//                        {
//                            System.out.println("눌렸습니다.");
//                        }
//                        else
//                            System.out.println("오차가 있습니다.");
//                        break;
//                    case 1:
//                        System.out.println("준비중");
//                        break;
//                }
//            }
//        }

    }

    public void mousePressed(MouseEvent e) {
        if(!drawing.isEmpty() && drawing.get(drawing.size() - 1).getMode() != 3)
        {
            int endSize = drawing.size() - 1;
            Point startPoint = drawing.get(endSize).getBeginPoint();
            Point endPoint = drawing.get(endSize).getEndPoint();

            int minX = Math.min(startPoint.x, endPoint.x);
            int minY = Math.min(startPoint.y, endPoint.y);
            int maxX = Math.max(startPoint.x, endPoint.x);
            int maxY = Math.max(startPoint.x, endPoint.y);

            if(e.getX() >= minX && e.getY() >= minY && e.getX() <= maxX && e.getY() <= maxY)
            {
                clickOn = true;
            }
        }
        if(clickOn)
            dragStartPoint = e.getPoint();
        else
        {
            if(mode != 3) {
                beforePoint.setLocation(e.getX(), e.getY());
                bandStartPoint.setLocation(e.getX(), e.getY());
            }
            else
                drawing.add(new Geometry(currentColor, currentStroke));
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(clickOn)
        {
            clickOn = false;
            dragStartPoint = new Point();
            dragEndPoint = new Point();
        }
        else {

            if (mode != 3) {
                afterPoint.setLocation(e.getX(), e.getY());

                drawing.add(new Geometry(beforePoint, afterPoint, mode, currentColor, currentStroke));
                repaint();
                beforePoint = new Point();
                afterPoint = new Point();

                bandStartPoint = new Point();
                bandEndPoint = new Point();
            }
            if (mode == 3) {
                lineDraw = false;
                System.out.println(drawing.get(drawing.size() - 1).getLine().size());
            }
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
        if(clickOn)
        {
            int endSize = drawing.size() - 1;
            Point drawStart = drawing.get(endSize).getBeginPoint();
            Point drawEnd = drawing.get(endSize).getEndPoint();

            dragEndPoint = e.getPoint();

            int dragX = dragEndPoint.x - dragStartPoint.x;
            int dragY = dragEndPoint.y - dragStartPoint.y;

            drawing.get(endSize).setBeginPoint(new Point(drawStart.x + dragX, drawStart.y + dragY));
            drawing.get(endSize).setEndPoint(new Point(drawEnd.x + dragX, drawEnd.y + dragY));
            repaint();
            dragStartPoint = dragEndPoint;
        }
        else {
            if (mode == 3) {
                beforePoint.setLocation(e.getX(), e.getY());
                drawing.get(drawing.size() - 1).getLine().add(beforePoint);
                beforePoint = new Point();
                repaint();
            } else {
                bandEndPoint.setLocation(e.getX(), e.getY());
                repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}