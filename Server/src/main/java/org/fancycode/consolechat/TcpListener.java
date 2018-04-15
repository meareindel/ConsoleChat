package org.fancycode.consolechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpListener implements IListener {

    private final Logger logger = LoggerFactory.getLogger(TcpListener.class);

    private ServerSocket serverSocket;

    public TcpListener(int listenPort) throws IOException {
      serverSocket = new ServerSocket(listenPort);
    }

    @Override
    public ITransport accept() throws IOException {
        Socket accept = serverSocket.accept();
        logger.info("Accepted new connection: {}", accept.getRemoteSocketAddress());
        return new TcpTransport(accept);
    }
}
