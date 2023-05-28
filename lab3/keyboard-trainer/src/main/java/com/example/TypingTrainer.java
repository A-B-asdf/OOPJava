package com.example;

import javax.swing.SwingUtilities;

import com.example.controller.TypingController;
import com.example.model.*;
import com.example.view.MainView;

public class TypingTrainer {
    public static void main(String[] args) {
        String sampleText = "Пример текста для клавиатурного тренажера.";

        TypingModel model = new TypingModel(sampleText);
        Statistics statistics = new Statistics(model);
        TypingController controller = new TypingController();
        MainView view = new MainView(model, controller);

        view.setStatistics(statistics);
        controller.setModel(model);
        controller.setView(view);
        controller.setStatistics(statistics);

        SwingUtilities.invokeLater(() -> view.setVisible(true));
    }
}
