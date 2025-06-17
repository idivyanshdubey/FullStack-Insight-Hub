package com.interface1;

import java.util.logging.*;

public class Main {
private static final Logger logger = Logger.getLogger(Main.class.getName());

static {
try {
LogManager.getLogManager().reset();

FileHandler fileHandler = new FileHandler("core_java_programs.log", true);
fileHandler.setFormatter(new SimpleFormatter());
fileHandler.setLevel(Level.INFO);

ConsoleHandler consoleHandler = new ConsoleHandler();
consoleHandler.setFormatter(new SimpleFormatter());
consoleHandler.setLevel(Level.INFO);

logger.addHandler(fileHandler);
logger.addHandler(consoleHandler);
logger.setLevel(Level.INFO);
} catch (Exception e) {
logger.log(Level.SEVERE, "Logger setup failed", e);
}
}

public static void main(String[] args) {
logger.info("Main method started.");
Car3 c = new Car3();
c.start();
logger.info("Main method ended.");
}
}
