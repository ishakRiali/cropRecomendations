package controller;



import Model.FuzzyOntology;
import Model.Soildata;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import View.Homepage;
import View.AddSoildata;
import View.Inference;
import View.ModifierEchantillon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomepageController {
    Homepage acceuil;
    FuzzyOntology o;

    LinkedList<String> e;
    DefaultTableModel model = new DefaultTableModel();

    //constructeur
    public HomepageController(Homepage acceuil) throws Exception {
        this.acceuil = acceuil;
//        model.addColumn("Identifiant");
        model.addColumn("echantillon");
        model.addColumn("N");
        model.addColumn("P");
        model.addColumn("K");
        model.addColumn("Ph");
        model.addColumn("Temperature");
        model.addColumn("Humidity");
        model.addColumn("Rainfall");
        model.addColumn("Best_crop");
    
    }

    public void initControlleur() {

        acceuil.getAjouter().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddSoildata frame2 = new AddSoildata();
                frame2.setVisible(true);
                AddSoildataController frame2controlleur = new AddSoildataController(frame2);
                frame2controlleur.initControlleur();
            }
            

        });
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        acceuil.getModifier().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (acceuil.getTable().getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Veuillez selectionner le patient à modifier","Information",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                ModifierEchantillon frame3 = new ModifierEchantillon();
                frame3.setVisible(true);
                int indice_ligne_soil = acceuil.getTable().getSelectedRow();
                ModifierEchantillonController frame3controlleur = new ModifierEchantillonController(frame3, new Soildata(acceuil.getTable().getValueAt(indice_ligne_soil, 0).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_soil, 1).toString(), acceuil.getTable().getValueAt(indice_ligne_soil, 2).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_soil, 3).toString(), acceuil.getTable().getValueAt(indice_ligne_soil, 4).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_soil, 5).toString(), acceuil.getTable().getValueAt(indice_ligne_soil, 6).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_soil, 7).toString()));
                frame3controlleur.initControlleur();
                }
            }
        });
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        acceuil.getInference().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Inference frame4 = new Inference();
                frame4.setVisible(true);
                int indice_ligne_patient = acceuil.getTable().getSelectedRow();
                InferenceController frame4controlleur = null;
                frame4controlleur = new InferenceController(frame4, new Soildata(acceuil.getTable().getValueAt(indice_ligne_patient, 0).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 1).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 2).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 3).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 4).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 5).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 6).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 7).toString() ));
                frame4controlleur.initController();

            }
        }); 
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        acceuil.getActualiser().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                try {
                    o = new FuzzyOntology("C:\\Users\\ybenk\\Documents\\NetBeansProjects\\Plantrecom\\src\\main\\java\\Ontologie\\crop.owl");
                } catch (OWLOntologyCreationException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    ArrayList<String> fdt = o.getIndividulsByClass("SoilData");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
               System.out.println(o.soil.size() + "" + o.N.size() + "" + o.P.size() + "" + o.K.size());
               for (int i = 0; i < o.soil.size(); i++) {
                    model.addRow(new Object[]{o.soil.get(i), o.N.get(i), o.P.get(i), o.K.get(i),
                            o.Ph.get(i),o.Temperature.get(i),o.Humidity.get(i),
                            o.Rainfall.get(i),o.Best_crop.get(i)});
                    
                } 
                acceuil.getTable().setModel(model);

            }
            

        });
        
      
        acceuil.getChercher().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    o = new FuzzyOntology("C:\\Users\\ybenk\\Documents\\NetBeansProjects\\Plantrecom\\src\\main\\java\\Ontologie\\crop.owl");
                } catch (OWLOntologyCreationException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    ArrayList<String> fdt = o.getIndividulsByClass("Soildata");
                    for (int i = 0; i < o.soil.size(); i++) {
                        if (o.soil.get(i).contains(acceuil.getRecherche().getText())) {
                            model.addRow(new Object[]{o.soil.get(i), o.N.get(i), o.P.get(i), o.K.get(i),
                                    o.Ph.get(i), o.Temperature.get(i), o.Humidity.get(i),
                                   
                                    o.Rainfall.get(i), o.Best_crop.get(i)});
                        }
                    }
                    if(model.getRowCount()==0){
                        JOptionPane.showMessageDialog(null, "Aucun résultat trouvé","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else {acceuil.getTable().setModel(model);}
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        acceuil.getSupprimer().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // s'assurer qu'un echantillon est selectionné
                if (acceuil.getTable().getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Veuillez selectionner le patient à supprimer","Information",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    int indice_ligne_soil = acceuil.getTable().getSelectedRow();
                    System.out.println(indice_ligne_soil);
                    try {
                        o = new FuzzyOntology("C:\\Users\\PC-Service\\IdeaProjects\\IOT_Healthcare_App\\src\\ontologie\\OntologieFinale.owl");
                        o.DELETE("soildata",acceuil.getTable().getValueAt(indice_ligne_soil, 0).toString());
                        JOptionPane.showMessageDialog(null, "L'echantillon \""+acceuil.getTable().getValueAt(indice_ligne_soil, 0).toString()+"\" a été supprimé ","Information",JOptionPane.INFORMATION_MESSAGE);
                        model.removeRow(indice_ligne_soil);
                        acceuil.getTable().setModel(model);
                    } catch (OWLOntologyCreationException | OWLOntologyStorageException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

        });

    }


}

