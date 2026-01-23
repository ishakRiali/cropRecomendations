package controller;

import View.Homepage;
import View.Prediction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PredictionController {
private Prediction predictionaffichage;

public PredictionController(Prediction predictionaffichage){
    this.predictionaffichage = predictionaffichage;
}
public void initControlleur(){
    predictionaffichage.getRetour().addMouseListener(new MouseAdapter()
    {
        public void mouseClicked (MouseEvent ev) {predictionaffichage.setVisible(false);}
    });
}}
