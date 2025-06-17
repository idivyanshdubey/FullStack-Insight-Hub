package com.interface1;

import java.util.logging.*;

class Car3 implements Vehicle4 {
private static final Logger logger = Logger.getLogger(Car3.class.getName());

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

@Override
public void start() {
logger.info("Car is starting.");
System.out.println("Car starts.");
}

public void run() {
logger.info("Car is running.");
System.out.println("running");
}
}
