package org.fancycode.consolechat;

import java.io.IOException;

public interface IListener {
    ITransport accept() throws IOException;
}
