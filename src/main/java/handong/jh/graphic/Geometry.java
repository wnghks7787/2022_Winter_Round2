package handong.jh.graphic;

import java.awt.*;
import java.util.ArrayList;

public class Geometry {

    private Point beginPoint;
    private Point endPoint;

    private int mode;
    private Color strokeColor;
    private float strokeSize;
    private ArrayList<Point> line;

    public Geometry(Point beginPoint, Point endPoint, int mode, Color strokeColor, float strokeSize)
    {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
        this.mode = mode;
        this.strokeColor = strokeColor;
        this.strokeSize = strokeSize;
    }
    public Geometry(Color strokeColor, float strokeSize)
    {
        this.line = new ArrayList<>();
        this.mode = 3;
        this.strokeColor = strokeColor;
        this.strokeSize = strokeSize;
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public void setBeginPoint(Point beginPoint) {
        this.beginPoint = beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public double getBeginPointX() {
        return beginPoint.getX();
    }

    public void setBeginPointX(int beginPointX) {
        this.beginPoint.x = beginPointX;
    }

    public double getBeginPointY() {
        return beginPoint.getY();
    }

    public void setBeginPointY(int beginPointY) {
        this.beginPoint.y = beginPointY;
    }

    public double getEndPointX() {
        return endPoint.getX();
    }

    public void setEndPointX(int endPointX) {
        this.endPoint.x = endPointX;
    }

    public double getEndPointY() {
        return endPoint.getY();
    }

//    public void

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public float getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(float strokeSize) {
        this.strokeSize = strokeSize;
    }

    public ArrayList<Point> getLine() {
        return line;
    }

    public void setLine(ArrayList<Point> line) {
        this.line = line;
    }
}
