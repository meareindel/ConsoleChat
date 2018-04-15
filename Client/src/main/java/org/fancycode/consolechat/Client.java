package org.fancycode.consolechat;

import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class Client {

    private final String SERVER_HOST_CONFIGURATION_KEY = "server.host";
    private final String DEFAULT_SERVER_HOST = "localhost";

    private final String SERVER_PORT_CONFIGURATION_KEY = "server.port";
    private final int DEFAULT_SERVER_PORT = 1234;

    private IUserInterface userInterface;
    private String username;
    private IConfigurationManager configurationManager;

    private Client() throws IOException {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        //initDebugLogging();

        userInterface = new ConsoleUserInterface();
        configurationManager = new PropertiesConfigurationManager();
        configurationManager.readFromFile("application.properties");
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.run();
    }

    private void initDebugLogging() {
        LogManager logManager = LogManager.getLogManager();
        List<String> loggerNames = Collections.list(logManager.getLoggerNames());
        for (String loggerName : loggerNames) {
            Logger logger = logManager.getLogger(loggerName);
            logger.setLevel(Level.ALL);
        }
    }

    private String getServerHost()
    {
        return configurationManager.getConfigurationOrDefault(String.class, SERVER_HOST_CONFIGURATION_KEY, DEFAULT_SERVER_HOST);
    }

    private int getServerPort()
    {
        return configurationManager.getConfigurationOrDefault(int.class, SERVER_PORT_CONFIGURATION_KEY, DEFAULT_SERVER_PORT);
    }

    private void run() throws IOException {
        ITransport transport = new TcpTransport(getServerHost(), getServerPort());
        username = userInterface.promt("Enter your name: ");
        transport.writeLine("/name " + username);
        userInterface.show(transport.readLine());
    }
}
