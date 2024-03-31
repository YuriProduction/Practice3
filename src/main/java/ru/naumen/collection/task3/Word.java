package ru.naumen.collection.task3;

public class Word
{
    public final int wordCount;
    public final String word;

    public Word(int wordCount, String word)
    {
        this.wordCount = wordCount;
        this.word = word;
    }

    @Override
    public String toString()
    {
        return "Word{" +
                "wordCount=" + wordCount +
                ", word='" + word + '\'' +
                '}';
    }
}
