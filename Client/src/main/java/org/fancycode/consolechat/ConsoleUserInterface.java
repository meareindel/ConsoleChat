package org.fancycode.consolechat;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

import java.io.IOException;

public class ConsoleUserInterface implements IUserInterface {
    private Terminal terminal;
    private LineReader reader;

    @Override
    public String promt(String promtMessage) {
        return reader.readLine(promtMessage);
    }

    @Override
    public void show(String showMessage) {
        terminal.writer().println(showMessage);
        terminal.flush();
    }

    public ConsoleUserInterface() throws IOException {
        terminal = TerminalBuilder.terminal();
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.flush();
        reader = LineReaderBuilder.builder().terminal(terminal).appName("Console Chat").build();
    }
}
