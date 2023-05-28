package com.example.model;

public class TypingModel {
    private String sampleText;
    private String userText;
    private int cursorPosition;
    int textLength = 0;

    public TypingModel(String sampleText) {
        this.sampleText = sampleText;
        this.userText = sampleText;
        textLength = this.sampleText.length();
    }

    public String getSampleText() {
        return sampleText;
    }

    public String getUserText() {
        return userText;
    }

    public void addUserText(char c) {
        if (cursorPosition < sampleText.length()) {
            StringBuilder sb = new StringBuilder(userText);
            sb.setCharAt(cursorPosition, c);
            userText = sb.toString();
            cursorPosition++;
        }
    }

    public void removeUserText() {
        if (cursorPosition > 0) {
            if (cursorPosition < textLength) {
                StringBuilder sb = new StringBuilder(userText);
                sb.setCharAt(cursorPosition, sampleText.charAt(cursorPosition));
                userText = sb.toString();
            }
            cursorPosition--;
        }
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public int getTextLength() {
        return textLength;
    }
}
