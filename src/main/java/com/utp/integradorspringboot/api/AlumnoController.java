
package com.utp.integradorspringboot.api;

import com.utp.integradorspringboot.models.Alumno;
import com.utp.integradorspringboot.repositories.AlumnoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api")
public class AlumnoController {
    
    @Autowired
    AlumnoRepository repository;


    @GetMapping("/alumno")
    public ResponseEntity<List<Alumno>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Alumno> lista = new ArrayList<Alumno>();
            repository.findAll().forEach(lista::add);
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<Alumno> getById(@PathVariable("id") Long id) {
        Optional<Alumno> entidad = repository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/alumno")
    public ResponseEntity<Alumno> create(@RequestBody Alumno entidad) {
        try {
            Alumno _entidad = repository.save(new Alumno(null, entidad.getNombres(), entidad.getApellidos()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/alumno/{id}")
    public ResponseEntity<Alumno> update(@PathVariable("id") Long id, @RequestBody Alumno entidad) {
        Alumno _entidad = repository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setNombres(entidad.getNombres());
            _entidad.setApellidos(entidad.getApellidos());
            return new ResponseEntity<>(repository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/alumno/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
