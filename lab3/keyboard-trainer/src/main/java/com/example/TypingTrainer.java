package com.example;

import javax.swing.SwingUtilities;

import com.example.controller.TypingController;
import com.example.model.TypingModel;
import com.example.view.TypingView;

public class TypingTrainer {
    public static void main(String[] args) {
        String sampleText = "Пример текста для клавиатурного тренажера";
        TypingModel model = new TypingModel(sampleText);
        TypingController controller = new TypingController(model);
        TypingView view = new TypingView(model, controller);

        SwingUtilities.invokeLater(() -> view.setVisible(true));
    }
}
