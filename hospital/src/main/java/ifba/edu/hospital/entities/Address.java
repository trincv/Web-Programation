package ifba.edu.hospital.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    
    private String place;
    private int number;
    private String complement;
    private String neighborhood;
    private String city;
    private String estate;
    private String cep;

    public Address() { }

    public Address(String place, String neighborhood, String city,String estate,String cep, int number, String complement) {
        this.place = place;
        this.number = number;
        this.neighborhood = neighborhood;
        this.cep = cep;
        this.city = city;
        this.complement = complement;
        this.estate = estate;
    }

    public Address(String place, String neighborhood, String city, String estate, String cep, String complement) {
        this.place = place;
        this.neighborhood = neighborhood;
        this.cep = cep;
        this.city = city;
        this.complement = complement;
        this.estate = estate;
    }

    public Address(String place, String neighborhood, String city, String estate, String cep, int number) {
        this.place = place;
        this.number = number;
        this.neighborhood = neighborhood;
        this.cep = cep;
        this.city = city;
        this.estate = estate;
    }

    public Address(String place, String neighborhood, String city, String estate, String cep) {
        this.place = place;
        this.neighborhood = neighborhood;
        this.cep = cep;
        this.city = city;
        this.estate = estate;
    }

    public Long getId() { return id; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getNeighborhood() { return neighborhood; }
    public void setNeighborhood(String neighborhood) { this.neighborhood = neighborhood; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getEstate() { return estate; }
    public void setEstate(String estate) { this.estate = estate; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public String getComplement() { return complement; }
    public void setComplement(String complement) { this.complement = complement; }

}
