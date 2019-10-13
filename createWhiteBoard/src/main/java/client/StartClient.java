package client;

import WhiteboardUtil.ParseMainParameters;
import multiInterface.BoardThread;
import multiInterface.ManageMultiInterface;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.rmi.RemoteException;

public class StartClient {
    public static void main(String[] args) throws RemoteException {
        ParseMainParameters args4j = new ParseMainParameters();
        CmdLineParser parser = new CmdLineParser(args4j);
        String username = null;

        try {

            parser.parseArgument(args);
            String serverIPAddress = args4j.getIpAddress();
            int serverPort = args4j.getPort();
            username = args4j.getUsername();
            BoardThread.port = serverPort;
            BoardThread.ipAddress = serverIPAddress;


        } catch (CmdLineException e) {
            e.printStackTrace();
        }
        BoardThread thread = new BoardThread();
        ManageMultiInterface.executor.execute(thread);
        BoardThread.client.setName(username);


    }

}
