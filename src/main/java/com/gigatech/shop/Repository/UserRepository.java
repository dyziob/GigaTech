package com.gigatech.shop.Repository;
// Repozytorium UserRepository odpowiedzialne za operacje na encji User.
import com.gigatech.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Metoda do pobierania użytkownika po nazwie użytkownika.
    Optional<User> findByUsername(String username);
}
