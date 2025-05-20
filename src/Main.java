import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.nio.file.*;
import java.text.ParseException;
import java.awt.BorderLayout;
import java.util.List;



public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Uso: java Main <archivo_san.txt> [--export-gfx output.dot]");
            System.exit(1);
        }
        String san = Files.readString(Path.of(args[0]));
        GameParser parser = new GameParser();
        List<Turn> turns = parser.parseGame(san);
        TreeNode<String> tree = parser.buildTree(turns);

        if (args.length == 3 && args[1].equals("--export-gfx")) {
            DotExporter.export(tree, args[2]);
            System.out.println("DOT exportado a " + args[2]);
            return;
        }

        SwingUtilities.invokeLater(() -> createAndShow(tree));
    }

    private static void createAndShow(TreeNode<String> root) {
    JFrame f = new JFrame("Árbol Binario de Ajedrez");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Convierte TreeNode a DefaultMutableTreeNode (para JTree)
    DefaultMutableTreeNode swingRoot = new DefaultMutableTreeNode(root.value());
    buildSwingTree(root, swingRoot);
    
    JTree tree = new JTree(swingRoot);
    f.add(new JScrollPane(tree), BorderLayout.CENTER);
    f.setSize(500, 600);
    f.setVisible(true);
}
private static void buildSwingTree(TreeNode<String> node, DefaultMutableTreeNode parent) {
    if (node.left() != null) {
        DefaultMutableTreeNode leftChild = new DefaultMutableTreeNode(node.left().value());
        parent.add(leftChild);
        buildSwingTree(node.left(), leftChild);
    }
    if (node.right() != null) {
        DefaultMutableTreeNode rightChild = new DefaultMutableTreeNode(node.right().value());
        parent.add(rightChild);
        buildSwingTree(node.right(), rightChild);
    }
}

    private static DefaultMutableTreeNode toSwing(TreeNode<String> node) {
    DefaultMutableTreeNode m = new DefaultMutableTreeNode(node.value());
    if (node.left() != null) {
        m.add(toSwing(node.left()));  // Agrega el hijo izquierdo
    }
    if (node.right() != null) {
        m.add(toSwing(node.right())); // Agrega el hijo derecho
    }
    return m;
}
}