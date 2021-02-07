/**
 * Operations performs the operations used by
 * the Calculator class for my CSC 4111 Assignment #2.
 *
 * @author Jordan Bronstetter
 * @date 01/27/2021
 */
public class Operations {
    private Operands operand;
    private Operators operator;

    public Operations() {}

    public Operations(Operands operand) {
        this.operand = operand;
    }

    public void preformOperation() {
        switch (getOperator()) {
            case ADD -> add();
            case DIVIDE -> divide();
            default -> sqrt();
        }
    }

    private void add() {
        operand.setResult(operand.getOperand1() + operand.getOperand2());
    }

    private void divide() {
        operand.setResult(operand.getOperand1() / operand.getOperand2());
    }

    private void sqrt() {
        operand.setResult(Math.sqrt(operand.getOperand1()));
    }

    public void setOperator(Operators operator) {
        this.operator = operator;
    }

    public Operators getOperator() {
        return operator;
    }
}