package org.fancycode.consolechat;

import java.io.IOException;

public interface ITransport {
    String readLine() throws IOException;
    void writeLine(String line);
}
