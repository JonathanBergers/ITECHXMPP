package server;

/**
 * Created by jonathan on 1-11-15.
 */
public class ServerSettings {

    static final String STANDARD_XMPPADDRESS = "model@model.com";
    static final String STANDARD_HOSTNAME = "localhost";
    static final int STANDARD_PORT = 8080;


    private final String xmppAddress, hostname;
    private final int port;

    public ServerSettings(String xmppAddress, String hostname, int port) {
        this.xmppAddress = xmppAddress;
        this.hostname = hostname;
        this.port = port;
    }

    public ServerSettings(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        this.xmppAddress = STANDARD_XMPPADDRESS;
    }

    public ServerSettings(int port) {
        this.port = port;
        this.hostname = STANDARD_HOSTNAME;
        this.xmppAddress = STANDARD_XMPPADDRESS;
    }

    public ServerSettings(String xmppAddress) {
        this.xmppAddress = xmppAddress;
        this.hostname = STANDARD_HOSTNAME;
        this.port = STANDARD_PORT;
    }

    public ServerSettings(){
        this.xmppAddress = STANDARD_XMPPADDRESS;
        this.hostname = STANDARD_HOSTNAME;
        this.port = STANDARD_PORT;
    }


    public String getXmppAddress() {
        return xmppAddress;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }
}
