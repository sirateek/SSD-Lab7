import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ChatServer extends JFrame {
    public static final int PORT = 5455;

    private Server server;
    private JTextArea screen;

    public ChatServer() {
        server = new Server();
        server.getKryo().register(Message.class);
        server.addListener(new Listener(){
            @Override
            public void received(Connection connection, Object object) {
                super.received(connection, object);
                if (object instanceof Message) {
                    Message message = (Message) object;
                    screen.append("Received message " + message.text + " From " + message.senderName + "\n");

                    server.sendToAllTCP(message);  // broadcast
                }
            }

            @Override
            public void connected(Connection connection) {
                super.connected(connection);
                screen.append("New client connected\n");
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
                screen.append("Client disconnected\n");
            }
        });
        initGuis();
    }

    public void start() {
        setVisible(true);
        screen.append("Server Started\n");

        server.start();
        try {
            server.bind(PORT);
        } catch (IOException e) {
            screen.append("Cannot bind to the port " + PORT);
            e.printStackTrace();
        }
    }

    private void initGuis() {
        setTitle("Server Screen");
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        screen = new JTextArea();
        screen.setPreferredSize(new Dimension(480, 480));
        screen.setBackground(Color.black);
        screen.setForeground(Color.green);
        screen.setEditable(false);
        add(screen);
        pack();
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.start();
    }
}