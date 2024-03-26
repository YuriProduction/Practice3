package ru.naumen.collection.task4;

import java.util.function.Function;

public class Task<R>
{
    private final Function<Void, R> function;
    private R result;

    public Task(Function<Void, R> function)
    {
        this.function = function;
    }

    public void execute()
    {
        result = function.apply(null);
    }

    public R getResult()
    {
        return result;
    }
}
