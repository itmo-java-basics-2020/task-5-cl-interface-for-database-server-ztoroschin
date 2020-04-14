package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    Optional<String> getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static DatabaseCommandResult success(String result) {
        return new DatabaseCommandResultImpl(DatabaseCommandStatus.SUCCESS, result);
    }

    static DatabaseCommandResult error(String message) {
        return new DatabaseCommandResultImpl(DatabaseCommandStatus.FAILED, message);
    }

    class DatabaseCommandResultImpl implements DatabaseCommandResult {
        private final DatabaseCommandStatus status;
        private final String value;

        private DatabaseCommandResultImpl(DatabaseCommandStatus status, String value) {
            this.status = status;
            this.value = value;
        }

        @Override
        public Optional<String> getResult() {
            if (isSuccess()) {
                return Optional.of(value);
            }

            return Optional.empty();
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return status.equals(DatabaseCommandStatus.SUCCESS);
        }

        @Override
        public Optional<String> getErrorMessage() {
            if (!isSuccess()) {
                return Optional.of(value);
            }

            return Optional.empty();
        }
    }
}