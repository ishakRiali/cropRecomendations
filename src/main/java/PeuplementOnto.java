import Model.Soildata;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class PeuplementOnto {
    public static void main(String[] args) throws FileNotFoundException, OWLOntologyCreationException, OWLOntologyStorageException {
        
        System.out.println("Hello Java");  
        String csvPath= "C:\\Users\\ybenk\\Documents\\NetBeansProjects\\Plantrecom\\src\\main\\java\\Dataset\\Soil_dataset.csv";
        int i=1;
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(csvPath)); //ouvrir et lire dataset
            String lineText = null;


            lineReader.readLine(); // skip le header
            while ((lineText = lineReader.readLine()) != null && i< 101){ //pour chaque ligne
                String[] data = lineText.split(",");

            //    String echantillon = "echantillon_"+data[0];
                String N = data[0];
                System.out.println("data : "+ N);
                String P = data[1];
                 System.out.println("data : "+ P);
                String K = data[2];
                 System.out.println("data : "+ K);
                String temperature = data[3];
                 System.out.println("data : "+ temperature);
                 
                String humidity = data[4];
                 System.out.println("data : "+ data[4]);
                String ph = data[5];
                 System.out.println("data : "+ data[5]);
                String rainfall = data[6];
                 System.out.println("data : "+ data[6]);
                String label = data[7];
                 System.out.println("data : "+ data[7]);
            String id = "EchantillonNum_"+Integer.toString(i);
            
                Soildata Soildata = new Soildata();
                 Soildata.setechantillon(id);
                Soildata.setN(N);
                Soildata.setP(P);
                Soildata.setK(K);
                Soildata.setPh(ph);
                Soildata.setTemperature(temperature);
                Soildata.setHumidity(humidity);
                Soildata.setRainfall(rainfall);
                Soildata.AddToOntology();
                Soildata.Addlabel(label);
                System.out.println("Fait!"+i);
                i++;
            }
            lineReader.close();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
