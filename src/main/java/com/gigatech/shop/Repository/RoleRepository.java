package com.gigatech.shop.Repository;
// Repozytorium RoleRepository odpowiedzialne za operacje na encji Role.
import com.gigatech.shop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Metoda do pobierania roli po jej nazwie (authority).
    Optional<Role> findByAuthority(String authority);
}
