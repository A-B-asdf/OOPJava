package com.example.view;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

import com.example.controller.TypingController;
import com.example.model.TypingModel;

public class TypingView extends JFrame {
    private TypingController controller;

    public TypingView(TypingModel model, TypingController controller) {
        this.controller = controller;
        setTitle("Клавиатурный тренажер");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

        JPanel contentPane = new ContentPane(screenHeight, screenWidth);
        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ContentPane extends JPanel {
        public ContentPane(int screenHeight, int screenWidth) {
            setBackground(Color.WHITE);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            int topPaneHeight = 50;
            TopPane topPane = new TopPane(screenWidth, topPaneHeight);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            gbc.weighty = 0;
            add(topPane, gbc);

            JPanel mainPane = new MainPane(screenHeight - topPaneHeight, screenWidth);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            gbc.weighty = 1;
            add(mainPane, gbc);
        }
    }

    private class TopPane extends JPanel {
        public TopPane(int screenWidth, int topPaneHeight) {
            setBackground(Color.LIGHT_GRAY);
            setPreferredSize(new Dimension(screenWidth, topPaneHeight));
            setLayout(new BorderLayout());

            LogoPane logoPane = new LogoPane();
            add(logoPane, BorderLayout.WEST);

            ButtonsPane buttonsPane = new ButtonsPane();
            add(buttonsPane, BorderLayout.EAST);
        }
    }

    private class LogoPane extends JPanel {
        public LogoPane() {
            setBackground(Color.YELLOW);

            JLabel logoLabel = new JLabel("Клавиатурный тренажер");
            add(logoLabel);
        }
    }

    private class ButtonsPane extends JPanel {
        public ButtonsPane() {
            setBackground(Color.GREEN);
            setLayout(new FlowLayout(FlowLayout.CENTER));

            JButton button1 = new JButton("Кнопка 1");
            JButton button2 = new JButton("Кнопка 2");
            JButton button3 = new JButton("Кнопка 3");

            add(button1);
            add(button2);
            add(button3);
        }
    }

    private class MainPane extends JPanel {
        public MainPane(int parentHeight, int parentWidth) {
            setBackground(Color.LIGHT_GRAY);
            double width2Height = 1.5;
            double screen2Pane = 0.9;
            int mainPaneHeight = (int) (Math.min(parentHeight, (int) (parentWidth / width2Height))
                    * screen2Pane);
            int mainPaneWidth = (int) (mainPaneHeight * width2Height);
            setPreferredSize(new Dimension(mainPaneWidth, mainPaneHeight));
        }
    }
}
