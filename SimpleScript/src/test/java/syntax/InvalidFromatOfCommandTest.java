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
 * Unit test case for invalid format of command scenario.
 * 
 * @author Georgi Iliev
 *
 */
public class InvalidFromatOfCommandTest extends TestCase {

    public void testHasValidCommandFormat() {
	Set<String> invalidCommandStatements = new HashSet<String>(8);
	CommandsTestData.loadInvalidStatements(invalidCommandStatements);

	String testName = this.getClass().getSimpleName();

	SimpleScriptMain.LOG.info("START: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());

	for (String statement : invalidCommandStatements) {

	    if (Command.hasValidFormat(statement)) {
		fail("UNEXPECTED BEHAVIOR: Invalid command format recognized!");
	    }
	}

	SimpleScriptMain.LOG.info("END: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());
    }

}
