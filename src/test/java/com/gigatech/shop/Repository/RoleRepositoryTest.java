package com.gigatech.shop.Repository;

import com.gigatech.shop.Repository.RoleRepository;
import com.gigatech.shop.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testFindByAuthority() {
        // Arrange
        Role role = new Role();
        role.setAuthority("ROLE_USER");
        roleRepository.save(role);

        // Act
        Optional<Role> foundRoleOptional = roleRepository.findByAuthority("ROLE_USER");

        // Assert
        assertTrue(foundRoleOptional.isPresent());
        Role foundRole = foundRoleOptional.get();
        assertEquals("ROLE_USER", foundRole.getAuthority());
    }

    @Test
    public void testFindByAuthority_NotFound() {
        // Act
        Optional<Role> foundRoleOptional = roleRepository.findByAuthority("ROLE_NON_EXISTENT");

        // Assert
        assertTrue(foundRoleOptional.isEmpty());
    }

}
