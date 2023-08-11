import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class WordCounterGUI extends JFrame {
    private JTextArea inputTextArea;
    private JTextField delimiterField;
    private JTextArea outputTextArea;
    private JButton analyzeButton;

    public WordCounterGUI() {
        setTitle("Word Counter GUI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputTextArea = new JTextArea();
        delimiterField = new JTextField();
        outputTextArea = new JTextArea();
        analyzeButton = new JButton("Analyze");

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JLabel("Enter text:"), BorderLayout.NORTH);
        inputPanel.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);
        inputPanel.add(new JLabel("Enter delimiter:"), BorderLayout.SOUTH);
        inputPanel.add(delimiterField, BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
        add(analyzeButton, BorderLayout.SOUTH);

        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analyzeText();
            }
        });
    }

    private void analyzeText() {
        String inputText = inputTextArea.getText();
        String delimiter = delimiterField.getText();

        if (inputText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter valid text.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] arr = inputText.split(delimiter);
        outputTextArea.setText("");

        LinkedHashMap<String, Integer> hm = new LinkedHashMap<>();
        for (String word : arr) {
            hm.put(word, hm.getOrDefault(word, 0) + 1);
        }

        outputTextArea.append("Total number of words: " + arr.length + "\n");
        outputTextArea.append("Number of unique words: " + hm.size() + "\n\n");
        outputTextArea.append("Frequencies of each word:\n\n");

        for (String word : hm.keySet()) {
            outputTextArea.append(word + " : " + hm.get(word) + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WordCounterGUI app = new WordCounterGUI();
                app.setVisible(true);
            }
        });
    }
}