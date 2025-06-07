/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.integradorspringboot.api;

import com.utp.integradorspringboot.models.Rol;
import com.utp.integradorspringboot.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api")
public class RolController {

    @Autowired
    private RolRepository repository;

    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> getAllRoles() {
        try {
            List<Rol> roles = new ArrayList<>();
            repository.findAll().forEach(roles::add);

            if (roles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable("id") Integer id) {
        Optional<Rol> rolData = repository.findById(id);

        if (rolData.isPresent()) {
            return new ResponseEntity<>(rolData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/roles")
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol) {
        try {
            Rol nuevoRol = repository.save(rol);
            return new ResponseEntity<>(nuevoRol, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<Rol> updateRol(@PathVariable("id") Integer id, @RequestBody Rol rol) {
        Optional<Rol> rolData = repository.findById(id);

        if (rolData.isPresent()) {
            Rol _rol = rolData.get();
            _rol.setNombre(rol.getNombre());
            return new ResponseEntity<>(repository.save(_rol), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<HttpStatus> deleteRol(@PathVariable("id") Integer id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
