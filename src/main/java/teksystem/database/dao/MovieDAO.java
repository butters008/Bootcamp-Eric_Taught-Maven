package teksystem.database.dao;

import teksystem.database.entity.Actor;
import teksystem.database.entity.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class MovieDAO {
    private static final String PERSISTENCE_UNIT_NAME = "moviesdb";
    private static EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public Movie findById(Integer id){
        EntityManager entityMgr = emFactoryObj.createEntityManager();

        // Select everything from movie id = 1
        String sql = "SELECT m FROM Movie m WHERE m.id = :movieId";
        TypedQuery<Movie> query = entityMgr.createQuery(sql, Movie.class);
        query.setParameter("movieId", id);

        Movie result = query.getSingleResult();
        return result;
    }

    public List<Movie> findByName(String firstName){
        EntityManager entityMgr = emFactoryObj.createEntityManager();

        String sql = "SELECT m FROM Movie m WHERE m.firstName = :movieName";
        TypedQuery<Movie> query = entityMgr.createQuery(sql, Movie.class);
        query.setParameter("movieName", firstName);

        List<Movie> result = query.getResultList();
        return result;
    }

    public Movie save(Movie movie) {
        //Boiler Plate along with beginning the transaction
        EntityManager entityMgr = emFactoryObj.createEntityManager();
        entityMgr.getTransaction().begin();

        //Saves the actor to the DB
        entityMgr.persist(movie);

        // Commit the transaction
        entityMgr.getTransaction().commit();
        entityMgr.clear();

        return movie;
    }

    public Movie update(Movie movie) {
        //Boiler Plate along with beginning the transaction
        EntityManager entityMgr = emFactoryObj.createEntityManager();
        entityMgr.getTransaction().begin();

        //Saves the actor to the DB
        entityMgr.merge(movie);

        // Commit the transaction
        entityMgr.getTransaction().commit();
        entityMgr.clear();

        return movie;
    }

}
