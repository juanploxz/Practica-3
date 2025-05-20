public class Move {
    private final String notation;
    private boolean valid;
    private String errorMessage;

    public Move(String notation) {
        this.notation = notation;
    }

    public String getNotation() { return notation; }
    public boolean isValid() { return valid; }
    public String getErrorMessage() { return errorMessage; }

    void setValid(boolean valid) { this.valid = valid; }
    void setErrorMessage(String msg) { this.errorMessage = msg; }

    @Override
    public String toString() {
        return notation + (valid ? "" : " [INVALID: " + errorMessage + "]");
    }
}