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
        //Наблюдение1: если использовать HashMap, то,
        //поскольку внутри массив из Bucket, он будет постоянно расширяться
        //причем очень часто, так как много различных слов
        //Ну то есть примерно log_3(n) раз он расшириться, 3 очень условно
        //А еще m раз мы перезапишем массив - итог O(n*log_3(n))
        //Далее, по нему медленно итерироваться, так как часть ячеек
        //может быть не заполнена, а нам нужно отсортировать коллекцию


        //Наблюдение2: По LinkedHashMap быстрее итерироваться, но по памяти добавляем 2 узла

        //Наблюдение3: Можно создать TreeSet<Word>, где Word состоит из полей [int wordLength, String word],
        //и сортировать по wordLength, но, к сожалению, у интерфейса Set нет метода get(). Так бы я, думаю,
        //как-то мог получать значения и изменять wordLength

        //Наблюдение4: Вроде бы, TreeMap здесь не очень уместен, т.к сложность будет O(n*log_2(n)), да и
        //зачем нам сортировка по ключам? Нам же слова нужно считать...
        //Updated: так-то вроде норм, потому что потом сортировать не придется, а сортировка может занять O(n^2)
        //а то, что у нас значение будет лежать в памяти, это конечно удручает

        //Updated: Решил использовать LinkedHashMap + TreeSet все же, лучше варианта не нашел.

        Map<String, Integer> wordsDictionary = new LinkedHashMap<>();
        new WordParser(WAR_AND_PEACE_FILE_PATH).forEachWord(
                word ->
                {
                    if (!wordsDictionary.containsKey(word))
                    {
                        wordsDictionary.put(word, 1);
                    } else
                    {
                        int count = wordsDictionary.get(word);
                        wordsDictionary.put(word, ++count);
                    }
                }
        );//Как я посчитал выше, примерно log_3(n) раз он расшириться, 3 очень условно, n - кол-во строк в файле
        TreeSet<Word> sortedWordsByFrequency = new TreeSet<>();
        for (Map.Entry<String, Integer> en : wordsDictionary.entrySet())
        {
            sortedWordsByFrequency.add(new Word(en.getValue(), en.getKey()));
        }//O(log(m)*m), где m - размер словаря

        //Наименее используемые 10 слов
        Iterator<Word> iterator = sortedWordsByFrequency.descendingIterator();
        for (int i = 0; i < 10; i++)
        {
            System.out.println(iterator.next());
        }
        //Наиболее используемые 10 слов
        iterator = sortedWordsByFrequency.iterator();
        for (int i = 0; i < 10; i++)
        {
            System.out.println(iterator.next());
        }

        //Итог O(n*log(n)+log(m)*m+20) ~ O(n*log(n)), n - кол-во строк в файле
    }
}
