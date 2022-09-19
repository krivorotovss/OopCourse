package ru.academits.krivorotov.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private Comparator<? super T> comparator;
    private TreeNode<T> root;
    private int count;

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
            if (compare(data, currentNode.getData()) == 0) {
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
        if (root == null) {
            return;
        }

        TreeNode<T> currentNode = root;
        TreeNode<T> parentNode = null;

        boolean isCurrentLeftChild = false;

        if (compare(data, root.getData()) == 0) { // Нужно проверить есть ли правый сын
            if (root.getRight() != null) { // Если правый сын есть
                TreeNode<T> minNode = searchMinNodeAndChangeNode(root, null);
                TreeNode<T> oldRoot = root;

                root = minNode;
                minNode.setLeft(oldRoot.getLeft());

                if (minNode.getRight() == null) {
                    minNode.setRight(oldRoot.getRight());
                }

                count--;

                return;
            }

            root = root.getLeft(); // Если нет правого сына у рута, то рут - левый сын
            count--;

            return;
        }

        while (true) {
            if (compare(data, currentNode.getData()) == 0 && parentNode != null) {
                removeNode(currentNode, parentNode, isCurrentLeftChild);
                return;
            }

            if (compare(data, currentNode.getData()) < 0) { // Искомое значение слева от текущего
                if (currentNode.getLeft() != null) {
                    isCurrentLeftChild = true;

                    parentNode = currentNode;
                    currentNode = currentNode.getLeft();
                } else {
                    return;
                }
            } else {
                if (currentNode.getRight() != null) { // Искомое значение справа от текущего
                    isCurrentLeftChild = false;

                    parentNode = currentNode;
                    currentNode = currentNode.getRight();
                } else {
                    return;
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

        if (currentNode.getLeft() == null || currentNode.getRight() == null) { // Удаляется узел с 1 потомком
            if (currentNode.getLeft() == null) { // Нет левого внука
                if (isCurrentLeftChild) {
                    parentNode.setLeft(currentNode.getRight());
                } else {
                    parentNode.setRight(currentNode.getRight());
                }
            } else { // Нет правого внука
                if (isCurrentLeftChild) {
                    parentNode.setLeft(currentNode.getLeft());
                } else {
                    parentNode.setRight(currentNode.getLeft());
                }
            }

        } else { // Удаление узла с 2-мя потомками
            searchMinNodeAndChangeNode(currentNode, parentNode);
        }

        count--;
    }

    private TreeNode<T> searchMinNodeAndChangeNode(TreeNode<T> deletedNode, TreeNode<T> parentNode) {
        TreeNode<T> currentNode = deletedNode;
        TreeNode<T> nextNode = deletedNode.getRight();
        TreeNode<T> startParentNode = parentNode;

        int count = 0;

        while (nextNode != null) { // Ищем минимальный узел в правом поддереве
            parentNode = currentNode;
            currentNode = nextNode;
            nextNode = currentNode.getLeft();
            count++;
        }

        if (count == 1) { // Если в правом поддереве нет левого сына, то текущий узел и есть минимальный
            if (startParentNode == null) { // Если удаляется root (у него нет родителя)
                currentNode.setLeft(deletedNode.getLeft()); // Назначает ссылку слева от корня, для текущего узла
                currentNode.setRight(currentNode.getRight()); // Назначает ссылку справа от корня, для текущего узла

                return currentNode;
            }

            if (compare(currentNode.getData(), startParentNode.getData()) < 0) { // Текущий узел слева от родителя, если true
                startParentNode.setLeft(currentNode);
            } else { // Иначе справа
                startParentNode.setRight(currentNode);
            }

            currentNode.setLeft(deletedNode.getLeft());
        }

        if (currentNode.getRight() == null) {
            parentNode.setLeft(null); // Если минимальный узел лист, то ссылок для передачи нет, удаляем ссылку у родителя
        } else {
            parentNode.setLeft(currentNode.getRight()); // Передаем правое поддерево от минимального узла к родителю мин узла
            currentNode.setRight(deletedNode.getRight()); // Передаем минимальному узлу ссылку на правое поддерево, удаляемого узла
            currentNode.setLeft(deletedNode.getLeft()); // Передаем минимальному узлу ссылку на левое поддерево, удаляемого узла

            if (startParentNode != null) {
                startParentNode.setRight(currentNode);
            }
        }

        return currentNode;
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
}