package handong.jh.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.setStroke(new BasicStroke(6.5f));
        g.drawLine((BTN_SIZE_W + 10) * 4 + 10, 0, (BTN_SIZE_W + 10) * 4 + 10,  getHeight());
        g.drawLine(0, getHeight(), getWidth(), getHeight());
    }

    static final int BTN_SIZE_W = 70;
    static final int BTN_SIZE_H = 25;
    public MenuPanel(int width)
    {
        setLayout(null);
        setBounds(0, 0, width, 50);
        setBackground(Color.GRAY);

        addDrawButton();
        addStrokeButton();
    }

    void addDrawButton()
    {
        JButton[] drawBtn = new JButton[4];

        for(int i = 0 ; i < 4 ; i++)
        {
            drawBtn[i] = new JButton();

            drawBtn[i].setBounds((10 + BTN_SIZE_W)  * i + 5, 10, BTN_SIZE_W, BTN_SIZE_H);
            add(drawBtn[i]);
        }
        drawBtn[0].setText("Line");
        drawBtn[1].setText("Rect");
        drawBtn[2].setText("Circle");
        drawBtn[3].setText("Sketch");

        drawBtn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaintPanel.mode = 0;
            }
        });
        drawBtn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaintPanel.mode = 1;
            }
        });
        drawBtn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaintPanel.mode = 2;
            }
        });
        drawBtn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaintPanel.mode = 3;
            }
        });
    }

    void addStrokeButton()
    {
        JButton[] strokeBtn = new JButton[2];

        for(int i = 0 ; i < 2 ; i++)
        {
            strokeBtn[i] = new JButton();

            strokeBtn[i].setBounds((BTN_SIZE_W + 10) * (4 + i) + 30, 10, BTN_SIZE_W, BTN_SIZE_H);
            add(strokeBtn[i]);
        }
        strokeBtn[0].setText("+");
        strokeBtn[1].setText("-");

        strokeBtn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(PaintPanel.currentStroke < 10f)
                    PaintPanel.currentStroke += 1f;
            }
        });
        strokeBtn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(PaintPanel.currentStroke > 1f)
                    PaintPanel.currentStroke -= 1f;
            }
        });
    }
}
