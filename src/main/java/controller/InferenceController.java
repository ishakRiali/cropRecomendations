package controller;

import Model.Soildata;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import smile.License;
import smile.Network;
import View.Homepage;
import View.Inference;
import View.Prediction;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InferenceController {
    private Inference inference;
    private Soildata Soildata;
    Network net;
    static smile.License NotreLicense = new smile.License(
           "SMILE LICENSE 2c8e95cf 72d66cb5 e0d88f23 " +
	"THIS IS AN ACADEMIC LICENSE AND CAN BE USED " +
	"SOLELY FOR ACADEMIC RESEARCH AND TEACHING, " +
	"AS DEFINED IN THE BAYESFUSION ACADEMIC " +
	"SOFTWARE LICENSING AGREEMENT. " +
	"Serial #: ci86hero9lnzfwstchy3y424k " +
	"Issued for: Bully shop (contact.bullyshop@gmail.com) " +
	"Academic institution: saad dahleb blida " +
	"Valid until: 2023-12-06 " +
	"Issued by BayesFusion activation server",
	new byte[] {
	-107,92,88,-86,32,-11,83,13,33,-29,79,12,-60,84,-18,-84,
	116,81,-71,68,-95,-78,43,-124,100,20,18,7,45,-79,-52,-100,
	46,-57,-41,106,73,39,-99,75,-26,91,-87,-81,-50,26,109,55,
	-12,76,73,103,76,53,-30,-116,18,-54,60,105,93,-58,-29,-72
	}
);



    public InferenceController(Inference inference,Soildata Soildata){
        this.inference=inference;
        this.Soildata=Soildata;

        System.loadLibrary("jsmile");
        // load the network created by Tutorial1
        net = new Network();
      net.readFile("C:\\Users\\ybenk\\Documents\\NetBeansProjects\\Plantrecom\\src\\main\\java\\Reseaubayesien\\Crop_recommendation.xdsl");

    }
    public void initController(){
        
         int i = 0;
        this.inference.getEchantillon().setText(this.Soildata.getechantillon());
        
      

       
        this.inference.getN().setText(this.Soildata.getN());
        this.inference.getP().setText(this.Soildata.getP());
        this.inference.getK().setText(this.Soildata.getK());
        this.inference.getPh().setText(this.Soildata.getPh());
        this.inference.getRainfall().setText(this.Soildata.getRainfall());
        this.inference.getTemperature().setText(this.Soildata.getTemperature());
        this.inference.getHumidity().setText(this.Soildata.getHumidity());
        
        this.inference.getInferer().addMouseListener(new MouseAdapter() {
        
            public void mouseClicked(MouseEvent ev) {
                String echantillon = InferenceController.this.inference.getEchantillon().getText();
                String n = InferenceController.this.inference.getN().getText();
               
                String p = InferenceController.this.inference.getP().getText();
                String k = InferenceController.this.inference.getK().getText();
                String ph = InferenceController.this.inference.getPh().getText();
                String humidity = InferenceController.this.inference.getHumidity().getText();
                String temperature = InferenceController.this.inference.getTemperature().getText();
                String rainfall = InferenceController.this.inference.getRainfall().getText();
               
                InferenceController.this.Soildata.setechantillon(echantillon);
                InferenceController.this.Soildata.setN(n);
                InferenceController.this.Soildata.setP(p);
                InferenceController.this.Soildata.setK(k);
                InferenceController.this.Soildata.setPh(ph);
                InferenceController.this.Soildata.setHumidity(humidity);
                InferenceController.this.Soildata.setTemperature(temperature);
                InferenceController.this.Soildata.setRainfall(rainfall);
               
               
                try {
                    Soildata.FuzzificationSoildata(net);
                } catch (OWLOntologyStorageException | OWLOntologyCreationException e) {
                    throw new RuntimeException(e);
                }
                double[] resultat = Soildata.getResultat();
                Prediction pframe = new Prediction(resultat);
                PredictionController pframecontrolleur = new PredictionController(pframe);
                pframe.setVisible(true);
                pframecontrolleur.initControlleur();

            } 
    });
        inference.getAccueil().addMouseListener(new MouseAdapter() {  //Modifier les informations de l'echantillon dans l'ontologie lorsqu'on clique sur le bouton "Modifier",
            //remplacer getAjouter()par getModifier()
            public void mouseClicked(MouseEvent ev) {inference.setVisible(true);}
        });
        /////////
       
        //////////////////////////////////////////////////////












    }}
