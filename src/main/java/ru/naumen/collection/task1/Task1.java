package ru.naumen.collection.task1;

import java.util.*;

/**
 * Дано:
 * <pre>
 * public class User {
 *     private String username;
 *     private String email;
 *     private byte[] passwordHash;
 *     …
 * }
 * </pre>
 * Нужно написать утилитный метод
 * <pre>
 * public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB);
 * </pre>
 * <p>который возвращает дубликаты пользователей, которые есть в обеих коллекциях.</p>
 * <p>Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username,
 * email, passwordHash. Дубликаты внутри коллекций collA, collB можно не учитывать.</p>
 * <p>Метод должен быть оптимален по производительности.</p>
 * <p>Пользоваться можно только стандартными классами Java SE.
 * Коллекции collA, collB изменять запрещено.</p>
 * <p>
 * См. {@link User}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task1
{
    public static void main(String[] args)
    {

    }

    /**
     * Возвращает дубликаты пользователей, которые есть в обеих коллекциях
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB)
    {
        List<User> users = new ArrayList<>();
        Set<User> collAUsers = new HashSet<>(collA);
        for (User user : collB)
        {
            if (collAUsers.contains(user)) //почитал документацию contains - сверяет по equals => нам подходит
            {
                users.add(user);
            }
        }
        //Сложность операции сначала O(n), где n - кол-во элементов в collA, т.к. пришлось перекопировать их в  HashSet
        //Далее идем по collB, получаем O(m)
        //Периодически массив users расширяется, поэтому добавляем еще O(n)
        //Итоговая сложность O(n)
        return users;
    }

}
