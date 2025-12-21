package ifba.edu.hospital.entities;

import ifba.edu.hospital.dtos.doctor.DoctorFormDTO;
import ifba.edu.hospital.enums.Specialty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor {
    
    @Id
    @Column(nullable = false, unique = true)
    private String crm;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cellphone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialty specialty;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true, referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_id", unique = true, referencedColumnName = "id")
    private LoginData login;

    public Doctor() { }

    public Doctor(String crm, String name, String email, String cellphone, Specialty specialty) {
        this.crm = crm;
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.specialty = specialty;
    }

    public Doctor(DoctorFormDTO doctor) {
        crm = doctor.crm();
        name = doctor.name();
        email = doctor.email();
        cellphone = doctor.cellphone();
        specialty = doctor.specialty();
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

    public LoginData getLogin() { return login; }
    public void setLogin(LoginData login) { this.login = login; }
}
