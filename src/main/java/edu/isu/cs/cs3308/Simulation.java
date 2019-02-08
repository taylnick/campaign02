package edu.isu.cs.cs3308;

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
    ArrayList<LinkedQueue> queueArray = new ArrayList<LinkedQueue>();
    SinglyLinkedList<Integer> waitTimes = new SinglyLinkedList<>();
    // You will probably need more fields

    public void addToShortest(){
        LinkedQueue shortest = queueArray.get(0);
        for(LinkedQueue curr: queueArray){
            if(curr.size() < shortest.size()) shortest = curr;
        }
         shortest.offer(0);
    }

    public int calcAvgWaitTime(LinkedQueue currQueue){
        int averageWait = 0;

        //TODO: Take in the currQueue and average all of the values
        return averageWait;
    }

    public void updateQueueTimes(){
        for(LinkedQueue current: queueArray){
            int counter = 0;
            while(counter < current.size()){
                current.offer((int)current.poll() + 1);
                counter++;
            }
        }
    }

    public void updateNewMinute(int counter){
        if(counter == 720) return;
        int newArrivals = getRandomNumPeople(arrivalRate);
        while(newArrivals != 0){
            addToShortest();
        }
        for(LinkedQueue current: queueArray){
            waitTimes.addFirst((int)current.poll());
            waitTimes.addFirst((int)current.poll());
        }
    }

    /**
     * Executes the Simulation
     */
    public void runSimulation() {





        throw new UnsupportedOperationException("Not yet implemented");
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

        int tempMaxNumQueues = maxNumQueues;
        while(tempMaxNumQueues != 0){
            queueArray.add(new LinkedQueue());
            tempMaxNumQueues--;
        }

    }

    /**
     * Constructs a new siulation with the given arrival rate and maximum number of queues. The Random
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
