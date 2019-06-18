import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    // Test main
    @Test
    public void testValidInputString() {
        String[] args = {"*/15 0 1,15 * 1-5 /usr/bin/find"};
        assertDoesNotThrow(() -> {
            Parser.main(args);
        });
    }

    @Test
    public void testEmptyInputString() {
        String[] args = {};
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.main(args);
        });
    }

    @Test
    public void testInvalidInputString() {
        String[] args = {""};
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.main(args);
        });
    }


    // Test parseTime
    @Test
    public void testParseAllMinute() {
        assertEquals(Parser.parseTime("*", Parser.Type.MINUTE).length, 60);
    }

    @Test
    public void testParseValidSingleMinute() {
        assertEquals(Parser.parseTime("1", Parser.Type.MINUTE)[0], 1);
    }

    @Test
    public void testParseAnyMinute() {
        assertEquals(Parser.parseTime("?", Parser.Type.MINUTE).length, 0);
    }

    @Test
    public void testParseValidMinuteRange() {
        int[] range = {14, 15, 16};
        assertArrayEquals(Parser.parseTime("14-16", Parser.Type.MINUTE), range);
    }

    @Test
    public void testParseValidMinuteIncrement() {
        int[] increments = {50, 55};
        assertArrayEquals(Parser.parseTime("50/5", Parser.Type.MINUTE), increments);
    }

    @Test
    public void testParseValidMinuteIncrementAll() {
        int[] increments = {0, 15, 30, 45};
        assertArrayEquals(Parser.parseTime("*/15", Parser.Type.MINUTE), increments);
    }

    @Test
    public void testParseValidMinuteLast() {
        assertEquals(Parser.parseTime("L", Parser.Type.MINUTE)[0], 59);
    }

    @Test
    public void testParseInvalidMinute() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseTime("test", Parser.Type.MINUTE);
        });
    }

    @Test
    public void testParseEmptyMinute() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseTime("", Parser.Type.MINUTE);
        });
    }


    @Test
    public void testParseAllHour() {
        assertEquals(Parser.parseTime("*", Parser.Type.HOUR).length, 24);
    }

    @Test
    public void testParseValidSingleHour() {
        assertEquals(Parser.parseTime("22", Parser.Type.HOUR)[0], 22);
    }

    @Test
    public void testParseAnyHour() {
        assertEquals(Parser.parseTime("?", Parser.Type.HOUR).length, 0);
    }

    @Test
    public void testParseValidHourRange() {
        int[] range = {2, 3, 4};
        assertArrayEquals(Parser.parseTime("2-4", Parser.Type.HOUR), range);
    }

    @Test
    public void testParseValidHourIncrement() {
        int[] increments = {3, 6, 9, 12, 15, 18, 21};
        assertArrayEquals(Parser.parseTime("3/3", Parser.Type.HOUR), increments);
    }

    @Test
    public void testParseValidHourIncrementAll() {
        int[] increments = {0, 3, 6, 9, 12, 15, 18, 21};
        assertArrayEquals(Parser.parseTime("*/3", Parser.Type.HOUR), increments);
    }

    @Test
    public void testParseValidHourLast() {
        assertEquals(Parser.parseTime("L", Parser.Type.HOUR)[0], 23);
    }

    @Test
    public void testParseInvalidHour() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseTime("test", Parser.Type.HOUR);
        });
    }

    @Test
    public void testParseEmptyHour() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseTime("", Parser.Type.HOUR);
        });
    }


    @Test
    public void testParseAllDayOfMonth() {
        assertEquals(Parser.parseTime("*", Parser.Type.DAYOFMONTH).length, 31);
    }

    @Test
    public void testParseValidSingleDayOfMonth() {
        assertEquals(Parser.parseTime("1", Parser.Type.DAYOFMONTH)[0], 1);
    }

    @Test
    public void testParseAnyDayOfMonth() {
        assertEquals(Parser.parseTime("?", Parser.Type.DAYOFMONTH).length, 0);
    }

    @Test
    public void testParseValidDayOfMonthRange() {
        int[] range = {2, 3, 4};
        assertArrayEquals(Parser.parseTime("2-4", Parser.Type.DAYOFMONTH), range);
    }

    @Test
    public void testParseValidDayOfMonthIncrement() {
        int[] increments = {3, 6, 9, 12, 15, 18, 21, 24, 27, 30};
        assertArrayEquals(Parser.parseTime("3/3", Parser.Type.DAYOFMONTH), increments);
    }

    @Test
    public void testParseValidDayOfMonthIncrementAll() {
        int[] increments = {1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31};
        assertArrayEquals(Parser.parseTime("*/3", Parser.Type.DAYOFMONTH), increments);
    }

    @Test
    public void testParseValidDayOfMonthLast() {
        assertEquals(Parser.parseTime("L", Parser.Type.DAYOFMONTH)[0], 31);
    }

    @Test
    public void testParseInvalidDayOfMonth() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseTime("test", Parser.Type.DAYOFMONTH);
        });
    }

    @Test
    public void testParseEmptyDayOfMonth() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseTime("", Parser.Type.DAYOFMONTH);
        });
    }


    @Test
    public void testParseAllMonth() {
        assertEquals(Parser.parseTime("*", Parser.Type.MONTH).length, 12);
    }

    @Test
    public void testParseValidSingleMonth() {
        assertEquals(Parser.parseTime("6", Parser.Type.MONTH)[0], 6);
    }

    @Test
    public void testParseAnyMonth() {
        assertEquals(Parser.parseTime("?", Parser.Type.MONTH).length, 0);
    }

    @Test
    public void testParseValidMonthRange() {
        int[] range = {2, 3, 4};
        assertArrayEquals(Parser.parseTime("2-4", Parser.Type.MONTH), range);
    }

    @Test
    public void testParseValidMonthIncrement() {
        int[] increments = {3, 6, 9, 12};
        assertArrayEquals(Parser.parseTime("3/3", Parser.Type.MONTH), increments);
    }

    @Test
    public void testParseValidMonthIncrementAll() {
        int[] increments = {1, 4, 7, 10};
        assertArrayEquals(Parser.parseTime("*/3", Parser.Type.MONTH), increments);
    }

    @Test
    public void testParseValidMonthLast() {
        assertEquals(Parser.parseTime("L", Parser.Type.MONTH)[0], 12);
    }

    @Test
    public void testParseInvalidMonth() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseTime("test", Parser.Type.MONTH);
        });
    }

    @Test
    public void testParseEmptyMonth() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseTime("", Parser.Type.MONTH);
        });
    }



    @Test
    public void testParseAllDayOfWeek() {
        assertEquals(Parser.parseTime("*", Parser.Type.DAYOFWEEK).length, 7);
    }

    @Test
    public void testParseValidSingleDayOfWeek() {
        assertEquals(Parser.parseTime("2", Parser.Type.DAYOFWEEK)[0], 2);
    }

    @Test
    public void testParseAnyDayOfWeek() {
        assertEquals(Parser.parseTime("?", Parser.Type.DAYOFWEEK).length, 0);
    }

    @Test
    public void testParseValidDayOfWeekRange() {
        int[] range = {2, 3, 4};
        assertArrayEquals(Parser.parseTime("2-4", Parser.Type.DAYOFWEEK), range);
    }

    @Test
    public void testParseValidDayOfWeekIncrement() {
        int[] increments = {3, 6};
        assertArrayEquals(Parser.parseTime("3/3", Parser.Type.DAYOFWEEK), increments);
    }

    @Test
    public void testParseValidDayOfWeekIncrementAll() {
        int[] increments = {1, 4, 7};
        assertArrayEquals(Parser.parseTime("*/3", Parser.Type.DAYOFWEEK), increments);
    }

    @Test
    public void testParseValidDayOfWeekLast() {
        assertEquals(Parser.parseTime("L", Parser.Type.DAYOFWEEK)[0], 7);
    }

    @Test
    public void testParseInvalidDayOfWeek() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseTime("test", Parser.Type.DAYOFWEEK);
        });
    }

    @Test
    public void testParseEmptyDayOfWeek() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseTime("", Parser.Type.DAYOFWEEK);
        });
    }

}
