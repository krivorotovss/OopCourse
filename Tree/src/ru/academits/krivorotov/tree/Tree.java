package ru.academits.krivorotov.tree;

import java.util.*;

public class Tree<T> {
    private Comparator<? super T> comparator;
    private TreeNode<T> rootNode;
    private int nodesCount;

    public int getNodesCount() {
        return nodesCount;
    }

    public Tree() {
    }

    public Tree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    private int compare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null) {
            if (data2 == null) {
                return 0;
            }

            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<? super T>) data1).compareTo(data2);
    }

    public void add(T data) {
        if (rootNode == null) {
            rootNode = new TreeNode<>(data);
            nodesCount++;

            return;
        }

        TreeNode<T> currentNode = rootNode;

        while (true) {
            if (compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data));
                    nodesCount++;

                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(data));
                    nodesCount++;

                    return;
                }
            }
        }
    }

    public boolean search(T data) {
        TreeNode<T> currentNode = rootNode;

        while (true) {
            if (data == currentNode.getData()) {
                return true;
            }

            if (compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }
        }
    }

    public void remove(T data) {
        TreeNode<T> currentNode = rootNode;
        TreeNode<T> parentNode = null;

        boolean isCurrentLeftChild = false;

        while (true) {
            if (data == rootNode.getData()) {
                currentNode = rootNode.getRight();

                TreeNode<T> oldRoot = rootNode;
                rootNode = currentNode;
                currentNode.setLeft(oldRoot.getLeft());

                nodesCount--;

                return;
            }

            if (data == currentNode.getData() && parentNode != null) {
                if (currentNode.getLeft() == null && currentNode.getRight() == null) { //Удаляется узел - лист
                    if (isCurrentLeftChild) {
                        parentNode.setLeft(null);
                    } else {
                        parentNode.setRight(null);
                    }

                    nodesCount--;

                    return;
                }

                if (currentNode.getLeft() == null || currentNode.getRight() == null) { //Удаляется узел с 1 потомком
                    if (currentNode.getLeft() == null) { //нет левого внука
                        if (isCurrentLeftChild) {
                            parentNode.setLeft(currentNode.getRight());
                        } else {
                            parentNode.setRight(currentNode.getRight());
                        }
                    } else { // нет правого внука
                        if (isCurrentLeftChild) {
                            parentNode.setLeft(currentNode.getLeft());
                        } else {
                            parentNode.setRight(currentNode.getLeft());
                        }
                    }

                    nodesCount--;
                } else { //Удаление узла с 2-мя потомками
                    TreeNode<T> minNode = searchMinNodeAndChangeNode(currentNode, parentNode);

                    if (minNode.getLeft() == null) {
                        minNode.setLeft(currentNode.getLeft());
                        minNode.setRight(currentNode.getRight());

                        if (compare(minNode.getData(), parentNode.getData()) < 0) { //Если меньше 0, то мы слева от родителя
                            parentNode.setLeft(minNode);
                        } else {
                            parentNode.setRight(minNode);
                        }

                        return;
                    }
                }

                nodesCount--;

                return;
            }

            if (compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    isCurrentLeftChild = true;

                    parentNode = currentNode;
                    currentNode = currentNode.getLeft();
                } else {
                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    isCurrentLeftChild = false;

                    parentNode = currentNode;
                    currentNode = currentNode.getRight();
                } else {
                    return;
                }
            }
        }
    }

    private TreeNode<T> searchMinNodeAndChangeNode(TreeNode<T> startNode, TreeNode<T> parentNode) {
        TreeNode<T> currentNode = startNode;
        TreeNode<T> nextNode = startNode.getRight();
        TreeNode<T> startParentNode = parentNode;

        int count = 0;

        while (nextNode != null) {
            parentNode = currentNode;
            currentNode = nextNode;
            nextNode = currentNode.getLeft();
            count++;
        }

        if (count == 1) {//если в правом поддереве нет левого сына
            if (compare(currentNode.getData(), startParentNode.getData()) < 0) {
                startParentNode.setLeft(currentNode);
                currentNode.setLeft(startNode.getLeft());
            } else {
                startParentNode.setRight(currentNode);
                currentNode.setLeft(startNode.getLeft());
            }
        }

        if (currentNode.getRight() == null) {
            parentNode.setLeft(null); //если наименьший узел лист
        } else {
            parentNode.setLeft(currentNode.getRight());
        }

        return currentNode;
    }

    public void widthTraversalAndPrint() {
        if (rootNode == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(rootNode);

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.remove();
            System.out.print(currentNode.getData() + " ");

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void depthTraversalAndPrintWithRecursion() {
        if (rootNode == null) {
            return;
        }

        depthTraversalAndPrintWithRecursion(rootNode);
    }

    private void depthTraversalAndPrintWithRecursion(TreeNode<T> node) {
        if (node == null) {
            return;
        }

        System.out.print(node.getData() + " ");

        depthTraversalAndPrintWithRecursion(node.getLeft());
        depthTraversalAndPrintWithRecursion(node.getRight());
    }

    public void depthTraversalAndPrint() {
        if (rootNode == null) {
            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addFirst(rootNode);

        while (!stack.isEmpty()) {
            TreeNode<T> currentNode = stack.remove();
            System.out.print(currentNode.getData() + " ");

            if (currentNode.getRight() != null) {
                stack.addFirst(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.addFirst(currentNode.getLeft());
            }
        }
    }
}