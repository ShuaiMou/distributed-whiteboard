package server;


import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import remoteInterface.Communication;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class StartServer {
    public static void main(String[] args) {
        ParseMainParameters args4j = new ParseMainParameters();
        CmdLineParser parser = new CmdLineParser(args4j);
        try {
            Communication server = new ServerImpl();
            parser.parseArgument(args);
            String serverIPAddress = args4j.getIpAddress();
            int serverPort = args4j.getPort();
            LocateRegistry.createRegistry(serverPort);
            Naming.rebind("rmi://"+serverIPAddress+ ":"+serverPort+"/Communication", server);
            System.out.println(serverIPAddress);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (CmdLineException e) {
            e.printStackTrace();
        }
    }
}
