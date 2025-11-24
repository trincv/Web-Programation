package ifba.edu.hospital.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Roles {
    DOCTOR("Doctor"),
    PATIENT("Patient");

    private final String displayName;

    Roles(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return this.displayName;
    }

}
