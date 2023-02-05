package com.shapran.container;

import com.shapran.model.Car;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CarList <T extends Car>{

    private Node first;
    private Node last;
    private int size;

    public <T extends Car> void addFirst(T car){
        Node emptyNode = new Node(car);
        if (first == null){
            first = emptyNode;
            last = emptyNode;
            first.prev = null;
            last.next = null;
        }else {
            first.prev = emptyNode;
            emptyNode.next = first;
            first = emptyNode;
            first.prev = null;
        }
        size++;
    }

    public <T extends Car> void addLast(T car){
        if (last == null){
            addFirst(car);
        }else {
            Node emptyNode = new Node(car);
            last.next = emptyNode;
            emptyNode.prev = last;
            last = emptyNode;
            last.next = null;
            size++;
        }
    }
    public <T extends Car> int searchPositionElement(T car){
        int position = 0;
        Node actual = first;
        if (actual != null){
            if (actual.car.equals(car)){
                return position;
            }else {
                actual = actual.next;
                position++;
            }
        }
        return -1;
    }

    public <T extends Car> void insertPosition(T car, int position){
        if (position < 0){
            addFirst(car);
        }
        if (position > size){
            addLast(car);
        }else {
            Node newNode = new Node(car);
            Node actual = first;
            for (int i = 0; i < position; i++){
                actual = actual.prev;
            }
            newNode.prev = actual.prev;
            newNode.prev.next = newNode;
            newNode.next = actual;
            newNode.prev = newNode;
            size++;
        }
    }

    public void deletePosition(int position){
        if (size > 0){
            if (position < 0){
                Node newFirst = first.next;
                first.next = null;
                first.prev = null;
                newFirst.prev = null;
                first = newFirst;
            }else if (position > 0){
                Node newLast = last.prev;
                last.next = null;
                last.prev = null;
                newLast.next = null;
                last = newLast;
            }else {
                Node actual = first;
                for (int i = 0; i < position; i++){
                    actual = actual.next;
                }
                actual.prev.next = actual.next;
                actual.next.prev = actual.prev;
            }
            size--;
        }
    }

    public int allCarCount(){
        int count = 0;
        Node actual = first;
        while (actual.next != null){
            count += actual.getCar().getCount();
            actual = actual.next;
        }
        return count;
    }

     private class Node <T extends Car>{
        protected T car;
        protected Node<T> next;
        protected Node<T> prev;

        public Node(T car) {
            this.car = car;
        }

        public T getCar(){
            return car;
        }
    }
}
