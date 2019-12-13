// author: Muhammad Ahmad
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length >= 2) {
            System.out.println("program start");
            ArrayList<String> dictionary = new ArrayList<String>();         // Array list with all the words in the dictionary
            boolean upper_case = true;                                      // if dictionary has upper case or lower case letters
            int rows = 0;                                                   // number of rows - initialize to 0
            int cols = 0;                                                   // number of cols - initialize to 0
            int minimum_length = 3;                                         // minimum length of words to find -- inclusive

            //* reading in dictionary from a txt file *\\
            //*****************************************\\
            File file = new File(args[0]);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                dictionary.add(sc.nextLine().toUpperCase());
                //upper_case = Character.isUpperCase(dictionary.get(0).charAt(0));
            }
            //*****************************************\\


            //* reading in the board from a txt file *\\
            //*****************************************\\
            file = new File(args[1]);
            sc = new Scanner(file);
            String str = sc.nextLine();
            String[] tokens = str.split(" ");
            rows = Integer.valueOf(tokens[0]);
            cols = Integer.valueOf(tokens[1]);
            char[][] input_array = new char[rows][cols];                // 2D char array of the characters on the board
            for (int i = 0; i < rows; i++) {
                if (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split(" ");
                    for (int j = 0; j < cols; j++) {
                        input_array[i][j] = Character.toUpperCase(tokens[j].toCharArray()[0]);
                    }
                }
            }

            //*****************************************\\

            // Uses character array to create an instance of Board
            Board board = new Board(input_array, rows, cols);

            // Creates an instance of solver - makes a trie tree with all the words in the dictionary
            Solver solve = new Solver(dictionary, minimum_length);

            // finding and printing valid words
            solve.getValidWords(board);
            board.printValidWords();

            sc.close();                  // closing scanner
        }
        else{
            System.out.println("Please provide a dictionary and a board file");
        }
    }
}
