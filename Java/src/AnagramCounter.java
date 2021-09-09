public class AnagramCounter {

    private int wordLength;
    private int count;

    public AnagramCounter(int wordLength, int count) {
        this.wordLength = wordLength;
        this.count = count;
    }

    public int getWordLength() {
        return wordLength;
    }

    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
