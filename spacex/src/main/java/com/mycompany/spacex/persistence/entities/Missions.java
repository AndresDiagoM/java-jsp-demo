/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spacex.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author felip
 */
@Entity
@Table(name = "missions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Missions.findAll", query = "SELECT m FROM Missions m"),
    @NamedQuery(name = "Missions.findByDate", query = "SELECT m FROM Missions m WHERE m.date = :date"),
    @NamedQuery(name = "Missions.findByName", query = "SELECT m FROM Missions m WHERE m.name = :name"),
    @NamedQuery(name = "Missions.findByLaunchId", query = "SELECT m FROM Missions m WHERE m.launchId = :launchId"),
    @NamedQuery(name = "Missions.findByLaunchStatus", query = "SELECT m FROM Missions m WHERE m.launchStatus = :launchStatus")})
public class Missions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 24)
    @Column(name = "launch_id")
    private String launchId;
    @Size(max = 10)
    @Column(name = "launch_status")
    private String launchStatus;
    @JoinColumn(name = "rocket_id", referencedColumnName = "rocket_id")
    @ManyToOne
    private Rockets rocketId;
    @JoinColumn(name = "launchpad_id", referencedColumnName = "launchpad_id")
    @ManyToOne
    private Launchpads launchpadId;
    @JoinColumn(name = "payload_id", referencedColumnName = "payload_id")
    @ManyToOne
    private Payloads payloadId;

    public Missions() {
    }

    public Missions(String launchId) {
        this.launchId = launchId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLaunchId() {
        return launchId;
    }

    public void setLaunchId(String launchId) {
        this.launchId = launchId;
    }

    public String getLaunchStatus() {
        return launchStatus;
    }

    public void setLaunchStatus(String launchStatus) {
        this.launchStatus = launchStatus;
    }

    public Rockets getRocketId() {
        return rocketId;
    }

    public void setRocketId(Rockets rocketId) {
        this.rocketId = rocketId;
    }

    public Launchpads getLaunchpadId() {
        return launchpadId;
    }

    public void setLaunchpadId(Launchpads launchpadId) {
        this.launchpadId = launchpadId;
    }

    public Payloads getPayloadId() {
        return payloadId;
    }

    public void setPayloadId(Payloads payloadId) {
        this.payloadId = payloadId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (launchId != null ? launchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Missions)) {
            return false;
        }
        Missions other = (Missions) object;
        if ((this.launchId == null && other.launchId != null) || (this.launchId != null && !this.launchId.equals(other.launchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.spacex.persistence.entities.Missions[ launchId=" + launchId + " ]";
    }
    
}
