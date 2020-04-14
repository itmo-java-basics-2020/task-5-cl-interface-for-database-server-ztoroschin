package ru.andrey.kvstorage.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

public class CreateDatabase implements DatabaseCommand {

    private final ExecutionEnvironment env;
    private final String databaseName;

    public CreateDatabase(ExecutionEnvironment env, String databaseName) {
        this.env = env;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() {
        if(env.getDatabase(databaseName).isPresent()) {
            return DatabaseCommandResult.error("Database is already exists");
        }

        return DatabaseCommandResult.success(databaseName);
    }
}
