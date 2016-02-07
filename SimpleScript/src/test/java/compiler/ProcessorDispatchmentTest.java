package compiler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import simplescript.language.scripType.exceptions.CommandFormatException;
import simplescript.language.scripType.exceptions.UnknownCommandException;
import simplescript.language.scripType.processors.CommandProcessor;
import simplescript.program.gui.SimpleScriptMain;
import simplescript.program.utilities.StringConstants;
import syntax.TestCommandStatements;

/**
 * TO BE IMPLEMENTED:
 * 
 * Unit test case for correct command processor dispatching.
 * 
 * @author Georgi Iliev
 *
 */
public class ProcessorDispatchmentTest extends TestCase {

    public void testGetProcessor() {
	SimpleScriptMain.LOG.info("Initiated ProcessorDispatchmentTest: " + Calendar.getInstance().getTime());

	List<String> validStatements = new ArrayList<String>();
	TestCommandStatements.loadValidStatements(validStatements);

	List<String> invalidStatements = new ArrayList<String>();
	TestCommandStatements.loadInvalidStatements(invalidStatements);

	try {
	    SimpleScriptMain.LOG.info("Initiating POSITIVE case for statements...");
	    performTest(validStatements);
	    SimpleScriptMain.LOG.info("Passed POSITIVE case for statements!");

	    SimpleScriptMain.LOG.info("Initiating NEGATIVE case for statements...");
	    performTest(invalidStatements);
	    SimpleScriptMain.LOG.info("Passed NEGATIVE test case for statements!");

	} catch (UnknownCommandException e) {
	    SimpleScriptMain.LOG.error("Invalid command in TEST: ", e);
	    fail("Invalid command detected!");
	} catch (CommandFormatException e) {
	    SimpleScriptMain.LOG.error("Ivalid format of command in TEST: ", e);
	    fail("Ivalid format of command detected!");
	}

	SimpleScriptMain.LOG.info("Passed ProcessorDispatchmentTest: " + Calendar.getInstance().getTime());
    }

    private void performTest(List<String> statements) throws UnknownCommandException, CommandFormatException {
	CommandProcessor processor;
	Iterator<String> iterator = statements.iterator();

	while (iterator.hasNext()) {
	    String statement = iterator.next();
	    String command = statement.split(StringConstants.WHITESPACE)[0];

	    processor = CommandProcessor.getProcessor(statement, null);

	    String processorName = processor.getClass().getSimpleName().toLowerCase();

	    if (!processorName.startsWith(command)) {
		fail("Invalid command processor dispatched: " + processorName + " for command: " + command + "!");
	    }

	}

    }

}
