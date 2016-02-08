package syntax;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;
import simplescript.language.scripType.Keywords;
import simplescript.program.gui.SimpleScriptMain;
import simplescript.program.utilities.StringConstants;
import testdata.CommandTestData;

/**
 * 
 * Unit test case for incorrect command scenario.
 * 
 * @author Georgi Iliev
 *
 */
public class InvalidCommandTest extends TestCase {

    public void testIsValidCommand() {
	Set<String> invalidCommands = new HashSet<String>(8);
	CommandTestData.loadInvalidCommandKeywords(invalidCommands);

	String testName = this.getClass().getSimpleName();

	SimpleScriptMain.LOG.info("START: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());

	Field[] fields = Keywords.class.getDeclaredFields();

	for (int i = 0; i < fields.length; i++) {
	    if (invalidCommands.contains(fields[i].getName())) {
		fail("UNEXPECTED BEHAVIOR: Invalid command recognized in fieldlist!");
	    }
	}

	SimpleScriptMain.LOG.info("END: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());
    }
}
