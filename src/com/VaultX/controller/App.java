package com.VaultX.controller;

import javax.swing.*;

public class App extends JFrame {
    public static void main(String[] args) {
        int appWidth = 500;
        int appHeight = 500;
        JFrame frame = new JFrame("VaultX");
        frame.setSize(appWidth,appHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
