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
 * Unit test case for correct command processor dispatching.
 * 
 * @author Georgi Iliev
 *
 */
public class ProcessorDispatchmentTest extends TestCase {

    public void testGetProcessor() {
	Map<String, CommandProcessor> testData = new HashMap<String, CommandProcessor>(8);
	DispatchmentTestData.loadExpectedDispatchment(testData);

	CommandProcessor expectedProcessor;
	CommandProcessor dispatchedProcessor;

	String command = StringConstants.EMPTY_STRING;
	String expectedQname = StringConstants.EMPTY_STRING;
	String dispatchedQname = StringConstants.EMPTY_STRING;
	String processorName = StringConstants.EMPTY_STRING;

	String testName = this.getClass().getSimpleName();

	SimpleScriptMain.LOG.info("START: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());

	for (Entry<String, CommandProcessor> testItem : testData.entrySet()) {
	    command = testItem.getKey();
	    expectedProcessor = testItem.getValue();

	    dispatchedProcessor = CommandProcessor.getProcessor(command, null);

	    expectedQname = expectedProcessor.getClass().getName();
	    dispatchedQname = dispatchedProcessor.getClass().getName();

	    processorName = dispatchedProcessor.getClass().getSimpleName();

	    if (expectedQname != dispatchedQname) {
		fail("Invalid command processor dispatched: " + processorName + " for command: " + command + "!");
	    }
	}

	SimpleScriptMain.LOG.info("END: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());
    }

}
