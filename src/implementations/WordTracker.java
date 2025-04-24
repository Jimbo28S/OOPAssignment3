package implementations;
import utilities.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordTracker {
	public static void main(String[]args) {
		if (args.length != 1) {
			System.out.println("Usage:java WordTracker<filename>");
			return;
		
		}
		int option = 0;
		if (args[1] == "-pf") {
			option = 1;
		}
		else if (args[1] == "-pl") {
			option = 2;
		}
		else if (args[1] == "-po") {
			option = 3;
		}
		String filename = args [0];
		BSTree<Word> tree= new BSTree<>();
		try (Scanner scanner = new Scanner (new File(filename))){
			
			int lineNumber = 0;
			
			while
				(scanner.hasNext()) {
				String wordText = scanner.next().replaceAll("[^a-zA-Z]"," ").toLowerCase();
				if (!wordText.isEmpty( )) {
					Word word = new Word(wordText,lineNumber,filename); 
					Word found = tree.search(word).data;
					if
					(found == null) {
						tree.add(word);
					}else
					{
						found.incrementCount();
						found.addFile(filename);
						found.addLine(lineNumber);
						if (wordText.contains("/n")) {
							lineNumber++;
						}
						
					}
					
					
				}
			}
			//*Need to include what files these words are in & include what lines the words are in.
			//Option 1 need to be in alphabetical order and the files they appear in. check args list for -PF
			//Option 2 alphabetical files and line numbers. -PL
			//Option 3 alpha order files words are in lines they're in and frequency they appear in. -PO.
			
			switch (option) {
			case 1: System.out.println("Words and their files in alphabetical order:");
			Iterator <Word> iterator1 = tree.inorderIterator();
			while (iterator1.hasNext()) {
				Word current = iterator1.next();
				System.out.println(current.getText()+ "Is in file: " + current.getFile());
			}
			break;
			
			case 2: System.out.println("Words and their files and their lines in alphabetical order:");
			Iterator <Word> iterator2 = tree.inorderIterator();
			while (iterator2.hasNext()) {
				Word current = iterator2.next();
				System.out.println(current.getText() + "Is in file: " + current.getFile() + "Is in lines: " + current.getLine());
			}
			break;
			
			case 3: System.out.println("Words, their files, their lines and their frequencies in alphabetical order:");
			Iterator <Word> iterator3 = tree.inorderIterator();
			while (iterator3.hasNext()) {
				Word current = iterator3.next();
				System.out.println(current.getText() + "Is in file: " 
				+ current.getFile() + "Is in lines: " 
				+ current.getLine() + "With frequency: " 
				+ current.getCount());
			}
			break;
			
			default: System.out.println("Option is N/A");
				
				break;
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found: "+ filename);
		}
	}
}
