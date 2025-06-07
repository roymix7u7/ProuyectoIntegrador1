/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.integradorspringboot.api;

import com.utp.integradorspringboot.models.Servicio;
import com.utp.integradorspringboot.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api")
public class ServicioController {

    @Autowired
    private ServicioRepository repository;

    @GetMapping("/servicios")
    public ResponseEntity<List<Servicio>> getAllServicios() {
        try {
            List<Servicio> servicios = new ArrayList<>();
            repository.findAll().forEach(servicios::add);

            if (servicios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(servicios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/servicios/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable("id") Integer id) {
        Optional<Servicio> servicioData = repository.findById(id);

        if (servicioData.isPresent()) {
            return new ResponseEntity<>(servicioData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/servicios")
    public ResponseEntity<Servicio> createServicio(@RequestBody Servicio servicio) {
        try {
            Servicio nuevoServicio = repository.save(servicio);
            return new ResponseEntity<>(nuevoServicio, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/servicios/{id}")
    public ResponseEntity<Servicio> updateServicio(@PathVariable("id") Integer id, @RequestBody Servicio servicio) {
        Optional<Servicio> servicioData = repository.findById(id);

        if (servicioData.isPresent()) {
            Servicio _servicio = servicioData.get();
            _servicio.setNombre(servicio.getNombre());
            _servicio.setDescripcion(servicio.getDescripcion());
            _servicio.setPrecio(servicio.getPrecio());
            return new ResponseEntity<>(repository.save(_servicio), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/servicios/{id}")
    public ResponseEntity<HttpStatus> deleteServicio(@PathVariable("id") Integer id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
