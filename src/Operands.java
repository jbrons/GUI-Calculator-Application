/**
 * Operands stores the operands used by
 * the Calculator class for my CSC 4111 Assignment #2.
 *
 * @author Jordan Bronstetter
 * @date 01/25/2021
 */
public class Operands {
    private double operand1;
    private double operand2;
    private double result;
    boolean operand1Active = false;
    boolean operand2Active = false;

    public double getOperand1() {
        return operand1;
    }

    public void setOperand1(String operand1) {
        this.operand1 = Double.parseDouble(operand1);
        operand1Active = true;
    }

    public double getOperand2() {
        return operand2;
    }

    public void setOperand2(String operand2) {
        this.operand2 = Double.parseDouble(operand2);
        operand2Active = true;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public boolean areOperandsActive() {
        return (operand1Active && operand2Active);
    }
}
