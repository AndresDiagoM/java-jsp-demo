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
@Table(name = "launchpads")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Launchpads.findAll", query = "SELECT l FROM Launchpads l"),
    @NamedQuery(name = "Launchpads.findByLaunchpadId", query = "SELECT l FROM Launchpads l WHERE l.launchpadId = :launchpadId"),
    @NamedQuery(name = "Launchpads.findByName", query = "SELECT l FROM Launchpads l WHERE l.name = :name"),
    @NamedQuery(name = "Launchpads.findByFullName", query = "SELECT l FROM Launchpads l WHERE l.fullName = :fullName"),
    @NamedQuery(name = "Launchpads.findByStatus", query = "SELECT l FROM Launchpads l WHERE l.status = :status"),
    @NamedQuery(name = "Launchpads.findByLocality", query = "SELECT l FROM Launchpads l WHERE l.locality = :locality"),
    @NamedQuery(name = "Launchpads.findByRegion", query = "SELECT l FROM Launchpads l WHERE l.region = :region"),
    @NamedQuery(name = "Launchpads.findByTimeZone", query = "SELECT l FROM Launchpads l WHERE l.timeZone = :timeZone"),
    @NamedQuery(name = "Launchpads.findByLatitude", query = "SELECT l FROM Launchpads l WHERE l.latitude = :latitude"),
    @NamedQuery(name = "Launchpads.findByLongitude", query = "SELECT l FROM Launchpads l WHERE l.longitude = :longitude")})
public class Launchpads implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 24)
    @Column(name = "launchpad_id")
    private String launchpadId;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 80)
    @Column(name = "full_name")
    private String fullName;
    @Size(max = 30)
    @Column(name = "status")
    private String status;
    @Size(max = 50)
    @Column(name = "locality")
    private String locality;
    @Size(max = 30)
    @Column(name = "region")
    private String region;
    @Size(max = 40)
    @Column(name = "TimeZone")
    private String timeZone;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Latitude")
    private Float latitude;
    @Column(name = "Longitude")
    private Float longitude;
    @OneToMany(mappedBy = "launchpadId")
    private List<Missions> missionsList;

    public Launchpads() {
    }

    public Launchpads(String launchpadId) {
        this.launchpadId = launchpadId;
    }

    public String getLaunchpadId() {
        return launchpadId;
    }

    public void setLaunchpadId(String launchpadId) {
        this.launchpadId = launchpadId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
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
        hash += (launchpadId != null ? launchpadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Launchpads)) {
            return false;
        }
        Launchpads other = (Launchpads) object;
        if ((this.launchpadId == null && other.launchpadId != null) || (this.launchpadId != null && !this.launchpadId.equals(other.launchpadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.spacex.persistence.entities.Launchpads[ launchpadId=" + launchpadId + " ]";
    }
    
}
