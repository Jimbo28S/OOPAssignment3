package implementations;

public class Word implements Comparable<Word>{
		
		private String text;
		private int count;
		private String files = "";
		private String lines = "";
		public Word (String text,int line,String file)
		{
			this.text = text;
			this.files = file;
			this.lines = String.valueOf(line);
			this.count = 1;
		}
		
		public void incrementCount () {
			count ++;
		}
		public int getCount() {
			return count;
		}
		public void addLine (int line) {
			lines = lines + ", " + line;
		}
		public String getLine () {
			return lines;
		}
		public void addFile (String file) {
			files = files + ", " + file;
		}
		public String getFile () {
			return files;
		}
		public String getText()
		{
			return text;
		}
		
		@Override
		public int compareTo(Word other) {
			return this.text.compareTo(other.text);
		}
		@Override
		public String toString() {
			return text + " ;" + count;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == this.text) {
				return this.text.equals(this.text);
			}
			return false;
			
			}
		@Override
		public int hashCode() {
			return
					text.hashCode();
		}
		
	}
