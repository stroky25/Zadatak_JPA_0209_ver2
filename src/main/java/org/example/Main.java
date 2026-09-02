package org.example;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Kreiranje jela
        Meal pizza = new Meal("Pizza");

        // Kreiranje sastojaka
        Ingredient cheese = new Ingredient("Cheese");

        Ingredient tomato = new Ingredient("Tomato");

        Ingredient ham = new Ingredient("Ham");

        // Povezivanje sastojaka s jelom
        pizza.addIngredient(cheese);
        pizza.addIngredient(tomato);
        pizza.addIngredient(ham);

        // Spremanje u bazu
        em.getTransaction().begin();
        em.persist(pizza);
        em.getTransaction().commit();


        // Dohvaćanje svih jela
        List<Meal> meals = em.createQuery("SELECT m FROM Meal m", Meal.class).getResultList();


        // Ispis
        for (Meal meal : meals) {
            System.out.println("Jelo: " + meal.getName());
            for (Ingredient ingredient : meal.getIngredients()) {
                System.out.println(" - " + ingredient.getName());
            }
        }

        tx.commit();
        em.close();
        emf.close();
    }
}