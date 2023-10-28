package edu.project1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public final class Dictionary {
    public static final HashMap<String, Lines> WORDS = new HashMap<>() {{
        put("ANCHOR", new Lines("A heavy object used to keep a ship or", "boat in place."));
        put("APPLE", new Lines("A fruit known for its sweet taste,", "often consumed raw or used in cooking."));
        put("CANYON", new Lines("A deep gorge, typically one with a river", "flowing through it."));
        put("CASTLE", new Lines("A fortified building with towers", "and a moat, historically used for defense."));
        put("CIRCUS", new Lines("A place for entertainment featuring", "acrobats and clowns."));
        put("COFFEE", new Lines("A popular beverage brewed from roasted", "beans."));
        put("COMET", new Lines("A celestial object with a glowing tail", "that orbits the sun."));
        put("COTTAGE", new Lines("A small simple house typically one near", "a lake or beach."));
        put("DESERT", new Lines("A dry, arid region with little or no", "vegetation."));
        put("DIAMOND", new Lines("A precious gemstone known for its", "brilliant sparkle."));
        put("ELEPHANT", new Lines("A large, grey mammal known for its long", "trunk and tusks."));
        put("FLOWER", new Lines("The reproductive part of a plant known for", "its colorful and fragrant petals."));
        put("GUITAR", new Lines("A stringed musical instrument with a", "soundboard and frets."));
        put("HARBOR", new Lines("A sheltered coastal area for docking ships", "and boats."));
        put("ISLAND", new Lines("A piece of land completely surrounded by", "water."));
        put("LIBRARY", new Lines("A place where books and other reading", "materials are stored and accessed."));
        put("MOUNTAIN", new Lines("A large landform rising prominently above", "its surroundings"));
        put("ORCHARD", new Lines("An area of land devoted to the cultivation", "of fruit or nut trees."));
        put("RAINBOW", new Lines("A meteorological phenomenon featuring a", "spectrum of colors in the sky."));
        put("RIVER", new Lines("A natural watercourse that flows towards", "an ocean or sea."));
        put("ROBOT", new Lines("A machine capable of performing tasks", "autonomously."));
        put("SAILBOAT", new Lines("A boat propelled by sails;", "often used for pleasure trips or racing."));
        put("TIGER", new Lines("A large, striped big cat known for its", "strength and agility."));
        put("VIOLIN", new Lines("A musical instrument with strings that", "are played with a bow."));
        put("WOMBAT", new Lines("An Australian marsupial that resembles", "a small bear with short legs."));
        put("ZOO", new Lines("An establishment where various animals", "are exhibited to the public."));

    }};

    private Dictionary() {
    }

    public static String getRandomWord() {
        var random = new Random();
        var keys = new ArrayList<>(Dictionary.WORDS.keySet());

        return keys.get(random.nextInt(keys.size()));
    }

    public static class Lines {
        String firstLine;
        String secondLine;

        Lines(String firstLine, String secondLine) {
            this.firstLine = firstLine;
            this.secondLine = secondLine;
        }

        public String getFirstLine() {
            return firstLine;
        }

        public String getSecondLine() {
            return secondLine;
        }
    }
}
