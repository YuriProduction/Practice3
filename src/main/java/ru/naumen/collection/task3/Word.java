package ru.naumen.collection.task3;

public class Word implements Comparable<Word> //Пытался как-то прикрутить, чтоб можно было сортировать по числу встреч слова, но что=то не вышло
{
    private int wordCount; //используем как доп.атрибут
    private final String word;

    public Word(int wordCount, String word)
    {
        this.wordCount = wordCount;
        this.word = word;
    }

    @Override
    public String toString()
    {
        return "Word{" + "wordLength=" + wordCount + ", word='" + word + '\'' + '}';
    }

    @Override
    public int compareTo(Word o)
    {
        if (o.wordCount != wordCount)
        {
            return Integer.compare(wordCount, o.wordCount);
        } else
        {
            return word.compareTo(o.word); // Сравниваем по длине слова, чтобы не потерять элементы
        }
    }
}
