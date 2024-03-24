package ru.naumen.collection.task2;

import java.util.List;
import java.util.Map;

/**
 * Дано:
 * <pre>
 * public class Ticket {
 *     private long id;
 *     private String client;
 *     …
 * }</pre>
 * <p>Разработать программу для бармена в холле огромного концертного зала.
 * Зрители в кассе покупают билет (класс Ticket), на котором указан идентификатор билета (id) и имя зрителя.
 * При этом, есть возможность докупить наборы разных товаров (комбо-обед): нет товаров, напитки, еда и напитки.
 * Доп. услуги оформляются через интернет и привязываются к билету, но хранятся отдельно от билета
 * (нельзя добавить товары в класс Ticket).</p>
 * <p>Бармен сканирует билет и получает объект Ticket. По этому объекту нужно уметь
 * находить необходимые товары по номеру билета. И делать это нужно очень быстро,
 * ведь нужно как можно быстрее всех накормить.</p>
 * <p>
 * См. {@link Ticket}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task2
{

    public static void main(String[] args)
    {
        Map<Ticket, PackageOfGoods> barmenBook = fillBarmenBook();
        List<Ticket> ticketList = List.of(new Ticket(2, "Anton"),
                new Ticket(1, "Vitali"),
                new Ticket(3, "Elena"));
        for (final Ticket ticket : ticketList)
        {
            System.out.println(barmenBook.get(ticket).getTitle());
        }
        //Выбрал Map, так как мгновенно получаем объект по ключу (за исключением коллизии)
        //Сложность каждого get = O(1) => общая сложность O(n), где n - число гостей мероприятия
    }

    private static Map<Ticket, PackageOfGoods> fillBarmenBook()
    {
        return Map.of(new Ticket(1, "Vitali"), PackageOfGoods.NO_GOOD,
                new Ticket(2, "Anton"), PackageOfGoods.DRINKS,
                new Ticket(3, "Elena"), PackageOfGoods.EAT_AND_DRINKS);
    }
}
