package ifba.edu.hospital.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Specialty {
    ORTOPEDY("Ortopedia"),
    CARDIOLOGY("Cardiologia"),
    GINECOLOGY("Ginecologia"),
    DERMATOLOGY("Dermatologia");

    private final String displayName;

    Specialty(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}
