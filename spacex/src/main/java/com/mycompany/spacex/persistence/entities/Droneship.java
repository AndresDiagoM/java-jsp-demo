/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spacex.persistence.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author felip
 */
@Entity
@Table(name = "droneship")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Droneship.findAll", query = "SELECT d FROM Droneship d"),
    @NamedQuery(name = "Droneship.findByShipId", query = "SELECT d FROM Droneship d WHERE d.shipId = :shipId"),
    @NamedQuery(name = "Droneship.findByHomePort", query = "SELECT d FROM Droneship d WHERE d.homePort = :homePort"),
    @NamedQuery(name = "Droneship.findByName", query = "SELECT d FROM Droneship d WHERE d.name = :name"),
    @NamedQuery(name = "Droneship.findByType", query = "SELECT d FROM Droneship d WHERE d.type = :type"),
    @NamedQuery(name = "Droneship.findByRoles", query = "SELECT d FROM Droneship d WHERE d.roles = :roles"),
    @NamedQuery(name = "Droneship.findByActivity", query = "SELECT d FROM Droneship d WHERE d.activity = :activity"),
    @NamedQuery(name = "Droneship.findByMassKg", query = "SELECT d FROM Droneship d WHERE d.massKg = :massKg"),
    @NamedQuery(name = "Droneship.findByMassLb", query = "SELECT d FROM Droneship d WHERE d.massLb = :massLb")})
public class Droneship implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 24)
    @Column(name = "ship_id")
    private String shipId;
    @Size(max = 30)
    @Column(name = "home_port")
    private String homePort;
    @Size(max = 35)
    @Column(name = "name")
    private String name;
    @Size(max = 20)
    @Column(name = "type")
    private String type;
    @Size(max = 45)
    @Column(name = "roles")
    private String roles;
    @Size(max = 10)
    @Column(name = "activity")
    private String activity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mass_kg")
    private Float massKg;
    @Column(name = "mass_lb")
    private Float massLb;

    public Droneship() {
    }

    public Droneship(String shipId) {
        this.shipId = shipId;
    }

    public String getShipId() {
        return shipId;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId;
    }

    public String getHomePort() {
        return homePort;
    }

    public void setHomePort(String homePort) {
        this.homePort = homePort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Float getMassKg() {
        return massKg;
    }

    public void setMassKg(Float massKg) {
        this.massKg = massKg;
    }

    public Float getMassLb() {
        return massLb;
    }

    public void setMassLb(Float massLb) {
        this.massLb = massLb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shipId != null ? shipId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Droneship)) {
            return false;
        }
        Droneship other = (Droneship) object;
        if ((this.shipId == null && other.shipId != null) || (this.shipId != null && !this.shipId.equals(other.shipId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.spacex.persistence.entities.Droneship[ shipId=" + shipId + " ]";
    }
    
}
