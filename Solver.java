import java.util.ArrayList;

public class Solver {

    final int ALPHABET_SIZE = 26;
    private int min_length;                                         // minimum length of words to find -- inclusive
    private char offset = 'A';                                      // offset for the ascii characters - if dictionary is capital = 'A', for lower case 'a'
    TrieNode root;                                                  // root of the Trie

    // TrieNode class
    class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];          // children of node
        boolean end_of_word;                                        // true - if end of word, otherwise false

        // TrieNode constructor
        TrieNode() {
            end_of_word = false;
            // initializing all children to null
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                children[i] = null;
            }
        }
    }

    // constructor
    public Solver(ArrayList<String> dictionary, int minimum_length) {
        this.min_length = minimum_length;
        this.root = new TrieNode();
        // adds all the words in the dictionary to Trie tree
        for (String word : dictionary) {
            insert(root, word);
        }
    }

    // Adds word to the Trie tree
    private void insert(TrieNode root, String word) {
        // Stores words that are only greater than or equal to the minimum length
        if(word.length() >= min_length) {
            TrieNode child = root;
            int temp_index;
            //loop through all characters of the word
            for (int i = 0; i < word.length(); i++) {
                if (Character.isLetter(word.charAt(i))) {
                    temp_index = word.charAt(i) - offset;
                    // if child node doesn't exist create one
                    if (child.children[temp_index] == null) {
                        child.children[temp_index] = new TrieNode();
                    }
                    // move on to the child node
                    child = child.children[temp_index];
                }
            }
            // set end of word to true
            child.end_of_word = true;
        }
    }


    // Method to find all valid words in the Board
    // Calls search method on every character
    public void getValidWords(Board board) {
        boolean[][] visited = new boolean[board.getRows()][board.getColumns()];
        TrieNode child = root;
        String str = "";
        // loop through every character on the board and call search (dfs) on it
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                // if it exist in the trie, run search on it
                if (child.children[board.getChar(i, j) - 'A'] != null) {
                    search(child.children[board.getChar(i, j) - 'A'],
                            board, i, j, visited, str);
                    str = "";
                }
            }
        }
    }

    // runs recursive dfs algorithm on a character to find all possible words
    // if a word exists in the dictionary - adds it to a HashMap (stores unique words)
    // if the word path is not in the trie, it stops computation and returns
    private void search(TrieNode node, Board board, int r, int c, boolean[][] visited, String str) {
        // if node is null or already visited return
        if(node == null || visited[r][c] == true){  return; }

        // concatenate letter to the string
        char temp_char = board.getChar(r, c);
        str += temp_char;
        // set current character to visited
        visited[r][c] = true;

        // if end of word and greater than minimum length - add it to the HashMap of valid words
        // we do not return here as there may be another word with the current str as the prefix
        if(node.end_of_word == true && str.length() >= min_length){
            board.addWord(str);
        }

        // checks all surrounding characters
        for(int i = -1; i <= 1; i++){                   // i = change in row
            for (int j = -1; j <= 1; j++){              // j = change in column
                // if current square - continue
                if(i == 0 && j == 0){
                    continue;
                }
                // else check if square is accessible - if so recursively call dfs on it
                else if(isSafe(r, c, i, j, board)){
                    search(node.children[board.getChar(r+i, c+j) - 'A'], board, r+i, c+j, visited, str);
                }
            }
        }

        visited[r][c] = false;        // sets character back to not visited
        return;
    }

    // method to check if new dimensions are safe or not
    // returns true if the r+i and c+j are within the board's dimensions, else false
    private boolean isSafe(int r, int c, int i, int j, Board board) {
        return ((r+i >= 0 && r+i < board.getRows()) && (c+j >= 0 && c+j < board.getColumns()));
    }

     /*
    public void printTrie() {
        print(root, 0);
    }

    private void print(TrieNode root, int i) {
        System.out.println(root.val);
        // here you can play with the order of the children

        for (TrieNode child : root.children) {
            if (child != null) {
                print(child, i + 2);
            }
        }
    }
    */
}
