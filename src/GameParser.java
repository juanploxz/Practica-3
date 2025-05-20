import java.text.ParseException;
import java.util.regex.*;
import java.util.*;

public class GameParser {
    private static final Pattern RE_ENROQUE = Pattern.compile("^(O-O|O-O-O)([+#])?$");
    private static final Pattern RE_PIEZA = Pattern.compile(
        "^[KQRBN]" +
        "([a-h1-8]|[a-h][1-8])?" +
        "(x)?" +
        "[a-h][1-8]" +
        "(=[KQRBN])?" +
        "([+#])?" +
        ""
    );
    private static final Pattern RE_PEON_AVANCE = Pattern.compile("^[a-h][1-8](=[KQRBN])?([+#])?$");
    private static final Pattern RE_PEON_CAPTURA = Pattern.compile("^[a-h]x[a-h][1-8](=[KQRBN])?([+#])?$");

    public Move parseMove(String san, int turnNumber, String color) throws ParseException {
        Move m = new Move(san);
        if (RE_ENROQUE.matcher(san).matches() ||
            RE_PIEZA.matcher(san).matches() ||
            RE_PEON_AVANCE.matcher(san).matches() ||
            RE_PEON_CAPTURA.matcher(san).matches()) {
            m.setValid(true);
        } else {
            throw new ParseException(
                String.format("Turno %d (%s): notación inválida '%s'", turnNumber, color, san), turnNumber);
        }
        return m;
    }

    public List<Turn> parseGame(String input) throws ParseException {
        String[] tokens = input.trim().split("\\s+");
        List<Turn> turns = new ArrayList<>();
        int i = 0;
        while (i < tokens.length) {
            String numToken = tokens[i++];
            if (!numToken.matches("\\d+\\.")) {
                throw new ParseException("Se esperaba número de turno + '.', encontrado '" + numToken + "'", i);
            }
            int num = Integer.parseInt(numToken.replace(".", ""));
            if (i >= tokens.length) throw new ParseException("Falta blanco en turno " + num, i);

            Move white = parseMove(tokens[i++], num, "white");
            Move black = null;
            if (i < tokens.length && !tokens[i].matches("\\d+\\.")) {
                black = parseMove(tokens[i++], num, "black");
            }
            turns.add(new Turn(num, white, black));
        }
        return turns;
    }

public TreeNode<String> buildTree(List<Turn> turns) {
    TreeNode<String> root = new TreeNode<>("Partida");
    TreeNode<String> current = root;

    for (Turn turn : turns) {
        // Nodo para el turno actual (ej: "Turno 1")
        TreeNode<String> turnNode = new TreeNode<>("Turno " + turn.number());
        
        // Movimiento de blancas (siempre presente)
        TreeNode<String> whiteMove = new TreeNode<>("1. " + turn.white());
        turnNode.setLeft(whiteMove);

        // Movimiento de negras (opcional)
        if (turn.black() != null) {
            TreeNode<String> blackMove = new TreeNode<>("2. " + turn.black());
            turnNode.setRight(blackMove);
        }

        // Enlaza el turno al árbol principal
        if (current.left() == null) {
            current.setLeft(turnNode);
        } else {
            current.setRight(turnNode);
        }
        current = turnNode;
    }
    return root;
}
}