package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.demain.Recipe;

import java.util.ArrayList;
import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
