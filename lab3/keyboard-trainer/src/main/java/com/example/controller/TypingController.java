package com.example.controller;

import com.example.model.*;
import com.example.view.*;

public class TypingController {
    private TypingModel model;
    private MainView view;
    private Statistics statistics;

    public TypingController() {

    }

    public TypingModel getModel() {
        return model;
    }

    public void setModel(TypingModel model) {
        this.model = model;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public void setView(MainView view) {
        this.view = view;
    }

    public void handleKeyTyped(char c) {
        if (c == '\b') {
            model.removeUserText();
            view.update(model);
        } else {
            model.addUserText(c);
            view.update(model);
        }
    }
}
