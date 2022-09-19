package ru.academits.krivorotov.tree;

class TreeNode<T> {
    private TreeNode<T> right;
    private TreeNode<T> left;
    private final T data;

    public TreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }
}