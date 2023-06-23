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
import com.mycompany.spacex.persistence.entities.Missions;
import com.mycompany.spacex.persistence.entities.Payloads;
import com.mycompany.spacex.persistence.jpa.exceptions.NonexistentEntityException;
import com.mycompany.spacex.persistence.jpa.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author felip
 */
public class PayloadsJpaController implements Serializable {

    public PayloadsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Payloads payloads) throws PreexistingEntityException, Exception {
        if (payloads.getMissionsList() == null) {
            payloads.setMissionsList(new ArrayList<Missions>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Missions> attachedMissionsList = new ArrayList<Missions>();
            for (Missions missionsListMissionsToAttach : payloads.getMissionsList()) {
                missionsListMissionsToAttach = em.getReference(missionsListMissionsToAttach.getClass(), missionsListMissionsToAttach.getLaunchId());
                attachedMissionsList.add(missionsListMissionsToAttach);
            }
            payloads.setMissionsList(attachedMissionsList);
            em.persist(payloads);
            for (Missions missionsListMissions : payloads.getMissionsList()) {
                Payloads oldPayloadIdOfMissionsListMissions = missionsListMissions.getPayloadId();
                missionsListMissions.setPayloadId(payloads);
                missionsListMissions = em.merge(missionsListMissions);
                if (oldPayloadIdOfMissionsListMissions != null) {
                    oldPayloadIdOfMissionsListMissions.getMissionsList().remove(missionsListMissions);
                    oldPayloadIdOfMissionsListMissions = em.merge(oldPayloadIdOfMissionsListMissions);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPayloads(payloads.getPayloadId()) != null) {
                throw new PreexistingEntityException("Payloads " + payloads + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Payloads payloads) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Payloads persistentPayloads = em.find(Payloads.class, payloads.getPayloadId());
            List<Missions> missionsListOld = persistentPayloads.getMissionsList();
            List<Missions> missionsListNew = payloads.getMissionsList();
            List<Missions> attachedMissionsListNew = new ArrayList<Missions>();
            for (Missions missionsListNewMissionsToAttach : missionsListNew) {
                missionsListNewMissionsToAttach = em.getReference(missionsListNewMissionsToAttach.getClass(), missionsListNewMissionsToAttach.getLaunchId());
                attachedMissionsListNew.add(missionsListNewMissionsToAttach);
            }
            missionsListNew = attachedMissionsListNew;
            payloads.setMissionsList(missionsListNew);
            payloads = em.merge(payloads);
            for (Missions missionsListOldMissions : missionsListOld) {
                if (!missionsListNew.contains(missionsListOldMissions)) {
                    missionsListOldMissions.setPayloadId(null);
                    missionsListOldMissions = em.merge(missionsListOldMissions);
                }
            }
            for (Missions missionsListNewMissions : missionsListNew) {
                if (!missionsListOld.contains(missionsListNewMissions)) {
                    Payloads oldPayloadIdOfMissionsListNewMissions = missionsListNewMissions.getPayloadId();
                    missionsListNewMissions.setPayloadId(payloads);
                    missionsListNewMissions = em.merge(missionsListNewMissions);
                    if (oldPayloadIdOfMissionsListNewMissions != null && !oldPayloadIdOfMissionsListNewMissions.equals(payloads)) {
                        oldPayloadIdOfMissionsListNewMissions.getMissionsList().remove(missionsListNewMissions);
                        oldPayloadIdOfMissionsListNewMissions = em.merge(oldPayloadIdOfMissionsListNewMissions);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = payloads.getPayloadId();
                if (findPayloads(id) == null) {
                    throw new NonexistentEntityException("The payloads with id " + id + " no longer exists.");
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
            Payloads payloads;
            try {
                payloads = em.getReference(Payloads.class, id);
                payloads.getPayloadId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The payloads with id " + id + " no longer exists.", enfe);
            }
            List<Missions> missionsList = payloads.getMissionsList();
            for (Missions missionsListMissions : missionsList) {
                missionsListMissions.setPayloadId(null);
                missionsListMissions = em.merge(missionsListMissions);
            }
            em.remove(payloads);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Payloads> findPayloadsEntities() {
        return findPayloadsEntities(true, -1, -1);
    }

    public List<Payloads> findPayloadsEntities(int maxResults, int firstResult) {
        return findPayloadsEntities(false, maxResults, firstResult);
    }

    private List<Payloads> findPayloadsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Payloads.class));
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

    public Payloads findPayloads(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Payloads.class, id);
        } finally {
            em.close();
        }
    }

    public int getPayloadsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Payloads> rt = cq.from(Payloads.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
