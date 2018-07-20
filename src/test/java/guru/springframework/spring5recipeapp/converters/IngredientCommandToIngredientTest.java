package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.demain.Ingredient;
import guru.springframework.spring5recipeapp.demain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "description";
    private static final BigDecimal AMOUNT = new BigDecimal(1);
    private static UnitOfMeasure UOM;

    private IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        UOM = new UnitOfMeasure();
        UOM.setId(ID_VALUE);
        UOM.setDescription(DESCRIPTION);
    }

    @Test
    public void testNullObjet() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void testConvert() {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setUom(UOM);

        //when
        Ingredient ingredient = converter.convert(ingredientCommand);

        //then
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM, ingredient.getUom());
    }
}