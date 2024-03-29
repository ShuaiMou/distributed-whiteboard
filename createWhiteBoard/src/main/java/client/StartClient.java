package client;

import WhiteboardUtil.ParseMainParameters;
import multiInterface.BoardThread;
import multiInterface.ManageMultiInterface;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import remoteInterface.Communication;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StartClient {
    public static void main(String[] args) throws RemoteException {
        ParseMainParameters args4j = new ParseMainParameters();
        CmdLineParser parser = new CmdLineParser(args4j);

        try {
            parser.parseArgument(args);
            String serverIPAddress = args4j.getIpAddress();
            int serverPort = args4j.getPort();
            String username = args4j.getUsername();
            
            BoardThread thread = new BoardThread();
            ManageMultiInterface.executor.execute(thread);
            BoardThread.client.setName(username);
            UnicastRemoteObject.exportObject(BoardThread.client,0 );
            BoardThread.server = (Communication) Naming.lookup("rmi://" + serverIPAddress +":" + serverPort + "/Communication");

            Thread.currentThread().sleep(3000);

            BoardThread.server.managerLogin(BoardThread.client);

        } catch (CmdLineException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
