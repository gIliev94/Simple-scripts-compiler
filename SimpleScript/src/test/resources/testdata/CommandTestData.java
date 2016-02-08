package testdata;

import java.util.List;
import java.util.Set;

/**
 * Test data for command and command format validations.
 * 
 * @author Georgi Iliev
 *
 */
public class CommandTestData {

    private CommandTestData() {
    }

    /**
     * Loads a collection of INVALID command keywords, that are not case
     * sensitive.
     * 
     * @param validStatements
     */
    public static void loadInvalidCommandKeywords(Set<String> commands) {
	commands.add("mock");
	commands.add("hover");
	commands.add("moovit");
	commands.add("terrorize");
	commands.add("crouch");
	commands.add("stay");
	commands.add("alter");
	commands.add("type");
    }

    /**
     * Loads a collection of INVALID command statements, that are not case
     * sensitive.
     * 
     * @param invalidStatements
     */
    public static void loadInvalidStatements(List<String> invalidStatements) {
	invalidStatements.add("line FF0000 400 400 320 400 0");
	invalidStatements.add("point #FFFFFF 320");
	invalidStatements.add("delay3 3000");
	invalidStatements.add("cmd.exe");
	invalidStatements.add("press CTRL+S twice");
	invalidStatements.add("click 3");
	invalidStatements.add("move 1700 750 200");
	invalidStatements.add("text It`s alright. 4424");
    }

}
