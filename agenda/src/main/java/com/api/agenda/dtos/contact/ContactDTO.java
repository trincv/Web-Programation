package com.api.agenda.dtos.contact;

import java.util.List;

import com.api.agenda.dtos.telephone.TelephoneDTO;
import com.api.agenda.entities.Contact;

public record ContactDTO (

    String firstName,
    String lastName,
    String email,
    List<TelephoneDTO> numbers

) {
    public ContactDTO(Contact contact) {
        this(contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getNumbers().stream().map(TelephoneDTO::new).toList());
    }
}
