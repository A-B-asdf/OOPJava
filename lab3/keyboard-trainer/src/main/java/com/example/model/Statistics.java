package com.example.model;

public class Statistics {
    private int characterCount;
    private int totalCharacterCount;
    private long startTime;
    private long endTime;
    private int errorCount;

    public Statistics(TypingModel model) {
        characterCount = 0;
        totalCharacterCount = model.getSampleText().length();
        startTime = System.currentTimeMillis();
        endTime = startTime;
        errorCount = 0;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getCharacterCount() {
        return characterCount;
    }

    public double getTypingSpeed() {
        long totalTime = endTime - startTime;
        double minutes = totalTime / 1000.0 / 60.0;
        return characterCount / minutes;
    }

    public double getTypingProgress() {
        double typingProgress = characterCount / totalCharacterCount * 100;
        return typingProgress;
    }

    public double getErrorRate() {
        return (double) errorCount / characterCount * 100;
    }

    public void update(char c, boolean isError) {
        characterCount++;
        if (isError) {
            errorCount++;
        }
        endTime = System.currentTimeMillis();
    }
}
