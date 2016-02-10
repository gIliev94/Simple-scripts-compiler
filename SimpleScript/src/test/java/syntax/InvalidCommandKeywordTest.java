package syntax;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;
import simplescript.language.scripType.commands.Command;
import simplescript.program.gui.SimpleScriptMain;
import simplescript.program.utilities.StringConstants;
import testdata.CommandsTestData;

/**
 * Unit test case for invalid command keyword scenario.
 * 
 * @author Georgi Iliev
 *
 */
public class InvalidCommandKeywordTest extends TestCase {

    public void testHasValidKeyword() {
	Set<String> invalidCommandKeywords = new HashSet<String>(8);
	CommandsTestData.loadInvalidCommandKeywords(invalidCommandKeywords);

	String testName = this.getClass().getSimpleName();

	SimpleScriptMain.LOG.info("START: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());

	for (String keyword : invalidCommandKeywords) {
	    if (Command.hasValidKeyword(keyword)) {
		fail("UNEXPECTED BEHAVIOR: Invalid command recognized as existant in fieldlist!");
	    }

	}

	SimpleScriptMain.LOG.info("END: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());
    }

}