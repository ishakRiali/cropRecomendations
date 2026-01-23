/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLDocumentFormat;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.formats.OWLXMLDocumentFormat;
import org.semanticweb.owlapi.util.OWLEntityRemover;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;


public class FuzzyOntology {
    
     File file;

    private LinkedList<String> classes;
    private LinkedList<String> individuals;
    private LinkedList<String> objectproperties;
    private LinkedList<String> dataproperties;
    private LinkedList<String> datatypes;
    private LinkedList<String> fuzzydatatypes;

    private OWLOntology ontology;

    private IRI ontologyIRI;
    private PrefixManager pm;
    private OWLReasoner reasoner;
    private OWLReasonerFactory reasonerFactory;
    private OWLDataFactory df = OWLManager.getOWLDataFactory();
    OWLOntologyManager manager;
    //listes de stockage pour la Jtable de la page accueil lors du bouton "Actualiser"
    public ArrayList<String> soil = new ArrayList<String>(), N = new ArrayList<String>(), P = new ArrayList<String>(), K = new ArrayList<String>(),
            Temperature = new ArrayList<String>(), Humidity = new ArrayList<String>(), Ph = new ArrayList<String>(),
            Rainfall = new ArrayList<String>() , Best_crop = new ArrayList<String>(); 
    
     //constructeur
    public FuzzyOntology(String link) throws OWLOntologyCreationException {
        file = new File("C:\\Users\\ybenk\\Documents\\NetBeansProjects\\Plantrecom\\src\\main\\java\\Ontologie\\crop.owl");
        classes = new LinkedList<String>();
        individuals = new LinkedList<String>();
        objectproperties = new LinkedList<String>();
        dataproperties = new LinkedList<String>();
        datatypes = new LinkedList<String>();
        fuzzydatatypes = new LinkedList<String>();

        try
        {
            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

            ontology = manager.loadOntologyFromOntologyDocument(file);
            ontologyIRI = ontology.getOntologyID().getOntologyIRI().get();
            pm = new DefaultPrefixManager(ontologyIRI.toString());
            reasonerFactory = new StructuralReasonerFactory();
            reasoner = reasonerFactory.createNonBufferingReasoner(ontology);
            OWLDataFactory df = OWLManager.getOWLDataFactory();
        }
        catch (OWLOntologyCreationException ex) {}


    }

    //to get classes
    public void getClasses() {
        for (OWLClass i : ontology.getClassesInSignature(true)) {
            classes.add(i.getIRI().getFragment());
        }

        for (String aClass : classes) {
            System.out.println(aClass);
        }
    }

    //get individuals
    public void getIndividuals() {
        for (OWLNamedIndividual i : ontology.getIndividualsInSignature(true)) {
            individuals.add(i.getIRI().getFragment());
        }

        for (String individual : individuals) {
            System.out.println(individual);
        }
    }

    //to get object properties
    public void getObjectProperties() {
        for (OWLObjectProperty i : ontology.getObjectPropertiesInSignature(true)) {
            objectproperties.add(i.getIRI().getFragment());
        }

        for (String objectproperty : objectproperties) {
            System.out.println(objectproperty);
        }
    }

    // to get data properties
    public void getDataProperties() {
        for (OWLDataProperty i : ontology.getDataPropertiesInSignature(true)) {
            dataproperties.add(i.getIRI().getFragment());
            
        }

        for (String dataproperty : dataproperties) {
            System.out.println(dataproperty);
        }
    }

    //to get datatypes
    public void getDataTypes() {
        for (OWLDatatype i : ontology.getDatatypesInSignature(true)) {
            datatypes.add(i.getIRI().getFragment());
        }

        for (String datatype : datatypes) {
            System.out.println(datatype);
        }
    }

    //to get the fuzzy data types
    /*
    public void getFuzzyDataTypes() {
        for (OWLDatatype d : ontology.getDatatypesInSignature(true)) {
            for (OWLAnnotation annotation1 : d.getAnnotations(ontology)) {
                if ((annotation1.getProperty().getIRI().getFragment().contains("fuzzyLabel"))) {
                    // fuzzydatatypes.add(d.getIRI().getFragment());
                    OWLLiteral literal = (OWLLiteral) annotation1.getValue();
                    String literalString = literal.getLiteral();
                    fuzzydatatypes.add(literalString);
                }
            }
        }
        for (String fuzzydatatype : fuzzydatatypes) {
            System.out.println(fuzzydatatype);
        }
    } */
    //add data property
    public void addDataProperty(String ind, String dp, String v) throws OWLOntologyStorageException {
        OWLOntologyManager manager;
        OWLXMLOntologyFormat owlxmlFormat;
    //  OWLOntologyFormat format;
      OWLDocumentFormat format; 
      
         manager = OWLManager.createOWLOntologyManager();
        owlxmlFormat = new OWLXMLOntologyFormat();
         format = manager.getOntologyFormat(ontology);
        //System.out.println("1");
        pm = new DefaultPrefixManager(ontologyIRI.toString().concat("#"));
        OWLNamedIndividual indivi = df.getOWLNamedIndividual(":" + ind, pm);
        OWLDataProperty dpro = df.getOWLDataProperty(":" + dp, pm);
        OWLDataPropertyAssertionAxiom axiomDp = df.getOWLDataPropertyAssertionAxiom(dpro, indivi, v);
        AddAxiom addAxiomDp = new AddAxiom(ontology, axiomDp);
        manager.applyChange(addAxiomDp);
        manager.saveOntology(ontology, owlxmlFormat, IRI.create(file.toURI()));
    }

    //add an individual
    public void addIndividual(String ind, String classes) throws OWLOntologyStorageException {

        OWLOntologyManager manager;
        OWLXMLOntologyFormat owlxmlFormat;
    //  OWLOntologyFormat format;
      OWLDocumentFormat format; 
        manager = OWLManager.createOWLOntologyManager();
        owlxmlFormat = new OWLXMLOntologyFormat();
        format = manager.getOntologyFormat(ontology);
        // System.out.println("1");
        pm = new DefaultPrefixManager(ontologyIRI.toString().concat("#"));
        OWLClass simpleTypeClass = df.getOWLClass(":" + classes, pm);
        //System.out.println(simpleTypeClass.getIRI().getFragment());
        OWLNamedIndividual indi = df.getOWLNamedIndividual(":" + ind, pm);
        //System.out.println(indi.getIRI().getFragment());
        OWLAxiom axiomI = df.getOWLClassAssertionAxiom(simpleTypeClass, indi);
        AddAxiom addAxiomI = new AddAxiom(ontology, axiomI);
        manager.applyChange(addAxiomI);
        manager.saveOntology(ontology, owlxmlFormat, IRI.create(file.toURI()));
    }

    //link the individuals
    public void linkIndividuals(String ind1, String ind2, String relation) throws OWLOntologyStorageException {

        OWLOntologyManager manager;
        
   //     OWLOntologyFormat format;
        OWLDocumentFormat format ;
        manager = OWLManager.createOWLOntologyManager();
        OWLXMLDocumentFormat owlxmlFormat = new OWLXMLDocumentFormat();
        format = manager.getOntologyFormat(ontology);
//System.out.println("1");
        pm = new DefaultPrefixManager(ontologyIRI.toString().concat("#"));
///////////////////////////////////////////////////////////////////////////
        OWLNamedIndividual i1 = df.getOWLNamedIndividual(":" + ind1, pm);
        OWLNamedIndividual i2 = df.getOWLNamedIndividual(":" + ind2, pm);
        OWLObjectProperty rel = df.getOWLObjectProperty(":" + relation, pm);
///////////////////////////////////////////////////////////////////////////
        OWLObjectPropertyAssertionAxiom axiomOp = df.getOWLObjectPropertyAssertionAxiom(rel, i1, i2);
        AddAxiom addAxiomOp = new AddAxiom(ontology, axiomOp);
        manager.applyChange(addAxiomOp);
///////////////////////////////////////////////////////////////////////////
        manager.saveOntology(ontology, owlxmlFormat, IRI.create(file.toURI()));
    }
/////////////////////////////////////////////
    
    
      public void DELETE(String nameClass, String nameP) throws OWLOntologyCreationException, OWLOntologyStorageException {
        manager = OWLManager.createOWLOntologyManager();
        ontology = manager.loadOntologyFromOntologyDocument(file);
        OWLDataProperty dp;
        OWLReasoner reasoner = reasonerFactory.createNonBufferingReasoner(ontology);

        OWLEntityRemover removerToDeleteAlreadyAssignedInds = new OWLEntityRemover( Collections.singleton(ontology));
        for (OWLClass cls : ontology.getClassesInSignature()) {   //pour chaque classe de l'ontologie
            //l'instance soidata
            if (cls.getIRI().getFragment().equals(nameClass)) {   //pour selectionner la classe soildata

                NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cls, false);
                System.out.println("suppression de l'echantillon en cours ");
                for (OWLNamedIndividual ind : instances.getFlattened()) {  //parcourir chaque individu de la classe soildata
                    //je récupére touts les instances de soildata
                    if (ind.getIRI().getFragment().equals(nameP)) {  // si c'est le soildata à supprimer
                        ind.accept(removerToDeleteAlreadyAssignedInds);
                        manager.applyChanges(removerToDeleteAlreadyAssignedInds.getChanges());
                        removerToDeleteAlreadyAssignedInds.reset();
                        manager.saveOntology(ontology);
                    }
                }
            }
            //l'instance N du soildata
            if (cls.getIRI().getFragment().equals("N")) {   //pour selectionner la classe soildata

                NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cls, false);
                System.out.println("suppression de N en cours");
                for (OWLNamedIndividual ind : instances.getFlattened()) {  //parcourir chaque individu de la classe soildata
                    //je récupére touts les instances de soildata

                    if (ind.getIRI().getFragment().equals("Valeur_N_du_sol_"+nameP)) {  // si c'est le soildata à supprimer
                        ind.accept(removerToDeleteAlreadyAssignedInds);
                        manager.applyChanges(removerToDeleteAlreadyAssignedInds.getChanges());
                        removerToDeleteAlreadyAssignedInds.reset();
                        manager.saveOntology(ontology);
                    }
                }
            }
            
            if (cls.getIRI().getFragment().equals("P")) {  

                NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cls, false);
                System.out.println("suppression de P en cours");
                for (OWLNamedIndividual ind : instances.getFlattened()) {  
                   
                    if (ind.getIRI().getFragment().equals("Valeur_P_du_sol_"+nameP)) {  
                        ind.accept(removerToDeleteAlreadyAssignedInds);
                        manager.applyChanges(removerToDeleteAlreadyAssignedInds.getChanges());
                        removerToDeleteAlreadyAssignedInds.reset();
                        manager.saveOntology(ontology);
                    }
                }
            }
          
            if (cls.getIRI().getFragment().equals("K")) {   
                NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cls, false);
                System.out.println("suppression du K en cours");
                for (OWLNamedIndividual ind : instances.getFlattened()) { 
                   
                    if (ind.getIRI().getFragment().equals("Valeur_K_du_sol_"+nameP)) {  
                        ind.accept(removerToDeleteAlreadyAssignedInds);
                        manager.applyChanges(removerToDeleteAlreadyAssignedInds.getChanges());
                        removerToDeleteAlreadyAssignedInds.reset();
                        manager.saveOntology(ontology);
                    }
                }
            }
        
            if (cls.getIRI().getFragment().equals("PH")) {   

                NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cls, false);
                System.out.println("suppression du PH en cours");
                for (OWLNamedIndividual ind : instances.getFlattened()) {  
                  
                    if (ind.getIRI().getFragment().equals("Ph_du_sol_"+nameP)) {  
                        ind.accept(removerToDeleteAlreadyAssignedInds);
                        manager.applyChanges(removerToDeleteAlreadyAssignedInds.getChanges());
                        removerToDeleteAlreadyAssignedInds.reset();
                        manager.saveOntology(ontology);
                    }
                }
            }
            
            if (cls.getIRI().getFragment().equals("Temperature")) {  

                NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cls, false);
                System.out.println("suppression de le temperature  en cours");
                for (OWLNamedIndividual ind : instances.getFlattened()) {  
                  
                    if (ind.getIRI().getFragment().equals("Temperature_du_sol_"+nameP)) {  
                        ind.accept(removerToDeleteAlreadyAssignedInds);
                        manager.applyChanges(removerToDeleteAlreadyAssignedInds.getChanges());
                        removerToDeleteAlreadyAssignedInds.reset();
                        manager.saveOntology(ontology);
                    }
                }
            }
            
            if (cls.getIRI().getFragment().equals("Humidity")) {   

                NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cls, false);
                System.out.println("suppression du l'humidity en cours");
                for (OWLNamedIndividual ind : instances.getFlattened()) { 
                    
                    if (ind.getIRI().getFragment().equals("Humidite_du_sol_"+nameP)) {  
                        ind.accept(removerToDeleteAlreadyAssignedInds);
                        manager.applyChanges(removerToDeleteAlreadyAssignedInds.getChanges());
                        removerToDeleteAlreadyAssignedInds.reset();
                        manager.saveOntology(ontology);
                    }
                }
            }
        
            if (cls.getIRI().getFragment().equals("Rainfall")) {  

                NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cls, false);
                System.out.println("suppression du Rianfall en cours");
                for (OWLNamedIndividual ind : instances.getFlattened()) {  
                   
                    if (ind.getIRI().getFragment().equals("précipitation"+nameP)) {  
                        ind.accept(removerToDeleteAlreadyAssignedInds);
                        manager.applyChanges(removerToDeleteAlreadyAssignedInds.getChanges());
                        removerToDeleteAlreadyAssignedInds.reset();
                        manager.saveOntology(ontology);
                    }
                }
            }
            
        
      

        }
    }
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////
     public ArrayList<String> getIndividulsByClass(String nameClass) throws Exception {
        ArrayList<String> ip = new ArrayList<String>();
        soil.clear();
        N.clear();
        P.clear();
        K.clear();
        Ph.clear();
        Temperature.clear();
        Humidity.clear();
        Rainfall.clear();
        Best_crop.clear();
  
        manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
        OWLDataProperty dp;

        OWLReasoner reasoner = reasonerFactory.createNonBufferingReasoner(ontology);
        for (OWLClass cls : ontology.getClassesInSignature()) {   //pour chaque classe de l'ontologie
            if (cls.getIRI().getFragment().equals(nameClass)) {         //pour selectionner la classe soildata

                NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cls, false); // recuperer les individus de la classe soildata
                System.out.println("Number of instances of the class \"" + nameClass + "\" is  " + instances.getFlattened().size());
                for (OWLNamedIndividual ind : instances.getFlattened()) {  //parcourir chaque individu de la classe soildata

                    ip.add(ind.getIRI().getFragment());

                    System.out.println(" Individual " + ind.getIRI().getFragment());
                    //ajouter soil
                    soil.add(ind.getIRI().getFragment());                   //ajouter l'instance soildata à l'array list
                    dp = null;
                    // récupérer la dataproperty "Best_crop" pour le soildata courant
                    for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) { //pour chaque dataproperty
                        if (ont.getIRI().getFragment().equals("Best_crop")) {              //selectionner le dataproperty best_crop
                            dp = ont;
                            Set<OWLLiteral> values = reasoner.getDataPropertyValues(ind, dp);       //trouver les valeurs de best_crop
                            if (values.isEmpty()) {
                                System.out.print(" \t: Best_crop = null ");
                                Best_crop.add("");
                            }
                            for (OWLLiteral ol : values) {
                                System.out.print(" \t: Best_crop = " + ol.getLiteral());
                               
                                Best_crop.add(ol.getLiteral());                                                   //ajouter ces valeurs à l'arraylist Best_crop
                            }
                        }
                    }
                    /////////////////////////////////////////////////////////
                    //recuperer les instances de chaque autre classe liées avec l'instance soildata en question
                    //ensuite parcourir leurs dataproperty
                    //N
                    OWLObjectProperty op = null;
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {    //pour chaque objectproperty
                        if (opp.getIRI().getFragment().equals("Has-N")) {                   // selectionner le objectproperty has_age

                            op = opp;
                        }
                    }
                    

                    Set<OWLNamedIndividual> valuesAge = reasoner.getObjectPropertyValues(ind, op).getFlattened(); //trouver les instances HasN reliées avec l'individu soildata
                    for (OWLNamedIndividual indage : valuesAge) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("HasN")) {
                                dp = ont;
                                Set<OWLLiteral> valuesage = reasoner.getDataPropertyValues(indage, dp);

                                for (OWLLiteral ol : valuesage) {
                                    System.out.print(" \t: HasN = " + ol.getLiteral());
                                    //ajouter leur alt
                                    N.add(ol.getLiteral());
                                }
                            }
                        }
                    }
                    ////////////////////////////////////////////////////////
                    //P
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has-P")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesGender = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indgender : valuesGender) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("HasP")) {
                                dp = ont;
                                Set<OWLLiteral> valuesgender = reasoner.getDataPropertyValues(indgender, dp);

                                for (OWLLiteral ol : valuesgender) {
                                    System.out.print(" \t: HasP = " + ol.getLiteral());
                                    //ajouter leur alt
                                    P.add(ol.getLiteral());
                                }
                            }
                        }


                    }
                    ////////////////////////////////////////////////////////
                    //K
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has-Rainfall")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesCholesterol = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indcholesterol : valuesCholesterol) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("HasRainfall")) {
                                dp = ont;
                                Set<OWLLiteral> valuescholesterol = reasoner.getDataPropertyValues(indcholesterol, dp);

                                for (OWLLiteral ol : valuescholesterol) {
                                    System.out.print(" \t: HasRainfall = " + ol.getLiteral());
                                    //ajouter leur alt
                                    Rainfall.add(ol.getLiteral());
                                }
                            }
                        }


                    }
                    ////////////////////////////////////////////////////////
                    //Ph
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has-ph")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesGlucose = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indglucose : valuesGlucose) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("HasPh")) {
                                dp = ont;
                                Set<OWLLiteral> valuesglucose = reasoner.getDataPropertyValues(indglucose, dp);

                                for (OWLLiteral ol : valuesglucose) {
                                    System.out.print(" \t: HasPh = " + ol.getLiteral());
                                 
                                    Ph.add(ol.getLiteral());
                                }
                            }
                        }
                    }
                    ////////////////////////////////////////////////////////
                    //Temperature
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has-Temperature")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesSBP = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indSBP : valuesSBP) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("HasTemp")) {
                                dp = ont;
                                Set<OWLLiteral> valuessbp = reasoner.getDataPropertyValues(indSBP, dp);

                                for (OWLLiteral ol : valuessbp) {
                                    System.out.print(" \t: HasTemp = " + ol.getLiteral());
                                    //ajouter leur alt
                                    Temperature.add(ol.getLiteral());
                                }
                            }
                        }
                    }
                    ////////////////////////////////////////////////////////
                    //K
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has-K")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesDBP = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indDBP : valuesDBP) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("HasK")) {
                                dp = ont;
                                Set<OWLLiteral> valuesdbp = reasoner.getDataPropertyValues(indDBP, dp);

                                for (OWLLiteral ol : valuesdbp) {
                                    System.out.print(" \t: HasK = " + ol.getLiteral());
                                    //ajouter leur alt
                                    K.add(ol.getLiteral());
                                }
                            }
                        }
                    }
                    ////////////////////////////////////////////////////////
                    //Humidity
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has-Humidity")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesHeight = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indHeight : valuesHeight) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("HasHumidity")) {
                                dp = ont;
                                Set<OWLLiteral> valuesheight = reasoner.getDataPropertyValues(indHeight, dp);

                                for (OWLLiteral ol : valuesheight) {
                                    System.out.print(" \t: HasHumidity = " + ol.getLiteral());
                                    //ajouter leur alt
                                    Humidity.add(ol.getLiteral());
                                }
                            }
                        }
                    }
              
                 
                }
            }
        }
        return ip;
    }
    /////////// ici la fin 
}

