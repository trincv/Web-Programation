package com.api.agenda.entities;

import java.util.Date;
import java.util.List;

import com.api.agenda.dtos.contact.ContactFormDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;
    
    private String firstName;
    private String lastName;
    private String email;
    private Date creationDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "contactId")
    private List<Telephone> numbers;

    public Contact(String firstName, String lastName, String email, Date creationDate) {
        this.creationDate = creationDate;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact(ContactFormDTO contactForm) {
        this.email = contactForm.email();
        this.firstName = contactForm.firstName();
        this.lastName = contactForm.lastName();
        this.numbers = contactForm.numbers().stream().map(Telephone::new).toList();
    }

    public List<Telephone> getNumbers() { return numbers; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setNumbers(List<Telephone> numbers) {
        this.numbers = numbers;
    }
}

