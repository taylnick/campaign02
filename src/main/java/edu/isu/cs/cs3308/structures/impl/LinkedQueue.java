package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Queue;

public class LinkedQueue<E> implements Queue<E> {


    private SinglyLinkedList<E> sll;

    public LinkedQueue() {sll = new SinglyLinkedList<E>();}

    /**
     * @return The number of elements in the queue
     */
    @Override
    public int size() {
        return sll.size();
    }

    /**
     * @return tests whether the queue is empty.
     */
    @Override
    public boolean isEmpty() {
        return sll.isEmpty();
    }

    /**
     * Inserts an element at the end of the queue.
     *
     * @param element Element to be inserted.
     */
    @Override
    public void offer(E element) {
        sll.addLast(element);
    }

    /**
     * @return The value first element of the queue (with out removing it), or
     * null if empty.
     */
    @Override
    public E peek() {
        return sll.first();
    }

    /**
     * @return The value of the first element of the queue (and removes it), or
     * null if empty.
     */
    @Override
    public E poll() {
        return sll.removeFirst();
    }

    /**
     * Prints the contents of the queue starting at top, one item per line. Note
     * this method should not empty the contents of the queue.
     */
    @Override
    public void printQueue() {
        sll.printList();
    }

    /**
     * Tranfers the contents of this queue into the provided queue. The contents
     * of this queue are to found in reverse order at the top of the provided
     * queue. This queue should be empty once the transfer is completed. Note
     * that if the provided queue is null, nothing is to happen.
     *
     * @param into The new queue onto which the reversed order of contents from
     *             this queue are to be transferred to the top of, unless the provided queue
     *             is null.
     */
    @Override
    public void transfer(Queue into) {
        if(into == null) return;

        LinkedStack tempStack = new LinkedStack();
        while(!this.isEmpty()) {
            tempStack.push(this.poll());
        }
        while(!tempStack.isEmpty()){
            into.offer(tempStack.pop());
        }


    }

    /**
     * Reverses the contents of this queue.
     */
    @Override
    public void reverse() {
        this.transfer(this);

    }

    /**
     * Merges the contents of the provided queue onto the bottom of this queue.
     * The order of both queues must be preserved in the order of this queue
     * after the method call. Furthermore, the provided queue must still contain
     * its original contents in their original order after the method is
     * complete. If the provided queue is null, no changes should occur.
     *
     * @param from Queue whose contents are to be merged onto the bottom of
     *             this queue.
     */
    @Override
    public void merge(Queue from) {
        if(from == null) return;

        LinkedQueue tempQueue = new LinkedQueue();
        SinglyLinkedList copysll = new SinglyLinkedList();

        from.transfer(tempQueue);
        tempQueue.reverse();

        for(int i = 0; i < tempQueue.size(); i++){
            copysll.addLast(tempQueue.sll.get(i));
        }

        tempQueue.transfer(from);
        from.reverse();

        while(!copysll.isEmpty()){
            this.offer((E) copysll.removeFirst());
        }
    }
}
