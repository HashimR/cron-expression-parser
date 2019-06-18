# Cron Expression Parser

Parser that takes in a Cron Expression string and prints a human readable representation.

## Installation

Ensure [Java JDK](https://www.oracle.com/technetwork/java/javase/downloads/index.html) is installed.

## Run on Intellij

Import project folder and navigate to Parser.java.
To pass in cron string, click on 'Parser' (next to run) > Edit Configurations > Program agruments.

To test, navigate to ParserTest.java and run.

## Run From Command Line

Navigate to the directory containing Parser.java.

Compile the parser by executing:

```
$ javac Parser.java
```

Run the parser by executing:

```
$ java Parser.java INSERTCRON
```

replacing INSERTCRON with desired Cron string.

## Test

To run the JUnit tests, execute:

```
$ mvn test
```

## Current Limitations

* Solution requires more input validation, for each type of cron argument
* More complex variations of options such a 'L' and 'W' have not been implemented in this version
* In this solution every month has 31 days
* Test coverage can be improved