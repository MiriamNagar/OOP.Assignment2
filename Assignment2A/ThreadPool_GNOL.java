/**
 * This class calculates the num of lines in all the filenames using threadPool
 * @author Masuah Shani & Miriam Nagar
 * @version 1.0 9 Jan 2023
 */
package Assignment2A;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPool_GNOL implements Callable {

    String fileName;

    public ThreadPool_GNOL(){}
    public ThreadPool_GNOL(String fileName)
    {
        this.fileName=fileName;
    }


    /**
     * Computes a num of lines in a file, or throws an exception if unable to do so.
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Object call() throws Exception {
        int numOfLines=0;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            while (reader.readLine()!=null)
            {
                numOfLines++;
            }
            reader.close();
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
        return numOfLines;
    }

    /**
     *func that sums the number of lines in all the files except
     * for each file it calculates the number of lines using the call method
     * @param fileNames string with names of all the files
     * @return the sum of all the lines we counted
     * @throws InterruptedException if unable to compute a result
     * @throws ExecutionException if unable to compute a result
     */
    public int getNumOfLinesThreadPool(String[] fileNames)throws InterruptedException, ExecutionException
    {
        long start=System.nanoTime();
        Future<Integer> ans[]=new Future[fileNames.length];
        ExecutorService executor = Executors.newFixedThreadPool(fileNames.length);

        for (int i = 0; i < fileNames.length; i++)
        {
            Callable c = new ThreadPool_GNOL(fileNames[i]);
            Future<Integer> future = executor.submit(c);
            ans[i]=future;
        }
        executor.shutdown();

        int ansRet=0;
        for (int i = 0; i < ans.length; i++) {

            ansRet+=ans[i].get();
        }
        long end=System.nanoTime();
        double time=end-start;
        System.out.println("Time for calculating number of lines using threadPool: " +time);
        return ansRet;
    }


//    public static void main(String[] args) {
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//
//        ScheduledFuture<String> scheduledFuture = scheduledExecutorService.schedule(new Callable<String>() {
//            public String call() throws Exception {
//                System.out.println("Executed!");
//                return "Called!";
//            }
//        }, 5,TimeUnit.SECONDS);
//        String res=null;
//        try {
//            res = scheduledFuture.get();
//            System.out.println("result = "+res);
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//        if (scheduledFuture.isDone()){
//            System.out.println("Done!");
//            scheduledExecutorService.shutdown();
//        }
//    }

}
