/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spacex.persistence.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author felip
 */
@Entity
@Table(name = "payloads")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payloads.findAll", query = "SELECT p FROM Payloads p"),
    @NamedQuery(name = "Payloads.findByPayloadId", query = "SELECT p FROM Payloads p WHERE p.payloadId = :payloadId"),
    @NamedQuery(name = "Payloads.findByName", query = "SELECT p FROM Payloads p WHERE p.name = :name"),
    @NamedQuery(name = "Payloads.findByType", query = "SELECT p FROM Payloads p WHERE p.type = :type"),
    @NamedQuery(name = "Payloads.findByReuse", query = "SELECT p FROM Payloads p WHERE p.reuse = :reuse"),
    @NamedQuery(name = "Payloads.findByManufacture", query = "SELECT p FROM Payloads p WHERE p.manufacture = :manufacture"),
    @NamedQuery(name = "Payloads.findByMassKg", query = "SELECT p FROM Payloads p WHERE p.massKg = :massKg"),
    @NamedQuery(name = "Payloads.findByMassLb", query = "SELECT p FROM Payloads p WHERE p.massLb = :massLb"),
    @NamedQuery(name = "Payloads.findByOrbit", query = "SELECT p FROM Payloads p WHERE p.orbit = :orbit"),
    @NamedQuery(name = "Payloads.findByReferenceSystem", query = "SELECT p FROM Payloads p WHERE p.referenceSystem = :referenceSystem"),
    @NamedQuery(name = "Payloads.findByRegime", query = "SELECT p FROM Payloads p WHERE p.regime = :regime")})
public class Payloads implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 24)
    @Column(name = "payload_id")
    private String payloadId;
    @Size(max = 35)
    @Column(name = "name")
    private String name;
    @Size(max = 20)
    @Column(name = "type")
    private String type;
    @Size(max = 10)
    @Column(name = "reuse")
    private String reuse;
    @Size(max = 40)
    @Column(name = "manufacture")
    private String manufacture;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mass_kg")
    private Float massKg;
    @Column(name = "mass_lb")
    private Float massLb;
    @Size(max = 10)
    @Column(name = "orbit")
    private String orbit;
    @Size(max = 30)
    @Column(name = "reference_system")
    private String referenceSystem;
    @Size(max = 30)
    @Column(name = "regime")
    private String regime;
    @OneToMany(mappedBy = "payloadId")
    private List<Missions> missionsList;

    public Payloads() {
    }

    public Payloads(String payloadId) {
        this.payloadId = payloadId;
    }

    public String getPayloadId() {
        return payloadId;
    }

    public void setPayloadId(String payloadId) {
        this.payloadId = payloadId;
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

    public String getReuse() {
        return reuse;
    }

    public void setReuse(String reuse) {
        this.reuse = reuse;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
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

    public String getOrbit() {
        return orbit;
    }

    public void setOrbit(String orbit) {
        this.orbit = orbit;
    }

    public String getReferenceSystem() {
        return referenceSystem;
    }

    public void setReferenceSystem(String referenceSystem) {
        this.referenceSystem = referenceSystem;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    @XmlTransient
    public List<Missions> getMissionsList() {
        return missionsList;
    }

    public void setMissionsList(List<Missions> missionsList) {
        this.missionsList = missionsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (payloadId != null ? payloadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payloads)) {
            return false;
        }
        Payloads other = (Payloads) object;
        if ((this.payloadId == null && other.payloadId != null) || (this.payloadId != null && !this.payloadId.equals(other.payloadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.spacex.persistence.entities.Payloads[ payloadId=" + payloadId + " ]";
    }
    
}
