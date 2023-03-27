package com.example.controller;

import com.example.model.TypingModel;

public class TypingController {
    private TypingModel model;

    public TypingController(TypingModel model) {
        this.model = model;
    }

    public void handleKeyTyped(char c) {
        if (c == '\b') {
            model.removeUserText();
        } else {
            model.addUserText(c);
        }
    }
}
