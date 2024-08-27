/**
 * Program Name:Calculator.java
 * Purpose: a calculator
 * @author: Jude Ma
 * Date: Jul 25, 2024
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Calculator extends JFrame {
    JTextField display;
    int counter = 0;
    double first = 0;
    double second = 0;
    String sign = "";

    public Calculator() {
        super("My calculator app");

        // JFrame boilerplate code
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Close and destroy the window
        this.setSize(400, 440); // Width and height in pixels
        this.setLocationRelativeTo(null); // Centers the frame on the screen

        this.setLayout(new BorderLayout());
        
        try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Display field
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 34));
        display.setPreferredSize(new Dimension(400, 80)); // Width, Height
        this.add(display, BorderLayout.NORTH);

        // JPanel
        JPanel main = new JPanel(new GridLayout(4, 4)); // 5 rows x 4 columns

        // Button labels
        String[] buttonLabels = {
            "1", "2", "3", "-",
            "4", "5", "6", "x",
            "7", "8", "9", "/",
            "CE", "0", "=", "+"
        };

        // Common listener
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                JButton source = (JButton) ev.getSource();
                String btn = source.getText();

                if (btn.equals("=")) {
                	  try {
                          second = Double.parseDouble(display.getText());
                          double ans = calc(first, second, sign);
                          display.setText(String.valueOf(ans));
                          sign = "";
                          counter = 0;
                      } catch (NumberFormatException e) {
                          JOptionPane.showMessageDialog(Calculator.this, "Error: Invalid number format");
                      
                    }
                } else if (btn.equals("CE")) {
                    display.setText("");
                    counter = 0;
                    sign = "";
                    first = 0;
                    second = 0;
                } else if (btn.equals("+") || btn.equals("-") || btn.equals("x") || btn.equals("/")) {
                    if (!display.getText().isEmpty()) {
                        try {
                            first = Double.parseDouble(display.getText());
                            display.setText("");
                            sign = btn;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(Calculator.this, "Error: Invalid number format");
                        }
                    }
                } else {
                    if (counter < 13) {
                        display.setText(display.getText() + btn);
                        counter++;
                    }
                }
            }
        };

        // Add buttons to the panel
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(60, 60)); // Size of each button
            button.addActionListener(buttonListener); // Add the common ActionListener
            main.add(button);
        }

        // Add main panel to the frame
        this.add(main, BorderLayout.CENTER);

        // Make the frame visible
        this.setVisible(true);
    }

    public double calc(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "x":
                return a * b;
            case "/":
                if (b != 0) {
                    return a / b;
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Division by zero");
                    return 0;
                }
            default:
                JOptionPane.showMessageDialog(this, "Error: Invalid operator");
                return 0;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}