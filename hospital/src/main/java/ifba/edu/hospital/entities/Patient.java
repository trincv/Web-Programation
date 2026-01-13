package ifba.edu.hospital.entities;

import ifba.edu.hospital.dtos.patient.PatientFormDTO;
import ifba.edu.hospital.enums.Specialty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @Column(nullable = false)
    private String cpf;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cellphone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true, referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_id", unique = true, referencedColumnName = "id")
    private LoginData login;

    public Patient() {}

    public Patient(PatientFormDTO patient) {
        cpf = patient.cpf();
        name = patient.name();
        email = patient.email();
        cellphone = patient.cellphone();
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCellphone() { return cellphone; }
    public void setCellphone(String cellphone) { this.cellphone = cellphone; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public LoginData getLogin() { return login; }
    public void setLogin(LoginData login) { this.login = login; }
}
