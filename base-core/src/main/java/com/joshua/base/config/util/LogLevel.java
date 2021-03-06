package com.joshua.base.config.util;


import ch.qos.logback.classic.Level;

/**
 * Created by joshua on 3/29/17.
 */
public enum  LogLevel {
    ALL(Level.ALL), TRACE(Level.TRACE), DEBUG(Level.DEBUG), INFO(Level.INFO), WARN(Level.WARN), ERROR(Level.ERROR), OFF(Level.OFF);

    private final Level level;

    private LogLevel(Level level){
        this.level = level;
    }

    public Level value(){
        return level;
    }
}
