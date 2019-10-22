package WhiteboardUtil;

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

	@Option(name="-username", required = false, usage = "the user name")
	private String username = "aa";
}
