package simplescript.language.scripType.commands;

import java.awt.AWTException;
import java.io.IOException;

public class Open extends Command {

    protected String program;

    public Open() {
    }

    public Open(String programToOpen) {
	this.program = programToOpen;
    }

    public void execute() throws AWTException, IOException {

	if (program.toLowerCase().startsWith("www.")
		|| program.toLowerCase().endsWith(".exe")) {
	    Runtime.getRuntime().exec("cmd /c start " + program.toLowerCase());
	}
    }

    public String toString() {
	return "open";
    }

    public String getCommandFormat() {
	return "OPEN\\s\\w+";
    }
}
