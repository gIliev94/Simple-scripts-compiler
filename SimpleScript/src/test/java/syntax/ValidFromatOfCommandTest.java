package syntax;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import simplescript.language.scripType.commands.Command;
import simplescript.program.gui.SimpleScriptMain;
import simplescript.program.utilities.StringConstants;
import testdata.CommandTestData;
import junit.framework.TestCase;

/**
 * Unit test case for valid format of command scenario.
 * 
 * @author Georgi Iliev
 *
 */
public class ValidFromatOfCommandTest extends TestCase {

    public void testHasValidCommandFormat() {
	Set<String> validCommandStatements = new HashSet<String>(8);
	CommandTestData.loadValidStatements(validCommandStatements);

	String testName = this.getClass().getSimpleName();
	SimpleScriptMain.LOG.info("START: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());

	for (String statement : validCommandStatements) {

	    if (!Command.hasValidFormat(statement)) {
		fail("UNEXPECTED BEHAVIOR: Invalid command recognized as existing in fieldlist!");
	    }
	}

	SimpleScriptMain.LOG.info("END: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());
    }

}
