/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.DetalleServicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleServicioRepository extends JpaRepository<DetalleServicio, Long> {
}