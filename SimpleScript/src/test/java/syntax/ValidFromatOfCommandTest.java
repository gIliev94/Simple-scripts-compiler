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
 * Unit test case for valid format of command scenario.
 * 
 * @author Georgi Iliev
 *
 */
public class ValidFromatOfCommandTest extends TestCase {

    public void testHasValidCommandFormat() {
	Set<String> validCommandStatements = new HashSet<String>(8);
	CommandsTestData.loadValidStatements(validCommandStatements);

	String testName = this.getClass().getSimpleName();

	SimpleScriptMain.LOG.info("START: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());

	for (String statement : validCommandStatements) {

	    if (!Command.hasValidFormat(statement)) {
		fail("UNEXPECTED BEHAVIOR: Valid command format NOT recognized!");
	    }
	}

	SimpleScriptMain.LOG.info("END: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());
    }

}
