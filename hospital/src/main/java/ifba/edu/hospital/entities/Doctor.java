package ifba.edu.hospital.entities;

import ifba.edu.hospital.enums.Specialty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "doctor")
public class Doctor {
    
    @Id
    @NotBlank(message = "The crm cannot be null")
    private String crm;

    @NotBlank(message = "The name cannot be null")
    private String name;

    @NotBlank(message = "The email cannot be null")
    private String email;

    @NotBlank(message = "The cellphone cannot be null")
    private String cellphone;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "The specialty cannot be null")
    private Specialty specialty;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true, referencedColumnName = "id")
    @NotBlank(message = "The address cannot be null")
    private Address address;

    public Doctor() { }

    public Doctor(String crm, String name, String email, String cellphone, Specialty specialty, Address address) {
        this.crm = crm;
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.specialty = specialty;
        this.address = address;
    }

    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCellphone() { return cellphone; }
    public void setCellphone(String cellphone) { this.cellphone = cellphone; }

    public Specialty getSpecialty() { return specialty; }
    public void setSpecialty(Specialty specialty) { this.specialty = specialty; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

}
