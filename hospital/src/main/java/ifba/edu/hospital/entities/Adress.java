package ifba.edu.hospital.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Adress {
    
    private String place;
    private int number;
    private String complement;
    private String neighborhood;
    private String city;
    private String estate;
    private String cep;

    public Adress() {}

    @JsonCreator
    public Adress(@JsonProperty("place") String place, @JsonProperty("neighborhood") String neighborhood, 
                  @JsonProperty("city") String city, @JsonProperty("estate") String estate, @JsonProperty("cep") String cep, 
                  @JsonProperty("number") int number, @JsonProperty("complement") String complement) 
    {
        this.place = place;
        this.number = number;
        this.neighborhood = neighborhood;
        this.cep = cep;
        this.city = city;
        this.complement = complement;
        this.estate = estate;
    }

    public Adress(String place, String neighborhood, String city, String estate, String cep, String complement) {
        this.place = place;
        this.neighborhood = neighborhood;
        this.cep = cep;
        this.city = city;
        this.complement = complement;
        this.estate = estate;
    }

    public Adress(String place, String neighborhood, String city, String estate, String cep, int number) {
        this.place = place;
        this.number = number;
        this.neighborhood = neighborhood;
        this.cep = cep;
        this.city = city;
        this.estate = estate;
    }

    public Adress(String place, String neighborhood, String city, String estate, String cep) {
        this.place = place;
        this.neighborhood = neighborhood;
        this.cep = cep;
        this.city = city;
        this.estate = estate;
    }


}
