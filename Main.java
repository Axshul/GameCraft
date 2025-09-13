import java.util.HashMap;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main{
    public static void main(String[] args) {
        boolean running = true;
        boolean userLogged = false;
        String currentUser = null;
        HashMap<String, String> users = new HashMap<>();

        try (ServerSocket serverSocket = new ServerSocket(5431)) {
            System.out.println("Listening on port 5431...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Connected To GameCraft");

            while (running) {
                String command = in.readLine();
                if (command == null) break;

                switch (command.toLowerCase()) {
                    case "newuser":
                        if (!userLogged) {
                            out.println("New Username: ");
                            String newName = in.readLine();

                            out.println("New Password: ");
                            String newPass = in.readLine();

                            users.put(newName, newPass);
                            userLogged = true;
                            currentUser = newName;
                            out.println("UserCreated!");
                            System.out.println("UID: " + newName + ":" + newPass);
                        } else {
                            out.println("Already logged in.");
                        }
                        break;

                    case "ls":
                        out.println("file1.txt file2.txt project.java");
                        break;

                    case "whoami":
                        if (userLogged && currentUser != null) {
                            out.println("You are logged in as " + currentUser);
                        } else {
                            out.println("Guest (not logged in)");
                        }
                        break;

                    case "exit":
                        out.println("Bye Bye!");
                        running = false;
                        break;

                    default:
                        out.println("Unknown command: " + command);
                        break;
                }
            }

            System.out.println("Server shutting down...");
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
