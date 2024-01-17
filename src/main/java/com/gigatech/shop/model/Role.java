package com.gigatech.shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private long roleId;
    private String authority;

    // Konstruktor tworzący obiekt Role z podanym atrybutem authority
    public Role(String authority) {
        this.authority = authority;
    }
    // Konstruktor tworzący obiekt Role z podanymi atrybutami roleId i authority
    public Role(long roleId, String authority) {
        this.roleId = roleId;
        this.authority = authority;
    }

    // Implementacja metody interfejsu GrantedAuthority zwracającej role
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
