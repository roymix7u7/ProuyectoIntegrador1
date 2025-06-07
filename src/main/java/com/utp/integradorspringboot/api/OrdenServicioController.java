/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.integradorspringboot.api;

import com.utp.integradorspringboot.models.OrdenServicio;
import com.utp.integradorspringboot.repositories.OrdenServicioRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class OrdenServicioController {

    @Autowired
    OrdenServicioRepository repository;

    @GetMapping("/ordenes")
    public ResponseEntity<List<OrdenServicio>> getAll() {
        try {
            List<OrdenServicio> lista = new ArrayList<>();
            repository.findAll().forEach(lista::add);
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ordenes/{id}")
    public ResponseEntity<OrdenServicio> getById(@PathVariable("id") Integer id) {
        Optional<OrdenServicio> entidad = repository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/ordenes")
    public ResponseEntity<OrdenServicio> create(@RequestBody OrdenServicio entidad) {
        try {
            OrdenServicio nueva = repository.save(new OrdenServicio(
                null,
                entidad.getFecha(),
                entidad.getVehiculoId(),
                entidad.getUsuarioId(),
                entidad.getEstado(),
                entidad.getTotal()
            ));
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ordenes/{id}")
    public ResponseEntity<OrdenServicio> update(@PathVariable("id") Integer id, @RequestBody OrdenServicio entidad) {
        Optional<OrdenServicio> data = repository.findById(id);

        if (data.isPresent()) {
            OrdenServicio actual = data.get();
            actual.setFecha(entidad.getFecha());
            actual.setVehiculoId(entidad.getVehiculoId());
            actual.setUsuarioId(entidad.getUsuarioId());
            actual.setEstado(entidad.getEstado());
            actual.setTotal(entidad.getTotal());
            return new ResponseEntity<>(repository.save(actual), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/ordenes/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

