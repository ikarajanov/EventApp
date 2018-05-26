package org.eventapp.services;

import org.eventapp.enums.Category;

import java.util.Arrays;
import java.util.List;

public class Categories {

  private static List<String> allCategories = Arrays.asList(
    Category.Art.toString(), Category.Causes.toString(), Category.Crafts.toString(), Category.Dance.toString(),
    Category.Drinks.toString(), Category.Film.toString(), Category.Fitness.toString(), Category.Food.toString(),
    Category.Games.toString(), Category.Health.toString(), Category.Home.toString(), Category.Literature.toString(),
    Category.Music.toString(), Category.Networking.toString(), Category.Party.toString(), Category.Religion.toString(),
    Category.Shopping.toString(), Category.Sports.toString(), Category.Theatre.toString());

  public static List<String> getAllCategories() {
    return allCategories;
  }
}
