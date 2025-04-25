package implementations;

import utilities.Iterator;
import implementations.BSTree;
import implementations.Word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordTracker {

    private static final String SERIALIZED_FILE = "repository.ser";

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f<output.txt>]");
            return;
        }

        String inputFileName = args[0];
        String option = args[1];
        String outputFileName = null;

        if (args.length == 3 && args[2].startsWith("-f")) {
            outputFileName = args[2].substring(2); // Extract filename after -f
        }

        // Load tree from serialized pre-order list
        BSTree<Word> tree = loadTree();

        // Read and process input file
        try (Scanner scanner = new Scanner(new File(inputFileName))) {
            int lineNumber = 0;

            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);

                while (lineScanner.hasNext()) {
                    String wordText = lineScanner.next().replaceAll("[^a-zA-Z]", "").toLowerCase();
                    if (!wordText.isEmpty()) {
                        Word word = new Word(wordText, lineNumber, inputFileName);
                        BSTreeNode<Word> node = tree.search(word);

                        if (node == null) {
                            tree.add(word);
                        } else {
                            Word existing = node.getElement();
                            existing.incrementCount();
                            existing.addFile(inputFileName);
                            existing.addLine(lineNumber);
                        }
                    }
                }

                lineScanner.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + inputFileName);
            return;
        }

        // Prepare output destination
        PrintStream out = System.out;
        try {
            if (outputFileName != null) {
                out = new PrintStream(new FileOutputStream(outputFileName));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not write to file: " + outputFileName);
            return;
        }

        // Output based on command-line option
        Iterator<Word> iterator = tree.inorderIterator();

        switch (option) {
            case "-pf":
                out.println("Words and their files:");
                while (iterator.hasNext()) {
                    Word word = iterator.next();
                    out.println(word.getText() + " | File(s): " + word.getFile());
                }
                break;

            case "-pl":
                out.println("Words and their files with line numbers:");
                while (iterator.hasNext()) {
                    Word word = iterator.next();
                    out.println(word.getText() + " | File(s): " + word.getFile() + " | Lines: " + word.getLine());
                }
                break;

            case "-po":
                out.println("Words, files, lines, and frequency:");
                while (iterator.hasNext()) {
                    Word word = iterator.next();
                    out.println(word.getText() + " | File(s): " + word.getFile() + " | Lines: " + word.getLine() + " | Count: " + word.getCount());
                }
                break;

            default:
                out.println("Invalid option. Use -pf, -pl, or -po.");
        }

        if (out != System.out) {
            out.close();
        }

        // Save pre-order list of words to serialized file
        saveTree(tree);
    }

    private static BSTree<Word> loadTree() {
        File file = new File(SERIALIZED_FILE);
        if (!file.exists()) return new BSTree<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                @SuppressWarnings("unchecked")
                ArrayList<Word> wordList = (ArrayList<Word>) obj;
                BSTree<Word> tree = new BSTree<>();
                for (Word word : wordList) {
                    tree.add(word);
                }
                System.out.println("Rebuilt BSTree from pre-order serialized word list.");
                return tree;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load tree: " + e.getMessage());
        }

        return new BSTree<>();
    }

    private static void saveTree(BSTree<Word> tree) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIALIZED_FILE))) {
            ArrayList<Word> preorderList = new ArrayList<>();
            Iterator<Word> preorder = tree.preorderIterator();
            while (preorder.hasNext()) {
                preorderList.add(preorder.next());
            }
            oos.writeObject(preorderList);
            System.out.println("Saved BSTree as pre-order word list to " + SERIALIZED_FILE);
        } catch (IOException e) {
            System.out.println("Failed to save tree: " + e.getMessage());
        }
    }
}

