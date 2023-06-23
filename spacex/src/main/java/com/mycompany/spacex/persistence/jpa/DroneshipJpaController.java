/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spacex.persistence.jpa;

import com.mycompany.spacex.persistence.entities.Droneship;
import com.mycompany.spacex.persistence.jpa.exceptions.NonexistentEntityException;
import com.mycompany.spacex.persistence.jpa.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author felip
 */
public class DroneshipJpaController implements Serializable {

    public DroneshipJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Droneship droneship) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(droneship);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDroneship(droneship.getShipId()) != null) {
                throw new PreexistingEntityException("Droneship " + droneship + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Droneship droneship) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            droneship = em.merge(droneship);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = droneship.getShipId();
                if (findDroneship(id) == null) {
                    throw new NonexistentEntityException("The droneship with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Droneship droneship;
            try {
                droneship = em.getReference(Droneship.class, id);
                droneship.getShipId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The droneship with id " + id + " no longer exists.", enfe);
            }
            em.remove(droneship);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Droneship> findDroneshipEntities() {
        return findDroneshipEntities(true, -1, -1);
    }

    public List<Droneship> findDroneshipEntities(int maxResults, int firstResult) {
        return findDroneshipEntities(false, maxResults, firstResult);
    }

    private List<Droneship> findDroneshipEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Droneship.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Droneship findDroneship(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Droneship.class, id);
        } finally {
            em.close();
        }
    }

    public int getDroneshipCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Droneship> rt = cq.from(Droneship.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
