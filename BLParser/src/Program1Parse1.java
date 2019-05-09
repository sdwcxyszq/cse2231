import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Put your name here
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to statement string of body of
     *          instruction at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        // TODO - fill in body
        tokens.dequeue();
        String name = tokens.dequeue();

        Reporter.assertElseFatalError(Tokenizer.isIdentifier(name),
                "Name of program is an identifier");
        Reporter.assertElseFatalError(tokens.dequeue().equals("IS"),
                "IS does not follow name");

        body.parseBlock(tokens);

        Reporter.assertElseFatalError(tokens.dequeue().equals("END"),
                "End of Program is not END");
        Reporter.assertElseFatalError(tokens.dequeue().equals(name),
                "Beginning/Ending names do not match");

        return name;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // Checks the beginning lines of the program for proper beginning tags,
        // a valid program name & proper ending (IS)
        Reporter.assertElseFatalError(tokens.dequeue().equals("PROGRAM"),
                "PROGRAM is not at the start of the program.");
        String programName = tokens.dequeue();

        Reporter.assertElseFatalError(!Tokenizer.isIdentifier(programName),
                "An identifier may not be a program name.");
        Reporter.assertElseFatalError(!programName.equals("PROGRAM"),
                "Program name cannot be 'program'");
        Reporter.assertElseFatalError(tokens.dequeue().equals("IS"),
                "IS doesn't follow name of program");

        // Creates a new context for instructions to be passed into.
        Map<String, Statement> newContext = this.newContext();

        while (tokens.front().equals("INSTRUCTION")) {
            Statement newBody = this.newBody();
            String newName = parseInstruction(tokens, newBody);
            for (Map.Pair<String, Statement> element : newContext) {
                Reporter.assertElseFatalError(!element.key().equals(newName),
                        "Instruction is already defined.");
            }
            newContext.add(newName, newBody);
        }

        Reporter.assertElseFatalError(tokens.dequeue().equals("BEGIN"),
                "Program does not start with BEGIN");

        Statement secondBody = this.newBody();
        secondBody.parseBlock(tokens);

        Reporter.assertElseFatalError(tokens.dequeue().equals("END"),
                "END is not at the end of the program.");
        Reporter.assertElseFatalError(tokens.dequeue().equals(programName),
                "The program name at the end doesn't match the name at the beginning.");
        Reporter.assertElseFatalError(
                tokens.front().equals("### END OF INPUT ###"),
                "End of input was not found");
        /*
         * Sets the program to the new, correct values
         */
        this.setName(programName);
        this.swapBody(secondBody);
        this.swapContext(newContext);
    }

    /*
     *
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
