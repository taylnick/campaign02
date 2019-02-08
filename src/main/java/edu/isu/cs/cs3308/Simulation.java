package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.List;
import edu.isu.cs.cs3308.structures.impl.LinkedQueue;
import edu.isu.cs.cs3308.structures.impl.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class representing a wait time simulation program.
 *
 * @author Isaac Griffith
 * @author
 */
public class Simulation {

    private int arrivalRate;
    private int maxNumQueues;
    private Random r;
    private int numIterations = 50;
    private int minutesPassed = 0;
    private static ArrayList<LinkedQueue> queueArray;
    public static SinglyLinkedList waitTimes = new SinglyLinkedList();

    // You will probably need more fields

    /**
     * Adds the value 0(signifying 0 time in the line) to the shortest line available or the first line if they are all equal.
     */
    public void addToShortest(){
        LinkedQueue shortest = queueArray.get(0);
        for(LinkedQueue curr: queueArray){
            if(curr.size() < shortest.size()) shortest = curr;
        }
         shortest.offer(0);
    }

    /**
     * Takes all the values stored in the waitTimes list and calculates an overall average by emptying the list so it is ready for the next iteration.
     * @return int (average wait time)
     */
    public int calcAvgWaitTime(){
        int totalNumOfPeople = waitTimes.size();
        int totalWait = 0;
        for(int i = 0; i < waitTimes.size(); i++) {totalWait += (int)waitTimes.removeFirst();}
        return totalWait / totalNumOfPeople;
    }

    /**
     * Iterates through all the time values stored in the queueArray and increments by one.
     */
    public void updateQueueTimes(){
        for(LinkedQueue current: queueArray){
            int counter = 0;
            while(counter < current.size()){
                current.offer((int)current.poll() + 1);
                counter++;
            }
        }
    }

    /**
     * Iterates through 720 minutes of new people arriving, being assigned to lines, and two people from each line getting out and recording the final time to waitTimes
     */
    public void updateNewMinute(){
        int counter = 0;
        while(counter < 720) {
            int newArrivals = getRandomNumPeople(arrivalRate);
            while (newArrivals != 0) {
                addToShortest();
                newArrivals--;
            }
            for (LinkedQueue current : queueArray) {
                waitTimes.addFirst(current.poll());
                waitTimes.addFirst(current.poll());
            }
            updateQueueTimes();
            counter++;
        }
    }

    /**
     * Executes the Simulation
     */
    public void runSimulation() {
        System.out.printf("Arrival rate: %d\n", arrivalRate);

        for(int i = 1; i <= maxNumQueues; i++){
            int counter = 0;
            while(counter < 50) {
                Simulation currSim = new Simulation(arrivalRate, i);
                currSim.updateNewMinute();
                counter++;
            }
            int averageWait = calcAvgWaitTime();

            System.out.printf("Average wait time using %d queue(s): %d\n", i, averageWait);
            queueArray.clear();
        }
    }

    /**
     * Constructs a new simulation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the current time. This defaults to using 50 iterations.
     *
     * Also creates Linked Queues to populate the queueArray.
     *
     * @param arrivalRate the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues the maximum number of lines that are open
     */
    public Simulation(int arrivalRate, int maxNumQueues) {
        this.arrivalRate = arrivalRate;
        this.maxNumQueues = maxNumQueues;
        r = new Random();
        queueArray = new ArrayList<>();
        int tempMaxNumQueues = this.maxNumQueues;
        while(tempMaxNumQueues != 0){
            queueArray.add(new LinkedQueue());
            tempMaxNumQueues--;
        }
    }

    /**
     * Constructs a new situation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the provided seed value, and the number of iterations is set to
     * the provided value.
     *
     * @param arrivalRate the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues the maximum number of lines that are open
     * @param numIterations the number of iterations used to improve data
     * @param seed the initial seed value for the random number generator
     */
    public Simulation(int arrivalRate, int maxNumQueues, int numIterations, int seed) {
        this(arrivalRate, maxNumQueues);
        r = new Random(seed);
        this.numIterations = numIterations;

        queueArray = new ArrayList<>();
        int tempMaxNumQueues = this.maxNumQueues;
        while(tempMaxNumQueues != 0) {
            queueArray.add(new LinkedQueue());
            tempMaxNumQueues--;
        }
    }

    /**
     * returns a number of people based on the provided average
     *
     * @param avg The average number of people to generate
     * @return An integer representing the number of people generated this minute
     */
    //Don't change this method.
    private static int getRandomNumPeople(double avg) {
        Random r = new Random();
        double L = Math.exp(-avg);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
}
