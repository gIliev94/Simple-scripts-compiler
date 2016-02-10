package compiler;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.TestCase;
import simplescript.language.scripType.processors.CommandProcessor;
import simplescript.program.gui.SimpleScriptMain;
import simplescript.program.utilities.StringConstants;
import testdata.DispatchmentTestData;

/**
 * Unit test case for command processor dispatching.
 * 
 * @author Georgi Iliev
 *
 */
public class ProcessorDispatchmentTest extends TestCase {

    public void testGetProcessor() {
	Map<String, CommandProcessor> expectedDispatchment = new HashMap<String, CommandProcessor>(8);
	DispatchmentTestData.loadExpectedDispatchment(expectedDispatchment);

	CommandProcessor expectedProcessor;
	CommandProcessor dispatchedProcessor;

	String command = StringConstants.EMPTY_STRING;
	String testName = this.getClass().getSimpleName();

	SimpleScriptMain.LOG.info("START: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());

	for (Entry<String, CommandProcessor> dispatchment : expectedDispatchment.entrySet()) {
	    command = dispatchment.getKey();
	    expectedProcessor = dispatchment.getValue();

	    dispatchedProcessor = CommandProcessor.getProcessor(command, null);

	    Class<? extends CommandProcessor> expectedClass = expectedProcessor.getClass();
	    Class<? extends CommandProcessor> dispatchedClass = dispatchedProcessor.getClass();

	    if (!dispatchedClass.isAssignableFrom(expectedClass)) {
		String processorName = dispatchedClass.getSimpleName();
		fail("Invalid command processor dispatched: " + processorName + " for command: " + command + "!");
	    }

	}

	SimpleScriptMain.LOG.info("END: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());
    }

}
