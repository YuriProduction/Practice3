package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.*;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace
{

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    public static void main(String[] args)
    {
        //Удобно использовать PriorityQueue на благо экономии памяти
        Queue<Word> firstTens = new PriorityQueue<>(11, Comparator.comparingInt(o -> o.wordCount));
        Queue<Word> lastTens = new PriorityQueue<>(11, Comparator.comparingInt(o -> -o.wordCount));
        Map<String, Integer> wordsDictionary = new LinkedHashMap<>();
        new WordParser(WAR_AND_PEACE_FILE_PATH).forEachWord(
                word ->
                        wordsDictionary.merge(word, 1, Integer::sum)
        ); //O(n)

        for (Map.Entry<String, Integer> entry : wordsDictionary.entrySet())
        {
            final Word newWord = new Word(entry.getValue(), entry.getKey());
            if (firstTens.size() < 10 || newWord.wordCount > firstTens.peek().wordCount)
            {
                firstTens.offer(newWord);
                if (firstTens.size() > 10)
                {
                    firstTens.poll();
                }
            }
            if (lastTens.size() < 10 || newWord.wordCount < lastTens.peek().wordCount)
            {
                lastTens.offer(newWord);
                if (lastTens.size() > 10)
                {
                    lastTens.poll();
                }
            }
        } //O(n*log(n))

        for (int i = 0; i < 10; i++)
        {
            System.out.println(firstTens.poll());
        }
        System.out.println("----------------------------------");
        for (int i = 0; i < 10; i++)
        {
            System.out.println(lastTens.poll());
        }

        //Итог O(n*log(n))
    }
}
