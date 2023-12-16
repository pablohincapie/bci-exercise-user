package com.bci.exercise.user.repository;

import com.bci.exercise.user.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByToken(String token);

    Usuario save(Usuario usuario);

    Optional<Usuario> findByEmail(String email);
}
