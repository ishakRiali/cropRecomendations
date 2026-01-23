package Model;

import com.fuzzylite.term.Trapezoid;
import com.fuzzylite.variable.InputVariable;
//import org.jfree.ui.RefineryUtilities;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import smile.License;
import smile.Network;
import Reseaubayesien.Fuzzification;
import Model.ValidationKFold;
import java.io.BufferedWriter;

import org.semanticweb.owlapi.model.OWLOntologyStorageException;
//import smile.Network;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import smile.learning.DataMatch;
import smile.learning.DataSet;
import smile.learning.EM;
public class Soildata {
    
    String echantillon , N,P,K,Temperature,Humidity,Ph,Rainfall ; 
    String N1,P1,K1,Temperature1,Humidity1,Ph1,Rainfall1 ; 
    
    
     
    double maxVal = 0;
    double[] resultat;

    FuzzyOntology o;

    //constructor 1
    public Soildata(String echantillon, String N, String P, String K, String Ph, String Temperature, String Humidity, String Rainfall)  {
        this.echantillon = echantillon; this.N= N; this.P=P; this.K=K; this.Temperature=Temperature; this.Humidity=Humidity; this.Ph=Ph; this.Rainfall=Rainfall;
        
    }

    //constructor 2
    public Soildata() {
    }

    //remplir les attributs
    public void RemplirAttributs(){
        //préparer les noms d'instance
        N1 ="Valeur_N_du_sol_"+ echantillon;
        P1 ="Valeur_P_du_sol_"+ echantillon;
        K1="Valeur_K_du_sol_"+ echantillon;
        Temperature1="Temperature_du_sol"+ echantillon;
        Humidity1="Humidite_du_sol"+ echantillon;
        Ph1 ="Ph_du_sol"+ echantillon;
        Rainfall1 ="précipitations_"+ echantillon;
        
    }
    public void Addlabel(String label) throws OWLOntologyStorageException, OWLOntologyCreationException {
        o= new FuzzyOntology("C:\\Users\\ybenk\\Documents\\NetBeansProjects\\Plantrecom\\src\\main\\java\\Ontologie\\cropIOT.owl");
        o.addDataProperty(echantillon,"Best_crop",label);
        

    }
    
    
    public static void usingBufferedWritter(String fileContent, String file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(fileContent);
        writer.close();
    }

    //ajouter à l'ontologie
    public void AddToOntology() throws OWLOntologyStorageException, OWLOntologyCreationException {
        o= new FuzzyOntology("C:\\Users\\ybenk\\Documents\\NetBeansProjects\\Plantrecom\\src\\main\\java\\Ontologie\\cropIOT.owl");
        RemplirAttributs();
        //ajouter des individus aux classes     demander sexe a kamel
        o.addIndividual(echantillon, "SoilData");
        o.addIndividual(N1,"N");
        o.addIndividual(P1,"P");
        o.addIndividual(K1, "K");
        o.addIndividual(Ph1, "PH");
        o.addIndividual(Temperature1, "Temperature");
        o.addIndividual(Humidity1, "Humidity");
        o.addIndividual(Rainfall1, "Rainfall");
  


     





        //ajouter data properties
        o.addDataProperty(N1,"HasN",N);
        o.addDataProperty(P1,"HasP",P);
        o.addDataProperty(K1,"HasK",K);
        o.addDataProperty(Temperature1,"HasTemp",Temperature);
            o.addDataProperty(Humidity1,"HasHumidity",Humidity);
        o.addDataProperty(Rainfall1,"HasRainfall",Rainfall);
     o.addDataProperty(Ph1,"HasPh",Ph);
        
        
        //link les propr aux individus  //changer pour object property
        o.linkIndividuals(echantillon,N1,"Has-N");
        o.linkIndividuals(echantillon,P1,"Has-P");
        o.linkIndividuals(echantillon,K1,"Has-K");
        o.linkIndividuals(echantillon,Humidity1,"Has-Humidity");
        o.linkIndividuals(echantillon,Rainfall1,"Has-Rainfall");
        o.linkIndividuals(echantillon,Ph1,"Has-ph");
        o.linkIndividuals(echantillon,Temperature1,"Has-Temperature");
     

    }

      public void FuzzificationSoildata(Network net) throws OWLOntologyStorageException, OWLOntologyCreationException, IOException {
        o= new FuzzyOntology("C:\\Users\\ybenk\\Documents\\NetBeansProjects\\Plantrecom\\src\\main\\java\\Ontologie\\crop.owl");
        Fuzzification f = new Fuzzification();
         ValidationKFold c=new ValidationKFold("C:\\Users\\ybenk\\Downloads\\archive\\Crop_recommendation3.csv",
                    "C:\\Users\\ybenk\\Downloads\\archive\\Train2.txt",
                    "C:\\Users\\ybenk\\Downloads\\archive\\Test.txt",
                    1,net);
   String content2= c.getTrain(1, 2200, "C:\\Users\\ybenk\\Downloads\\archive\\Soil_dataset_discret.csv");
                
                
                DataSet ds = new DataSet();
                ds.readFile(c.train);
                DataMatch[] matching = ds.matchNetwork(c.net);
                EM em = new EM();
                em.learn(ds, c.net, matching);
//ajouter var floue age
       
        InputVariable n = f.createFuzzyNode("N", 0, 150);
        f.addFuzzyState(n, new Trapezoid("LowN", 0, 0, 27, 50));
        f.addFuzzyState(n, new Trapezoid("HighN", 27, 50, 150, 150));

        
        InputVariable p = f.createFuzzyNode("P", 5, 150);
        f.addFuzzyState(p, new Trapezoid("LowP", 5, 5, 27, 70));
        f.addFuzzyState(p, new Trapezoid("HighP", 27, 70, 150, 150));

        
        InputVariable k = f.createFuzzyNode("K", 5, 250);
        f.addFuzzyState(k, new Trapezoid("LowK", 5, 5, 25, 28));
        f.addFuzzyState(k, new Trapezoid("HighK", 25, 28, 250, 250));

        InputVariable temperature = f.createFuzzyNode("Temperature", 8.82, 43.67);
        f.addFuzzyState(temperature, new Trapezoid("LowTemperature", 8.82, 8.82, 21.69, 31));
        f.addFuzzyState(temperature, new Trapezoid("HighTemperature", 21.69, 31, 43.67, 43.67));

        InputVariable rainfall = f.createFuzzyNode("Rainfall", 10, 350);
        f.addFuzzyState(rainfall, new Trapezoid("LowRainfall", 10, 10, 75.22, 123));
        f.addFuzzyState(rainfall, new Trapezoid("HighRainfall", 75.22, 123, 350, 350));

        
        InputVariable humidity = f.createFuzzyNode("Humidity", 10, 100);
        f.addFuzzyState(humidity, new Trapezoid("LowHumidity", 10, 10, 41.53, 84.69));
        f.addFuzzyState(humidity, new Trapezoid("HighHumidity", 41.53, 84.69, 100, 100));

        
        InputVariable ph = f.createFuzzyNode("Ph", 3, 12);
        f.addFuzzyState(ph, new Trapezoid("LowPh", 3, 3, 5.95, 7.13));
        f.addFuzzyState(ph, new Trapezoid("HighPh", 5.95, 7.13, 12,12));
       

        ////////////////////////////////////
        double somme;
        if(!Objects.equals(this.N, ""))
        {
            System.out.println("La valeur de N que j'ai recu "+ this.N);
        double[] evaluer_N = new double[2];
       
        evaluer_N[1] = f.getMembershipDegree(n, Double.parseDouble(this.N), "HighN");
        evaluer_N[0] = f.getMembershipDegree(n, Double.parseDouble(this.N), "LowN");
        somme = evaluer_N[0] + evaluer_N[1];
        if (somme != 0 && somme != 1) {  // je dois normaliser les valeurs pour que la somme soit 1
            evaluer_N[1] = ((float) evaluer_N[1] / somme);
            evaluer_N[0] = ((float) evaluer_N[0] / somme);
        }
        if (somme != 0) net.setVirtualEvidence("N", evaluer_N);
        System.out.println("Valeur n" + evaluer_N[0] );
            System.out.println("Valeur n" + evaluer_N[1] );
        }

        /////////////////////////////////////
        if(!Objects.equals(this.P, ""))
        {
             System.out.println("La valeur de P que j'ai recu "+ this.P);
            double[] evaluer_P = new double[2];
            evaluer_P[1] = f.getMembershipDegree(p, Double.parseDouble(this.P), "HighP");
            evaluer_P[0] = f.getMembershipDegree(p, Double.parseDouble(this.P), "LowP");
            somme = evaluer_P[0] + evaluer_P[1];
            if (somme != 0 && somme != 1) {  // je dois normaliser les valeurs pour que la somme soit 1
                evaluer_P[1] = ((float) evaluer_P[1] / somme);
                evaluer_P[0] = ((float) evaluer_P[0] / somme);
            }
            if (somme != 0) net.setVirtualEvidence("P", evaluer_P);
            System.out.println("Valeur p" + evaluer_P[0] );
            System.out.println("Valeur p" + evaluer_P[1] );
        }

        //---------------------------------------------------
        if(!Objects.equals(this.K, ""))
        {
             System.out.println("La valeur de K que j'ai recu "+ this.K);
            double[] evaluer_K = new double[2];
            evaluer_K[1] = f.getMembershipDegree(k, Double.parseDouble(this.K), "HighK");
            evaluer_K[0] = f.getMembershipDegree(k, Double.parseDouble(this.K), "LowK");
            somme = evaluer_K[0] + evaluer_K[1];
            if (somme != 0 && somme != 1) {  // je dois normaliser les derÃ©s pour que la somme soit 1
                evaluer_K[1] = ((float) evaluer_K[1] / somme);
                evaluer_K[0] = ((float) evaluer_K[0] / somme);
            }
            if (somme != 0) net.setVirtualEvidence("K", evaluer_K);
            System.out.println("Valeur k" + evaluer_K[0] );
            System.out.println("Valeur k" + evaluer_K[1] );
        }
        //---------------------------------------------------
        if(!Objects.equals(this.Ph, ""))
        {
             System.out.println("La valeur de PH que j'ai recu "+ this.Ph);
            double[] evaluer_Ph = new double[2];
            evaluer_Ph[1] = f.getMembershipDegree(ph, Double.parseDouble(this.Ph), "HighPh");
            evaluer_Ph[0] = f.getMembershipDegree(ph, Double.parseDouble(this.Ph), "LowPh");
            System.out.println("Valeur PH2.0" + evaluer_Ph[0] );
            System.out.println("Valeur PH2.0" + evaluer_Ph[1] );
            somme = evaluer_Ph[0] + evaluer_Ph[1];
            if (somme != 0 && somme != 1) {  // je dois normaliser les derÃ©s pour que la somme soit 1
                evaluer_Ph[1] = ((float) evaluer_Ph[1] / somme);
                evaluer_Ph[0] = ((float) evaluer_Ph[0] / somme);
            }
            if (somme != 0) net.setVirtualEvidence("ph", evaluer_Ph);
            System.out.println("Valeur PH" + evaluer_Ph[0] );
            System.out.println("Valeur PH" + evaluer_Ph[1] );
        }
        //---------------------------------------------------
        if(!Objects.equals(this.Temperature, ""))
        {
             System.out.println("La valeur de Temp que j'ai recu "+ this.Temperature);
            double[] evaluer_Temperature = new double[2];
            evaluer_Temperature[1] = f.getMembershipDegree(temperature, Double.parseDouble(this.Temperature), "HighTemperature");
            evaluer_Temperature[0] = f.getMembershipDegree(temperature, Double.parseDouble(this.Temperature), "LowTemperature");
            somme = evaluer_Temperature[0] + evaluer_Temperature[1];
            if (somme != 0 && somme != 1) {  // je dois normaliser les vals pour que la somme soit 1
                evaluer_Temperature[1] = ((float) evaluer_Temperature[1] / somme);
                evaluer_Temperature[0] = ((float) evaluer_Temperature[0] / somme);
            }
            if (somme != 0) net.setVirtualEvidence("temperature", evaluer_Temperature);
            System.out.println("Valeur temp" + evaluer_Temperature[0] );
            System.out.println("Valeur temp" + evaluer_Temperature[1] );
        }
        //---------------------------------------------------
        if(!Objects.equals(this.Humidity, ""))
        {
             System.out.println("La valeur de Humi que j'ai recu "+ this.Humidity);
            double[] evaluer_Humidity = new double[2];
            evaluer_Humidity[1] = f.getMembershipDegree(humidity, Double.parseDouble(this.Humidity), "HighHumidity");
            evaluer_Humidity[0] = f.getMembershipDegree(humidity, Double.parseDouble(this.Humidity), "LowHumidity");
            somme = evaluer_Humidity[0] + evaluer_Humidity[1];
            if (somme != 0 && somme != 1) {  // je dois normaliser les vals pour que la somme soit 1
                evaluer_Humidity[1] = ((float) evaluer_Humidity[1] / somme);
                evaluer_Humidity[0] = ((float) evaluer_Humidity[0] / somme);
            }
            if (somme != 0) net.setVirtualEvidence("humidity", evaluer_Humidity);
            System.out.println("Valeur Humidity" + evaluer_Humidity[0] );
            System.out.println("Valeur Humidity" + evaluer_Humidity[1] );
        }
        //---------------------------------------------------
        if(!Objects.equals(this.Rainfall, ""))
        {
             System.out.println("La valeur de Rianfall que j'ai recu "+ this.Rainfall);
            double[] evaluer_Rainfall = new double[2];
            evaluer_Rainfall[1] = f.getMembershipDegree(rainfall, Double.parseDouble(this.Rainfall), "HighRainfall");
            evaluer_Rainfall[0] = f.getMembershipDegree(rainfall, Double.parseDouble(this.Rainfall), "LowRainfall");
            somme = evaluer_Rainfall[0] + evaluer_Rainfall[1];
            if (somme != 0 && somme != 1) {  // je dois normaliser les vals pour que la somme soit 1
                evaluer_Rainfall[1] = ((float) evaluer_Rainfall[1] / somme);
                evaluer_Rainfall[0] = ((float) evaluer_Rainfall[0] / somme);
            }
            if (somme != 0) net.setVirtualEvidence("rainfall", evaluer_Rainfall);
            System.out.println("Valeur Rinfall" + evaluer_Rainfall[0] );
            System.out.println("Valeur Rinfall" + evaluer_Rainfall[1] );
           
            
        }
        //---------------------------------------------------
        
        /////////////////////////////////////////////

        
        net.updateBeliefs();
        this.resultat = net.getNodeValue("label");

        System.out.print("Liste : ");
for (double valeur : resultat) {
    System.out.print(valeur + " ");
}
System.out.println();
int m = 0 ; 
maxVal = resultat[0];
        for (int i = 1; i <resultat.length ; i++) {
            if (resultat[i]>maxVal){
                maxVal = resultat[i];
                 m=i ;
            }
           
        }
        
        
         String bestCrop="";
         System.out.println("M = "+ m );
         System.out.println("MaxVal = "+ maxVal );

    switch (m) {
        case 0:
            bestCrop = "apple";
            break;
        case 1:
            bestCrop = "banana";
            break;
        case 2:
            bestCrop = "blackgram";
            break;
        case 3:
            bestCrop = "chickpea";
            break;
        case 4:
            bestCrop = "coconut";
            break;
        case 5:
            bestCrop = "coffe";
            break;
        case 6:
            bestCrop = "cotton";
            break;
        case 7:
            bestCrop = "grapes";
            break;
        
        case 8 :
            bestCrop = "jute"  ;
            break;
        case 9 :
             bestCrop = "kidneybeans" ; 
             break;
        case 10 :
             bestCrop = "lentil" ; 
             break;
        case 11 :
             bestCrop = "maize" ; 
             break;
        case 12 :
             bestCrop = "mango" ; 
             break;
        case 13 :
             bestCrop = "mothbeans" ; 
             break;
        case 14 :
             bestCrop = "mungbean" ; 
             break; 
        case 15 :
             bestCrop = "muskmelon" ; 
             break;
        case 16 :
             bestCrop = "orang" ; 
             break;
        case 17 :
             bestCrop = "papaya" ; 
             break;
        case 18 :
             bestCrop = "pigeonpeas" ; 
             break;
        case 19 :
             bestCrop = "pomegranate" ; 
             break;
        case 20 :
             bestCrop = "rice" ; 
             break;
             case 21 :
             bestCrop = "watermelon" ; 
             break;
        
    }
net.clearAllEvidence();
    System.out.println("Best_crop = " + bestCrop);

        
//        resultat[0] représente l'état YES du noeud diagnostic
//        resultat[1] représente l'état NO du noeud diagnostic

            o.addDataProperty(echantillon,"Best_crop",bestCrop);}

    
    // getters et setters
    public void setechantillon(String echantillon) {
        this.echantillon = echantillon;
    }

    public void setN(String N) {
        this.N = N;
    }

    public void setP(String P) {
        this.P = P;
    }

    public void setK(String K) {
        this.K = K;
    }

    public void setPh(String Ph) {
        this.Ph = Ph;
    }

    public void setHumidity(String Humidity) {
        this.Humidity = Humidity;
    }

    public void setRainfall(String Rainfall) {
        this.Rainfall = Rainfall;
    }

    public void setTemperature(String Temperature) {
        this.Temperature = Temperature;
    }

   

    public String getechantillon() {
        return echantillon;
    }

    public String getN() {
        return N;
    }

    public String getP() {
        return P;
    }

    public String getK() {
        return K;
    }

    public String getPh() {
        return Ph;
    }

    public String getTemperature() {
        return Temperature;
    }

    public String getHumidity() {
        return Humidity;
    }

    public String getRainfall() {
        return Rainfall;
    }

    

    public double[] getResultat() {
        return resultat;
    }


}

