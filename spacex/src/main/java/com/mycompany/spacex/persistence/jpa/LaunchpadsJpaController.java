/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spacex.persistence.jpa;

import com.mycompany.spacex.persistence.entities.Launchpads;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.spacex.persistence.entities.Missions;
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
public class LaunchpadsJpaController implements Serializable {

    public LaunchpadsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Launchpads launchpads) throws PreexistingEntityException, Exception {
        if (launchpads.getMissionsList() == null) {
            launchpads.setMissionsList(new ArrayList<Missions>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Missions> attachedMissionsList = new ArrayList<Missions>();
            for (Missions missionsListMissionsToAttach : launchpads.getMissionsList()) {
                missionsListMissionsToAttach = em.getReference(missionsListMissionsToAttach.getClass(), missionsListMissionsToAttach.getLaunchId());
                attachedMissionsList.add(missionsListMissionsToAttach);
            }
            launchpads.setMissionsList(attachedMissionsList);
            em.persist(launchpads);
            for (Missions missionsListMissions : launchpads.getMissionsList()) {
                Launchpads oldLaunchpadIdOfMissionsListMissions = missionsListMissions.getLaunchpadId();
                missionsListMissions.setLaunchpadId(launchpads);
                missionsListMissions = em.merge(missionsListMissions);
                if (oldLaunchpadIdOfMissionsListMissions != null) {
                    oldLaunchpadIdOfMissionsListMissions.getMissionsList().remove(missionsListMissions);
                    oldLaunchpadIdOfMissionsListMissions = em.merge(oldLaunchpadIdOfMissionsListMissions);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLaunchpads(launchpads.getLaunchpadId()) != null) {
                throw new PreexistingEntityException("Launchpads " + launchpads + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Launchpads launchpads) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Launchpads persistentLaunchpads = em.find(Launchpads.class, launchpads.getLaunchpadId());
            List<Missions> missionsListOld = persistentLaunchpads.getMissionsList();
            List<Missions> missionsListNew = launchpads.getMissionsList();
            List<Missions> attachedMissionsListNew = new ArrayList<Missions>();
            for (Missions missionsListNewMissionsToAttach : missionsListNew) {
                missionsListNewMissionsToAttach = em.getReference(missionsListNewMissionsToAttach.getClass(), missionsListNewMissionsToAttach.getLaunchId());
                attachedMissionsListNew.add(missionsListNewMissionsToAttach);
            }
            missionsListNew = attachedMissionsListNew;
            launchpads.setMissionsList(missionsListNew);
            launchpads = em.merge(launchpads);
            for (Missions missionsListOldMissions : missionsListOld) {
                if (!missionsListNew.contains(missionsListOldMissions)) {
                    missionsListOldMissions.setLaunchpadId(null);
                    missionsListOldMissions = em.merge(missionsListOldMissions);
                }
            }
            for (Missions missionsListNewMissions : missionsListNew) {
                if (!missionsListOld.contains(missionsListNewMissions)) {
                    Launchpads oldLaunchpadIdOfMissionsListNewMissions = missionsListNewMissions.getLaunchpadId();
                    missionsListNewMissions.setLaunchpadId(launchpads);
                    missionsListNewMissions = em.merge(missionsListNewMissions);
                    if (oldLaunchpadIdOfMissionsListNewMissions != null && !oldLaunchpadIdOfMissionsListNewMissions.equals(launchpads)) {
                        oldLaunchpadIdOfMissionsListNewMissions.getMissionsList().remove(missionsListNewMissions);
                        oldLaunchpadIdOfMissionsListNewMissions = em.merge(oldLaunchpadIdOfMissionsListNewMissions);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = launchpads.getLaunchpadId();
                if (findLaunchpads(id) == null) {
                    throw new NonexistentEntityException("The launchpads with id " + id + " no longer exists.");
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
            Launchpads launchpads;
            try {
                launchpads = em.getReference(Launchpads.class, id);
                launchpads.getLaunchpadId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The launchpads with id " + id + " no longer exists.", enfe);
            }
            List<Missions> missionsList = launchpads.getMissionsList();
            for (Missions missionsListMissions : missionsList) {
                missionsListMissions.setLaunchpadId(null);
                missionsListMissions = em.merge(missionsListMissions);
            }
            em.remove(launchpads);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Launchpads> findLaunchpadsEntities() {
        return findLaunchpadsEntities(true, -1, -1);
    }

    public List<Launchpads> findLaunchpadsEntities(int maxResults, int firstResult) {
        return findLaunchpadsEntities(false, maxResults, firstResult);
    }

    private List<Launchpads> findLaunchpadsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Launchpads.class));
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

    public Launchpads findLaunchpads(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Launchpads.class, id);
        } finally {
            em.close();
        }
    }

    public int getLaunchpadsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Launchpads> rt = cq.from(Launchpads.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
