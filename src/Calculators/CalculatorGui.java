package Calculators;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CalculatorGui {
    private static String lhsNumber = "0";
    private static String rhsNumber = "0";
    private static String operator = "";
    private static HashMap<String, Runnable> operationFunctions = new HashMap<String, Runnable>();

    static class Button {
        public JButton btn;
        public String type;

        public Button(JButton btn, String type) {
            this.btn = btn;
            this.type = type;
        }
    }

    public static void startGui() {
        operationFunctions.put("+", new Runnable() {
            public void run() {
                lhsNumber = Double.toString(Double.parseDouble(lhsNumber) + Double.parseDouble(rhsNumber));
            }
        });
        operationFunctions.put("-", new Runnable() {
            public void run() {
                lhsNumber = Double.toString(Double.parseDouble(lhsNumber) - Double.parseDouble(rhsNumber));
            }
        });
        operationFunctions.put("÷", new Runnable() {
            public void run() {
                lhsNumber = Double.toString(Double.parseDouble(lhsNumber) / Double.parseDouble(rhsNumber));
            }
        });
        operationFunctions.put("x", new Runnable() {
            public void run() {
                lhsNumber = Double.toString(Double.parseDouble(lhsNumber) * Double.parseDouble(rhsNumber));
            }
        });

        final int WIDTH = 410;
        final int HEIGHT = 600;

        final int yOffset = 26;

        final Color backgroundColor = new Color(32, 32, 32);
        final Color operationButtons = new Color(50, 50, 50);
        final Color numberButtons = new Color(60, 60, 60);
        final Color equalsButton = new Color(69, 78, 95);
        final Color equalsHoverButton = new Color(86, 98, 120);

        final Font numberFont = new Font("assets/Roboto.ttf", Font.BOLD, 20);
        final Font numberLabelFont = new Font("assets/Roboto.ttf", Font.BOLD, 60);

        JFrame frame = new JFrame("Calculator");
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("assets/calculator-logo.png").getImage());
        frame.getContentPane().setBackground(backgroundColor);
        frame.setLayout(null);

        JLabel numberPanel = new JLabel(lhsNumber + " ", SwingConstants.RIGHT);
        numberPanel.setBounds(4, 70, 388, 80);
        numberPanel.setForeground(Color.white);
        numberPanel.setFont(numberLabelFont);
        frame.add(numberPanel);

        JLabel savedNumberPanel = new JLabel("", SwingConstants.RIGHT);
        savedNumberPanel.setBounds(4, 40, 388, 30);
        savedNumberPanel.setFont(numberFont);
        savedNumberPanel.setForeground(Color.white);
        frame.add(savedNumberPanel);

        JButton oneBtn = new JButton("1");
        JButton twoBtn = new JButton("2");
        JButton threeBtn = new JButton("3");

        JButton fourBtn = new JButton("4");
        JButton fiveBtn = new JButton("5");
        JButton sixBtn = new JButton("6");

        JButton sevenBtn = new JButton("7");
        JButton eightBtn = new JButton("8");
        JButton nineBtn = new JButton("9");

        JButton percentBtn = new JButton("%");
        JButton clearExpressionBtn = new JButton("CE");
        JButton clearAllBtn = new JButton("C");
        JButton backspaceBtn = new JButton("BS");

        JButton divideOneByXBtn = new JButton("1/x");
        JButton raiseXByTwoBtn = new JButton("x²");
        JButton rootXBtn = new JButton("√x");
        JButton divideBtn = new JButton("÷");

        JButton multiplyBtn = new JButton("x");
        JButton minusBtn = new JButton("-");
        JButton plusBtn = new JButton("+");

        JButton negateBtn = new JButton("+/-");
        JButton zeroBtn = new JButton("0");
        JButton decimalBtn = new JButton(".");
        JButton equalsBtn = new JButton("=");

        final Button[][] buttonLayout = {
                {
                        new Button(percentBtn, "exp"), new Button(clearExpressionBtn, "exp"),
                        new Button(clearAllBtn, "exp"), new Button(backspaceBtn, "exp")
                },
                {
                        new Button(divideOneByXBtn, "exp"), new Button(raiseXByTwoBtn, "exp"),
                        new Button(rootXBtn, "exp"), new Button(divideBtn, "exp")
                },
                {
                        new Button(sevenBtn, "num"), new Button(eightBtn, "num"),
                        new Button(nineBtn, "num"), new Button(multiplyBtn, "exp")
                },
                {
                        new Button(fourBtn, "num"), new Button(fiveBtn, "num"),
                        new Button(sixBtn, "num"), new Button(minusBtn, "exp")
                },
                {
                        new Button(oneBtn, "num"), new Button(twoBtn, "num"),
                        new Button(threeBtn, "num"), new Button(plusBtn, "exp")
                },
                {
                        new Button(negateBtn, "num"), new Button(zeroBtn, "num"),
                        new Button(decimalBtn, "num"), new Button(equalsBtn, "equals")
                }
        };

        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 4; x++) {
                int xPos = (94 * x) + 4 * (x + 1);
                int yPos = yOffset + ((HEIGHT - 62 * 2) - ((62 * y) + 4 * (y + 1)));

                Button btnClass = buttonLayout[5 - y][x];

                JButton btn = btnClass.btn;
                btn.setBounds(xPos, yPos, 94, 62);
                btn.setForeground(Color.white);
                btn.setFont(numberFont);
                btn.setBorderPainted(false);

                if (btnClass.type == "num") {

                    btn.setBackground(numberButtons);
                    btn.addMouseListener(new MouseAdapter() {
                        public void mouseEntered(MouseEvent event) {
                            btn.setBackground(operationButtons);
                        }

                        public void mouseExited(MouseEvent event) {
                            btn.setBackground(numberButtons);
                        }
                    });

                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            if (btn.getText() == ".") {
                                if (savedNumberPanel.getText() != "") {
                                    rhsNumber = rhsNumber + ".";
                                    numberPanel.setText(rhsNumber);
                                } else {
                                    lhsNumber = lhsNumber + ".";
                                    numberPanel.setText(lhsNumber);
                                }

                            } else if (btn.getText() == "+/-") {
                                if (savedNumberPanel.getText() != "") {
                                    rhsNumber = rhsNumber.startsWith("-") ? rhsNumber.substring(1, rhsNumber.length())
                                            : "-" + rhsNumber;
                                    numberPanel.setText(rhsNumber);
                                } else {
                                    lhsNumber = lhsNumber.startsWith("-") ? lhsNumber.substring(1, lhsNumber.length())
                                            : "-" + lhsNumber;
                                    numberPanel.setText(lhsNumber);
                                }

                            } else if (operator == "") {
                                lhsNumber = lhsNumber == "0" ? btn.getText() : lhsNumber + btn.getText();
                                numberPanel.setText(lhsNumber.strip() + " ");

                            } else {
                                rhsNumber = rhsNumber == "0" ? btn.getText() : rhsNumber + btn.getText();
                                numberPanel.setText(rhsNumber.strip() + " ");
                            }
                        }
                    });

                } else if (btnClass.type == "exp") {

                    btn.setBackground(operationButtons);
                    btn.addMouseListener(new MouseAdapter() {
                        public void mouseEntered(MouseEvent event) {
                            btn.setBackground(numberButtons);
                        }

                        public void mouseExited(MouseEvent event) {
                            btn.setBackground(operationButtons);
                        }
                    });
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            if (btn.getText() == "1/x") {
                                lhsNumber = Double.toString(1.0 / Double.parseDouble(lhsNumber));
                                numberPanel.setText(lhsNumber + " ");

                            } else if (btn.getText() == "x²") {
                                lhsNumber = Double.toString(Math.pow(Double.parseDouble(lhsNumber), 2));
                                numberPanel.setText(lhsNumber + " ");

                            } else if (btn.getText() == "√x") {
                                lhsNumber = Double.toString(Math.sqrt(Double.parseDouble(lhsNumber)));
                                numberPanel.setText(lhsNumber + " ");

                            } else if (btn.getText() == "BS") {
                                if (savedNumberPanel.getText() == "") {
                                    lhsNumber = lhsNumber.strip().substring(0, lhsNumber.strip().length() - 1);
                                    numberPanel.setText(lhsNumber + " ");
                                } else {
                                    rhsNumber = rhsNumber.strip().substring(0, rhsNumber.strip().length() - 1);
                                    numberPanel.setText(rhsNumber + " ");
                                }
                            } else if (btn.getText() == "C") {
                                lhsNumber = "0";
                                rhsNumber = "0";
                                operator = "";
                                savedNumberPanel.setText("");
                                numberPanel.setText(lhsNumber);

                            } else if (btn.getText() == "%") {
                                if (savedNumberPanel.getText() != "") {
                                    rhsNumber = Double.toString(Double.parseDouble(rhsNumber) / 100.0);
                                    numberPanel.setText(rhsNumber);
                                } else {
                                    lhsNumber = Double.toString(Double.parseDouble(lhsNumber) / 100.0);
                                    numberPanel.setText(lhsNumber);
                                }

                            } else if (btn.getText() == "CE") {
                                if (savedNumberPanel.getText() != "") {
                                    savedNumberPanel.setText("");
                                    lhsNumber = rhsNumber;
                                    operator = "";
                                    rhsNumber = "0";
                                    numberPanel.setText(lhsNumber);
                                }

                            } else if (lhsNumber != "0") {
                                operator = btn.getText();
                                savedNumberPanel.setText(lhsNumber + " " + operator + " ");
                                numberPanel.setText(rhsNumber);
                            }
                        }
                    });

                } else if (btnClass.type == "equals") {

                    btn.setBackground(equalsButton);
                    btn.addMouseListener(new MouseAdapter() {
                        public void mouseEntered(MouseEvent event) {
                            btn.setBackground(equalsHoverButton);
                        }

                        public void mouseExited(MouseEvent event) {
                            btn.setBackground(equalsButton);
                        }
                    });

                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            if (lhsNumber != "0" && rhsNumber != "0") {
                                operationFunctions.get(operator).run();
                                savedNumberPanel.setText("");
                                rhsNumber = "0";
                                operator = "";
                                numberPanel.setText(lhsNumber);
                            }
                        }
                    });

                }

                frame.add(btn);

            }
        }

        frame.setVisible(true);

    }
}
