package Assignment2B;

import java.util.PriorityQueue;
import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import java.util.concurrent.*;

public class Tests {

    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    @Test
    public void partialTest(){
        CustomExecutor customExecutor = new CustomExecutor();
        var task = Task.createTask(()->{
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);
        var sumTask = customExecutor.submit(task);
        final int sum;
        try {
            sum = (int) sumTask.get(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        logger.info(()-> "Sum of 1 through 10 = " + sum);
        Callable<Double> callable1 = ()-> {
            return 1000 * Math.pow(1.02, 5);
        };
        Callable<String> callable2 = ()-> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            return sb.reverse().toString();
        };
        // var is used to infer the declared type automatically
        var priceTask = customExecutor.submit(()-> {
            return 1000 * Math.pow(1.02, 5);
        }, TaskType.COMPUTATIONAL);
        var reverseTask = customExecutor.submit(callable2, TaskType.IO);
        final Double totalPrice;
        final String reversed;
        try {
            totalPrice = (Double) priceTask.get();
            reversed = (String) reverseTask.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        logger.info(()-> "Reversed String = " + reversed);
        logger.info(()->String.valueOf("Total Price = " + totalPrice));
        logger.info(()-> "Current maximum priority = " +
                customExecutor.getCurrentMax());
        customExecutor.gracefullyTerminate();
    }


    public static void main(String[] args) {

        PriorityQueue<Task> q2=new PriorityQueue<>();

        Callable<String> call1= new Callable() {
            @Override
            public Object call() throws Exception {
                sleep(7000);
                System.out.println("h1");

                return "hello1";
            }
        };
        Callable<String> call2= new Callable() {
            @Override
            public Object call() throws Exception {

                sleep(7000);
                System.out.println("h2");

                return "h2";
            }
        };
        Callable<String> call3= new Callable() {
            @Override
            public Object call() throws Exception {
                sleep(7000);
                System.out.println("h3");

                return "h3";
            }
        };
        Callable<Double> callable1 = ()-> {
            sleep(7000);
            System.out.println("h12");
            return 1000 * Math.pow(1.02, 5);
        };



        Task t1=Task.createTask(callable1,TaskType.IO);
        Task t2=Task.createTask(call2,TaskType.OTHER);
        Task t3=Task.createTask(call3,TaskType.COMPUTATIONAL);
        TaskType.IO.setPriority(1);
        TaskType.COMPUTATIONAL.setPriority(5);
        Task t4= Task.createTask(call1,TaskType.IO);
        CustomExecutor ce=new CustomExecutor();

        var h1=ce.submit(t1);
        var h2=ce.submit(t2);
        var h3=ce.submit(t3);
        var h4=ce.submit(t4);
        ce.gracefullyTerminate();


        try
        {
            System.out.println(h1.get()+" "+h2.get()+" "+h3.get()+" "+h4.get());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }


    }
}

