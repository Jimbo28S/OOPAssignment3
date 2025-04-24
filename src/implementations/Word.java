package implementations;

public class Word implements Comparable<Word>{
		
		private String text;
		private int count;
		public Word (String text)
		{
			this.text = text;
					this.count = 1;
		}
		
		public void incrementCount () {
			count ++;
		}
		public int getCount() {
			return count;
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

}
