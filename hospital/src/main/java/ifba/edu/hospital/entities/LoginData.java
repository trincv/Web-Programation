package ifba.edu.hospital.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ifba.edu.hospital.enums.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "loginData")
public class LoginData implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public LoginData() {}

    public LoginData(String userName, String password, Roles role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    @Override
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String getUsername() { return userName; }
    public void setName(String login) { this.userName = login; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         
        if (role == Roles.DOCTOR)
            return List.of(new SimpleGrantedAuthority("ROLE_DOCTOR"));

        else
            return List.of(new SimpleGrantedAuthority("ROLE_PATIENT"));
        
    }

}
