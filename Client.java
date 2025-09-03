import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        boolean Active = true;
        String CMD = "@~";

        Scanner Scan = new Scanner(System.in);

        try{
            Socket Socket = new Socket("localhost",5431);
            PrintWriter Out = new PrintWriter(Socket.getOutputStream(),true);
            BufferedReader In = new BufferedReader(new InputStreamReader(Socket.getInputStream()));

            System.out.println(In.readLine());
            while(Active){
                System.out.print(CMD);
                String Input = Scan.nextLine();
                Out.println(Input);
                if(Input.equals("exit")){
                    System.out.println(In.readLine());
                    Active = false;
                    Socket.close();
                }
                if(Input.equals("newuser") && CMD == "@~"){
                    System.out.println(In.readLine());
                    String NewName = Scan.nextLine();
                    Out.println(NewName);

                    System.out.println(In.readLine());
                    String NewPas = Scan.nextLine();
                    Out.println(NewPas);

                    System.out.println(In.readLine());
                    CMD = NewName+"@~";
                }
            }

            Socket.close();
        }catch(IOException err){
            err.printStackTrace();
        }
    }
}

