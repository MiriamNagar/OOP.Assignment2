# Assignment 2 Part 1
In this program we create files with randim number of lines and calculate the sum of all the files lines in 3 ways:
* Regularly
* Using threads
* Using threadPool
and saw which way of calculating was the fastest
the result we got is that using threadPool is the fastest way to calculate.

## Technologies
We used Java in the project.
We used io.*, Random, ExecutionException, BufferedReader, FileReader, concurrent.*, Callable, ExecutorService, Executors, Future libraries.

## Launch
To launch the methods you need to use creatTextfile() to create the files and afterwards use getNumOfLines(), getNumOfLinesThreads()) and getNumOfLinesThreadPool() on the retune value from creatTextfile() method. 

## Examples of use
See how to use in Ex2_1 main method.

## Tests
We ran for the same files those three static methods and printed the results to see they worked and their time, we created 19 files with max of 80 lines.

## Run Time
![image](https://user-images.githubusercontent.com/118976833/212316807-3a64561a-3f72-4625-91ad-0c9f014aeac4.png)
The time printed is in nano seconds.
We can see in the run times that calculating the lines regularly takes much more time then using threads or threadPool, and using threadPool takes less time then using threads.
When using threads we try to optimize CPU usage. Rather than sticking with a process for a long time, even when it's waiting on data for example, the system quickly changes to the next task. because of that using threads improve the running time, so in our program we can see how the threads truly give us better performance timewise rather then calculating the lines regularly.
Furtheremore threadPool will provide benefits for frequent and relatively short operations by reusing threads that have already been created instead of creating new ones (an expensive process) so for this reason the time preformance using the threadPool is better then using threads and claculating lines regularly.

# Assignment 2 Part 2 - Task & customExecutor
Java enables developers to set the priority of a thread, but not the Runnable operation it executes.
Tightly coupling the operation with the execution path that runs it creates major drawback when
using an executor such as a ThreadPoolExecutor: the collection of threads in an executor is defined by
a ThreadFactory. By default, it creates all threads with the same priority and non-daemon status.
Moreover, if we wish to execute a returning value operation, for example using the Callable<V>
interface, there are no constructors in the Thread class that get a Callable<V> as parameter and we
ought to use an Executor of some type, such as a ThreadPoolExecutor.
To solve the non priority problem in the threadPool and also have values returned we create two new types that extend the functionality of Javas Concurrenct Framework:
* Task - Build mainly from Callable that responsible for the actual task.
Each task has a priority used for scheduling͕ inferred from the integer value of the tasks Type
* customExecutor- custom thread pool class that have priority consideration when subniting tasks.

## Technologies
We used Java in the project.
We used Callable and Future to create threads, Collections, PriorityQueue, concurrent.*, Test, Logger, LoggerFactory, Thread.sleep  libraries.

## Launch
To launch the program you need to create Tasks and register them in customExecutor obj you create and do Casting when using the Future class get method

## Examples of use
See Examples in the Tests Class.

## Tests
We used the given tests among others we wrote and saw it runs correctly

## complexity
For the max priority part, each time we submited another task to customExecutor we saved the task with the highest priority in a variable - "max" so the getCurrentMax()
method complexity is O(1)
