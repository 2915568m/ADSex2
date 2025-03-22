public class Node {
    public int element;
    public Node right;
    public Node left;

    public Node(int element)
    {
        this.element = element;
        this.right = this.left = null;
    }
}
