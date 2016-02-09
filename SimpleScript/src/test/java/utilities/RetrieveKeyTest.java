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
	Map<Character, Integer> testData = new HashMap<Character, Integer>(5);
	RetrieveKeyTestData.loadExpectedKeyMappings(testData);

	int expectedKeyCode = 0;
	int retrievedKeyCode = 0;

	String testName = this.getClass().getSimpleName();

	SimpleScriptMain.LOG.info("START: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());

	for (Entry<Character, Integer> testItem : testData.entrySet()) {
	    expectedKeyCode = testItem.getValue();

	    retrievedKeyCode = KeyMapper.retrieveKeyCode(testItem.getKey());

	    if (expectedKeyCode != retrievedKeyCode) {
		Character character = new Character(testItem.getKey());
		fail("Invalid key code retrieved for key: " + StringConstants.quote(character.toString()));
	    }
	}

	SimpleScriptMain.LOG.info("END: " + StringConstants.quote(testName) + " " + Calendar.getInstance().getTime());
    }

}
