package teksystem;

import teksystem.database.dao.ActorDAO;
import teksystem.database.dao.MovieDAO;
import teksystem.database.entity.Actor;
import teksystem.database.entity.Movie;

import java.util.List;

public class HibernateDemo {
    private ActorDAO actorDAO = new ActorDAO();
    private MovieDAO movieDAO = new MovieDAO();

    public void create(){
        Actor actor = new Actor();
        actor.setFirstName("First Name");
        actor.setLastName("Last Name");
        actor.setAge(123);
        System.out.println("Before Save : " + actor);
        actorDAO.save(actor);
        System.out.println("After Save : " + actor);
    }

    public void read(){
        int actorID = 3;
        Actor actor = actorDAO.findById(actorID);
        if(actor == null){
            System.out.println("Unable to find actor by id " + actorID);

        }
        else{
            System.out.println("Find by id " + actor.toString());
        }

        List<Actor> actors = actorDAO.findByFirstName("Matthew");
        if(actors.size() == 0){
            System.out.println("There are no names in the list ");
        }
        else{
            for(Actor a : actors){
                System.out.println("Find by first name: " + a);
            }
        }
    }

    public void readByFirstAndLastName(){
        List<Actor> actors = actorDAO.findByFirstNameAndLastName("Mark", "Hamil");
        if(actors.size() == 0){
            System.out.println("There are no names in the list ");
        }
        else{
            for(Actor a : actors){
                System.out.println("Find by first and last name: " + a);
            }
        }
    }

    public void update(){
        Actor actor = actorDAO.findById(3);
        System.out.println("Before update : " + actor);
        actor.setFirstName("This is");
        actor.setLastName("An Update");
        actor.setAge(123);
        actorDAO.update(actor);
        System.out.println("After update : " + actor);
    }

    public void deleteByEntityManager(){
        Actor actor = actorDAO.findById(6);
        actorDAO.delete(actor);
    }

    public void deleteById(){
        actorDAO.deleteById(1);
    }

    public void addActorToMovie(){
        Movie movie = movieDAO.findById(1);

        Actor actor = actorDAO.findById(2);
        actor.getMovies().add(movie);
        actorDAO.update(actor);

        Actor actor2 = actorDAO.findById(12);
        actor2.getMovies().add(movie);
        actorDAO.update(actor2);

        movie.getActors().add(actor);
        movie.getActors().add(actor2);

        movieDAO.update(movie);
    }

    private void work() {
//        create();
//        read();
//        update();
//        deleteByEntityManager();
//        deleteById();
//        readByFirstAndLastName();
        addActorToMovie();
    }

    public static void main(String[] args) {
        new HibernateDemo().work();
    }
}