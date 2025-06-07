/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.integradorspringboot.api;

import com.utp.integradorspringboot.models.Usuario;
import com.utp.integradorspringboot.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        try {
            List<Usuario> usuarios = new ArrayList<>();
            repository.findAll().forEach(usuarios::add);

            if (usuarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Integer id) {
        Optional<Usuario> usuarioData = repository.findById(id);

        if (usuarioData.isPresent()) {
            return new ResponseEntity<>(usuarioData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = repository.save(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioData = repository.findById(id);

        if (usuarioData.isPresent()) {
            Usuario _usuario = usuarioData.get();
            _usuario.setNomUsuario(usuario.getNomUsuario());
            _usuario.setNombres(usuario.getNombres());
            _usuario.setApellidoPaterno(usuario.getApellidoPaterno());
            _usuario.setApellidoMaterno(usuario.getApellidoMaterno());
            _usuario.setCorreo(usuario.getCorreo());
            _usuario.setContraseña(usuario.getContraseña());
            _usuario.setTelefono(usuario.getTelefono());
            _usuario.setDni(usuario.getDni());
            _usuario.setEstado(usuario.getEstado());
            return new ResponseEntity<>(repository.save(_usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("id") Integer id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
