package com.google.android.exoplayer.demo.util;

import android.util.Log;

public class Logger {

    private static LoggingMode mode = LoggingMode.testing;

    private String tag;
    public Logger(String tag) {
        this.tag = tag;
    }

    public void verbose(String string, Object... args) {
        if (mode != LoggingMode.release) {
            if (args != null) {
                verboseLog(getArgsString(string, args));
            } else {
                verboseLog(string);
            }
        }
    }

    private void verboseLog(String string) {
        if (mode == LoggingMode.debug) {
            Log.v(tag, string);
        } else if (mode == LoggingMode.testing) {
            System.out.println(string);
        }
    }

    public void debug(String string, Object... args) {
        if (mode != LoggingMode.release) {
            if (args != null) {
                debugLog(getArgsString(string, args));
            } else {
                debugLog(string);
            }
        }
    }

    private void debugLog(String string) {
        if (mode == LoggingMode.debug) {
            Log.d(tag, string);
        } else if (mode == LoggingMode.testing) {
            System.out.println(string);
        }
    }

    public void warn(String string, Object... args) {
        if (mode != LoggingMode.release) {
            if (args != null) {
                warnLog(getArgsString(string, args));
            } else {
                warnLog(string);
            }
        }
    }

    private void warnLog(String string) {
        if (mode == LoggingMode.debug) {
            Log.w(tag, string);
        } else if (mode == LoggingMode.testing) {
            System.out.println(string);
        }
    }

    public void error(String string, Object... args) {
        if (args != null) {
            errorLog(getArgsString(string, args));
        } else {
            errorLog(string);
        }
    }

    private void errorLog(String string) {
        if (mode == LoggingMode.testing) {
            System.out.println(string);
        } else {
            Log.e(tag, string);
        }
    }
    
    private String getArgsString(String startString, Object... args) {
        StringBuilder sb = new StringBuilder(startString);
        for (Object arg : args) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(arg.toString());
        }
        return sb.toString();
    }

    public static Logger getLogger(Class clazz) {
        return new Logger(clazz.getSimpleName());
    }

    public static void configureLogging(LoggingMode loggingMode) {
        mode = loggingMode;
    }

    public enum LoggingMode {
        debug,
        release,
        testing
    }
}
