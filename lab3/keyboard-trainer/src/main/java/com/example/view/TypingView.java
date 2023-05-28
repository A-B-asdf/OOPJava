package com.example.view;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

import com.example.controller.TypingController;
import com.example.model.*;

public class TypingView extends JFrame {
    private TypingController controller;
    private TypingModel model;
    private Statistics statistics;

    MainPane mainPane;

    public TypingView(TypingModel model, TypingController controller) {
        this.model = model;
        this.controller = controller;

        setTitle("Клавиатурный тренажер");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

        JPanel contentPane = new ContentPane(screenHeight, screenWidth);
        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
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

            mainPane = new MainPane(model, controller, screenHeight - topPaneHeight, screenWidth);

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
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.CENTER;

            JLabel logoLabel = new JLabel("Клавиатурный тренажер");

            // Изменяем шрифт
            logoLabel.setFont(new Font("Arial", Font.BOLD, 24));

            // Изменяем цвет шрифта
            logoLabel.setForeground(Color.ORANGE);

            // Добавляем отступы
            logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            add(logoLabel, gbc);
        }
    }

    private class ButtonsPane extends JPanel {
        public ButtonsPane() {
            setBackground(Color.GREEN);
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            JButton button1 = new JButton("Кнопка 1");
            JButton button2 = new JButton("Кнопка 2");
            JButton button3 = new JButton("Кнопка 3");

            button1.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the buttons horizontally
            button2.setAlignmentX(Component.CENTER_ALIGNMENT);
            button3.setAlignmentX(Component.CENTER_ALIGNMENT);

            add(Box.createHorizontalGlue());
            add(Box.createHorizontalStrut(10)); // Добавляем пробел высотой 10 пикселей
            add(button1);
            add(Box.createHorizontalStrut(10)); // Добавляем пробел высотой 10 пикселей
            add(button2);
            add(Box.createHorizontalStrut(10)); // Добавляем пробел высотой 10 пикселей
            add(button3);
            add(Box.createHorizontalStrut(10)); // Добавляем пробел высотой 10 пикселей
            add(Box.createHorizontalGlue());
        }
    }

    public void update(TypingModel model) {
        mainPane.update(model);
    }
}
