package com.example.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

import com.example.controller.TypingController;
import com.example.model.TypingModel;

public class MainPane extends JPanel {
    private TypingController controller;
    private TypingModel model;

    private SampleTextPane sampleTextPane;
    private InputTextPane inputTextPane;

    static final int ADD_TEXT = 1;
    static final int REMOVE_TEXT = -1;

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

        sampleTextPane = new SampleTextPane(model.getSampleText());

        inputTextPane = new InputTextPane();

        typingPane.add(sampleTextPane.getScrollPane());
        typingPane.add(inputTextPane.getScrollPane());

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH; // BOTH для заполнения доступного пространства
        gbc.weightx = 1;
        gbc.weighty = 0.3;
        add(typingPane, gbc);

        StatisticView statisticView = new StatisticView();
        gbc.gridy = 1;
        gbc.weighty = 0.4;
        add(statisticView, gbc);

        AnimationView animationView = new AnimationView();
        gbc.gridy = 2;
        gbc.weighty = 0.3;
        add(animationView, gbc);
    }

    public class TextPane extends JTextPane {
        protected JScrollPane scrollPane;

        public TextPane() {
            setEditable(false);
            setFont(new Font("Monospaced", Font.PLAIN, 48));
            this.scrollPane = new JScrollPane(this);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        }

        public JScrollPane getScrollPane() {
            return scrollPane;
        }
    }

    public class SampleTextPane extends TextPane {
        private StyledDocument doc;
        private Style defaultStyle;
        private Style correctStyle;
        private Style incorrectStyle;

        public SampleTextPane(String text) {
            super();
            setText(text);
            setEditable(false);
            setBackground(Color.LIGHT_GRAY);

            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            doc = getStyledDocument();
            defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
            correctStyle = doc.addStyle("CORRECT_STYLE", defaultStyle);
            StyleConstants.setForeground(correctStyle, Color.GREEN);
            incorrectStyle = doc.addStyle("INCORRECT_STYLE", defaultStyle);
            StyleConstants.setForeground(incorrectStyle, Color.RED);
        }

        public void setDefaultStyle(int position) {
            doc.setCharacterAttributes(position, 1, defaultStyle, true);
        }

        public void setCorrectStyle(int position) {
            doc.setCharacterAttributes(position, 1, correctStyle, true);
        }

        public void setIncorrectStyle(int position) {
            doc.setCharacterAttributes(position, 1, incorrectStyle, true);
        }

        public void update(TypingModel model) {
            String userInput = model.getUserText();
            String sampleText = model.getSampleText();
            int cursorPosition = model.getCursorPosition();
            for (int i = (cursorPosition != 0 ? cursorPosition - 1 : 0); i < sampleText.length(); i++) {
                if (i < userInput.length()) {
                    if (userInput.charAt(i) == sampleText.charAt(i)) {
                        sampleTextPane.setCorrectStyle(i);
                    } else {
                        sampleTextPane.setIncorrectStyle(i);
                    }
                } else {
                    sampleTextPane.setDefaultStyle(i);
                }
            }
        }
    }

    public class InputTextPane extends TextPane {

        public InputTextPane() {
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    controller.handleKeyTyped(e.getKeyChar());
                }
            });
        }
    }

    public void update(TypingModel model) {
        sampleTextPane.update(model);
        int cursorPosition = model.getCursorPosition();
        inputTextPane.setText(model.getUserText());
        inputTextPane.requestFocus();

        // Scroll to cursor position
        try {
            int endPosition = sampleTextPane.getDocument().getLength();
            Rectangle cursorRectangle = sampleTextPane.modelToView(cursorPosition);
            Rectangle endRectangle = sampleTextPane.modelToView(endPosition);

            if (cursorRectangle.getY() + cursorRectangle.getHeight() < endRectangle.getY()) {
                cursorRectangle.setBounds(cursorRectangle.x, cursorRectangle.y + cursorRectangle.height * 2,
                        cursorRectangle.width, cursorRectangle.height);
            }

            if (cursorRectangle != null) {
                inputTextPane.scrollRectToVisible(cursorRectangle);
                sampleTextPane.scrollRectToVisible(cursorRectangle);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
