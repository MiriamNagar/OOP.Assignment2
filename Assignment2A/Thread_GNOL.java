/**
 * This class calculates the num of lines in all the filenames using threads
 * @author Masuah Shani & Miriam Nagar
 * @version 1.0 9 Jan 2023
 */
package Assignment2A;

import java.io.BufferedReader;
import java.io.FileReader;



public class Thread_GNOL extends Thread{

    private int numOfLines;
    private String fileName;
    private String [] fileNames;

    /**
     * default constructor
     */
    public Thread_GNOL(){}

    /**
     * constructor that initialize filename
     * @param fileName string to be put in filename
     */
    public Thread_GNOL(String fileName)
    {
        this.fileName=fileName;
    }

    /**
     * @return the var numOfLines
     */
    public int getNumOfLines() {
        return numOfLines;
    }

    /**
     * changing the filename value
     * @param fileName string to be put in filename
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Computes a num of lines in a file, or throws an exception if unable to do so.
     */
    @Override
    public void run()
    {
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
    }

    /**
     * func that sums the number of lines in all the files except
     * for each file it calculates the number of lines using the run method
     * @param fileNames string with names of all the files
     * @return the sum of all the lines we counted
     */
    public int getNumOfLinesThreads(String[] fileNames)  {
        long start=System.nanoTime();
        int ans = 0;
        this.fileNames=fileNames;
        for (int i = 0; i < fileNames.length; i++)
        {
            Thread_GNOL threadGnol = new Thread_GNOL(fileNames[i]);
            threadGnol.start();
            try {
                threadGnol.join();
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }

            ans+=threadGnol.getNumOfLines();

        }
        long end=System.nanoTime();
        double time=end-start;
        System.out.println("Time for calculating number of lines using threads: " + time);
        return ans;
    }
}
