import java.util.Random;

public class Sentence {
    private String subjectMarker;
    private String adjective;
    private String subject;
    private String adverb;
    private String verb;
    private String directObjectMarker;
    private String directObject;

    private String[] sentenceTable = new String[7];

    public Sentence(String sentence) {
        parse(sentence);
    }

    private void parse(String sentence) {
        String[] words = sentence.split(" ");
        if (words.length != 7) {
            throw new IllegalArgumentException("Invalid sentence: Expected exactly 7 words, but got " + words.length);
        }
        subjectMarker = words[0];
        adjective = words[1];
        subject = words[2];
        adverb = words[3];
        verb = words[4];
        directObjectMarker = words[5];
        directObject = words[6];

        System.arraycopy(words, 0, sentenceTable, 0, 7);
    }

    @Override
    public String toString() {
        return String.join(" ", subjectMarker, adjective, subject, adverb, verb, directObjectMarker, directObject);
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = sentenceTable.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            String temp = sentenceTable[i];
            sentenceTable[i] = sentenceTable[index];
            sentenceTable[index] = temp;
        }
    }

    public String getOriginalSentence() {
        return toString();
    }

    public String getShuffledSentence() {
        return String.join(" ", sentenceTable);
    }
}