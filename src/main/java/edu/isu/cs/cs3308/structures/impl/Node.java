package edu.isu.cs.cs3308.structures.impl;

/**
 * @author nntay
 *
 * @param <E> element type
 */
public class Node<E> {

    /**
     * @data holds the name and score.
     */
    private E data;

    /**
     * @nextNode points to the next node in the list.
     */
    private Node<E> nextNode;

    /**
     * @prevNode points to previous Node in the list;
     */
    private Node<E> prevNode;

    /**
     * Node constructor
     */
    public Node(E data){
        this.data = data;
        this.setNextNode(null);
        this.setPrevNode(null);
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getNextNode() {
        return nextNode;
    }

    public Node<E> getPrevNode(){
        return prevNode;
    }

    public void setNextNode(Node<E> next) {
        this.nextNode = next;
    }

    public void setPrevNode(Node<E> prev) {
        this.prevNode = prev;
    }
}
