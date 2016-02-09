package syntax;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;
import simplescript.language.scripType.commands.Command;
import simplescript.program.gui.SimpleScriptMain;
import simplescript.program.utilities.StringConstants;
import testdata.CommandTestData;

/**
 * Unit test case for valid command scenario.
 * 
 * @author Georgi Iliev
 *
 */
public class ValidCommandTest extends TestCase {

    public void testIsValidCommand() {
	Set<String> validCommandKeywords = new HashSet<String>(8);
	CommandTestData.loadValidCommandKeywords(validCommandKeywords);

	String testName = this.getClass().getSimpleName();
	SimpleScriptMain.LOG.info("START: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());

	for (String keyword : validCommandKeywords) {

	    if (!Command.hasValidCommandKeyword(keyword)) {
		fail("UNEXPECTED BEHAVIOR: Invalid command recognized as existing in fieldlist!");
	    }

	}

	SimpleScriptMain.LOG.info("END: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());
    }

}