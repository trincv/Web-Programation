package com.api.agenda.dtos.telephone;

import com.api.agenda.entities.Telephone;
import com.api.agenda.entities.TelephoneCategory;

public record TelephoneDTO(

    String number,
    TelephoneCategory category

) {
    public TelephoneDTO(Telephone telephone) {
        this(telephone.getNumber(), telephone.getCategory());
    }
}
