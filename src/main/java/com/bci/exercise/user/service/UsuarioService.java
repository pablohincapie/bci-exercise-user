package com.bci.exercise.user.service;

import com.bci.exercise.user.dto.PhoneDTO;
import com.bci.exercise.user.dto.UsuarioDTO;
import com.bci.exercise.user.model.Phone;
import com.bci.exercise.user.model.Usuario;
import com.bci.exercise.user.repository.PhoneRepository;
import com.bci.exercise.user.repository.UsuarioRepository;
import com.bci.exercise.user.util.JwtTokenUtil;
import com.bci.exercise.user.util.Util;
import com.bci.exercise.user.util.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PhoneRepository phoneRepository;

    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioResponse = null;

        usuarioResponse = usuarioRepository.save(convertirUsuarioDTOToEntity(usuarioDTO));
        Phone phoneSave = convertirPhoneToEntity(usuarioDTO.getPhones(), usuarioResponse);
        phoneRepository.save(phoneSave);

        return usuarioResponse;
    }
    public Usuario convertirUsuarioDTOToEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setName(usuarioDTO.getName());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(PasswordEncryptor.encryptPassword(usuarioDTO.getPassword()));
        usuario.setToken(JwtTokenUtil.generateToken(usuarioDTO.getEmail()));
        return usuario;
    }
    public Phone convertirPhoneToEntity(List<PhoneDTO> listPhone, Usuario usuario) {
        Phone phone = new Phone();

        for (PhoneDTO phoneResult : listPhone) {
            Phone phoneEntity = new Phone();
            phoneEntity.setNumber(phoneResult.getNumber());
            phoneEntity.setCityCode(phoneResult.getCitycode());
            phoneEntity.setCountryCode(phoneResult.getContrycode());
            phoneEntity.setUsuario(usuario);
            phone = phoneEntity;
        }

        return phone;
    }
    public boolean isValidData(UsuarioDTO usuarioDTO) {
        boolean result = true;
        if (!Util.isValidEmail(usuarioDTO.getEmail()) || !Util.isValidPassword(usuarioDTO.getPassword())) {
            result = false;
        }
        return result;
    }
    public boolean existUsuario(String email) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        return usuarioOptional.isPresent();
    }
    public Optional<Usuario> getUsuariobyToken(String token) {

        Optional<Usuario> usuarioToken = usuarioRepository.findByToken(token);
        if (usuarioToken.isPresent()) {
            Usuario usuario = new Usuario();
            usuario.setId(usuarioToken.get().getId());
            usuario.setToken(JwtTokenUtil.generateToken(usuarioToken.get().getToken()));
            usuario.setEmail(usuarioToken.get().getEmail());
            usuario.setFechaCreacion(usuarioToken.get().getFechaCreacion());
            usuario.setName(usuarioToken.get().getName());
            usuario.setPassword(usuarioToken.get().getPassword());
            usuarioRepository.save(usuario);
        }
        return usuarioToken;
    }

}
