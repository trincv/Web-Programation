package com.api.agenda.dtos.user;

import java.util.List;

import com.api.agenda.entities.Contact;
import com.api.agenda.entities.User;


public record UserDTO(
    String username,
    List<Contact> contacts
) {
    public UserDTO(User user) {
        this(user.getUsername(), user.getContacts());
    }
}
