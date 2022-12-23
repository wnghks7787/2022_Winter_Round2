package handong.jh.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    static final int BTN_SIZE_W = 70;
    static final int BTN_SIZE_H = 20;
    public MenuPanel(int width)
    {
        setLayout(null);
        setBounds(0, 0, width, 50);
        setBackground(Color.YELLOW);

        JButton[] btn = new JButton[4];

        for(int i = 0 ; i < 4 ; i++)
        {
            btn[i] = new JButton();

            btn[i].setBounds((10 + BTN_SIZE_W)  * i + 5, 10, BTN_SIZE_W, BTN_SIZE_H);
            add(btn[i]);
        }
        btn[0].setText("Line");
        btn[1].setText("Rect");
        btn[2].setText("Circle");
        btn[3].setText("Sketch");

        btn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaintPanel.mode = 0;
            }
        });
        btn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaintPanel.mode = 1;
            }
        });
        btn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaintPanel.mode = 2;
            }
        });
        btn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaintPanel.mode = 3;
            }
        });
    }
}
