package handong.jh.graphic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicFrame extends JFrame {

    static final int BTN_SIZE_W = 70;
    static final int BTN_SIZE_H = 25;

    MenuPanel menuPanel = new MenuPanel(1000);
    PaintPanel paintPanel = new PaintPanel(1000, 750);
    public GraphicFrame()
    {
        setSize(1000, 750);
        setTitle("그래픽 에디터");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        add(paintPanel);
        add(menuPanel);

        addUndoButton();
//        addSelectButton();
    }

    void addUndoButton()
    {
        JButton undoBtn = new JButton("Undo");
        JButton redoBtn = new JButton("Redo");
        undoBtn.setBounds(600, 10, BTN_SIZE_W, BTN_SIZE_H);
        redoBtn.setBounds(650, 10, BTN_SIZE_W, BTN_SIZE_H);

        menuPanel.add(undoBtn);
        menuPanel.add(redoBtn);

        undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!paintPanel.drawing.isEmpty()) {
                    paintPanel.reDraw.add(paintPanel.drawing.get(paintPanel.drawing.size() - 1));
                    paintPanel.drawing.remove(paintPanel.drawing.size() - 1);
                    paintPanel.repaint();
                }
            }
        });

        redoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!paintPanel.reDraw.isEmpty()) {
                    paintPanel.drawing.add(paintPanel.reDraw.get(paintPanel.reDraw.size() - 1));
                    paintPanel.reDraw.remove(paintPanel.reDraw.size() - 1);
                    paintPanel.repaint();
                }
            }
        });
    }

//    void addSelectButton()
//    {
//        JButton selectBtn = new JButton("select");
//        selectBtn.setBounds(200, 20, BTN_SIZE_W, BTN_SIZE_H);
//        menuPanel.add(selectBtn);
//
//        selectBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(!paintPanel.clickOn)
//                    paintPanel.clickOn = true;
//                else
//                    paintPanel.clickOn = false;
//                System.out.println("clickOn : " + paintPanel.clickOn);
//            }
//        });
//    }
}
