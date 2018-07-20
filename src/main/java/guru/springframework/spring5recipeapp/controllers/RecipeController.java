package guru.springframework.spring5recipeapp.controllers;


import guru.springframework.spring5recipeapp.demain.Recipe;
import guru.springframework.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String getRecipe(Model model, @PathVariable("id") String id) {

        Recipe recipe = recipeService.findById(Long.decode(id));
        model.addAttribute("recipe", recipe);

        return "recipe/show";
    }
}
