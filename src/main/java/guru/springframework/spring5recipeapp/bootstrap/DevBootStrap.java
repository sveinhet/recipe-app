package guru.springframework.spring5recipeapp.bootstrap;

import guru.springframework.spring5recipeapp.demain.*;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {
    RecipeRepository recipeRepository;
    CategoryRepository categoryRepository;
    UnitOfMeasureRepository unitOfMeasureRepository;

    public DevBootStrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setServings(4);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");


        guacamole.addIngredient(new Ingredient("ripe avocados", BigDecimal.valueOf(2), unitOfMeasureRepository.findByDescription("").get() ));
        guacamole.addIngredient(new Ingredient("kosher salt", BigDecimal.valueOf(0.6), unitOfMeasureRepository.findByDescription("Teaspoon").get() ));
        guacamole.addIngredient(new Ingredient("fresh lime juice or lemon juice", BigDecimal.valueOf(1), unitOfMeasureRepository.findByDescription("Tablespoon").get()));
        guacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", BigDecimal.valueOf(2), unitOfMeasureRepository.findByDescription("Tablespoon").get()));
        guacamole.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", BigDecimal.valueOf(1), unitOfMeasureRepository.findByDescription("").get()));
        guacamole.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", BigDecimal.valueOf(2), unitOfMeasureRepository.findByDescription("Tablespoon").get()));
        guacamole.addIngredient(new Ingredient("freshly grated black pepper", BigDecimal.valueOf(1), unitOfMeasureRepository.findByDescription("A dash of").get()));
        guacamole.addIngredient(new Ingredient("ripe tomatoes, seeds and pulp removed, chopped", BigDecimal.valueOf(0.5), unitOfMeasureRepository.findByDescription("").get()));

        guacamole.setNotes(new Notes("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl."));

        guacamole.getCategories().add(categoryRepository.findByDescription("Mexican").get());
        guacamole.getCategories().add(categoryRepository.findByDescription("American").get());

        recipes.add(guacamole);

        Recipe tacos = new Recipe();
        tacos.setDescription("Spicy Grilled Chicken Tacos");
        tacos.setPrepTime(20);
        tacos.setCookTime(15);
        tacos.setServings(4);
        tacos.setDifficulty(Difficulty.MODERATE);
        tacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        tacos.addIngredient(new Ingredient("ancho chili powder", BigDecimal.valueOf(2), unitOfMeasureRepository.findByDescription("Tablespoon").get()));
        tacos.addIngredient(new Ingredient("dried oregano", BigDecimal.valueOf(1), unitOfMeasureRepository.findByDescription("Teaspoon").get()));
        tacos.addIngredient(new Ingredient("dried cumin", BigDecimal.valueOf(1), unitOfMeasureRepository.findByDescription("Teaspoon").get()));
        tacos.addIngredient(new Ingredient("sugar", BigDecimal.valueOf(1), unitOfMeasureRepository.findByDescription("Teaspoon").get()));
        tacos.addIngredient(new Ingredient("salt", BigDecimal.valueOf(0.5), unitOfMeasureRepository.findByDescription("Teaspoon").get()));
        tacos.addIngredient(new Ingredient("garlic, finely chopped", BigDecimal.valueOf(1), unitOfMeasureRepository.findByDescription("Clove").get()));
        tacos.addIngredient(new Ingredient("finely grated orange zest", BigDecimal.valueOf(1), unitOfMeasureRepository.findByDescription("Tablespoon").get()));
        tacos.addIngredient(new Ingredient("fresh-squeezed orange juice", BigDecimal.valueOf(3), unitOfMeasureRepository.findByDescription("Tablespoon").get()));
        tacos.addIngredient(new Ingredient("olive oil", BigDecimal.valueOf(2), unitOfMeasureRepository.findByDescription("Tablespoon").get()));
        tacos.addIngredient(new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)", BigDecimal.valueOf(4), unitOfMeasureRepository.findByDescription("").get()));

        tacos.getCategories().add(categoryRepository.findByDescription("Mexican").get());
        tacos.getCategories().add(categoryRepository.findByDescription("American").get());

        tacos.setNotes(new Notes("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings."));

        recipes.add(tacos);

        return recipes;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }
}
