package Model;

import com.fuzzylite.term.Trapezoid;
import com.fuzzylite.variable.InputVariable;
import Reseaubayesien.Fuzzification;
import smile.Network;
import smile.SMILEException;
import smile.learning.DataMatch;
import smile.learning.DataSet;
import smile.learning.EM;
import smile.learning.Validator;

import java.io.*;
import java.util.LinkedList;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

import java.util.Scanner;

public class ValidationKFold {
    String dataset;
    public Network net;
    int k;
    String train;
    String[] node;
    String test;
    float accuracy, rappel, precision, erreur;
    LinkedList<Float> allaccuracy = new LinkedList<>();
     LinkedList<Float> allerreur = new LinkedList<>();
    LinkedList<Float> allrappel = new LinkedList<>();
    LinkedList<Float> allprecision = new LinkedList<>();


    public ValidationKFold(String dataset, String train, String test, int k,Network net) {
        this.train = train;
        this.dataset = dataset;
        this.test = test;
        this.k = k;
        this.net = net;
    }

    public void inference() throws IOException {
        double[] evaluer_N = new double[2];
        double[] evaluer_P = new double[2];
        double[] evaluer_K = new double[2];
        double[] evaluer_Ph = new double[2];
        double[] evaluer_Temperature = new double[2];
        double[] evaluer_Humidity = new double[2];
        double[] evaluer_Rainfall = new double[2];
         Scanner scanner;
       
        double TP = 0.0, TN = 0.0, FP = 0.0, FN = 0.0;
       Fuzzification f = new Fuzzification();
       
 

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


        BufferedReader br = null;

        br = new BufferedReader(new FileReader(test));//charger le dataset pour le lire et le parcourir
        String line;
        int cmp = 0;
        String temp = "";
        double somme = 0;
        double[] resultat= new double[2];
        double maxVal = 0;
      double mae=0 ; 
        int i;
        line = br.readLine();
       
        
        
        
        ////////////////////
     while ((line = br.readLine()) != null) {

            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            i = 0;
            while (scanner.hasNext()) {
                temp = scanner.next();
                if (i == 0 && !temp.equals("*")) {
                    net.clearAllEvidence();
                }
                if (i == 1 && !temp.equals("*")) {
             //      System.out.print("N : "+temp);
                       
                    evaluer_N[1] = f.getMembershipDegree(n, Double.parseDouble(temp), "HighN");
                     evaluer_N[0] = f.getMembershipDegree(n, Double.parseDouble(temp), "LowN");
        somme = evaluer_N[0] + evaluer_N[1];
        if (somme != 0 && somme != 1) {  
            evaluer_N[1] = ((float) evaluer_N[1] / somme);
            evaluer_N[0] = ((float) evaluer_N[0] / somme);
        }
        if (somme != 0) net.setVirtualEvidence("N", evaluer_N);
                } else if (i == 2 && !temp.equals("*")) {
                 //   System.out.print("p : "+temp);
                     evaluer_P[1] = f.getMembershipDegree(p, Double.parseDouble(temp), "HighP");
            evaluer_P[0] = f.getMembershipDegree(p, Double.parseDouble(temp), "LowP");
            somme = evaluer_P[0] + evaluer_P[1];
            if (somme != 0 && somme != 1) {  
                evaluer_P[1] = ((float) evaluer_P[1] / somme);
                evaluer_P[0] = ((float) evaluer_P[0] / somme);
            }
            if (somme != 0) net.setVirtualEvidence("P", evaluer_P);
                } else if (i == 3 && !temp.equals("*")) {
                  //     System.out.print("k : "+temp);
                   evaluer_K[1] = f.getMembershipDegree(k, Double.parseDouble(temp), "HighK");
                   
                   evaluer_K[0] = f.getMembershipDegree(k, Double.parseDouble(temp), "LowK");
            somme = evaluer_K[0] + evaluer_K[1];
            if (somme != 0 && somme != 1) {  
                evaluer_K[1] = ((float) evaluer_K[1] / somme);
                evaluer_K[0] = ((float) evaluer_K[0] / somme);
            }
            if (somme != 0) net.setVirtualEvidence("K", evaluer_K);

                } else if (i == 4 && !temp.equals("*")) {
              //   System.out.print("temp : "+temp);
                    evaluer_Temperature[1] = f.getMembershipDegree(temperature, Double.parseDouble(temp), "HighTemperature");
            evaluer_Temperature[0] = f.getMembershipDegree(temperature, Double.parseDouble(temp), "LowTemperature");
            somme = evaluer_Temperature[0] + evaluer_Temperature[1];
            if (somme != 0 && somme != 1) {  
                evaluer_Temperature[1] = ((float) evaluer_Temperature[1] / somme);
                evaluer_Temperature[0] = ((float) evaluer_Temperature[0] / somme);
            }
            if (somme != 0) net.setVirtualEvidence("temperature", evaluer_Temperature);

                }
                else if (i == 5 && !temp.equals("*")) {
                //     System.out.print("humidity : "+temp);
                    evaluer_Humidity[1] = f.getMembershipDegree(humidity, Double.parseDouble(temp), "HighHumidity");
            evaluer_Humidity[0] = f.getMembershipDegree(humidity, Double.parseDouble(temp), "LowHumidity");
            somme = evaluer_Humidity[0] + evaluer_Humidity[1];
            if (somme != 0 && somme != 1) {  
                evaluer_Humidity[1] = ((float) evaluer_Humidity[1] / somme);
                evaluer_Humidity[0] = ((float) evaluer_Humidity[0] / somme);
            }
            if (somme != 0) net.setVirtualEvidence("humidity", evaluer_Humidity);

                }
                else if (i == 6 && !temp.equals("*")) {
               //    System.out.print("ph : "+temp);
                    evaluer_Ph[1] = f.getMembershipDegree(ph, Double.parseDouble(temp), "HighPh");
            evaluer_Ph[0] = f.getMembershipDegree(ph, Double.parseDouble(temp), "LowPh");
        
            somme = evaluer_Ph[0] + evaluer_Ph[1];
            if (somme != 0 && somme != 1) {  
                evaluer_Ph[1] = ((float) evaluer_Ph[1] / somme);
                evaluer_Ph[0] = ((float) evaluer_Ph[0] / somme);
            }
            if (somme != 0) net.setVirtualEvidence("ph", evaluer_Ph);
         //    System.out.println("-------Ph_degré : "+evaluer_Ph[0]+"Ph_High : "+evaluer_Ph[1]);

                }
                else if (i == 7 && !temp.equals("*")) {
                 //   System.out.print("rainfall : "+temp);
                    evaluer_Rainfall[1] = f.getMembershipDegree(rainfall, Double.parseDouble(temp), "HighRainfall");
            evaluer_Rainfall[0] = f.getMembershipDegree(rainfall, Double.parseDouble(temp), "LowRainfall");
            somme = evaluer_Rainfall[0] + evaluer_Rainfall[1];
            if (somme != 0 && somme != 1) {  // je dois normaliser les vals pour que la somme soit 1
                evaluer_Rainfall[1] = ((float) evaluer_Rainfall[1] / somme);
                evaluer_Rainfall[0] = ((float) evaluer_Rainfall[0] / somme);
            }
            if (somme != 0) net.setVirtualEvidence("rainfall", evaluer_Rainfall);

                }
                
                else if (i == 8 && !temp.equals("*")) {
                    
                    net.updateBeliefs();
                    resultat = net.getNodeValue("label");
                     
                    //gestion tablo resultat
//                    for (int j = 0; j < resultat.length; j++) {
//                        System.out.println("resultat"+j+":"+resultat[j]);
//                    }
              int m = 0 ; 
maxVal = resultat[0];
        for (int w = 1; w <resultat.length ; w++) {
            if (resultat[w]>maxVal){
                maxVal = resultat[w];
                 m=w ;
            }
           
        }
        
        String b ="";

    switch (m) {
        case 0:
            b = "apple";
            break;
        case 1:
            b = "banana";
            break;
        case 2:
            b = "blackgram";
            break;
        case 3:
            b = "chickpea";
            break;
        case 4:
            b = "coconut";
            break;
        case 5:
            b = "coffee";
            break;
        case 6:
            b = "cotton";
            break;
        case 7:
            b = "grapes";
            break;
        
        case 8 :
            b = "jute"  ;
            break;
        case 9 :
             b = "kidneybeans" ; 
             break;
        case 10 :
             b = "lentil" ; 
             break;
        case 11 :
             b = "maize" ; 
             break;
        case 12 :
             b = "mango" ; 
             break;
        case 13 :
             b = "mothbeans" ; 
             break;
        case 14 :
             b = "mungbean" ; 
             break; 
        case 15 :
             b = "muskmelon" ; 
             break;
        case 16 :
             b = "orange" ; 
             break;
        case 17 :
             b = "papaya" ; 
             break;
        case 18 :
             b = "pigeonpeas" ; 
             break;
        case 19 :
             b = "pomegranate" ; 
             break;
        case 20 :
             b = "rice" ; 
             break;
             case 21 :
             b = "watermelon" ; 
             break;
        
    }
 //   System.out.print("Label : "+temp);
 //   System.out.println("Prediction : "+b);
  //  System.out.print(b);
    double maea ; 
    
    
     if (temp.equals(b) ) {
                        TP++;
                //    System.out.println("Ta justyeeeee");
                maea=Math.abs(1-resultat[m]);
                mae=mae+maea ; 
                    }
                    else  {
                        FN++;
                          maea=Math.abs(0-resultat[m]);
                mae=mae+maea ; 
                         //    System.out.println("Ta FAUUUU");
                    }
                }
                i++;
            }

        }        
///////////////////
        
        
        
  
//Calculer accuracy rappel precision
//        System.out.println("TP:"+TP+",TN:"+TN+",FP:"+FP+",FN:"+FN);
        if ((TP + FN) != 0.0)
            accuracy = ((float) ((TP) / (TP + FN)))*100; //l'accuracy est le pourcentage de bonnes prédictions.
            erreur= ((float) ((mae) / (TP + FN)))*100;
            allerreur.add(erreur);
        allaccuracy.add(accuracy);
        
        }




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int inRange(int i, int d, int f) {
        if (i >= d && i <= f) return 1;
        else return 0;
    }

    ///////////////////////////////////////////
    ///////////////////////////////////////////
    public String getTrain(int iter, int sizezOffold, String dataset) throws FileNotFoundException, IOException { //fct pour generer un dataset pour le train a partir du dataset original

        BufferedReader br = new BufferedReader(new FileReader(dataset));
        String line;
        String content = "";
        line = br.readLine();
        content += line + "\n";
        int d = sizezOffold * (iter - 1);
        int f = (sizezOffold * iter) - 1;
        int i = 0;
        while ((line = br.readLine()) != null) {
            if (inRange(i, d, f) == 0) {

                content += line;
                content += "\n";
            }
            i++;
        }
        return content;
    }

    public String getTest(int iter, int sizezOffold, String dataset) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader(dataset));
        String line;
        line = br.readLine();
        String content = "";
        int d = sizezOffold * (iter - 1);
        int f = sizezOffold * iter;

        int i = 0;
        while ((line = br.readLine()) != null) {

            if (inRange(i, d, f) == 1) {

                content += line;
                content += "\n";
            }

            i++;

        }

        return content;

    }

    public static void usingBufferedWritter(String fileContent, String file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(fileContent);
        writer.close();
    }

    public static void main(String args[]) throws FileNotFoundException, IOException {
        new smile.License(
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



        for (int fold=2;fold<11;fold++){ 
            Network Net = new Network();
            Net.readFile("C:\\Users\\ybenk\\Documents\\NetBeansProjects\\Plantrecom\\src\\main\\java\\Reseaubayesien\\Crop_recommendation.xdsl");
            ValidationKFold c=new ValidationKFold("C:\\Users\\ybenk\\Downloads\\archive\\Crop_recommendation3.csv",
                    "C:\\Users\\ybenk\\Downloads\\archive\\Train.txt",
                    "C:\\Users\\ybenk\\Downloads\\archive\\Test.txt",
                    fold,Net);
            BufferedReader br = new BufferedReader(new FileReader(c.dataset));
            String line;
            line = br.readLine();
            c.node=line.split(",");
            c.rappel=0;
            c.accuracy=0;
            c.erreur=0;
            c.precision=0;


            int k=c.k;
            int sizeoffold= 2200 /c.k;
            System.out.println("Inférence systeme:");
//            System.out.print (" K="+fold+"\n");          
         
            for (int i=1;i<=k;i++){ 
                String content1=  c.getTest(i, sizeoffold,c.dataset) ; 
                String content2= c.getTrain(i, sizeoffold, "C:\\Users\\ybenk\\Downloads\\archive\\Soil_dataset_discret.csv");
                usingBufferedWritter(content1,c.test);
                usingBufferedWritter(content2,c.train);
                DataSet ds = new DataSet();
                ds.readFile(c.train);
                DataMatch[] matching = ds.matchNetwork(c.net);
                EM em = new EM();
                em.learn(ds, c.net, matching);   //apprentissage a partir du DT_train fait
                c.inference();
                c.net.clearAllEvidence();
            }
            System.out.println("Pour le Kfold égal à "+fold+" les résultats:");
            double sommeaccuracy=0;
            for(int l=0;l<c.allaccuracy.size();l++)
                sommeaccuracy += c.allaccuracy.get(l);
            double sommerappel=0;
            for(int l=0;l<c.allrappel.size();l++)
                sommerappel += c.allrappel.get(l);
            double sommeprecision=0;
            for(int l=0;l<c.allprecision.size();l++)
                sommeprecision += c.allprecision.get(l);
            
            ////calcul erreur 
            double sommeerreur=0;
            for(int l=0;l<c.allaccuracy.size();l++)
                sommeerreur += c.allerreur.get(l);


            double moyenneerreur = sommeerreur / c.allerreur.size();
             double moyenneaccuracy = sommeaccuracy / c.allaccuracy.size();
            
            
            System.out.println("Erreur moyenne  : "+moyenneerreur);
             System.out.println("Accuracy  : "+moyenneaccuracy);
      
        }

    }

    }






