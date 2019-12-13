Muhammad Ahmad
Coding Exercise
Date: 12/13/2019

How to run the program:
Go to the file directory and double check you have a dictionary (txt file) and a board (txt file)
Run the following commands:
1. javac Main.java
2. java Main [dictionary] [board]

Dictionary file:
List of words

Board file:
On the first line it should contain 2 integers for number of rows and columns
Followed by the characters separated by a space. A new line denotes, a new row

Code layout:
There are 3 classes in this package:
1. Main - has the main class, reads the dictionary and the board
2. Board - contains information about the board and has it's corresponding getters
3. Solver - creates a Trie (with the help of a subclass: TrieNode), searches valid words on the board

Core functionality of the program:
Using a dictionary (text file), the program creates a Trie tree with all the valid words.
Then the program runs a dfs algorithm on every character of the board and compares it with the Trie on every iteration.
If it exists, the word is added to a HashMap. If there is no corresponding path on the Trie, the program returns and moves onto the next possibility.