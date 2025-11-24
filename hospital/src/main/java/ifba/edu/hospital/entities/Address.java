package ifba.edu.hospital.entities;

import ifba.edu.hospital.dtos.address.AddressFormDTO;
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
    private Long id;

    private String place;
    private String neighborhood;
    private String city;
    private String estate;
    private String cep;
    private Integer number;
    private String complement;

    public Address() { }

    public Address(String place, String neighborhood, String city, String estate, String cep, Integer number, String complement) {
        this.place = place;
        this.number = number;
        this.neighborhood = neighborhood;
        this.cep = cep;
        this.city = city;
        this.complement = complement;
        this.estate = estate;
    }

    public Address(AddressFormDTO addressFormDTO) {
        this.place = addressFormDTO.place();
        this.number = addressFormDTO.number();
        this.neighborhood = addressFormDTO.neighborhood();
        this.cep = addressFormDTO.cep();
        this.city = addressFormDTO.city();
        this.complement = addressFormDTO.complement();
        this.estate = addressFormDTO.estate();
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

    public Integer getNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }

    public String getComplement() { return complement; }
    public void setComplement(String complement) { this.complement = complement; }

}
