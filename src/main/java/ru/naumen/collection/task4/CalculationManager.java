package ru.naumen.collection.task4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;


public class CalculationManager<R>
{
    private final BlockingQueue<Task<R>> taskQueue = new LinkedBlockingQueue<>();

    public void addTask(Function<Void, R> function)
    {
        Task<R> task = new Task<>(function);
        taskQueue.add(task);
    }

    public R getResult() throws InterruptedException
    {
        Task<R> task = taskQueue.take(); //получили задачу
        task.execute(); //выполнили задачу
        return task.getResult();
    }

    public static void main(String[] args) throws InterruptedException
    {
        CalculationManager<Integer> manager = new CalculationManager<>();
        manager.addTask(Void -> {
            int sum = 0;
            for (int i = 1; i <= 100; i++)
            {
                sum += i;
            }
            return sum;
        });

        manager.addTask(Void -> 42);

        System.out.println(manager.getResult()); // Результат первой задачи
        System.out.println(manager.getResult()); // Результат второй задачи
        //BlockingQueue поддерживает ожидание при добавлении и извлечении элементов из очереди
        //Сложность O(n), n - число задач, т.к. это обычная очередь
    }
}


