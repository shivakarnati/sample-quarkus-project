// Author: Shivakrishna Karnati
// Step 1. javac Interview.java
// Step 2. java Interview 5 or desired number

import java.util.*;

abstract class Command {
    /*
        This abstract class is useful to execute the command, and undo the command.
        The methods can be executed in subclasses.
     */
    public abstract int execute(int value);
    public abstract int undo(int value);
}

class IncrementCommand extends Command {
    /*
        This class is useful to increase the value by 1 when executed,
        and it can also reverse to the original value when undo command is executed.
     */
    public int execute(int value) { return value + 1; }
    public int undo(int value) { return value - 1; }
}

class DecrementCommand extends Command {
    /*
        This class is useful to decrease the value by 1 when executed,
        and it can also reverse to the original value when undo command is executed.
     */
    public int execute(int value) { return value - 1; }
    public int undo(int value) { return value + 1; }
}

class DoubleCommand extends Command {
    /*
        This class is useful to double the value by multiplying with 2 when executed,
        and it can also reverse to the original value when undo command is executed.
     */
    public int execute(int value) { return value * 2; }
    public int undo(int value) { return value / 2; }
}

class RandAddCommand extends Command {
    /*
        This class is useful to generate the random value by generating using RandomGenerator,
        and it can also reverse to the original value when undo command is executed.
    */
    private int randomValue;
    private static Random rand = new Random();

    public int execute(int value) {
        randomValue = rand.nextInt(10) + 1; // To add a random number between 1 and 10
        return value + randomValue;
    }

    public int undo(int value) { return value - randomValue; }
}

class CommandProcessor {
    /*
        This class is helpful to store the command output,
        and also produces the result. It implements the abstract methods from class Command.
     */
    private int result;
    private Stack<Command> history = new Stack<>();

    public CommandProcessor(int initialValue) {
        this.result = initialValue;
    }

    public void executeCommand(Command command) {
        result = command.execute(result);
        history.push(command);
        System.out.println("Result: " + result);
    }

    public void undo() {
        if (!history.isEmpty()) {
            Command lastCommand = history.pop();
            result = lastCommand.undo(result);
            System.out.println("Undo executed. Result: " + result);
        } else {
            System.out.println("No commands to undo.");
        }
    }

    public void run() {
        /*
            This method is useful to accept the input from terminal/command line and
            to check to the input command.
         */
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("exit")) break;

            switch (input) {
                case "increment": executeCommand(new IncrementCommand()); break;
                case "decrement": executeCommand(new DecrementCommand()); break;
                case "double": executeCommand(new DoubleCommand()); break;
                case "randadd": executeCommand(new RandAddCommand()); break;
                case "undo": undo(); break;
                default: System.out.println("Unknown command."); break;
            }
        }
        scanner.close();
    }
}

public class Interview {
    public static void main(String[] args) {
        /*
            main()
         */
        if (args.length != 1) {
            System.out.println("Enter: java Interview <initial_value>");
            return;
        }
        int initialValue;
        try {
            initialValue = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
            return;
        }
        CommandProcessor processor = new CommandProcessor(initialValue);
        processor.run();
    }
}