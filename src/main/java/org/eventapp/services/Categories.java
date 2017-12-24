package org.eventapp.services;

import java.util.Arrays;
import java.util.List;
public class Categories {

  private static final String Art = "Art";
  private static final String Causes = "Causes";
  private static final String Crafts = "Crafts";
  private static final String Dance = "Dance";
  private static final String Drinks = "Drinks";
  private static final String Film = "Film";
  private static final String Fitness = "Fitness";
  private static final String Food = "Food";
  private static final String Games = "Games";
  private static final String Health = "Health";
  private static final String Home = "Home";
  private static final String Literature = "Literature";
  private static final String Music = "Music";
  private static final String Networking = "Networking";
  private static final String Party = "Party";
  private static final String Religion = "Religion";
  private static final String Shopping = "Shopping";
  private static final String Sports = "Sports";
  private static final String Theatre = "Theatre";
  private static final String Wellness = "Wellness";

  private static List<String> allCategories = Arrays.asList(
    Art, Causes, Crafts, Dance, Drinks, Film, Fitness, Food, Games,
    Health, Home, Literature, Music, Networking, Party, Religion, Shopping, Sports, Theatre);

  public static List<String> getAllCategories() {
    return allCategories;
  }
}
