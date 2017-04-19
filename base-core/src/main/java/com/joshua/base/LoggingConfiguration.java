package com.joshua.base;


import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.joshua.base.config.builder.Builder;
import com.joshua.base.config.util.LogLevel;
import com.joshua.base.config.util.Size;
import com.sun.xml.internal.txw2.annotation.XmlElement;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlType;
import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

import java.util.Map;
import java.util.TimeZone;

/**
 * Created by joshua on 3/29/17.
 */

@XmlType
public class LoggingConfiguration {
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    @XmlType
    public static class ConsoleConfiguration {
        @NotNull
        private LogLevel level = LogLevel.ALL;

        @NotNull
        private Optional<String> format = Optional.absent();

        @NotNull
        private TimeZone timeZone = UTC;

        public ConsoleConfiguration() {
        }

        private ConsoleConfiguration(LogLevel level, Optional<String> format, TimeZone timeZone) {
            this.level = level;
            this.format = format;
            this.timeZone = timeZone;
        }

        @XmlElement
        public LogLevel getLevel(){
            return level;
        }

        @XmlElement
        public Optional<String> getFormat() {
            return format;
        }

        @XmlElement
        public TimeZone getTimeZone() {
            return timeZone;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ConsoleConfiguration that = (ConsoleConfiguration) o;

            if (level != that.level) return false;
            if (format != null ? !format.equals(that.format) : that.format != null) return false;
            return timeZone != null ? timeZone.equals(that.timeZone) : that.timeZone == null;

        }

        @Override
        public int hashCode() {
            int result = level != null ? level.hashCode() : 0;
            result = 31 * result + (format != null ? format.hashCode() : 0);
            result = 31 * result + (timeZone != null ? timeZone.hashCode() : 0);
            return result;
        }
    }

    @XmlType
    public static class FileConfiguration{
        @XmlType
        public static class ArchiveConfiguration{
            @NotNull
            private String namePattern;

            private int maxHistory = 30;

            @NotNull
            private Size maxFileSize = new Size(100,Size.Unit.MB);

            private boolean cleanHistoryOnStart = true;

            public ArchiveConfiguration() {
            }

            private ArchiveConfiguration(String namePattern, int maxHistory, Size maxFileSize, boolean cleanHistoryOnStart) {
                this.namePattern = namePattern;
                this.maxHistory = maxHistory;
                this.maxFileSize = maxFileSize;
                this.cleanHistoryOnStart = cleanHistoryOnStart;
            }

            @XmlElement
            public String getNamePattern() {
                return namePattern;
            }

            @XmlElement
            public int getMaxHistory() {
                return maxHistory;
            }

            @XmlElement
            public Size getMaxFileSize() {
                return maxFileSize;
            }

            @XmlElement
            public boolean isCleanHistoryOnStart() {
                return cleanHistoryOnStart;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                ArchiveConfiguration that = (ArchiveConfiguration) o;

                if (maxHistory != that.maxHistory) return false;
                if (cleanHistoryOnStart != that.cleanHistoryOnStart) return false;
                if (namePattern != null ? !namePattern.equals(that.namePattern) : that.namePattern != null)
                    return false;
                return maxFileSize != null ? maxFileSize.equals(that.maxFileSize) : that.maxFileSize == null;

            }

            @Override
            public int hashCode() {
                int result = namePattern != null ? namePattern.hashCode() : 0;
                result = 31 * result + maxHistory;
                result = 31 * result + (maxFileSize != null ? maxFileSize.hashCode() : 0);
                result = 31 * result + (cleanHistoryOnStart ? 1 : 0);
                return result;
            }
        }

        @NotNull
        private LogLevel level = LogLevel.ALL;

        @NotNull
        private Optional<String> format = Optional.absent();

        @NotNull
        private TimeZone timeZone = UTC;

        @NotNull
        private String filename;

        @NotNull
        private Optional<ArchiveConfiguration> archive = Optional.absent();

        public FileConfiguration() {
        }

        private FileConfiguration(LogLevel level, Optional<String> format, TimeZone timeZone, String filename, Optional<ArchiveConfiguration> archive) {
            this.level = level;
            this.format = format;
            this.timeZone = timeZone;
            this.filename = filename;
            this.archive = archive;
        }

        @XmlElement
        public LogLevel getLevel() {
            return level;
        }

        @XmlElement
        public Optional<String> getFormat() {
            return format;
        }

        @XmlElement
        public TimeZone getTimeZone() {
            return timeZone;
        }

        @XmlElement
        public String getFilename() {
            return filename;
        }

        @XmlElement
        public Optional<ArchiveConfiguration> getArchive() {
            return archive;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            FileConfiguration that = (FileConfiguration) o;

            if (level != that.level) return false;
            if (format != null ? !format.equals(that.format) : that.format != null) return false;
            if (timeZone != null ? !timeZone.equals(that.timeZone) : that.timeZone != null) return false;
            if (filename != null ? !filename.equals(that.filename) : that.filename != null) return false;
            return archive != null ? archive.equals(that.archive) : that.archive == null;

        }

        @Override
        public int hashCode() {
            int result = level != null ? level.hashCode() : 0;
            result = 31 * result + (format != null ? format.hashCode() : 0);
            result = 31 * result + (timeZone != null ? timeZone.hashCode() : 0);
            result = 31 * result + (filename != null ? filename.hashCode() : 0);
            result = 31 * result + (archive != null ? archive.hashCode() : 0);
            return result;
        }
    }

    @NotNull
    private LogLevel level = LogLevel.INFO;

    @NotNull
    private Map<String,LogLevel> loggers = ImmutableMap.of();

    @NotNull
    private Optional<ConsoleConfiguration> console = Optional.absent();

    @NotNull
    private Optional<FileConfiguration> file = Optional.absent();

    public LoggingConfiguration() {
    }

    private LoggingConfiguration(LogLevel level, Map<String, LogLevel> loggers, Optional<ConsoleConfiguration> console, Optional<FileConfiguration> file) {
        this.level = level;
        this.loggers = loggers;
        this.console = console;
        this.file = file;
    }

    @XmlElement
    public static TimeZone getUTC() {
        return UTC;
    }

    @XmlElement
    public LogLevel getLevel() {
        return level;
    }

    @XmlElement
    public Map<String, LogLevel> getLoggers() {
        return loggers;
    }

    @XmlElement
    public Optional<ConsoleConfiguration> getConsole() {
        return console;
    }

    @XmlElement
    public Optional<FileConfiguration> getFile() {
        return file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoggingConfiguration that = (LoggingConfiguration) o;

        if (level != that.level) return false;
        if (loggers != null ? !loggers.equals(that.loggers) : that.loggers != null) return false;
        if (console != null ? !console.equals(that.console) : that.console != null) return false;
        return file != null ? file.equals(that.file) : that.file == null;

    }

    @Override
    public int hashCode() {
        int result = level != null ? level.hashCode() : 0;
        result = 31 * result + (loggers != null ? loggers.hashCode() : 0);
        result = 31 * result + (console != null ? console.hashCode() : 0);
        result = 31 * result + (file != null ? file.hashCode() : 0);
        return result;
    }

    public static class LoggingConfigurationBuilder implements Builder<LoggingConfiguration> {
        private Configuration.ConfigurationBuilder parent;

        public class ConsoleConfigurationBuilder implements Builder<ConsoleConfiguration> {
            private LogLevel level = LogLevel.ALL;
            private Optional<String> format = absent();
            private TimeZone timeZone = TimeZone.getTimeZone("UTC");

            public ConsoleConfigurationBuilder() {
            }

            public ConsoleConfigurationBuilder level(LogLevel level) {
                this.level = level;
                return this;
            }

            public ConsoleConfigurationBuilder format(String format) {
                this.format = of(format);
                return this;
            }

            public ConsoleConfigurationBuilder timeZone(String timeZone) {
                this.timeZone = TimeZone.getTimeZone(timeZone);
                return this;
            }

            public ConsoleConfiguration build() {
                return new ConsoleConfiguration(level, format, timeZone);
            }

            public LoggingConfigurationBuilder end() {
                return LoggingConfigurationBuilder.this;
            }
        }

        public class FileConfigurationBuilder implements Builder<FileConfiguration> {
            private LogLevel level = LogLevel.ALL;
            private Optional<String> format = Optional.absent();
            private TimeZone timeZone = TimeZone.getTimeZone("UTC");
            private String filename;


        }
    }
}
