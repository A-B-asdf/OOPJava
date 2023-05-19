package com.example.view;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

import com.example.controller.TypingController;
import com.example.model.TypingModel;

public class MainPane extends JPanel {
    private TypingController controller;
    private TypingModel model;

    private JTextPane sampleTextPane;
    private JTextPane inputTextPane;

    public MainPane(TypingModel model, TypingController controller, int parentHeight, int parentWidth) {
        this.model = model;
        this.controller = controller;

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
        gbc.fill = GridBagConstraints.BOTH; // BOTH для заполнения доступного пространства
        gbc.weightx = 1;
        gbc.weighty = 0.3;
        add(typingPane, gbc);

        StatisticView StatisticView = new StatisticView();
        gbc.gridy = 1;
        gbc.weighty = 0.4;
        add(StatisticView, gbc);

        AnimationView AnimationView = new AnimationView();
        gbc.gridy = 2;
        gbc.weighty = 0.3;
        add(AnimationView, gbc);
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
}
