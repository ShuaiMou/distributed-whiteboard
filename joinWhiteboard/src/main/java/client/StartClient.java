package client;

import WhiteboardUtil.ParseMainParameters;
import multiInterface.BoardThread;
import multiInterface.ManageMultiInterface;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import remoteInterface.Communication;

import javax.swing.*;
import java.io.IOException;
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

            Thread.currentThread().sleep(2000);

            BoardThread.server.collaboratorLogin(BoardThread.client);
        } catch (CmdLineException e) {
            e.printStackTrace();

        } catch (NotBoundException | InterruptedException | IOException e) {
            Object[] selection = {"OK"};
            JOptionPane.showOptionDialog(null, "there is somthing wrong to find the RMI service, please try again.",
                    "Warning",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, selection, null);
            System.exit(0);
        }

    }

}
