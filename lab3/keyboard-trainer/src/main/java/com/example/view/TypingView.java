package com.example.view;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

import com.example.controller.TypingController;
import com.example.model.TypingModel;

public class TypingView extends JFrame {
    private TypingController controller;
    private TypingModel model;
    private JTextPane sampleTextPane;
    private JTextPane inputTextPane;

    public TypingView(TypingModel model, TypingController controller) {
        this.controller = controller;
        this.model = model;
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
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.CENTER;

            JLabel logoLabel = new JLabel("Клавиатурный тренажер");

            // Изменяем шрифт
            logoLabel.setFont(new Font("Arial", Font.BOLD, 24));

            // Изменяем цвет шрифта
            logoLabel.setForeground(Color.BLUE);

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

    private class MainPane extends JPanel {
        public MainPane(int parentHeight, int parentWidth) {
            setBackground(Color.LIGHT_GRAY);
            double width2Height = 1.5;
            double screen2Pane = 0.9;
            int mainPaneHeight = (int) (Math.min(parentHeight, (int) (parentWidth / width2Height)) * screen2Pane);
            int mainPaneWidth = (int) (mainPaneHeight * width2Height);
            setPreferredSize(new Dimension(mainPaneWidth, mainPaneHeight));

            JPanel typingPane = new JPanel(); // Обертка для sampleTextPane и inputTextPane
            typingPane.setLayout(new GridLayout(1, 2));

            sampleTextPane = new JTextPane();
            sampleTextPane.setText(model.getSampleText());
            sampleTextPane.setEditable(false);
            sampleTextPane.setBackground(Color.LIGHT_GRAY);

            inputTextPane = new JTextPane();
            inputTextPane.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    controller.handleKeyTyped(e.getKeyChar());
                    updateView(model);
                }
            });

            typingPane.add(sampleTextPane);
            typingPane.add(inputTextPane);

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH; // Используйте BOTH для заполнения доступного пространства
            gbc.weightx = 1;
            gbc.weighty = 0.3; // Установите фиксированную высоту панели
            add(typingPane, gbc);

            StatisticView StatisticView = new StatisticView(); // Замените StatisticView на фактический класс вашей
                                                               // панели
            gbc.gridy = 1;
            gbc.weighty = 0.4; // Установите фиксированную высоту панели
            add(StatisticView, gbc);

            AnimationView AnimationView = new AnimationView(); // Замените AnimationView на фактический класс вашей
                                                               // панели
            gbc.gridy = 2;
            gbc.weighty = 0.3; // Установите фиксированную высоту панели
            add(AnimationView, gbc);
        }
    }

    public void updateView(TypingModel model) {
        String userInput = model.getUserText();
        String sampleText = model.getSampleText();
        StyledDocument doc = sampleTextPane.getStyledDocument();
        Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        Style correctStyle = doc.addStyle("CORRECT_STYLE", defaultStyle);
        StyleConstants.setForeground(correctStyle, Color.GREEN);
        Style incorrectStyle = doc.addStyle("INCORRECT_STYLE", defaultStyle);
        StyleConstants.setForeground(incorrectStyle, Color.RED);

        int length = userInput.length();
        for (int i = 0; i < length; i++) {
            if (i < sampleText.length() && userInput.charAt(i) == sampleText.charAt(i)) {
                doc.setCharacterAttributes(i, 1, correctStyle, true);
            } else {
                doc.setCharacterAttributes(i, 1, incorrectStyle, true);
            }
        }
        inputTextPane.requestFocus();
    }

    public class StatisticView extends JPanel {

        public StatisticView() {
            setBackground(Color.GREEN);
        }
    }

    public class AnimationView extends JPanel {

        public AnimationView() {
            setBackground(Color.CYAN);
        }
    }
}
