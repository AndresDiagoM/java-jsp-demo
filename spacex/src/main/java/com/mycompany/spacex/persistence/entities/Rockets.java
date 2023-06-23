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
@Table(name = "rockets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rockets.findAll", query = "SELECT r FROM Rockets r"),
    @NamedQuery(name = "Rockets.findByRocketId", query = "SELECT r FROM Rockets r WHERE r.rocketId = :rocketId"),
    @NamedQuery(name = "Rockets.findByName", query = "SELECT r FROM Rockets r WHERE r.name = :name"),
    @NamedQuery(name = "Rockets.findByType", query = "SELECT r FROM Rockets r WHERE r.type = :type"),
    @NamedQuery(name = "Rockets.findByActive", query = "SELECT r FROM Rockets r WHERE r.active = :active"),
    @NamedQuery(name = "Rockets.findByCountry", query = "SELECT r FROM Rockets r WHERE r.country = :country"),
    @NamedQuery(name = "Rockets.findByCompany", query = "SELECT r FROM Rockets r WHERE r.company = :company"),
    @NamedQuery(name = "Rockets.findByCostPerLaunch", query = "SELECT r FROM Rockets r WHERE r.costPerLaunch = :costPerLaunch")})
public class Rockets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 24)
    @Column(name = "rocket_id")
    private String rocketId;
    @Size(max = 20)
    @Column(name = "name")
    private String name;
    @Size(max = 10)
    @Column(name = "type")
    private String type;
    @Size(max = 10)
    @Column(name = "active")
    private String active;
    @Size(max = 40)
    @Column(name = "country")
    private String country;
    @Size(max = 20)
    @Column(name = "company")
    private String company;
    @Column(name = "cost_per_launch")
    private Integer costPerLaunch;
//    @OneToMany(mappedBy = "rocketId")
//    private List<Missions> missionsList;

    public Rockets() {
    }

    public Rockets(String rocketId) {
        this.rocketId = rocketId;
    }

    public String getRocketId() {
        return rocketId;
    }

    public void setRocketId(String rocketId) {
        this.rocketId = rocketId;
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getCostPerLaunch() {
        return costPerLaunch;
    }

    public void setCostPerLaunch(Integer costPerLaunch) {
        this.costPerLaunch = costPerLaunch;
    }

//    @XmlTransient
//    public List<Missions> getMissionsList() {
//        return missionsList;
//    }
//
//    public void setMissionsList(List<Missions> missionsList) {
//        this.missionsList = missionsList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rocketId != null ? rocketId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rockets)) {
            return false;
        }
        Rockets other = (Rockets) object;
        if ((this.rocketId == null && other.rocketId != null) || (this.rocketId != null && !this.rocketId.equals(other.rocketId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.spacex.persistence.entities.Rockets[ rocketId=" + rocketId + " ]";
    }
    
}
