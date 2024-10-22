package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ExportCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalFileTypes.FILE_TYPE_CSV;
import static seedu.address.testutil.TypicalFileTypes.FILE_TYPE_VCF;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) for {@code ExportCommand}.
 */
public class ExportCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_export_success() {
        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS, "CSV"),
            false, false);
        assertCommandSuccess(new ExportCommand("CSV"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void toCsvStringMethod() {
        ExportCommand exportCommand = new ExportCommand(FILE_TYPE_CSV);
        String expected = "Alice Pauline,94351253,alice@example.com,\"123, Jurong West Ave 6, #08-111\"" +
            ",\"friends\",\"High profile client, Likes dumplings\"";
        assertEquals(expected, exportCommand.toCsvString(ALICE));
    }

    @Test
    public void equals() {

        ExportCommand exportFirstCommand = new ExportCommand(FILE_TYPE_CSV);
        ExportCommand exportSecondCommand = new ExportCommand(FILE_TYPE_VCF);

        // same object -> returns true
        assertTrue(exportFirstCommand.equals(exportFirstCommand));

        // same values -> returns true
        ExportCommand exportFirstCommandCopy = new ExportCommand(FILE_TYPE_CSV);
        assertTrue(exportFirstCommand.equals(exportFirstCommandCopy));

        // different types -> returns false
        assertFalse(exportFirstCommand.equals(1));

        // null -> returns false
        assertFalse(exportFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(exportFirstCommand.equals(exportSecondCommand));
    }

    @Test
    public void toStringMethod() {
        ExportCommand firstExportCommand = new ExportCommand(FILE_TYPE_CSV);
        String firstExportExpected = ExportCommand.class.getCanonicalName() + "{fileType=" + FILE_TYPE_CSV + "}";
        assertEquals(firstExportExpected, firstExportCommand.toString());

        ExportCommand secondExportCommand = new ExportCommand(FILE_TYPE_VCF);
        String secondExportExpected = ExportCommand.class.getCanonicalName() + "{fileType=" + FILE_TYPE_VCF + "}";
        assertEquals(secondExportExpected, secondExportCommand.toString());
    }
}
