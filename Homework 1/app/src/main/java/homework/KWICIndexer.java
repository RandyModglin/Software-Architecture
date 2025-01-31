package homework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

public class KWICIndexer {

    public void IndexFile(File currFile) throws FileNotFoundException, IOException{
        HashMap<String, ArrayList<Integer>> keyWordMap = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(currFile))) {
            String line;
            int currIndex = 1;

            while ((line = br.readLine()) != null) {
                String[] keywords = line.split("\\s+");

                for (String word : keywords) {
                    ArrayList<Integer> indexes = keyWordMap.get(word);
                    
                    if(indexes == null){
                        keyWordMap.put(word, new ArrayList<>());
                    }
                    
                    keyWordMap.get(word).add(currIndex);
                }

                currIndex += 1;
            }
            
            DisplayIndex(keyWordMap);
        }
    }

    private void DisplayIndex(HashMap<String, ArrayList<Integer>> keyWordMap){
        System.out.println();

        TreeMap<String, ArrayList<Integer>> sortedMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        sortedMap.putAll(keyWordMap);

        for(Entry<String, ArrayList<Integer>> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            ArrayList<Integer> indexes = entry.getValue();

            System.out.print(key);
            for(int index: indexes){
                System.out.print(", " + index);
            }

            System.out.println();
        }

        System.out.println();
    }
}
