package com.shapran.container;

import com.shapran.model.Car;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CarTree <T extends Car> {

    public Node root;
    public CarTree(T car){
        this.root = new Node(car, null, null);
    }

    public void add(Node node, T car){
        if (node == null){
            node = root;
        }
        if (car.getCount() < node.car.getCount()){
            if (node.leftChild == null){
                node.leftChild = new Node(car, null, null);
                return;
            }else {
                add(node.leftChild, car);
            }
        }
        if (car.getCount() > node.car.getCount()){
            if (node.rightChild == null){
                node.rightChild = new Node(car,null, null);
            }else {
                add(node.rightChild, car);
            }
        }
    }

    public int sumOfAllElements(Node temp){
        int sum;
        int sumLeft = 0;
        int sumRight = 0;

        if (root == null){
            return 0;
        }else
            if (temp.leftChild != null)
                sumLeft = sumOfAllElements(temp.leftChild);
            if (temp.rightChild != null)
                sumRight = sumOfAllElements(temp.rightChild);
                return  sum = sumLeft + sumRight + temp.car.getCount();

        }

    private class Node <T extends Car> {
        T car;
        private Node leftChild;
        private Node rightChild;

        public Node(T car, Node leftChild, Node rightChild) {
            this.car = car;
            this.leftChild = null;
            this.rightChild = null;
        }
    }
}

