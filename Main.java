import java.util.Scanner;
import java.util.HashMap;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        boolean Server = true;
        HashMap<String,String> User = new HashMap<String,String>();

        try{
            System.out.println("Listening On Port 5431");
            ServerSocket ServerSocket = new ServerSocket(5431);
            Socket ClientSocket = ServerSocket.accept();
            System.out.println("Client Connected");

            PrintWriter Out = new PrintWriter(ClientSocket.getOutputStream(),true);
            BufferedReader In = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));

            Out.println("Connected To GameCraft");
            while(Server){
                String ClientCommand = In.readLine();

                if(ClientCommand.equals("newuser" ||  )){
                    Out.println("New Username: ");
                    String NewName = In.readLine();

                    Out.println("New Password: ");
                    String NewPas = In.readLine();

                    Out.println("UserCreated!");
                    System.out.println("UID: "+NewName+":"+NewPas);
                }

                if(ClientCommand.equals("exit")){
                    Out.println("Bye Bye!");
                    Server = false;
                    ServerSocket.close();
                }
            }

            ServerSocket.close();

        } catch(IOException err){
            err.printStackTrace();
        }
    }
}