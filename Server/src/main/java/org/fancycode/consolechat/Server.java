package org.fancycode.consolechat;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Server
{
    private final String LISTER_PORT_CONFIGURATION_KEY = "listen.port";
    private final int DEFAULT_LISTEN_PORT = 1234;

    private IConfigurationManager configurationManager;

    private Server()
    {
        configurationManager = new PropertiesConfigurationManager();
        configurationManager.readFromFile("application.properties");
    }

    private int getListenPort()
    {
        return configurationManager.getConfigurationOrDefault(int.class, LISTER_PORT_CONFIGURATION_KEY, DEFAULT_LISTEN_PORT);
    }

    private void run() throws IOException {
        IListener listener = new TcpListener(getListenPort());
        ITransport transport = listener.accept();

        String usernameCommand = transport.readLine();
        String username = usernameCommand.replace("/name ","");
        transport.writeLine("Hello, " + username + "! Entering chat on server...");
    }

    public static void main( String[] args ) throws IOException {
        Server server = new Server();
        server.run();
    }
}
