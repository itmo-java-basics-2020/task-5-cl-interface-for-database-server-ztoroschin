package ru.andrey.kvstorage.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;

public class CreateDatabaseCommand implements DatabaseCommand {

    private final ExecutionEnvironment env;
    private final String databaseName;

    public CreateDatabaseCommand(ExecutionEnvironment env, String databaseName) {
        this.env = env;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if(env.getDatabase(databaseName).isPresent()) {
            return DatabaseCommandResult.error("Database is already exists");
        }

        env.addDatabase(null);

        return DatabaseCommandResult.success(databaseName);
    }
}
