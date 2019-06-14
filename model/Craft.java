package model;

import model.ItemOtherType.VoidItem;
import model.ItemPlaceableType.BlockBrick;
import model.ItemUsableType.Pickaxe;
import model.ItemUsableType.Shovel;

import java.util.ArrayList;
/** Craft
 * This class permit to craft during the game, it uses the file "Recipe.txt" that can be updated without touching that class. 
 * The major function are isCraftable and Craft.
 * @author Damien
 */
public class Craft {

    private final String RECIPES = "src/resources/other/recipes.txt";
    private String recipes;
    private String line;
    private String idItemCraft;
    private ArrayList<String> idItemNeeded;
    private ArrayList<String> quantityItemNeeded;

    public Craft() {
        this.recipes = getRecipes();
        this.line = "";
        this.idItemCraft = "";
        this.idItemNeeded = new ArrayList<>();
        this.quantityItemNeeded = new ArrayList<>();
    }

    //Check if the player can craft a given item
    public boolean isCraftable(int id) {
        ArrayList<Boolean> boolOfRecipe = new ArrayList<>();
        int lineRecipePos = 0;
        for (int recipe = 0; recipe < MathDataBuilder.countLinesinFiles(RECIPES) + 1; recipe++) {

            //Take the line of the recipe
            lineRecipePos = getRecipeLine(lineRecipePos);

            //Take the id from the line
            getEachElementOfTheLine();

            //Test is its craftable
            if (Integer.parseInt(idItemCraft) == id) {
                for (int item = 0; item < idItemNeeded.size(); item++) {
                    //System.out.println(idItemNeeded.get(item));
                    if (MathDataBuilder.world().getPlayer().getInventory().isInventoryContainsItem(Integer.parseInt(idItemNeeded.get(item)))) {
                        if (MathDataBuilder.world().getPlayer().getInventory().getItembyId(Integer.parseInt(idItemNeeded.get(item))).getQuantity() >= Integer.parseInt(quantityItemNeeded.get(item))) {
                            boolOfRecipe.add(true);
                        } else {
                            boolOfRecipe.add(false);
                        }
                    } else {
                        boolOfRecipe.add(false);
                    }
                }
            }
        }
        return isAllIngredientOk(boolOfRecipe);
    }

    //Craft the given Item
    public void craft(int id) {
        if (isCraftable(id)) {
            int lineRecipe = 0;
            boolean isFinished = false;
            for (int recipe = 0; recipe < MathDataBuilder.countLinesinFiles(RECIPES) + 1 && !isFinished; recipe++) {

                //Take the line of the recipe
                lineRecipe = getRecipeLine(lineRecipe);

                //Take the id from the line
                getEachElementOfTheLine();

                if (Integer.parseInt(idItemCraft) == id) {
                    isFinished = true;
                }
            }
            //Craft
            for (int item = 0; item < idItemNeeded.size(); item++) {
                MathDataBuilder.world().getPlayer().getInventory().removeQuantity(MathDataBuilder.world().getPlayer().getInventory().getItembyId(Integer.parseInt(idItemNeeded.get(item))), Integer.parseInt(quantityItemNeeded.get(item)));
            }
            MathDataBuilder.world().getPlayer().getInventory().addItem(idToItem(id));
        }
    }

    //Method that return an ArrayList of Integer of Id of item in recipe
    public ArrayList<Integer> getIdRecipeCraftable() {
        ArrayList<Integer> idRecipes = new ArrayList<>();

        int lineRecipe = 0;
        for (int recipe = 0; recipe < MathDataBuilder.countLinesinFiles(RECIPES) + 1; recipe++) {

            lineRecipe = getRecipeLine(lineRecipe);

            String idItemCraft = "";
            boolean isFinished = false;
            for (int c = 0; c < line.length() && !isFinished; c++) {
                idItemCraft = idItemCraft + line.charAt(c);
                if (line.charAt(c + 1) == ';') {
                    isFinished = true;
                }
            }
            idRecipes.add(Integer.parseInt(idItemCraft));
        }

        return idRecipes;
    }

    //Return a String of Item and quantity needed for a given Item (It's for the view)
    public String itemNeedToString(int id) {
        int lineRecipe = 0;
        boolean isFinished = false;
        for (int recipe = 0; recipe < MathDataBuilder.countLinesinFiles(RECIPES) + 1 && !isFinished; recipe++) {

            //Take the line of the recipe
            lineRecipe = getRecipeLine(lineRecipe);

            //Take the id from the line
            getEachElementOfTheLine();

            if (Integer.parseInt(idItemCraft) == id) {
                isFinished = true;
            }
        }
        String itemNeed = "";
        for (int i = 0; i < idItemNeeded.size(); i++) {
            if (i > 0) {
                itemNeed += ", ";
            }
            switch (Integer.parseInt(idItemNeeded.get(i))) {
                case 1:
                    itemNeed += "Dirt: ";
                    break;
                case 2:
                    itemNeed += "Stone: ";
                    break;
                case 3:
                    itemNeed += "Wood: ";
                    break;
                case 100:
                    itemNeed += "Shovel: ";
                    break;
                case 101:
                    itemNeed += "Pickaxe: ";
                    break;
                default:
                    return itemNeed;
            }
            int q = 0;
            Item item = MathDataBuilder.world().getPlayer().getInventory().getItembyId(Integer.parseInt(idItemNeeded.get(i)));
            if (item != null)
                q = item.getQuantity();
                itemNeed += q + "/" + quantityItemNeeded.get(i);
        }
        return itemNeed;
    }

    private void setLine(String line) {
        this.line = line;
    }

    private Item idToItem(int id) {
        Item i = null;
        switch (id) {
            case 4:
                i = new BlockBrick(0, 0);
                break;
            case 100:
                i = new Shovel(10);
                break;
            case 101:
                i = new Pickaxe(10);
                break;
            default:
                i = new VoidItem(0, 0);
                break;
        }
        return i;
    }
    
    //Cut the line of the given recipe
    private int getRecipeLine(int lineRecipePos) {
        String line = "";
        boolean isFinished = false;
        for (int c = lineRecipePos; c < recipes.length() && !isFinished; c++) {
            line = line + recipes.charAt(c);
            lineRecipePos++;
            if (recipes.charAt(c) == '.')
                isFinished = true;
        }
        setLine(line);
        return lineRecipePos;
    }

    //Cut the line of the recipe in different part ID/IdOfItemNeeded/QuantityNeededForItem
    private void getEachElementOfTheLine() {
        int nbOfComa = 0;
        String idItemCraft = "";
        ArrayList<String> idItemNeeded = new ArrayList<>();
        ArrayList<String> quantityItemNeeded = new ArrayList<>();
        boolean isFinished = false;
        for (int c = 0; c < line.length() && !isFinished; c++) {
            switch (nbOfComa) {

                case 2:
                    quantityItemNeeded.add(new String("" + line.charAt(c)));
                    if (line.charAt(c + 1) == '.') {
                        isFinished = true;
                    } else if (line.charAt(c + 1) == ';') {
                        nbOfComa = 1;
                        c++;
                    }
                    break;

                case 1:
                    idItemNeeded.add(new String("" + line.charAt(c)));
                    if (line.charAt(c + 1) == ',') {
                        nbOfComa++;
                        c++;
                    }
                    break;

                case 0:
                    idItemCraft = idItemCraft + line.charAt(c);
                    if (line.charAt(c + 1) == ';') {
                        nbOfComa++;
                        c++;
                    }
                    break;

                default:
                    break;
            }
        }
        this.idItemCraft = idItemCraft;
        this.idItemNeeded = idItemNeeded;
        this.quantityItemNeeded = quantityItemNeeded;
    }

    //Check the ArrayList of boolean to know if all ingredient are present to craft
    private boolean isAllIngredientOk(ArrayList<Boolean> listOfBool) {
        if (listOfBool.isEmpty() || listOfBool.contains(false))
            return false;
        return true;
    }

    private String getRecipes() {
        String content = MathDataBuilder.readFile(RECIPES);
        content = content.replaceAll("\\s+", "");
        return content;
    }

}
