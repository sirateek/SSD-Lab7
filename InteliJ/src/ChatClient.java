import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.*;

import com.esotericsoftware.kryonet.*;


public class ChatClient extends JFrame{
    private static final int PORT = 5455;
    private static final String IP = "127.0.0.1";
    private JTextArea messagePane;
    private JTextField inputName;
    private JTextField inputMessage;

    private Client client;

    public ChatClient() {
        client = new Client();
        initGuis();
    }

    public void startClient() {
        client.start();
        try {
            client.connect(5000, IP, PORT);
        } catch (IOException e) {
            messagePane.append("Can't connect to server");
        }
        setVisible(true);
    }

    private void initGuis() {
        inputName = new JTextField("No Name");
        messagePane = new JTextArea();
        inputMessage = new JTextField();

        messagePane.setPreferredSize(new Dimension(300,300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(inputName, BorderLayout.NORTH);
        add(messagePane, BorderLayout.CENTER);
        add(inputMessage, BorderLayout.SOUTH);

        pack();
    }

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();
        chatClient.startClient();
    }
}

