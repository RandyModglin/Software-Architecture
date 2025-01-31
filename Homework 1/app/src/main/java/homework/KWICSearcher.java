package homework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class KWICSearcher {
    private ArrayList<String[]> matchingLines;
    private Scanner scanner;

    public KWICSearcher(Scanner scanner){
        matchingLines = new ArrayList<String[]>();
        this.scanner = scanner;
    }

    public void SearchFile(File currFile) throws FileNotFoundException, IOException {
        matchingLines.clear();

        System.out.println("Enter the keyword you want to search: ");

        String searchTarget = scanner.nextLine();
        int foundCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(currFile))){
            String line;

            while ((line = br.readLine()) != null){
                String[] sepLine = line.split("\\s+");

                if(Arrays.asList(sepLine).contains(searchTarget)){
                    matchingLines.add(sepLine);
                    foundCount++;
                }
            }
        }

        PrintSearch(searchTarget, foundCount);
    }

    private void PrintSearch(String searchTarget, int foundCount){
        if(foundCount <= 0){
            System.out.println(searchTarget + " not found");
            return;
        }

        System.out.println(foundCount + " sentence is found: ");

        for(String[] currLine: matchingLines){
            for(String currWord: currLine){
                if(currWord == searchTarget){
                    System.out.print("\u001B[0m" + currWord + "\u001B[33m ");
                }
                else{
                    System.out.print(currWord + " ");
                }
            }
            System.out.println();
        }

        System.out.println();
    }

}
