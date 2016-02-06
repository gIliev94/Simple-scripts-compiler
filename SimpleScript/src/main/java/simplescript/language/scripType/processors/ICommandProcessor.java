package simplescript.language.scripType.processors;

import java.awt.AWTException;
import java.io.IOException;
import simplescript.language.scripType.commands.Command;

/**
 * Interface describing core behaviour of a SimpleScript
 * {@link CommandProcessor}.
 * 
 * @author Georgi Iliev
 */
public interface ICommandProcessor {

    /**
     * <h1><i>buildExecutableCommand</i></h1>
     * <p>
     * <p>
     * {@code public abstract Command buildExecutableCommand()}
     * </p>
     * Performs the neccessary low level opearations(object creation, settings,
     * post-validations) to build a ready-to-execute command. </p>
     * 
     * @return Command object containing a command, that is ready to be executed
     *         in runtime.
     * @throws AWTException
     * @throws IOException
     */
    Command buildExecutableCommand() throws AWTException, IOException;

}
