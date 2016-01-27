package simplescript.language.scripType;

import java.awt.AWTException;
import java.io.IOException;
import simplescript.language.scripType.commands.ICommand;

/**
 * <h1>Executor class - a Singleton that schedules all user input commands to be
 * ran in a sequence.</h1>
 * <p>
 * </p>
 * 
 * @author Georgi Iliev
 */
public class CommandRuntime {

    private static final CommandRuntime RUNTIME;

    private CommandRuntime() {
    }

    static {
	RUNTIME = new CommandRuntime();
    }

    /**
     * @return The only instance of the Singleton command runtime environment.
     */
    public static CommandRuntime getInstance() {
	return RUNTIME;
    }

    /**
     * <h1><i>run</i></h1>
     * <p>
     * <p>
     * {@code public void run(ICommand[] commands)}
     * </p>
     * Runs all the commands one after the other(sequentially). </p>
     * 
     * @param commands
     *            - the command sequence to be ran.
     * @throws AWTException
     * @throws IOException
     */
    public void run(ICommand[] commands) throws AWTException, IOException {
	if (commands == null)
	    return;
	for (int i = 0; i < commands.length; i++) {
	    commands[i].execute();
	}
    }
}