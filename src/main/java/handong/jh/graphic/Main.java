package handong.jh.graphic;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new GraphicFrame();
        frame.getContentPane().setBackground(Color.BLACK);
        MenuPanel menuPanel = new MenuPanel(frame.getWidth());
        PaintPanel paintPanel = new PaintPanel(frame.getWidth(), frame.getHeight());

        frame.add(paintPanel);
        frame.add(menuPanel);
    }
}