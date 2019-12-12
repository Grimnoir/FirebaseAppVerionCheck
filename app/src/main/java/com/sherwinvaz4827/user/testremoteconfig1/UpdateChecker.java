package com.sherwinvaz4827.user.testremoteconfig1;

public class UpdateChecker {
    private String Version;

    public UpdateChecker() {
    }

    public UpdateChecker(String version) {
        this.Version = version;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }
}
