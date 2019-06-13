package model;

import java.util.ArrayList;

import model.ItemOtherType.VoidItem;
import model.ItemUsableType.Shovel;

public class Craft {

	private final String RECIPES = "src/resources/other/Recipes.txt";
	private String recipes;

	public Craft() {
		this.recipes = getRecipes();
	}

	public boolean isCraftable(int id) {
		ArrayList<Boolean> boolOfRecipe = new ArrayList<>();

		for(int recipe = 0; recipe < MathDataBuilder.countLinesinFiles(RECIPES)+1; recipe++){

			//Take the line of the recipe
			String line="";
			loop : for(int c = recipe; c < recipes.length(); c++) {
				line=line+recipes.charAt(c);
				if(recipes.charAt(c) == '.') {
					break loop;
				}
			}

			//Take the id from the line
			String idItemCraft = "";
			ArrayList<String> idItemNeeded = new ArrayList<>();
			ArrayList<String> quantityItemNeeded = new ArrayList<>();
			int nbOfComa = 0;
			loop : for(int c = 0; c < line.length(); c++) {				
				switch (nbOfComa) {

				case 2: 
					quantityItemNeeded.add(new String(""+line.charAt(c)));
					if(line.charAt(c+1) == '.') {
						break loop;
					}
					else if(line.charAt(c+1) == ';') {
						nbOfComa = 1;
						c++;
					}
					break;

				case 1:
					idItemNeeded.add(new String(""+line.charAt(c)));
					nbOfComa++;
					c++;
					break;

				case 0:
					idItemCraft=idItemCraft+line.charAt(c);
					if(line.charAt(c+1) == ';') {
						nbOfComa++;
						c++;
					}
					break;

				default:
					break;
				}
			}

			if(Integer.parseInt(idItemCraft) == id) {
				for(int item = 0; item < idItemNeeded.size(); item++) {
					if(MathDataBuilder.world().getPlayer().getInventory().isInventoryContainsItem(Integer.parseInt(idItemNeeded.get(item)))) {
						if(MathDataBuilder.world().getPlayer().getInventory().getItembyId(Integer.parseInt(idItemNeeded.get(item))).getQuantity() >= Integer.parseInt(quantityItemNeeded.get(item))) {
							boolOfRecipe.add(true);
						}else {
							boolOfRecipe.add(false);
						}
					}else {
						boolOfRecipe.add(false);
					}
				}
			}


		}

		return isAllIngredientOk(boolOfRecipe);
	}
	
	public void craft(int id) {
		if(isCraftable(id)) {
			
			String idItemCraft = "";
			ArrayList<String> idItemNeeded = new ArrayList<>();
			ArrayList<String> quantityItemNeeded = new ArrayList<>();
			loopRecipe : for(int recipe = 0; recipe < MathDataBuilder.countLinesinFiles(RECIPES)+1; recipe++){

				//Take the line of the recipe
				String line="";
				loop : for(int c = recipe; c < recipes.length(); c++) {
					line=line+recipes.charAt(c);
					if(recipes.charAt(c) == '.') {
						break loop;
					}
				}

				//Take the id from the line
				int nbOfComa = 0;
				loop : for(int c = 0; c < line.length(); c++) {				
					switch (nbOfComa) {

					case 2: 
						quantityItemNeeded.add(new String(""+line.charAt(c)));
						if(line.charAt(c+1) == '.') {
							break loop;
						}
						else if(line.charAt(c+1) == ';') {
							nbOfComa = 1;
							c++;
						}
						break;

					case 1:
						idItemNeeded.add(new String(""+line.charAt(c)));
						nbOfComa++;
						c++;
						break;

					case 0:
						idItemCraft=idItemCraft+line.charAt(c);
						if(line.charAt(c+1) == ';') {
							nbOfComa++;
							c++;
						}
						break;

					default:
						break;
					}
				}
				
				if(Integer.parseInt(idItemCraft) == id) {
					break loopRecipe;
				}
			}
			
			if(Integer.parseInt(idItemCraft) == id) {
				for(int item = 0; item < idItemNeeded.size(); item++) {
					MathDataBuilder.world().getPlayer().getInventory().removeQuantity(MathDataBuilder.world().getPlayer().getInventory().getItembyId(Integer.parseInt(idItemNeeded.get(item))), Integer.parseInt(quantityItemNeeded.get(item)));
				}
				MathDataBuilder.world().getPlayer().getInventory().addItem(idToItem(id));
			}
		}
	}

	private Item idToItem(int id) {
		Item i = null;
		switch (id) {
		case 2:
			i = new Shovel(10);
			break;

		case 3:
			break;
		default:
			i = new VoidItem();
			break;
		}
		return i;
	}
	
	private boolean isAllIngredientOk(ArrayList<Boolean> listOfBool) {
		if(listOfBool.isEmpty() || listOfBool.contains(false)) 
			return false;
		return true;
	}

	private String getRecipes() {
		return MathDataBuilder.readFile(RECIPES);
	}
	
}
