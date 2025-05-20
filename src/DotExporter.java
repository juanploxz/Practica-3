import java.io.*;
public class DotExporter {
    public static void export(TreeNode<String> root, String filename) throws IOException {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println("digraph G {");
            exportNode(root, "n0", out);
            out.println("}");
        }
    }
    private static int counter = 0;
  private static void exportNode(TreeNode<String> node, String id, PrintWriter out) {
    out.printf("  %s [label=\"%s\"];%n", id, node.value());
    if (node.left() != null) {
        String leftId = "n" + (++counter);
        out.printf("  %s -> %s [label=\"left\"];%n", id, leftId);
        exportNode(node.left(), leftId, out);
    }
    if (node.right() != null) {
        String rightId = "n" + (++counter);
        out.printf("  %s -> %s [label=\"right\"];%n", id, rightId);
        exportNode(node.right(), rightId, out);
    }
}
}