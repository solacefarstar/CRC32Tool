package trebash;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 *
 * @author solace
 */
public class IffReader {

    public static String line;
    public static String enterDir;
    
    

    public static void recursive() throws FileNotFoundException, IOException {
        File dir = new File(enterDir);
        File listDir[] = dir.listFiles();
        int x;

        for (x = 0; x < listDir.length; x++) {
            if (listDir[x].isDirectory()) {
                System.err.println(listDir[x].getName());


            } else if (listDir[x].isFile()) {
                System.out.println("Contents of: " + listDir[x].getName());
                try (BufferedReader br = new BufferedReader(new FileReader(listDir[x]))) {


                    while ((line = br.readLine()) != null) {
                        
                        //Prints the line removing all non-english characters ***
                        System.out.println(line.replaceAll("[^\\p{L}\\p{N}]", "|")); ///***
                        String str = line;
                        Checksum checksum = new CRC32();
                        byte bytes[] = str.getBytes();
                        checksum.update(bytes,0,bytes.length);
                        long lngChecksum = checksum.getValue();
                        System.out.println("CRC32 checksum: " + lngChecksum);


                    }
                }// File List (TRE) method ends

            }
        }

        x++;


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File dir = new File(JOptionPane.showInputDialog(null, "Please Input the Directory to Read From"));
        //String txt = JOptionPane.showInputDialog("Location of Text File to write to?");
        File listDir[] = dir.listFiles();
        // FileWriter fw = new FileWriter(writeTo);
        //BufferedWriter bf = new BufferedWriter(fw);
        int x;
        for (x = 0; x < listDir.length; x++) {
            if (listDir[x].isDirectory()) {
                System.err.println(listDir[x].getName());
                enterDir = listDir[x].getAbsolutePath();
                recursive();


            } else if (listDir[x].isFile()) {
                System.out.println("Contents of: " + listDir[x].getName());
                try (BufferedReader br = new BufferedReader(new FileReader(listDir[x]))) {


                    while ((line = br.readLine()) != null) {

                        //Prints the line removing all non-english characters ***

                        //***System.out.println(line.replaceAll("[^\\p{L}\\p{N}]", "|")); ///***
                        main.list.append(line.replaceAll("[^\\p{L}\\p{N}]", "|\n"));
                        

                    }
                }// File List (TRE) method ends

            }
        }

        x++;

    }
}
