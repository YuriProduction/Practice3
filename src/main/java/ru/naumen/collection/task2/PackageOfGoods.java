package ru.naumen.collection.task2;

/**
 * Перечисление наборов товаров
 */
public enum PackageOfGoods
{
    NO_GOOD("Нет товаров"),
    DRINKS("Напитки"),
    EAT_AND_DRINKS("Еда и напитки");
    private final String title;

    PackageOfGoods(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
}
