import com.esotericsoftware.kryonet.*;
import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class ChatServer extends JFrame {
    private static final int PORT = 5455;
    private Server server;
    private JTextArea screen;

    public ChatServer() {
        server = new Server();
        server.addListener(new Listener() {

            @Override
            public void connected(Connection connection) {
                super.connected(connection);
                screen.append("New client connected!");
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
                screen.append("Client Disconnected.");
            }
        });
        initGuis();
        screen.append("Server Started");
    }

    public void startServer() {
        setVisible(true);

        server.start();

        try {
            server.bind(PORT);
        } catch (IOException e) {
            e.printStackTrace();
            screen.append("Cannot startserver on port" + PORT);
        }
    }

    private void initGuis() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Server Screen");
        screen = new JTextArea();
        screen.setPreferredSize(new Dimension(480,480));
        screen.setBackground(Color.black);
        screen.setForeground(Color.green);
        add(screen);
        pack();
    }


    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.startServer();
    }
}