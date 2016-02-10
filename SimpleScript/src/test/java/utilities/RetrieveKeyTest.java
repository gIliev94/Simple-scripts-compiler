package utilities;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import junit.framework.TestCase;
import simplescript.program.gui.SimpleScriptMain;
import simplescript.program.utilities.KeyMapper;
import simplescript.program.utilities.StringConstants;
import testdata.RetrieveKeyTestData;

/**
 * Unit test case for recognition of standart keyboard characters.
 * 
 * @author Georgi Iliev
 *
 */
public class RetrieveKeyTest extends TestCase {

    public void testRetrieveKey() {
	Map<Character, Integer> expectedKeyMappings = new HashMap<Character, Integer>(5);
	RetrieveKeyTestData.loadExpectedKeyMappings(expectedKeyMappings);

	int expectedKeyCode = 0;
	int retrievedKeyCode = 0;

	Character key;

	String testName = this.getClass().getSimpleName();
	SimpleScriptMain.LOG.info("START: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());

	for (Entry<Character, Integer> keyMapping : expectedKeyMappings.entrySet()) {
	    key = keyMapping.getKey();
	    expectedKeyCode = keyMapping.getValue();

	    retrievedKeyCode = KeyMapper.retrieveKeyCode(key);

	    if (expectedKeyCode != retrievedKeyCode) {
		fail("Invalid key code retrieved for key: " + StringConstants.quote(key.toString()));
	    }
	}

	SimpleScriptMain.LOG.info("END: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());
    }

}
