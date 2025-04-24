package implementations;

import implementations.BSTree;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordTracker {
	public static void main(String[]args) {
		if (args.length != 1) {
			System.out.println("Usage:java WordTracker<filename>");
			return;
		
		}
		String filename = args [0];
		BSTree<Word> tree= new BSTree<>();
		try (Scanner scanner = new Scanner (new File(filename))){
			while
				(scanner.hasNext()) {
				String wordText = scanner.next().replaceAll("[^a-zA-Z]"," ").toLowerCase();
				if (!wordText.isEmpty( )) {
					Word word = new Word(wordText); 
					Word found = tree.search(word).data;
					if
					(found == null) {
						tree.add(word);
					}else
					{
						found.incrementCount();
					}
				}
			}
		System.out.println("Words and their frequencies in alphabetical order:");
		tree.inorderIterator();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found: "+ filename);
			
		}
	}
}

				
				
				
					
				
			
		
	
		
	


