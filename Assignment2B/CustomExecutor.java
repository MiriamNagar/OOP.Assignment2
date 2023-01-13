/**
 * This class is for creating new time of threadPool that do tasks by their priority
 * @author Masuah Shani & Miriam Nagar
 * @version 1.0 9 Jan 2023
 */
package Assignment2B;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.*;

public class CustomExecutor {
    private final int minSize= Runtime.getRuntime().availableProcessors()/2;
    private final int maxSize = Runtime.getRuntime().availableProcessors()-1;
    private PriorityQueue<Task<?>> tasks=new PriorityQueue<>(Collections.reverseOrder());

    private  ThreadPoolExecutor pool=new ThreadPoolExecutor(minSize,maxSize, 300, TimeUnit.MILLISECONDS,new ArrayBlockingQueue(10) );
    private boolean close;
    private TaskType max;


    /**
     * constructor that by default the threadPool is open for submissions
     */
    public CustomExecutor(){close=false;}

    /**
     * this function is for submitting a task to the priority queue and then running the highest priority thread
     * @param task the task we submit to the pool
     * @return the value that the task returns
     */
    public Future<?> submit(Task<?> task)
    {
        if(!close)
        {
            tasks.add(task);
            Task<?> top=tasks.poll();

            if(top!=null)
            {
                Callable<?> call= top.getTask();
                top.setReturnVal((pool.submit((call))));

                max=top.getType();
            }
            //return task.getReturnVal();
        }
        // return null;
        return task.getReturnVal();

    }

    /**
     * this function is for submitting a task to the priority queue and then running the highest priority thread
     * @param c the task to do
     * @param t and priority of the task
     * @return the value that the task returns
     */
    public Future<?> submit(Callable<?> c, TaskType t)
    {
        Task<?> task=Task.createTask(c,t);
        return submit(task);
    }

    /**
     * this function is for submitting a task to the priority queue and then running the highest priority thread
     * the priority is set by the Task class
     * @param c the task to do
     * @return the value that the task returns
     */
    public Future<?> submit(Callable<?> c)
    {
        Task<?> task=Task.createTask(c);
        return submit(task);
    }

    /**
     * closes the threadPool, can't submit tasks
     * Attempts to stop all actively executing tasks, halts the processing of waiting tasks
     */
    public  void gracefullyTerminate()
    {
        close=true;
        pool.shutdown();
    }

    /**
     * @return the highest current priority in the threadPool
     */
    public int getCurrentMax()
    {
        // if(!tasks.isEmpty())
        {
            return max.getPriorityValue();
        }
        //  return null;
    }


}