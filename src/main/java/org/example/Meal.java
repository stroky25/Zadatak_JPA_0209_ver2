package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(
            mappedBy = "meal",
            cascade = CascadeType.ALL
    )
    private List<Ingredient> ingredients = new ArrayList<>();


    public Meal() {
    }


    public Meal(String name) {
        this.name = name;
    }


    public void addIngredient(Ingredient ingredient) {

        ingredients.add(ingredient);

        ingredient.setMeal(this);
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}