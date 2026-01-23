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
import java.util.Scanner;

public class ValidationKFoldclassic {
    String dataset;
    public Network net;
    int k;
    String train;
    String[] node;
    String test;
    float accuracy, rappel, precision;
    LinkedList<Float> allaccuracy = new LinkedList<>();
    LinkedList<Float> allrappel = new LinkedList<>();
    LinkedList<Float> allprecision = new LinkedList<>();


    public ValidationKFoldclassic(String dataset, String train, String test, int k,Network net) {
        this.train = train;
        this.dataset = dataset;
        this.test = test;
        this.k = k;
        this.net = net;
    }

    public void inference() throws IOException {
        double TP = 0.0, TN = 0.0, FP = 0.0, FN = 0.0;
        Scanner scanner;
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(test));
        String line;
        int cmp = 0;
        String temp = "";
        double somme = 0;
        double[] resultat= new double[2];
        double maxVal = 0;
        //String []node;
        int i;
        line = br.readLine();
        //node=line.split(",");
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
                    
                    net.setEvidence("N",temp);
                } else if (i == 2 && !temp.equals("*")) {
                    
                    net.setEvidence("P", temp);
                } else if (i == 3 && !temp.equals("*")) {
                    net.setEvidence("K",temp);

                } else if (i == 4 && !temp.equals("*")) {
                    net.setEvidence("temperature",temp);

                }
                else if (i == 5 && !temp.equals("*")) {
                    net.setEvidence("humidity",temp);

                }
                else if (i == 6 && !temp.equals("*")) {
                    net.setEvidence("ph",temp);

                }
                else if (i == 7 && !temp.equals("*")) {
                    net.setEvidence("rainfall",temp);

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
            b = "coffe";
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
             b = "orang" ; 
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
    
     if (temp.equals(b) ) {
                        TP++;

                    }
                    else  {
                        FN++;

                    }
                }
                i++;
            }

        }
//Calculer accuracy rappel precision
//        System.out.println("TP:"+TP+",TN:"+TN+",FP:"+FP+",FN:"+FN);
        if ((TP + FN) != 0.0)
            accuracy = ((float) ((TP) / (TP + FN)))*100; //l'accuracy est le pourcentage de bonnes prédictions.
       
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
    public String getTrain(int iter, int sizezOffold, String dataset) throws FileNotFoundException, IOException {

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



        for (int fold=2;fold<=10;fold++){ // on applique fold fois l'agorithme de kfold
            Network Net = new Network();
            Net.readFile("C:\\Users\\ybenk\\Documents\\NetBeansProjects\\Plantrecom\\src\\main\\java\\Reseaubayesien\\Crop_recommendation.xdsl");
            ValidationKFoldclassic c=new ValidationKFoldclassic("C:\\Users\\ybenk\\Downloads\\archive\\Soil_dataset_discret.csv",
                    "C:\\Users\\ybenk\\Downloads\\archive\\Train2.txt",
                    "C:\\Users\\ybenk\\Downloads\\archive\\Test2.txt",
                    fold,Net);
            BufferedReader br = new BufferedReader(new FileReader(c.dataset));
            String line;
            line = br.readLine();
            c.node=line.split(",");
            c.rappel=0;
            c.accuracy=0;
            c.precision=0;


            int k=c.k;
            int sizeoffold= 560 /c.k;
            System.out.println("Inférence classique:");
//            System.out.print (" K="+fold+"\n");           //l'execution du k fold
            // cette 2eme boucle pour les itérations de chaque algorithme k fold appliqué
            for (int i=1;i<=k;i++){
                String content1=  c.getTest(i, sizeoffold,c.dataset) ; //gettest est unemethode qui récupère les données pour le test a partir du dataset
                String content2= c.getTrain(i, sizeoffold, c.dataset);//gettrain est unemethode qui récupère les données pour le train a partir du dataset
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

            double moyenneaccuracy = sommeaccuracy / c.allaccuracy.size();
            double moyennerappel = sommerappel / c.allrappel.size();
            double moyenneprecision =sommeprecision / c.allprecision.size();
            System.out.println("accuracy : "+moyenneaccuracy);
            System.out.println("rappel : "+moyennerappel);
            System.out.println("precision : "+moyenneprecision);
        }

    }

}






