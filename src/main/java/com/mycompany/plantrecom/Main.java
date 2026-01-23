package com.mycompany.plantrecom; 
import controller.HomepageController;
import Model.FuzzyOntology;
import Model.Soildata;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import View.AddSoildata ; 
import View.Homepage ; 
//import smile.Network;
import View.*;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

//        InferencePatient frame = new InferencePatient();
//        frame.setVisible(true);
//        AjouterPatient frame2 = new AjouterPatient();
//        frame2.setVisible(true);

          Homepage frame3 = new Homepage();
          frame3.setVisible(true);
          HomepageController  frame3controlleur = new HomepageController(frame3);
          frame3controlleur.initControlleur();

//        ModifierPatient frame4 = new ModifierPatient();
//        frame4.setVisible(true);
//          prediction frame5 = new prediction(new double[]{0.00, 1.00});
//          frame5.setVisible(true);
//          java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                AjouterPatient frame = new AjouterPatient();
//                //AcceuilPage frame = new AcceuilPage();
//                //ModifierPatient frame = new ModifierPatient();
//                frame.setVisible(true);
//            }
//          });


    }
}