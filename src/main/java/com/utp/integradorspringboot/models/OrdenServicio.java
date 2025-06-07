/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.integradorspringboot.models;


import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ordenes_servicio")
public class OrdenServicio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha", length = 20)
    private String fecha;

    @Column(name = "vehiculo_id")
    private Integer vehiculoId;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "estado", length = 20)
    private String estado;

    @Column(name = "total")
    private Double total;

    public OrdenServicio() {
    }

    public OrdenServicio(Integer id, String fecha, Integer vehiculoId, Integer usuarioId, String estado, Double total) {
        this.id = id;
        this.fecha = fecha;
        this.vehiculoId = vehiculoId;
        this.usuarioId = usuarioId;
        this.estado = estado;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OrdenServicio)) {
            return false;
        }
        OrdenServicio other = (OrdenServicio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrdenServicio{" + "id=" + id + ", fecha=" + fecha + ", vehiculoId=" + vehiculoId + ", usuarioId=" + usuarioId + ", estado=" + estado + ", total=" + total + '}';
    }

}
