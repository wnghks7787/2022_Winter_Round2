package handong.jh.graphic;

import java.awt.*;

public class Geometry {

    private Point beginPoint;
    private Point endPoint;

    private int mode;
    private Color strokeColor;
    private float strokeSize;

    public Geometry(Point beginPoint, Point endPoint, int mode, Color strokeColor, float strokeSize)
    {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
        this.mode = mode;
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

    public double getBeginPointY() {
        return beginPoint.getY();
    }

    public double getEndPointX() {
        return endPoint.getX();
    }

    public double getEndPointY() {
        return endPoint.getY();
    }

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
}
