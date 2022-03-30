import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.*;

public class Aurreprozesamendua {


    public static void main(String[] args)throws Exception {

        //args[0] --> train.csv
        //args[1] --> train.arff

        /*
         * takes 2 arguments:
         *  CSV input file
         *  ARFF output file
         */

        // ** -- CSV garbiketa -- ** //

        //String[] character = {"`", "'", "?"};
        //for(String x : character)
        removeCharactersFromFile(args[0], "./Datuak/trainResult.csv");

           //  load CSV
        try {

            CSVLoader loader = new CSVLoader();
            loader.setSource(new File("./Datuak/trainResult.csv"));
            //loader.setSource(new File(args[0]));
            Instances data = loader.getDataSet();


            // save ARFF
            ArffSaver saver = new ArffSaver();
            saver.setInstances(data);
            saver.setFile(new File(args[1]));
            //saver.setDestination(new File(args[1]));
            saver.writeBatch();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void removeCharactersFromFile(String fileName, String fileResult) throws IOException{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            PrintWriter pw = new PrintWriter(fileResult);
            String line;

            while ((line = br.readLine()) != null) {
               // line = line.replace(subString, "");
                line = line.replaceAll("[`'?]", "");
                pw.println(line);
            }
            br.close();
            pw.close();
    }



}
