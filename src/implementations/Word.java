package implementations;

import java.io.Serializable;

public class Word implements Comparable<Word>, Serializable {

    private static final long serialVersionUID = 1L;

    private String text;
    private int count;
    private String files = "";
    private String lines = "";

    public Word(String text, int line, String file) {
        this.text = text;
        this.count = 1;
        this.files = file;
        this.lines = String.valueOf(line);
    }

    public void incrementCount() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public void addLine(int line) {
        lines = lines + ", " + line;
    }

    public String getLine() {
        return lines;
    }

    public void addFile(String file) {
        if (!files.contains(file)) {
            files = files + ", " + file;
        }
    }

    public String getFile() {
        return files;
    }

    public String getText() {
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
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Word other = (Word) obj;
        return text.equals(other.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
