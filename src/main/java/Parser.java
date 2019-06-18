import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class Parser {

    /**
     * Type of argument
     */
    enum Type {
        MINUTE,
        HOUR,
        DAYOFMONTH,
        MONTH,
        DAYOFWEEK
    }

    /**
     * Stores range of values for type
     */
    private static final HashMap<Type, int[]> rangeForType = new HashMap<>();

    /**
     * Populate ranges of values
     */
    static {
        rangeForType.put(Type.MINUTE, IntStream.rangeClosed(0, 59).toArray());
        rangeForType.put(Type.HOUR, IntStream.rangeClosed(0, 23).toArray());
        rangeForType.put(Type.DAYOFMONTH, IntStream.rangeClosed(1, 31).toArray());
        rangeForType.put(Type.MONTH, IntStream.rangeClosed(1, 12).toArray());
        rangeForType.put(Type.DAYOFWEEK, IntStream.rangeClosed(1, 7).toArray());
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Only 1 cron string required");
        }

        // Split arguments
        String[] cronArguments = args[0].split(" ");

        if (cronArguments.length != 6) {
            throw new IllegalArgumentException("There must be 6 arguments within the cron string");
        }

        // Get array containing parsed intervals
        int[] minute = parseTime(cronArguments[0], Type.MINUTE);
        int[] hour = parseTime(cronArguments[1], Type.HOUR);
        int[] dayOfMonth = parseTime(cronArguments[2], Type.DAYOFMONTH);
        int[] month = parseTime(cronArguments[3], Type.MONTH);
        int[] dayOfWeek = parseTime(cronArguments[4], Type.DAYOFWEEK);

        // Print each time interval
        printTime("minute", minute);
        printTime("hour", hour);
        printTime("day of month", dayOfMonth);
        printTime("month", month);
        printTime("day of week", dayOfWeek);
        printCommand(cronArguments[5]);
    }

    /**]
     * Takes in a cron argument and converts it into an array of relevant time values
     * @param arg cron string
     * @param type type of time
     * @return array of parsed time values
     */
    public static int[] parseTime(String arg, Type type) {
        // Return whole range
        if (arg.equals("*")) {
            return rangeForType.get(type);
        }

        // Not specified so return empty array
        if (arg.equals("?")) {
            return new int[0];
        }

        // Check type of syntax passed and return relevant range
        if (arg.contains("-")) {
            return handleRange(arg, type);
        } else if (arg.contains(",")) {
            return handleValues(arg);
        } else if (arg.contains("/")) {
            return handleIncrements(arg, type);
        } else if (arg.contains("L")) {
            return handleLast(arg, type);
        } else if (arg.matches("\\d+")) {
            // If single number then return that nunmber
            return new int[] {Integer.parseInt(arg)};
        } else {
            // Any other arguments are not handled
            throw new IllegalArgumentException("Incorrect argument specified");
        }
    }

    /**
     * When '-' is passed in argument then get range of values
     * @param arg cron string
     * @param type type of time
     * @return array of integer values for range
     */
    public static int[] handleRange(String arg, Type type) {
        String[] values = arg.split("-");
        int start = Integer.parseInt(values[0]);
        int end = Integer.parseInt(values[1]);
        int[] range = rangeForType.get(type);
        // Days start at 1 so decrement pointers
        if (type == Type.DAYOFMONTH || type == Type.MONTH || type == Type.DAYOFWEEK) {
            start--;
            end--;
        }
        // Return slice of array in range
        return Arrays.copyOfRange(range, start, end+1);
    }

    /**
     * When times are separated by ',' then get times
     * @param arg cron string
     * @return array of integer times
     */
    public static int[] handleValues(String arg) {
        String[] values = arg.split(",");
        return Arrays.asList(values).stream().mapToInt(Integer::parseInt).toArray();
    }

    /**
     * Handles cron strings where '/' is used
     * @param arg cron string
     * @param type type of time
     * @return array of integers that increments as specified
     */
    public static int[] handleIncrements(String arg, Type type) {
        String[] values = arg.split("/");
        int start = 0; // starting value
        if (!values[0].equals("*")) {
            // when starting at a specific time
            start = Integer.parseInt(values[0]);
            // day values are 1 above array index
            if (type == Type.DAYOFMONTH || type == Type.MONTH || type == Type.DAYOFWEEK) {
                start--;
            }
        }
        // how much to increment by
        int increment = Integer.parseInt(values[1]);

        ArrayList<Integer> increments = new ArrayList<>();
        int[] range = rangeForType.get(type);

        // increment by specified amount and collect values
        while (start < range.length) {
            increments.add(range[start]);
            start += increment;
        }
        return increments.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Handles cron strings containing L
     * @param arg cron string
     * @param type type of time
     * @return returns last value of specified time type
     */
    public static int[] handleLast(String arg, Type type) {
        int[] range = rangeForType.get(type);
        return Arrays.copyOfRange(range, range.length-1, range.length);
    }

    /**
     * Prints times for a time type
     * @param type type of time
     * @param time array of integer values containing required times
     */
    public static void printTime(String type, int[] time) {
        System.out.printf("%-14s", type);
        int counter = 0;
        while (counter < time.length) {
            if (counter == time.length-1) {
                // Last value inserts new line
                System.out.print(time[counter] + "\n");
            } else {
                // Other values add a space
                System.out.print(time[counter] + " ");
            }
            counter++;
        }
    }

    /**
     * Prints specified commamd
     * @param command to print
     */
    public static void printCommand(String command) {
        System.out.printf("%-14s", "command");
        System.out.print(command + "\n");
    }
}
