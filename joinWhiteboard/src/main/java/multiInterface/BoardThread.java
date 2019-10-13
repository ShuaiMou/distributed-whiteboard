package multiInterface;


import client.RMIClient;
import controller.Controller;
import view.View;

//Assemble all the pieces of the MVC
public class BoardThread implements Runnable{
    public static RMIClient client;
    public static int port;
    public static String ipAddress;

    static {
        client = new RMIClient();
    }

    public void run() {
        View view = new View();
        Controller controller = new Controller(view);
    }
}
