package homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class KWIC{
    private File currFile;
    private KWICProcessor processor;
    private KWICSearcher searcher;
    private KWICIndexer indexer;
    private Scanner scanner;

    private String[] supportedTypes = {".TXT"};

    private KWIC() {
        scanner = new Scanner(System.in);

        processor = new KWICProcessor();
        searcher = new KWICSearcher(scanner);
        indexer = new KWICIndexer();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the KWIC System!");
        System.out.println("This system reads txt files from the \"files\" of this project directory.");
        System.out.println("Please add your txt file to this directory.");
        System.out.println();
    
        new KWIC().GetFile();
    }
    
            
    private void GetFile(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the name of your file:");
            
            //Read in the user's file and make sure it's in the "Files" folder
            String fileName = scanner.nextLine();
            currFile = new File("files/"+fileName);

            //Nested Validation, a bit harder to read but provides better feedback to the end user.
            if (currFile.exists()) {
                
                if(ValidateFile(currFile)){
                    MainMenu();
                }  

                else{
                    System.out.println("Sorry, only .txt files are accepted at this time");
                    System.out.println();
                    GetFile();
                }
            }

            else {
                System.out.println("File not found: " + fileName);
                System.out.println("Please ensure it's in the files folder and that you include the file extension.");
                System.out.println();
                GetFile();
            }
        }
    }

    private boolean ValidateFile(File currFile){
        String fileName = currFile.getName().toUpperCase();

        for(String type : supportedTypes){
            if(fileName.endsWith(type)){
                return true;
            }
        }

        return false;
    }

    private void MainMenu(){
        System.out.println("Current File: " + currFile.getName());
        System.out.println("Please enter the number for what you'd like to do next:");
        System.out.println("1. Process File");
        System.out.println("2. Search File");
        System.out.println("3. Index File");
        System.out.println("4. Choose a new File");
        System.out.println("5. Exit System");

        boolean active = true;

        while(active){
            String optionIndex = scanner.nextLine();
    
            switch (optionIndex) {
                case "1":
                    ProcessFile();
                    break;
                case "2":
                    SearchFile();
                    break;
                case "3":
                    IndexFile();
                    break;
                case "4":
                    GetFile();
                    break;
                case "5":
                    active = false;
                    break;
                 default:
                    System.out.println("Invalid option, please try again");
                    break;
            }
        }
    }

    private void IndexFile(){
        try {
            indexer.IndexFile(currFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainMenu();
    }

    private void SearchFile(){
        try {
            searcher.SearchFile(currFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainMenu();
    }

    private void ProcessFile(){
        try {
            processor.ProcessFile(currFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainMenu();
    }
}