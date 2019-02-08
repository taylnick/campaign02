package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.List;

/**
 * Singly Linked List to track inputs of scores
 * @author NNTaylor
 * @param <E> element type
 */

public class SinglyLinkedList<E> implements List<E> {

    protected Node<E> head;
    protected Node<E> tail;
    protected int size;

    /**
     *
     * @return data from head node or null if empty
     */
    @Override
    public E first() {
        if(isEmpty()) return null;
        return head.getData();
    }

    /**
     *
     * @return data from tail or null if empty
     */
    @Override
    public E last() {
        if(isEmpty()) return null;
        if(size ==1) return head.getData();
        return tail.getData();
    }

    /**
     * Adds element in new tail node at the end of the list or returns null if the list is empty
     * @param element Element to be added to the end of the list.
     */
    @Override
    public void addLast(E element) {
        if(element == null) return;

        Node<E> newNode = new Node<>(element);

        if(isEmpty()){
            head = newNode;
            tail = newNode;
            size++;
        }
        else{
            tail.setNextNode(newNode);
            tail = newNode;
            size++;
        }
    }

    /**
     * Adds element to the front of the list in a new head node, or returns null if the list is empty.
     * @param element Element to be added to the front of the list.
     */
    @Override
    public void addFirst(E element) {
        if(element == null) return;

        Node<E> newNode = new Node<>(element);

        if(isEmpty()){
            newNode.setNextNode(null);
            head = newNode;
            tail = newNode;
            size++;
            return;
        }
        newNode.setNextNode(head);
        head = newNode;
        size++;
    }

    /**
     * removes the head node and moves head pointer.
     * @return data from the head node or null if list is empty
     */
    @Override
    public E removeFirst() {
        if(isEmpty()) return null;

        E removedData = head.getData();

        Node<E> tempNode = head;
        if(size ==1 ){
            head = null;
            tail = null;
            tempNode.setNextNode(null);
            size --;
            return removedData;
        }
        head = head.getNextNode();
        tempNode.setNextNode(null);
        size --;

        return removedData;
    }

    /**
     * removes tail from list and reassigns tail to the previous node via iteration.
     * @return data from the tail node or null if list is empty
     */
    @Override
    public E removeLast() {
        if(size == 0) return null;
        if(size == 1){
            return removeFirst();
        }
        E removedData = tail.getData();

        Node<E> currentNode = head;
        Node<E> temp = head;
        while(currentNode != null){
            temp = currentNode;
            currentNode = currentNode.getNextNode();
        }
        tail = temp;
        size--;

        return removedData;
    }

    /**
     * Inserts given element to the given index unless index is out of bounds of the currrent list
     * @param element Element to be added (as long as it is not null).
     * @param index Index in the list where the element is to be inserted.
     */
    @Override
    public void insert(E element, int index) {
        if(index <= 0 || index > size || element == null) return;

        Node<E> newNode = new Node<>(element);

        Node<E> currentNode = head;

        for(int i = 0; i < index-1; i++){
            currentNode = currentNode.getNextNode();
        }

        newNode.setNextNode(currentNode.getNextNode());
        currentNode.setNextNode(newNode);
        size++;

    }

    /**
     * removes the node at the given index and returns its data unless out of bounds of the list.
     * @param index Index of the element to remove
     * @return data at the given index
     */
    @Override
    public E remove(int index) {
        if(index <= 0 || index >= size) return null;

        Node<E> currentNode = head;
        for(int i = 0; i < index-1; i++){
            currentNode = currentNode.getNextNode();
        }
        Node<E> nodeToRemove = currentNode.getNextNode();
        E dataToRemove = nodeToRemove.getData();
        currentNode.setNextNode(nodeToRemove.getNextNode());
        nodeToRemove.setNextNode(null);
        size--;

        return dataToRemove;
    }

    /**
     * returns data at the given index unless it is out of bounds of the list.
     * @param index Index of the value to be retrieved.
     * @return data at given index
     */
    @Override
    public E get(int index) {
        if(index < 0 || index >= size) return null;
        if(index == 0) return head.getData();
        if(size == index) return tail.getData();

        Node<E> currentNode = head;
        for(int i = 0; i < index; i++){
            if(currentNode.getNextNode() == null)break;
            currentNode = currentNode.getNextNode();
        }

        return currentNode.getData();
    }

    /**
     * returns the current size of the list
     * @return current size of the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * checks size of the list and returns true if the list is empty
     * @return true if the list is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * iterates through the list and prints the data at each index.
     */
    @Override
    public void printList() {
        if(size == 0) return;
        Node<E> currentNode = head;
        int counter = 0;
        while(counter < size){
            System.out.println(currentNode.getData());
            currentNode = currentNode.getNextNode();
            counter ++;
        }

    }
}
