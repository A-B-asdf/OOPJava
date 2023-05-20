package com.example.model;

public class TypingModel {
    private String sampleText;
    private String userText;
    private int cursorPosition;

    public TypingModel(String sampleText) {
        this.sampleText = sampleText;
        this.userText = "";
    }

    public String getSampleText() {
        return sampleText;
    }

    public String getUserText() {
        return userText;
    }

    public void addUserText(char c) {
        userText += c;
        cursorPosition++;
    }

    public void removeUserText() {
        if (!userText.isEmpty()) {
            userText = userText.substring(0, userText.length() - 1);
            cursorPosition--;
        }
    }

    public int getCursorPosition() {
        return cursorPosition;
    }
}
