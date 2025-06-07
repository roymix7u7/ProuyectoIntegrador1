/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.integradorspringboot.api;

import com.utp.integradorspringboot.models.DetalleServicio;
import com.utp.integradorspringboot.repositories.DetalleServicioRepository;
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
public class DetalleServicioController {

    @Autowired
    DetalleServicioRepository repository;

    @GetMapping("/detalleservicios")
    public ResponseEntity<List<DetalleServicio>> getAll() {
        try {
            List<DetalleServicio> lista = new ArrayList<>();
            repository.findAll().forEach(lista::add);

            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detalleservicios/{id}")
    public ResponseEntity<DetalleServicio> getById(@PathVariable("id") Long id) {
        Optional<DetalleServicio> data = repository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/detalleservicios")
    public ResponseEntity<DetalleServicio> create(@RequestBody DetalleServicio entidad) {
        try {
            DetalleServicio nuevo = repository.save(new DetalleServicio(
                    null,
                    entidad.getVehiculo(),
                    entidad.getServicio(),
                    entidad.getCantidad(),
                    entidad.getPrecioUnitario(),
                    entidad.getSubtotal()
            ));
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/detalleservicios/{id}")
    public ResponseEntity<DetalleServicio> update(@PathVariable("id") Long id, @RequestBody DetalleServicio entidad) {
        Optional<DetalleServicio> data = repository.findById(id);

        if (data.isPresent()) {
            DetalleServicio actual = data.get();
            actual.setVehiculo(entidad.getVehiculo());
            actual.setServicio(entidad.getServicio());
            actual.setCantidad(entidad.getCantidad());
            actual.setPrecioUnitario(entidad.getPrecioUnitario());
            actual.setSubtotal(entidad.getSubtotal());
            return new ResponseEntity<>(repository.save(actual), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/detalleservicios/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
