package testdata;

import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * Test data for retrievement of key code - expected mappings for keys.
 * 
 * @author Georgi Iliev
 *
 */
public class RetrieveKeyTestData {

    private RetrieveKeyTestData() {
    }

    /**
     * Loads a collection of EXPECTED key mappings(key -> code ).
     * 
     * @param testData
     */
    public static void loadExpectedKeyMappings(Map<Character, Integer> testData) {
	testData.put('A', KeyEvent.VK_A);
	testData.put(' ', KeyEvent.VK_SPACE);
	testData.put('0', KeyEvent.VK_0);
	testData.put('=', KeyEvent.VK_EQUALS);
	testData.put('/', KeyEvent.VK_SLASH);
    }
}
