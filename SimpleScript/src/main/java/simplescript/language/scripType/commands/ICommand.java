package simplescript.language.scripType.commands;

import java.awt.AWTException;
import java.io.IOException;

/**
 * Interface describing core behavior of a SimpleScript {@link Command}.
 * 
 * @author Georgi Iliev
 *
 */
public interface ICommand {

    /**
     * <h1><i>execute</i></h1>
     * <p>
     * <p>
     * {@code public abstract void execute()}
     * </p>
     * Performs the neccessary low level opearations(object creation, settings,
     * validations) to execute the built command. </p>
     * 
     * @throws AWTException
     * @throws IOException
     */
    void execute() throws AWTException, IOException;

    /**
     * <h1><i>toString</i></h1>
     * <p>
     * <p>
     * {@code public abstract String toString()}
     * </p>
     * Convenience method, should it happen to need a String representation.
     * </p>
     * 
     * @return The command`s name/keyword as a String.
     */
    String toString();

}
