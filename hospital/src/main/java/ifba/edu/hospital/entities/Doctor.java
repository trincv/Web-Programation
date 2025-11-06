package ifba.edu.hospital.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ifba.edu.hospital.enums.Specialty;

public class Doctor {
    
    private String crm;
    private String name;
    private String email;
    private String cellphone;
    private Specialty specialty;
    private Adress adress;

    public Doctor() {}

    @JsonCreator
    public Doctor(@JsonProperty("crm") String crm, @JsonProperty("name") String name, @JsonProperty("email") String email, @JsonProperty("cellphone") String cellphone, @JsonProperty("specialty") Specialty specialty, @JsonProperty("adress") Adress adress) {
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.crm = crm;
        this.specialty = specialty;
        this.adress = adress;
    }



}
