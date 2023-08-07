import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TextSpamApp {
    private final JTextField inputText;
    private final JTextField inputInterval;
    private Thread spamThread;
    private boolean isSpamming;

    public TextSpamApp() {
        JFrame frame = new JFrame("Text Spam Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960, 718);


        // Load and set the background image
        ImageIcon backgroundImage = new ImageIcon("img\\Background.jpg");
        Image resizedImage = backgroundImage.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT);
        backgroundImage.setImage(resizedImage);
        JLabel backgroundLabel = new JLabel(backgroundImage);
        frame.setContentPane(backgroundLabel);

        ImageIcon icon = new ImageIcon("img\\icon.png");
        frame.setIconImage(icon.getImage());


        JLabel titleLabel = new JLabel("Text Spammer");
        titleLabel.setForeground(new Color(255,255,255));
        titleLabel.setFont(new Font("Roboto Condensed", Font.BOLD, 70));
        titleLabel.setBounds(243, 44, titleLabel.getPreferredSize().width, titleLabel.getPreferredSize().height);
        frame.add(titleLabel);

        JLabel textLabel = new JLabel("Text to be spammed:");
        textLabel.setForeground(new Color(255,255,255));
        textLabel.setFont(new Font("Roboto Condensed", Font.BOLD, 24));
        textLabel.setBounds(53, 112, 268,200);
        frame.add(textLabel);

        inputText = new JTextField();
        inputText.setBounds(60, 227, 827,72);
        inputText.setOpaque(false); // Make it transparent
        inputText.setBorder(null); // Remove the border
        inputText.setForeground(Color.WHITE); // Change the text color
        inputText.setFont(new Font("Roboto Condensed", Font.BOLD, 20));
        frame.add(inputText);

        JLabel intervalLabel = new JLabel("Time Interval");
        intervalLabel.setForeground(new Color(255,255,255));
        intervalLabel.setFont(new Font("Roboto Condensed", Font.BOLD, 24));
        intervalLabel.setBounds(53, 264, 268,200);
        frame.add(intervalLabel);

        inputInterval = new JTextField(20);
        inputInterval.setBounds(60, 379, 827,72);
        inputInterval.setOpaque(false); // Make it transparent
        inputInterval.setBorder(null); // Remove the border
        inputInterval.setForeground(Color.WHITE); // Change the text color
        inputInterval.setFont(new Font("Roboto Condensed", Font.BOLD, 20));
        frame.add(inputInterval);

        JButton startButton = new JButton("Start");
        startButton.setBounds(53, 489, 383, 70);
        startButton.setOpaque(false); // Make it transparent
        startButton.setContentAreaFilled(false); // Make the content area transparent
        startButton.setBorderPainted(false); // Remove the border
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Roboto Condensed", Font.BOLD, 20));
        startButton.addActionListener(e -> startSpam());
        frame.add(startButton);


        JButton stopButton = new JButton("Stop");
        stopButton.setBounds(510, 489, 383, 70);
        stopButton.setOpaque(false); // Make it transparent
        stopButton.setContentAreaFilled(false); // Make the content area transparent
        stopButton.setBorderPainted(false); // Remove the border
        stopButton.setForeground(Color.WHITE);
        stopButton.setFont(new Font("Roboto Condensed", Font.BOLD, 20));
        stopButton.addActionListener(e -> stopSpam());
        frame.add(stopButton);

        JLabel attributes = new JLabel("Made by Samipya Mainali ©");
        attributes.setForeground(new Color(255,255,255));
        attributes.setFont(new Font("Roboto Condensed", Font.BOLD, 16));
        attributes.setBounds(360, 610, titleLabel.getPreferredSize().width, titleLabel.getPreferredSize().height);
        frame.add(attributes);

        frame.add(stopButton);
        frame.setVisible(true);
        frame.setResizable(false);
    }




    public void startSpam() {
        if (!isSpamming) {
            isSpamming = true;
            spamThread = new Thread(() -> {
                while (isSpamming) {
                    String text = inputText.getText();
                    if (!text.isEmpty()) {
                        try {
                            Robot robot = new Robot();
                            robot.keyPress(KeyEvent.VK_SHIFT);
                            for (char c : text.toCharArray()) {
                                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                                robot.keyPress(keyCode);
                                robot.keyRelease(keyCode);
                            }
                            robot.keyRelease(KeyEvent.VK_SHIFT);
                            robot.keyPress(KeyEvent.VK_ENTER);
                            robot.keyRelease(KeyEvent.VK_ENTER);
                            Thread.sleep((int) (Double.parseDouble(inputInterval.getText()) * 1000));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            spamThread.start();
        }
    }

    public void stopSpam() {
        if (isSpamming) {
            isSpamming = false;
            if (spamThread != null && spamThread.isAlive()) {
                spamThread.interrupt();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextSpamApp::new);
    }
}
