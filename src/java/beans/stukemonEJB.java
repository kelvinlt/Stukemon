/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Pokemon;
import entities.Trainer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author x2382383c
 */
@Stateless
public class stukemonEJB {

    @PersistenceUnit
    EntityManagerFactory emf;

    public boolean insertarEntrenador(Trainer t) {
        if (!existeEntrenador(t)) {
            EntityManager em = emf.createEntityManager();
            em.persist(t);
            em.close();
            return true;
        }
        return false;
    }

    public boolean existeEntrenador(Trainer t) {
        EntityManager em = emf.createEntityManager();
        Trainer entrenadorEncontrado = em.find(Trainer.class, t.getName());
        em.close();
        return entrenadorEncontrado != null;
    }
    
public boolean insertarPokemon(Pokemon p) {
        EntityManager em = emf.createEntityManager();
        em.persist(p);
        em.close();
        return true;
    }
  

public List<Trainer> seleccionaEntrenador(){
    List<Trainer> listaEntrenador = emf.createEntityManager().createNamedQuery("Trainer.findAll").getResultList();
    List<Trainer> entrena = new ArrayList<>();
    for(Trainer entrenadorAhora:listaEntrenador){
        entrena.add(entrenadorAhora);     
    }
    return entrena;
}

public List<Pokemon> seleccionaPokemon(){
    List<Pokemon> listaPokemon = emf.createEntityManager().createNamedQuery("Pokemon.findAll").getResultList();
    List<Pokemon> poke = new ArrayList<>();
    for(Pokemon pokemonAhora:listaPokemon){
        poke.add(pokemonAhora);     
    }
    return poke;
}

public List<Trainer> seleccionaEntrenadorConPokemons(){
    List<Trainer> listaEntrenador = emf.createEntityManager().createNamedQuery("Trainer.findAll").getResultList();
    List<Trainer> entrena = new ArrayList<>();
    for(Trainer entrenadorAhora:listaEntrenador){
        if(entrenadorAhora.getPokemonCollection().size()<6){
        entrena.add(entrenadorAhora);
        }
    }
    return entrena;
}
    
//    public boolean encontrarEntrenador(String name){
//        return (Trainer) emf.createEntityManager().createNamedQuery("Trainer.findByName").setParameter("name", name).getSingleResult();
//                }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
