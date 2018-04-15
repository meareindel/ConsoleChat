package org.fancycode.consolechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpTransport implements ITransport {

    private PrintWriter socketWriter;
    private BufferedReader socketReader;

    private void initialize(Socket socket) throws IOException {
        socketWriter = new PrintWriter(socket.getOutputStream(), true);
        socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public TcpTransport(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        initialize(socket);
    }
    public TcpTransport(Socket socket) throws IOException {
        initialize(socket);
    }

    @Override
    public String readLine() throws IOException {
        return socketReader.readLine();
    }

    @Override
    public void writeLine(String line) {
        socketWriter.println(line);
    }
}
