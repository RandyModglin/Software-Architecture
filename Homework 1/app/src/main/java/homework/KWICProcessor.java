package homework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry; 
import java.util.TreeMap;

public class KWICProcessor {
    public void ProcessFile(File currFile) throws FileNotFoundException, IOException {
        HashMap<String, Integer> processedLines = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(currFile))){
            String line;
            int currIndex = 1;

            while ((line = br.readLine()) != null){
                String[] sepLine = line.split("\\s+");
                

                for(String word : sepLine){
                    Collections.rotate(Arrays.asList(sepLine), 1);
                    String joinLine = String.join(" ", sepLine);
                    processedLines.put(joinLine, currIndex);
                }

                currIndex++;
            }
        }

        PrintProcess(processedLines);
    }

    public void PrintProcess(HashMap<String, Integer> processedLines){
        System.out.println();

        TreeMap<String, Integer> sortedMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        sortedMap.putAll(processedLines);

        System.out.print("Index ");
        System.out.print(" Circular Shifted Line ");
        System.out.println(" Original Line Index");

        int index = 1;
        for(Entry<String, Integer> entry : sortedMap.entrySet()) {
            String line = entry.getKey();
            Integer originIndex = entry.getValue();

            System.out.print(index + ": ");
            System.out.print(line + "       ");
            System.out.println(originIndex);

            index++;
        }

        System.out.println();
    }
    
}
