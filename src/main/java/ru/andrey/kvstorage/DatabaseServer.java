package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseCommands;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        // here you can place code which creates DatabaseServer instance by
        // passing mocked ExecutionEnvironment into it for testing purposes
    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText == null) {
            return DatabaseCommandResult.error("commandText mustn't be null");
        }

        String[] commandArray = commandText.split(" ");

        try {
            DatabaseCommands databaseCommands = DatabaseCommands.valueOf(commandArray[0]);
            String[] arguments = new String[commandArray.length - 1];
            System.arraycopy(commandArray, 1, arguments, 0, arguments.length);

            return databaseCommands.getCommand(env, arguments).execute();
        } catch (DatabaseException | IllegalArgumentException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}
