/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spacex.persistence.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.spacex.persistence.entities.Rockets;
import com.mycompany.spacex.persistence.entities.Launchpads;
import com.mycompany.spacex.persistence.entities.Missions;
import com.mycompany.spacex.persistence.entities.Payloads;
import com.mycompany.spacex.persistence.jpa.exceptions.NonexistentEntityException;
import com.mycompany.spacex.persistence.jpa.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author felip
 */
public class MissionsJpaController implements Serializable {

    public MissionsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Missions missions) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rockets rocketId = missions.getRocketId();
            if (rocketId != null) {
                rocketId = em.getReference(rocketId.getClass(), rocketId.getRocketId());
                missions.setRocketId(rocketId);
            }
            Launchpads launchpadId = missions.getLaunchpadId();
            if (launchpadId != null) {
                launchpadId = em.getReference(launchpadId.getClass(), launchpadId.getLaunchpadId());
                missions.setLaunchpadId(launchpadId);
            }
            Payloads payloadId = missions.getPayloadId();
            if (payloadId != null) {
                payloadId = em.getReference(payloadId.getClass(), payloadId.getPayloadId());
                missions.setPayloadId(payloadId);
            }
            em.persist(missions);
//            if (rocketId != null) {
//                rocketId.getMissionsList().add(missions);
//                rocketId = em.merge(rocketId);
//            }
            if (launchpadId != null) {
                launchpadId.getMissionsList().add(missions);
                launchpadId = em.merge(launchpadId);
            }
            if (payloadId != null) {
                payloadId.getMissionsList().add(missions);
                payloadId = em.merge(payloadId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMissions(missions.getLaunchId()) != null) {
                throw new PreexistingEntityException("Missions " + missions + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Missions missions) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Missions persistentMissions = em.find(Missions.class, missions.getLaunchId());
            Rockets rocketIdOld = persistentMissions.getRocketId();
            Rockets rocketIdNew = missions.getRocketId();
            Launchpads launchpadIdOld = persistentMissions.getLaunchpadId();
            Launchpads launchpadIdNew = missions.getLaunchpadId();
            Payloads payloadIdOld = persistentMissions.getPayloadId();
            Payloads payloadIdNew = missions.getPayloadId();
            if (rocketIdNew != null) {
                rocketIdNew = em.getReference(rocketIdNew.getClass(), rocketIdNew.getRocketId());
                missions.setRocketId(rocketIdNew);
            }
            if (launchpadIdNew != null) {
                launchpadIdNew = em.getReference(launchpadIdNew.getClass(), launchpadIdNew.getLaunchpadId());
                missions.setLaunchpadId(launchpadIdNew);
            }
            if (payloadIdNew != null) {
                payloadIdNew = em.getReference(payloadIdNew.getClass(), payloadIdNew.getPayloadId());
                missions.setPayloadId(payloadIdNew);
            }
            missions = em.merge(missions);
//            if (rocketIdOld != null && !rocketIdOld.equals(rocketIdNew)) {
//                rocketIdOld.getMissionsList().remove(missions);
//                rocketIdOld = em.merge(rocketIdOld);
//            }
//            if (rocketIdNew != null && !rocketIdNew.equals(rocketIdOld)) {
//                rocketIdNew.getMissionsList().add(missions);
//                rocketIdNew = em.merge(rocketIdNew);
//            }
            if (launchpadIdOld != null && !launchpadIdOld.equals(launchpadIdNew)) {
                launchpadIdOld.getMissionsList().remove(missions);
                launchpadIdOld = em.merge(launchpadIdOld);
            }
            if (launchpadIdNew != null && !launchpadIdNew.equals(launchpadIdOld)) {
                launchpadIdNew.getMissionsList().add(missions);
                launchpadIdNew = em.merge(launchpadIdNew);
            }
            if (payloadIdOld != null && !payloadIdOld.equals(payloadIdNew)) {
                payloadIdOld.getMissionsList().remove(missions);
                payloadIdOld = em.merge(payloadIdOld);
            }
            if (payloadIdNew != null && !payloadIdNew.equals(payloadIdOld)) {
                payloadIdNew.getMissionsList().add(missions);
                payloadIdNew = em.merge(payloadIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = missions.getLaunchId();
                if (findMissions(id) == null) {
                    throw new NonexistentEntityException("The missions with id " + id + " no longer exists.");
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
            Missions missions;
            try {
                missions = em.getReference(Missions.class, id);
                missions.getLaunchId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The missions with id " + id + " no longer exists.", enfe);
            }
            Rockets rocketId = missions.getRocketId();
//            if (rocketId != null) {
//                rocketId.getMissionsList().remove(missions);
//                rocketId = em.merge(rocketId);
//            }
            Launchpads launchpadId = missions.getLaunchpadId();
            if (launchpadId != null) {
                launchpadId.getMissionsList().remove(missions);
                launchpadId = em.merge(launchpadId);
            }
            Payloads payloadId = missions.getPayloadId();
            if (payloadId != null) {
                payloadId.getMissionsList().remove(missions);
                payloadId = em.merge(payloadId);
            }
            em.remove(missions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Missions> findMissionsEntities() {
        return findMissionsEntities(true, -1, -1);
    }

    public List<Missions> findMissionsEntities(int maxResults, int firstResult) {
        return findMissionsEntities(false, maxResults, firstResult);
    }

    private List<Missions> findMissionsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Missions.class));
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

    public Missions findMissions(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Missions.class, id);
        } finally {
            em.close();
        }
    }

    public int getMissionsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Missions> rt = cq.from(Missions.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
