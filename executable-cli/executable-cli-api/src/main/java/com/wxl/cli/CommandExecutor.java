package com.wxl.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;

import java.io.PrintStream;
import java.util.List;

/**
 * Create by wuxingle on 2020/08/07
 * 命令执行
 */
public class CommandExecutor {

    private String name;

    private List<Command> commands;

    private CommandLineParser parser;

    private Options options;

    private CommandErrorHandler errorHandler;

    private PrintStream stdout;

    private PrintStream stderr;

    CommandExecutor(String name,
                    CommandLineParser parser,
                    List<Command> commands,
                    CommandErrorHandler errorHandler,
                    PrintStream stdout,
                    PrintStream stderr) {
        this.name = name;
        this.parser = parser;
        this.commands = commands;
        this.errorHandler = errorHandler;
        this.stdout = stdout;
        this.stderr = stderr;
        this.options = new Options();

        for (Command command : commands) {
            this.options.addOption(command.option());
        }
    }

    /**
     * 执行
     *
     * @param args
     */
    public void execute(String[] args) {
        DefaultCommandContext context = new DefaultCommandContext();
        try {
            context.commandLine = parser.parse(options, args);

            new DefaultCommandChain().doNext(context);
        } catch (Throwable e) {
            errorHandler.handleError(context, e);
        }
    }


    private class DefaultCommandChain implements CommandChain {

        private int index = 0;

        @Override
        public void doNext(CommandContext context) {
            if (index < commands.size()) {
                commands.get(index++).execute(context, this);
            }
            index = 0;
        }
    }

    private class DefaultCommandContext implements CommandContext {

        private CommandLine commandLine;

        public DefaultCommandContext() {
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public CommandLine commandLine() {
            return commandLine;
        }

        @Override
        public Options options() {
            return options;
        }

        @Override
        public PrintStream stdout() {
            return stdout;
        }

        @Override
        public PrintStream stderr() {
            return stderr;
        }
    }

}

