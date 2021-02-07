import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 * This class implements a calculator for my CSC 4111 Assignment #2.
 *
 * @author Jordan Bronstetter
 * @date 01/20/2021
 */
public class Calculator implements ActionListener, FocusListener, KeyListener {
    private JFrame mainFrame;
    private JPanel rootPanel;

    private JLabel lblOperand1;
    private JLabel lblOperand2;
    private JLabel lblResult;
    private JLabel lblOperations;

    private JTextField txtOperand1;
    private JTextField txtOperand2;
    private JTextField txtResult;
    private JTextField txtOperandFocus;

    private JButton btn0;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btnDot;
    private JButton btnAdd;
    private JButton btnDivide;
    private JButton btnSqrt;
    private JButton btnResult;
    private JButton btnClear;

    private Operands operands;
    private Operations operations;

    public Calculator() {
        mainFrame = new JFrame("Jordan Bronstetter's Calculator");

        operands = new Operands();
        operations = new Operations(operands);

        addListeners();

        btnResult.setEnabled(false);
        txtResult.setEditable(false);

        lblOperations.setOpaque(true);
        lblOperations.setBorder(BorderFactory.createLineBorder(Color.blue));
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.createWindow();
    }

    private void createWindow() {
        mainFrame.setContentPane(new Calculator().rootPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(600, 321));
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

        mainFrame.pack();

        txtOperand1.requestFocusInWindow();

        placeWindow();
    }

    private void placeWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - mainFrame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - mainFrame.getHeight()) / 2);
        mainFrame.setLocation(x, y);
    }

    private void setUserInput(String newInput) {
        String input = txtOperandFocus.getText().trim() + newInput;
        txtOperandFocus.setText(input);

        if (txtOperandFocus == txtOperand1) {
            operands.setOperand1(input);
        } else {
            operands.setOperand2(input);
        }
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        DecimalFormat outputFormat = new DecimalFormat("#.###");

        Object userButton = e.getSource();

        if (userButton == btnAdd) {
            operations.setOperator(Operators.ADD);
        } else if (userButton == btnDivide) {
            operations.setOperator(Operators.DIVIDE);
        } else if (userButton == btnSqrt) {
            operations.setOperator(Operators.SQRT);
        } else if (userButton == btnResult) {
            operations.preformOperation();
            operations.setOperator(null);
            txtResult.setText(outputFormat.format(Double.valueOf(operands.getResult())).toString());
        } else if (userButton == btnClear) {
            operations.setOperator(null);
            txtOperand1.setText("");
            txtOperand2.setText("");
            txtResult.setText("");;
            txtOperand1.requestFocus();
        } else {
            setUserInput(e.getActionCommand());
        }

        if (operands.areOperandsActive() && (operations.getOperator() != null)) {
            btnResult.setEnabled(true);
        }
        else {
            btnResult.setEnabled(false);
        }
    }

    /**
     * Invoked when a component gains the keyboard focus.
     *
     * @param e the event to be processed
     */
    @Override
    public void focusGained(FocusEvent e) {
        txtOperandFocus = (JTextField)e.getComponent();
    }

    /**
     * Invoked when a component loses the keyboard focus.
     *
     * @param e the event to be processed
     */
    @Override
    public void focusLost(FocusEvent e) {}

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {}

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        e.consume();
    }

    /**
     * Invoked to add either a FocusListener to JButtons
     * or an ActionListener and a KeyListener to JTextField.
     *
     * @param e the event to be processed
     */
    private void addListeners() {
        Component[] components = rootPanel.getComponents();

        for (Component component : components) {
            if (component instanceof JButton) {
                ((JButton)component).addActionListener(this);
            } else if (component instanceof JTextField) {
                if (component != txtResult) {
                    ((JTextField) component).addFocusListener(this);
                    ((JTextField) component).addKeyListener(this);
                }
            }
        }
    }
}