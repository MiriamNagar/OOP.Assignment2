/**
 * This class creates n files and randomly input num of lines in them,
 * we check the sum of all the number of lines in each file in 3 ways
 * a regular func that calculates, using thread, using threadPool
 * @author Masuah Shani & Miriam Nagar
 * @version 1.0 9 Jan 2023
 */
package Assignment2A;

import java.io.*;
import java.util.Random;
import java.util.concurrent.ExecutionException;


public class Ex2_1 {

    /**
     * func that creates n files and input in each file random number of lines
     * @param n the number of files we're creating
     * @param seed using a single long seed we create a new random number generator
     * @param bound the number of lines in each file
     * @return array of strings with names of all the files we created
     */
    public static String[] creatTextfile(int n, int seed, int bound)
    {
        Random rand = new Random(seed);
        String [] ans=new String[n];
        for (int i = 1; i <= n; i++)
        {
            try {
                FileWriter file =new FileWriter("file"+i);
                ans[i-1]="file"+i;
                int x=rand.nextInt(bound);
                writeInFile(x,file);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return ans;
    }

    /**
     * write in file we give him num times the line "Hello world this is a text file\n"
     * @param num the number of lines we write in the file
     * @param file the file we write the lines in
     */
    public static void writeInFile(int num, FileWriter file)
    {
        BufferedWriter writer=new BufferedWriter(file);

        try
        {
            for (int i = 0; i < num; i++)
            {
                writer.write("Hello world this is a text file\n");

            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * func that sums the number of lines in all the files
     * @param fileNames string with names of all the files
     * @return the sum of all the lines we counted
     */
    public static int getNumOfLines(String[] fileNames)
    {
        long start=System.nanoTime();
        int ans=0;
        for (int i = 0; i < fileNames.length; i++)
        {
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(fileNames[i]));

                while (reader.readLine()!=null)
                {
                    ans++;
                }
                reader.close();
            }
            catch(Exception e)
            {
                throw new RuntimeException(e);
            }
        }
        long end=System.nanoTime();
        long time=end-start;
        System.out.println("Time for calculating number of lines regularity: " + time);
        return ans;

    }

    public static int getNumOfLinesThreads(String[] fileNames)  {
        Thread_GNOL t = new Thread_GNOL();
        int ans = t.getNumOfLinesThreads(fileNames);
        return ans;
    }

    public static int getNumOfLinesThreadPool(String[] fileNames)throws InterruptedException, ExecutionException
    {
        ThreadPool_GNOL t = new ThreadPool_GNOL();
        int ans = t.getNumOfLinesThreadPool(fileNames);
        return ans;
    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String x []= creatTextfile(19,3,80);
        for (int i = 0; i <x.length ; i++) {
            System.out.print(x[i]+", ");
        }
        System.out.println();

//        System.out.println("num of lines: " +getNumOfLines(x));
//        Thread_GNOL threadGnol=new Thread_GNOL();
//        System.out.println("num of lines: " +threadGnol.getNumOfLinesThreads(x));
//        ThreadPool_GNOL threadPool_gnol=new ThreadPool_GNOL();
//        System.out.println("num of lines: " +threadPool_gnol.getNumOfLinesThreadPool(x));


        System.out.println("num of lines: " +getNumOfLines(x));
        System.out.println("num of lines: " +getNumOfLinesThreads(x));
        System.out.println("num of lines: " +getNumOfLinesThreadPool(x));


    }
}
