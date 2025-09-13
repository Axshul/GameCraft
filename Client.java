import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        boolean active = true;
        String prompt = "@~";

        try (Socket socket = new Socket("localhost", 5431);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scan = new Scanner(System.in)) {

            System.out.println(in.readLine());

            while (active) {
                System.out.print(prompt);
                String input = scan.nextLine();
                out.println(input);

                switch (input.toLowerCase()) {
                    case "exit":
                        System.out.println(in.readLine());
                        active = false;
                        break;

                    case "newuser":
                        System.out.println(in.readLine());
                        String newName = scan.nextLine();
                        out.println(newName);

                        System.out.println(in.readLine());
                        String newPass = scan.nextLine();
                        out.println(newPass);

                        System.out.println(in.readLine());
                        prompt = newName + "@~";
                        break;

                    default:
                        System.out.println(in.readLine());
                        break;
                }
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
