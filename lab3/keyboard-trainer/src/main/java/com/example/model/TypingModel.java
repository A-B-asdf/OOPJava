package com.example.model;

public class TypingModel {
    private String sampleText;
    private String userText;

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
    }

    public void removeUserText() {
        if (!userText.isEmpty()) {
            userText = userText.substring(0, userText.length() - 1);
        }
    }
}
