package com.mfw.goout.window;

import javax.swing.JFrame;
import java.awt.*;

public class Window {
    public Window(int w, int h, String t, Game game){
        game.setPreferredSize(new Dimension(w,h));
        game.setMaximumSize(new Dimension(w,h));
        game.setMinimumSize(new Dimension(w,h));

        JFrame frame = new JFrame(t);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.startThread();

    }
}
