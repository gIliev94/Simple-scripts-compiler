package compiler;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import junit.framework.TestCase;
import simplescript.language.scripType.Keywords;
import simplescript.language.scripType.processors.ClickProcessor;
import simplescript.language.scripType.processors.CommandProcessor;
import simplescript.language.scripType.processors.DelayProcessor;
import simplescript.language.scripType.processors.LineProcessor;
import simplescript.language.scripType.processors.MoveProcessor;
import simplescript.language.scripType.processors.OpenProcessor;
import simplescript.language.scripType.processors.PointProcessor;
import simplescript.language.scripType.processors.PressProcessor;
import simplescript.language.scripType.processors.TextProcessor;
import simplescript.program.gui.SimpleScriptMain;
import simplescript.program.utilities.StringConstants;

/**
 * Unit test case for correct command processor dispatching.
 * 
 * @author Georgi Iliev
 *
 */
public class ProcessorDispatchmentTest extends TestCase {

    public void testGetProcessor() {
	String testName = this.getClass().getSimpleName();

	SimpleScriptMain.LOG.info("START: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());

	Map<String, CommandProcessor> testData = new HashMap<String, CommandProcessor>(8);
	testData.put(Keywords.LINE, new LineProcessor(null, null));
	testData.put(Keywords.POINT, new PointProcessor(null, null));
	testData.put(Keywords.MOVE, new MoveProcessor(null));
	testData.put(Keywords.CLICK, new ClickProcessor(null));
	testData.put(Keywords.DELAY, new DelayProcessor(null));
	testData.put(Keywords.OPEN, new OpenProcessor(null));
	testData.put(Keywords.PRESS, new PressProcessor(null));
	testData.put(Keywords.TEXT, new TextProcessor(null));

	CommandProcessor expectedProcessor;
	CommandProcessor dispatchedProcessor;

	String command;
	String expectedQname;
	String dispatchedQname;
	String processorName;

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

	SimpleScriptMain.LOG.info("END: " + testName + StringConstants.WHITESPACE + Calendar.getInstance().getTime());
    }

}
