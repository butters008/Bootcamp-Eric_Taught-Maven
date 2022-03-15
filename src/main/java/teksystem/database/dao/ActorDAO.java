package teksystem.database.dao;

import teksystem.database.entity.Actor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class ActorDAO {
    private static final String PERSISTENCE_UNIT_NAME = "moviesdb";
    private static EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public Actor findById(Integer id){
        EntityManager entityMgr = emFactoryObj.createEntityManager();

        // Select everything from actor id = 1
        String sql = "SELECT a FROM Actor a WHERE a.id = :actorId";
        TypedQuery<Actor> query = entityMgr.createQuery(sql, Actor.class);
        query.setParameter("actorId", id);

        Actor result = query.getSingleResult();
        return result;
    }

    public List<Actor> findByFirstName(String firstName){
        EntityManager entityMgr = emFactoryObj.createEntityManager();

        // select * from actors where first_name = matthew
        //We are grabbing var names from entity (Java Class)
        String sql = "SELECT a FROM Actor a WHERE a.firstName = :firstName";
        TypedQuery<Actor> query = entityMgr.createQuery(sql, Actor.class);
        query.setParameter("firstName", firstName);

        List<Actor> result = query.getResultList();
        return result;
    }

    public List<Actor> findByFirstNameAndLastName(String firstName, String lastName){
        EntityManager entityMgr = emFactoryObj.createEntityManager();

        // select * from actors where first_name = matthew
        //We are grabbing var names from entity (Java Class)
        String sql = "SELECT a FROM Actor a WHERE a.firstName = :firstName AND a.lastName = :lastName";
        TypedQuery<Actor> query = entityMgr.createQuery(sql, Actor.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);

        List<Actor> result = query.getResultList();
        return result;
    }

    public Actor save(Actor actor) {
        //Boiler Plate along with beginning the transaction
        EntityManager entityMgr = emFactoryObj.createEntityManager();
        entityMgr.getTransaction().begin();

        //Saves the actor to the DB
        entityMgr.persist(actor);

        // Commit the transaction
        entityMgr.getTransaction().commit();
        entityMgr.clear();

        return actor;
    }

    public Actor update(Actor actor) {
        //Boiler Plate along with beginning the transaction
        EntityManager entityMgr = emFactoryObj.createEntityManager();
        entityMgr.getTransaction().begin();

        //Saves the actor to the DB
        entityMgr.merge(actor);

        // Commit the transaction
        entityMgr.getTransaction().commit();
        entityMgr.clear();

        return actor;
    }

    public Actor delete(Actor actor) {
        //Boiler Plate along with beginning the transaction
        EntityManager entityMgr = emFactoryObj.createEntityManager();
        entityMgr.getTransaction().begin();

        //removes the actor to the DB
        entityMgr.remove(entityMgr.contains(actor) ?
        actor : entityMgr.merge(actor));

        // Commit the transaction
        entityMgr.getTransaction().commit();
        entityMgr.clear();

        return actor;
    }

    public void deleteById(Integer id){
        EntityManager entityMgr = emFactoryObj.createEntityManager();
        entityMgr.getTransaction().begin();

        // delete everything from actor id = 1
        String sql = "DELETE FROM Actor a WHERE a.id = :actorId";
        Query query = entityMgr.createQuery(sql);
        query.setParameter("actorId", id);
        query.executeUpdate();
        entityMgr.getTransaction().commit();
    }
}
