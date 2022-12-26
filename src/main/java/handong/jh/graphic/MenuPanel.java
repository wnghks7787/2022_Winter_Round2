package handong.jh.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    public static JButton[] undoBtn = new JButton[2];

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.setStroke(new BasicStroke(6.5f));

        // 그리기 도구 | 선 굵기 버튼
        g.drawLine((BTN_SIZE_W + 10) * 4 + 10, 0, (BTN_SIZE_W + 10) * 4 + 10,  getHeight());
        // 패널 나눠지는 위치
        g.drawLine(0, getHeight(), getWidth(), getHeight());


    }

    static final int BTN_SIZE_W = 70;
    static final int BTN_SIZE_H = 25;
    public MenuPanel(int width)
    {
        setLayout(null);
        setBounds(0, 0, width, 100);
        setBackground(Color.GRAY);

        addDrawButton();
        addStrokeButton();
        addColorButton();
    }

    void addDrawButton()
    {
        JButton[] drawBtn = new JButton[4];

        for(int i = 0 ; i < 4 ; i++)
        {
            drawBtn[i] = new JButton();

            if(i % 2 == 0)
                drawBtn[i].setBounds((10 + BTN_SIZE_W) * (i / 2) + 5, 10, BTN_SIZE_W, BTN_SIZE_H);
            else
                drawBtn[i].setBounds((10 + BTN_SIZE_W) * (i / 2) + 5, 25 + (i % 2) * (BTN_SIZE_H + 10), BTN_SIZE_W, BTN_SIZE_H);
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
        JLabel strokeLabel = new JLabel("Stroke : 5");

        for(int i = 0 ; i < 2 ; i++)
        {
            strokeBtn[i] = new JButton();

            strokeBtn[i].setBounds((BTN_SIZE_W + 10) * (4 + i) + 30, 10, BTN_SIZE_W, BTN_SIZE_H);
            add(strokeBtn[i]);
        }

        strokeLabel.setBounds((BTN_SIZE_W + 10) * 6 + 30, 10, BTN_SIZE_W, BTN_SIZE_H);
        add(strokeLabel);
        strokeBtn[0].setText("+");
        strokeBtn[1].setText("-");



        strokeBtn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(PaintPanel.currentStroke < 10f) {
                    PaintPanel.currentStroke += 1f;

                    strokeLabel.setText("Stroke : " + (int) PaintPanel.currentStroke);
                }
            }
        });
        strokeBtn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(PaintPanel.currentStroke > 1f) {
                    PaintPanel.currentStroke -= 1f;

                    strokeLabel.setText("Stroke : " + (int) PaintPanel.currentStroke);
                }
            }
        });
    }

    void addColorButton()
    {
        JButton colorButton = new JButton();

        colorButton.setBounds(700, 10, BTN_SIZE_H, BTN_SIZE_H);

        colorButton.setOpaque(true);
        colorButton.setBorderPainted(false);
        colorButton.setFocusPainted(false);
        colorButton.setBackground(PaintPanel.currentColor);

        add(colorButton);

        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color myColor = JColorChooser.showDialog(null, "Color", PaintPanel.currentColor);
                if(myColor != null)
                    colorButton.setBackground(myColor);
                PaintPanel.currentColor = myColor;
            }
        });
    }
}