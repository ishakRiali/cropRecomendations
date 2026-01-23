package controller;

import Model.FuzzyOntology;
import Model.Soildata;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import View.Homepage;
import View.ModifierEchantillon;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModifierEchantillonController {
    private ModifierEchantillon modifier_infos;
    private Soildata soildata;

    //constructeur////////////////////////////////
    public ModifierEchantillonController(ModifierEchantillon modifier_infos, Soildata soildata) {
        this.modifier_infos = modifier_infos;
        this.soildata = soildata;
        // initControlleur();
    }

    public void initControlleur() {
        //chargement des données du patient dans les textfield//////////////////////////
        modifier_infos.getEchantillon().setText(soildata.getechantillon());
        modifier_infos.getN().setText(soildata.getN());
        
        modifier_infos.getP().setText(soildata.getP());
        modifier_infos.getK().setText(soildata.getK());
        modifier_infos.getPh().setText(soildata.getPh());
        modifier_infos.getTemperature().setText(soildata.getTemperature());
        modifier_infos.getHumidity().setText(soildata.getHumidity());
        modifier_infos.getRainfall().setText(soildata.getRainfall());
     

        modifier_infos.getModifier().addMouseListener(new MouseAdapter() {  //Modifier les informations du patient dans l'ontologie lorsqu'on clique sur le bouton "Modifier",
            //remplacer getAjouter()par getModifier()
            public void mouseClicked(MouseEvent ev) {
                FuzzyOntology NotreOntologie = null;
                String echantillon,n,p,k,ph,humidity,temperature,rainfall;
                // prendre les infos des TextFields actuels
                echantillon = modifier_infos.getEchantillon().getText().toString();
                n = modifier_infos.getN().getText().toString();
                p = modifier_infos.getP().getText().toString();
                k = modifier_infos.getK().getText().toString();
                ph = modifier_infos.getPh().getText().toString();
                humidity = modifier_infos.getHumidity().getText().toString();
                temperature = modifier_infos.getTemperature().getText().toString();
                rainfall = modifier_infos.getRainfall().getText().toString();
            
                //enregistrer ces infos dans les attributs de la classe du nouveau patient issu de la modification
                Soildata p2 =  new Soildata();
                p2.setechantillon(echantillon);
                p2.setN(n);
                p2.setP(p);
                p2.setK(k);
                p2.setPh(ph);
                p2.setTemperature(temperature);
                p2.setHumidity(humidity);
                p2.setRainfall(rainfall);
            

                try {//chargement de l'ontologie
                    NotreOntologie = new FuzzyOntology("C:\\Users\\ybenk\\Documents\\NetBeansProjects\\Plantrecom\\src\\main\\java\\Ontologie\\crop.owl");
                } catch (OWLOntologyCreationException ex) {
                    Logger.getLogger(ModifierEchantillonController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {//Supprimer l'ancien echantillon
                    if (NotreOntologie != null)
                        NotreOntologie.DELETE("soildata", soildata.getechantillon());
                } catch (OWLOntologyCreationException | OWLOntologyStorageException e) {
                    Logger.getLogger(ModifierEchantillonController.class.getName()).log(Level.SEVERE, null, e);
                }
                try { // rajouter le nouveau echantillon
                    p2.AddToOntology();
                } catch (OWLOntologyStorageException | OWLOntologyCreationException e) {
                    Logger.getLogger(ModifierEchantillonController.class.getName()).log(Level.SEVERE, null, e);
                }// notifier que l'echantillon  a bien été modifié

                JOptionPane.showMessageDialog(null, "Echantillon Modifié!", "Information", JOptionPane.INFORMATION_MESSAGE);


            }
        });

        modifier_infos.getAccueil().addMouseListener(new MouseAdapter() {  //Modifier les informations de l'echantillon  dans l'ontologie lorsqu'on clique sur le bouton "Modifier",
            //remplacer getAjouter()par getModifier()
            public void mouseClicked(MouseEvent ev) {
                modifier_infos.setVisible(false);
            }
        });

        
        //////////////////////////////////////////////////////
    }
}
