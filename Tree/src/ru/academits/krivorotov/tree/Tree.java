package ru.academits.krivorotov.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private final Comparator<? super T> comparator;
    private TreeNode<T> root;
    private int count;

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

    public int getCount() {
        return count;
    }

    public void add(T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            count++;

            return;
        }

        TreeNode<T> currentNode = root;

        while (true) {
            if (compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data));
                    count++;

                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(data));
                    count++;

                    return;
                }
            }
        }
    }

    public boolean contains(T data) {
        if (root == null) {
            return false;
        }

        TreeNode<T> currentNode = root;

        while (true) {
            int comparisonResult = compare(data, currentNode.getData());

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult < 0) {
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

    public boolean remove(T data) {
        if (root == null) {
            return false;
        }

        if (compare(data, root.getData()) == 0) { // Нужно проверить есть ли правый сын
            if (root.getRight() != null) { // Если правый сын есть
                root = searchMinNodeAndChangeNode(root, null);
                count--;

                return true;
            }

            root = root.getLeft(); // Если нет правого сына у корня, то корень - левый сын
            count--;

            return true;
        }

        TreeNode<T> currentNode = root;
        TreeNode<T> parentNode = null;

        boolean isCurrentLeftChild = false;

        while (true) {
            int comparisonResult = compare(data, currentNode.getData());

            if (comparisonResult == 0) {
                removeNode(currentNode, parentNode, isCurrentLeftChild);
                return true;
            }

            if (comparisonResult < 0) { // Искомое значение слева от текущего
                if (currentNode.getLeft() != null) {
                    isCurrentLeftChild = true;

                    parentNode = currentNode;
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            } else {
                if (currentNode.getRight() != null) { // Искомое значение справа от текущего
                    isCurrentLeftChild = false;

                    parentNode = currentNode;
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }
        }
    }

    private void removeNode(TreeNode<T> currentNode, TreeNode<T> parentNode, boolean isCurrentLeftChild) {
        if (currentNode.getLeft() == null && currentNode.getRight() == null) { // Удаляется узел - лист
            if (isCurrentLeftChild) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }

            count--;

            return;
        }

        // Удаляется узел с 1 потомком
        if (currentNode.getLeft() == null) { // Нет левого внука
            if (isCurrentLeftChild) {
                parentNode.setLeft(currentNode.getRight());
            } else {
                parentNode.setRight(currentNode.getRight());
            }
        } else if (currentNode.getRight() == null) { // Нет правого внука
            if (isCurrentLeftChild) {
                parentNode.setLeft(currentNode.getLeft());
            } else {
                parentNode.setRight(currentNode.getLeft());
            }
        } else { // Удаление узла с 2-мя потомками
            searchMinNodeAndChangeNode(currentNode, parentNode);
        }

        count--;
    }

    public void widthTraversal(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.remove();
            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void depthTraversalWithRecursion(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        depthTraversalWithRecursion(root, consumer);
    }

    private void depthTraversalWithRecursion(TreeNode<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());

        depthTraversalWithRecursion(node.getLeft(), consumer);
        depthTraversalWithRecursion(node.getRight(), consumer);
    }

    public void depthTraversal(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addFirst(root);

        while (!stack.isEmpty()) {
            TreeNode<T> currentNode = stack.removeFirst();
            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.addFirst(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.addFirst(currentNode.getLeft());
            }
        }
    }

    private TreeNode<T> searchMinNodeAndChangeNode(TreeNode<T> deletedNode, TreeNode<T> deletedNodeParentNode) {
        TreeNode<T> minNode = deletedNode.getRight(); // Минимальный узел в правом поддереве
        TreeNode<T> minNodeParentNode = deletedNode; // Родитель минимального узла

        if (minNode.getLeft() == null) { // Если нет левой ветки в правом поддереве, он и есть минимальный узел
            minNode.setLeft(deletedNode.getLeft());

            if (deletedNodeParentNode != null) {
                if (compare(deletedNode.getData(), deletedNodeParentNode.getData()) < 0) { // Удаляемый узел слева, если true
                    deletedNodeParentNode.setLeft(minNode);
                } else {
                    deletedNodeParentNode.setRight(minNode);
                }
            }

            return minNode;
        }

        while (minNode.getLeft() != null) { // Поиск минимального узла
            minNodeParentNode = minNode;
            minNode = minNode.getLeft();
        }

        if (minNode.getRight() == null) { // Случай, когда нет правого сына
            minNodeParentNode.setLeft(null);
        } else {
            minNodeParentNode.setLeft(minNode.getRight());
        }

        if (deletedNodeParentNode != null) {
            if (compare(deletedNode.getData(), deletedNodeParentNode.getData()) < 0) { // Удаляемый узел слева, если true
                deletedNodeParentNode.setLeft(minNode);
            } else {
                deletedNodeParentNode.setRight(minNode);
            }
        }

        minNode.setLeft(deletedNode.getLeft());
        minNode.setRight(deletedNode.getRight());

        return minNode;
    }
}