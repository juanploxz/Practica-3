public class TreeNode<T> {
    private final T value;
    private TreeNode<T> left;  // Hijo izquierdo
    private TreeNode<T> right; // Hijo derecho

    public TreeNode(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    // Setters y getters
    public void setLeft(TreeNode<T> node) { this.left = node; }
    public void setRight(TreeNode<T> node) { this.right = node; }
    public T value() { return value; }
    public TreeNode<T> left() { return left; }
    public TreeNode<T> right() { return right; }

    @Override
    public String toString() { return value.toString(); }
}