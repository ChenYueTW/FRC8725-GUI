import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private final JPanel mainPanel;
    private final JPanel buttonPanel;
    private final JScrollPane scrollPane;
    private final JButton addMotorButton;
    private final JButton removeMotorButton;
    private int motorCount = 1;

    public GUI() {
        this.mainPanel = new JPanel(new GridLayout(0, 1));
        this.buttonPanel = new JPanel(new GridLayout());
        this.addMotorButton = new JButton("+");
        this.removeMotorButton = new JButton("-");

        this.setSize(800, 600);
        this.setTitle("FRC 8725 GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addProjectName();
        this.addMotor("Motor1");

        this.addMotorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                motorCount ++;
                String motorName = "Motor" + motorCount;
                addMotor(motorName);
            }
        });
        this.removeMotorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeLastMotor();
            }
        });

        this.buttonPanel.add(this.addMotorButton, BorderLayout.WEST);
        this.buttonPanel.add(this.removeMotorButton, BorderLayout.EAST);

        this.scrollPane = new JScrollPane(this.mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        this.add(this.scrollPane, BorderLayout.CENTER);
        this.add(this.buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    private void addProjectName() {
        JPanel projectPanel = new JPanel();        
        JTextField projectField = new JTextField(10);

        projectPanel.add(new JLabel("Project Name: "));
        projectPanel.add(projectField);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;

        this.mainPanel.add(projectPanel, gbc);
    }

    private void addMotor(String motorName) {
        JPanel motorPanel = new JPanel(new GridBagLayout());
        motorPanel.setBorder(BorderFactory.createTitledBorder(motorName));

        JCheckBox reverseBox = new JCheckBox();
        JTextField motorIdField = new JTextField(10);

        GridBagConstraints idConstraints = new GridBagConstraints();
        idConstraints.gridx = 1;
        idConstraints.gridy = 0;
        idConstraints.anchor = GridBagConstraints.WEST;

        motorPanel.add(new JLabel("Motor ID: "));
        motorPanel.add(motorIdField, idConstraints);

        motorPanel.add(new JLabel("Reverse: "));
        motorPanel.add(reverseBox);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = this.motorCount;
        gbc.insets = new Insets(5, 5, 5, 5);

        this.mainPanel.add(motorPanel, gbc);
        revalidate();
        repaint();
    }

    private void removeLastMotor() {
        if (this.motorCount > 1) {
            this.mainPanel.remove(this.motorCount);
            this.motorCount --;
            revalidate();
            repaint();
        }
    }
}
