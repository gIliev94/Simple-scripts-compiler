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
 * Unit test case for valid command keyword scenario.
 * 
 * @author Georgi Iliev
 *
 */
public class ValidCommandKeywordTest extends TestCase {

    public void testHasValidKeyword() {
	Set<String> validCommandKeywords = new HashSet<String>(8);
	CommandsTestData.loadValidCommandKeywords(validCommandKeywords);

	String testName = this.getClass().getSimpleName();

	SimpleScriptMain.LOG.info("START: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());

	for (String keyword : validCommandKeywords) {

	    if (!Command.hasValidKeyword(keyword)) {
		fail("UNEXPECTED BEHAVIOR: Valid command NOT recognized as existant in fieldlist!");
	    }

	}

	SimpleScriptMain.LOG.info("END: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());
    }

}