package model;

import model.ItemOtherType.VoidItem;
import model.ItemPlaceableType.BlockDirt;
import model.ItemUsableType.Shovel;

import java.util.ArrayList;

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
                default:
                    return itemNeed;
            }

            itemNeed += MathDataBuilder.world().getPlayer().getInventory().getItembyId(Integer.parseInt(idItemNeeded.get(i))).getQuantity() + "/" + quantityItemNeeded.get(i);
        }
        return itemNeed;
    }

    private void setLine(String line) {
        this.line = line;
    }

    private Item idToItem(int id) {
        Item i = null;
        switch (id) {
            case 1:
                i = new BlockDirt(0, 0);
                break;
            case 100:
                i = new Shovel(10);
                break;
            default:
                i = new VoidItem();
                break;
        }
        return i;
    }

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
