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
//        frame.pack();

//        frame.getContentPane().add(paintPanel);
//        frame.getContentPane().add(menuPanel);
    }

//    btn[1].addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            addNumber = String.valueOf(btn[1].numValue);
//
//            if(labelNumber.equals("0") || flag)
//                labelNumber = addNumber;
//            else
//                labelNumber += addNumber;
//
//            label.setText(labelNumber);
//            flag = false;
//            checkOp = false;
//            opFlag = false;
//            label.setHorizontalAlignment(SwingConstants.RIGHT);
//        }
//    });
}