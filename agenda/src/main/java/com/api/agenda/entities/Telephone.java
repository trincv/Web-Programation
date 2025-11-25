package com.api.agenda.entities;

import com.api.agenda.dtos.telephone.TelephoneFormDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "telephone")
public class Telephone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long telephoneId;

    private String number;
    private boolean principal;
    private TelephoneCategory category; 

    public Telephone(String number, boolean principal, TelephoneCategory category) {
        this.category = category;
        this.number = number;
        this.principal = principal;
    }

    public Telephone(TelephoneFormDTO telephoneForm) {
        this.category = telephoneForm.category();
        this.number = telephoneForm.number();
        this.principal = telephoneForm.principal();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public TelephoneCategory getCategory() {
        return category;
    }

    public void setCategory(TelephoneCategory category) {
        this.category = category;
    }
}
