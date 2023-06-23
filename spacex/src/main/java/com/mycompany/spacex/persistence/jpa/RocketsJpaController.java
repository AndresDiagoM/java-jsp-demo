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
import com.mycompany.spacex.persistence.entities.Rockets;
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
public class RocketsJpaController implements Serializable {

    public RocketsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rockets rockets) throws PreexistingEntityException, Exception {
//        if (rockets.getMissionsList() == null) {
//            rockets.setMissionsList(new ArrayList<Missions>());
//        }
        System.out.println("[RocketJPA] id: "+rockets.getRocketId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
//            List<Missions> attachedMissionsList = new ArrayList<Missions>();
//            for (Missions missionsListMissionsToAttach : rockets.getMissionsList()) {
//                missionsListMissionsToAttach = em.getReference(missionsListMissionsToAttach.getClass(), missionsListMissionsToAttach.getLaunchId());
//                attachedMissionsList.add(missionsListMissionsToAttach);
//            }
//            rockets.setMissionsList(attachedMissionsList);
            em.persist(rockets);
//            for (Missions missionsListMissions : rockets.getMissionsList()) {
//                Rockets oldRocketIdOfMissionsListMissions = missionsListMissions.getRocketId();
//                missionsListMissions.setRocketId(rockets);
//                missionsListMissions = em.merge(missionsListMissions);
//                if (oldRocketIdOfMissionsListMissions != null) {
//                    oldRocketIdOfMissionsListMissions.getMissionsList().remove(missionsListMissions);
//                    oldRocketIdOfMissionsListMissions = em.merge(oldRocketIdOfMissionsListMissions);
//                }
//            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRockets(rockets.getRocketId()) != null) {
                throw new PreexistingEntityException("Rockets " + rockets + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rockets rockets) throws NonexistentEntityException, Exception {
        System.out.println("[RocketJPA-edit] id: "+rockets.getRocketId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            //Rockets persistentRockets = em.find(Rockets.class, rockets.getRocketId());
//            List<Missions> missionsListOld = persistentRockets.getMissionsList();
//            List<Missions> missionsListNew = rockets.getMissionsList();
//            List<Missions> attachedMissionsListNew = new ArrayList<Missions>();
//            for (Missions missionsListNewMissionsToAttach : missionsListNew) {
//                missionsListNewMissionsToAttach = em.getReference(missionsListNewMissionsToAttach.getClass(), missionsListNewMissionsToAttach.getLaunchId());
//                attachedMissionsListNew.add(missionsListNewMissionsToAttach);
//            }
//            missionsListNew = attachedMissionsListNew;
//            rockets.setMissionsList(missionsListNew);
//            rockets = em.merge(rockets);
//            for (Missions missionsListOldMissions : missionsListOld) {
//                if (!missionsListNew.contains(missionsListOldMissions)) {
//                    missionsListOldMissions.setRocketId(null);
//                    missionsListOldMissions = em.merge(missionsListOldMissions);
//                }
//            }
//            for (Missions missionsListNewMissions : missionsListNew) {
//                if (!missionsListOld.contains(missionsListNewMissions)) {
//                    Rockets oldRocketIdOfMissionsListNewMissions = missionsListNewMissions.getRocketId();
//                    missionsListNewMissions.setRocketId(rockets);
//                    missionsListNewMissions = em.merge(missionsListNewMissions);
//                    if (oldRocketIdOfMissionsListNewMissions != null && !oldRocketIdOfMissionsListNewMissions.equals(rockets)) {
//                        oldRocketIdOfMissionsListNewMissions.getMissionsList().remove(missionsListNewMissions);
//                        oldRocketIdOfMissionsListNewMissions = em.merge(oldRocketIdOfMissionsListNewMissions);
//                    }
//                }
//            }
            rockets = em.merge(rockets);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = rockets.getRocketId();
                if (findRockets(id) == null) {
                    throw new NonexistentEntityException("The rockets with id " + id + " no longer exists.");
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
            Rockets rockets;
            try {
                rockets = em.getReference(Rockets.class, id);
                rockets.getRocketId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rockets with id " + id + " no longer exists.", enfe);
            }
//            List<Missions> missionsList = rockets.getMissionsList();
//            for (Missions missionsListMissions : missionsList) {
//                missionsListMissions.setRocketId(null);
//                missionsListMissions = em.merge(missionsListMissions);
//            }
            em.remove(rockets);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rockets> findRocketsEntities() {
        return findRocketsEntities(true, -1, -1);
    }

    public List<Rockets> findRocketsEntities(int maxResults, int firstResult) {
        return findRocketsEntities(false, maxResults, firstResult);
    }

    private List<Rockets> findRocketsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rockets.class));
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

    public Rockets findRockets(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rockets.class, id);
        } finally {
            em.close();
        }
    }

    public int getRocketsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rockets> rt = cq.from(Rockets.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
