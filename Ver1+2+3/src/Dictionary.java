public class Dictionary {
    private Word[] words = new Word[200000];
    private int count_word;                   // số từ của mảng words

    /**
     * Constructor.
     */
    public Dictionary() {
    }

    /**
     * getter, settet cho count_word.
     */
    public int getCount_word() {
        return count_word;
    }

    public void setCount_word(int count_word) {
        this.count_word = count_word;
    }

    /**
     * getter phan tu thu i.
     */
    public Word getWord(int i) {
        return words[i];
    }

    /**
     * setter cho words.
     */
    public void setWords(Word word, int i) {
        words[i] = word;
    }
}