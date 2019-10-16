package server;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kohsuke.args4j.Option;

@Getter
@NoArgsConstructor
public class ParseMainParameters {
	@Option(name="-serverIPAddress" ,required = false, usage = "server IP address")
	private String ipAddress = "localhost";//"10.13.230.53";
	
	@Option(name="-serverPort", required = false, usage = "tcp port")
	private int port = 9999;


}
