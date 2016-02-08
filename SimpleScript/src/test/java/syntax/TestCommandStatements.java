package syntax;

import java.util.List;

/**
 * Test data for command and command format validations.
 * 
 * @author Georgi Iliev
 *
 */
public class TestCommandStatements {

    private TestCommandStatements() {
    }

    /**
     * Loads a collection of VALID command statements, that are not case
     * sensitive.
     * 
     * @param validStatements
     */
    public static void loadValidStatements(List<String> validStatements) {
	validStatements.add("line #FF0000 400 400 320 400 0");
	validStatements.add("point #FFFFFF 320 320");
	validStatements.add("delay 3000");
	validStatements.add("open cmd.exe");
	validStatements.add("press CTRL+S");
	validStatements.add("click right");
	validStatements.add("move 1700 750");
	validStatements.add("text It`s alright.");
    }

    /**
     * Loads a collection of INVALID command statements, that are not case
     * sensitive.
     * 
     * @param invalidStatements
     */
    public static void loadInvalidStatements(List<String> invalidStatements) {
	invalidStatements.add("line2 #FF0000 400 400 320 400 0");
	invalidStatements.add("point1 #FFFFFF 320 320");
	invalidStatements.add("delay3 3000");
	invalidStatements.add("ope4n cmd.exe");
	invalidStatements.add("press CTRL+S");
	invalidStatements.add("click right");
	invalidStatements.add("move 1700 750");
	invalidStatements.add("text It`s alright.");
    }

}
