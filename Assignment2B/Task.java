package Assignment2B; /**
 * This class is for creating tasks that contain method to be executed and the taskType of the method that
 * also contains the priority of execution, were also able to compare the tasks based on the priority.
 * @author Masuah Shani & Miriam Nagar
 * @version 1.0 9 Jan 2023
 */

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class Task<V>  implements Comparable<Task<V>> {

    private Callable<V> task;
    private TaskType type;
    private Future<?> returnVal=null;

    /**
     * constructs Task and is private for use within the class
     * @param task contains method we want to execute
     * @param type the type that sets the priority of the task
     */
    private Task(Callable<V> task, TaskType type)
    {
        this.task=task;
        this.type = type;

    }
    /**
     * @return the callable task
     */
    public Callable<V> getTask() {
        return task;
    }
    /**
     * @return the task type that also has the priority
     */
    public TaskType getType() {
        return this.type;
    }
    /**
     * sets the task to the parameter we get
     * @param task the parameter this.task is going to be equal to, type callable
     */
    public void setTask(Callable<V> task) {
        this.task = task;
    }
    /**
     * sets the type to the parameter we get
     * @param type the parameter this.type is going to be equal to, of type taskType
     */
    public void setType(TaskType type) {
        this.type = type;
    }
    /**
     * static function that creates task object and sets default priority to 5
     * @param task the task in this new Task obj we create
     * @return the Task obj we created
     */
    public static Task<?> createTask(Callable<?> task)
    {
        TaskType type = TaskType.OTHER;
        type.setPriority(5);
        return new Task<>(task,type);

    }
    /**
     * static function that creates task object
     * @param task the task in this new Task obj we create
     * @param type the tasktype of the new obj
     * @return the Task obj we created
     */
    public static Task<?> createTask(Callable<?> task,TaskType type)
    {
        return new Task<>(task,type);

    }



    /**
     * compares two tasks based on their priority
     * if they have the same priority then return 0,
     * if this type priority > task priority return positive int
     * if this type priority < task priority return negative int
     * @param task the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Task task)
    {
        return -(this.type.getPriorityValue()-task.type.getPriorityValue()); //add minus because the propety queue is prioritized min.

    }
    /**
     * checks if tasks are equal, if yes return true otherwise return false
     * we check equality in the taskType priority
     * @param task the obj we compare this task to
     * @return if equals - true, otherwise - false
     */
    public boolean equals(Task<V> task)
    {
        return this.compareTo(task) == 0;
    }

    /**
     * set the result of the callable task
     * @param returnVal the val we set into returnVal
     */
    public void setReturnVal(Future<?> returnVal) {
        this.returnVal = returnVal;
    }

    /**
     * @return the result of the callable task
     */
    public Future<?> getReturnVal() {
        return returnVal;
    }
}























