package com.example.view;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

import com.example.controller.TypingController;
import com.example.model.TypingModel;

public class TypingView extends JFrame {
    private JTextPane sampleTextPane;
    private JTextPane userInputTextPane;
    private TypingController controller;

    public TypingView(TypingModel model, TypingController controller) {
        this.controller = controller;
        setTitle("Клавиатурный тренажер");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        sampleTextPane = new JTextPane();
        sampleTextPane.setText(model.getSampleText());
        sampleTextPane.setEditable(false);
        sampleTextPane.setBackground(Color.LIGHT_GRAY);

        userInputTextPane = new JTextPane();
        userInputTextPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                controller.handleKeyTyped(e.getKeyChar());
                updateView(model);
            }
        });

        setLayout(new BorderLayout());
        add(sampleTextPane, BorderLayout.NORTH);
        add(userInputTextPane, BorderLayout.CENTER);
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
        userInputTextPane.requestFocus();
    }
}
