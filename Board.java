import java.util.HashMap;

public class Board {

    private int rows;                                               // number of rows
    private int columns;                                            // number of columns
    private char[][] main_board;                                         // 2D character array of the board
    private HashMap<String, Integer> unique_words;                  // HashMap with all the valid words
    // constructor
    public Board(char[][] input, int rows, int cols){
        this.rows = rows;
        this.columns = cols;
        this.main_board = input;
        this.unique_words = new HashMap<String, Integer>();
    }

    // adds word to the HashMap
    public void addWord(String str) {
        this.unique_words.put(str, 1);
    }

    // prints all the valid words
    public void printValidWords() {
        for(String w : unique_words.keySet()){
            System.out.println(w);
        }
        System.out.println("Number of words: " + unique_words.size());
    }

    // rows - getter
    public int getRows() {
        return this.rows;
    }
    // columns - getter
    public int getColumns() {
        return this.columns;
    }

    // board - getter
    public char[][] getBoard() {
        return this.main_board;
    }

    // getter to access a character from the board
    public char getChar(int i, int j) {
        return main_board[i][j];
    }

}
