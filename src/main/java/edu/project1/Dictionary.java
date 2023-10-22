package edu.project1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public final class Dictionary {
    public static final HashMap<String, Lines> WORDS = new HashMap<>() {{
        put("anchor", new Lines("A heavy object used to keep a ship or", "boat in place."));
        put("apple", new Lines("A fruit known for its sweet taste,", "often consumed raw or used in cooking."));
        put("canyon", new Lines("A deep gorge, typically one with a river", "flowing through it."));
        put("castle", new Lines("A fortified building with towers", "and a moat, historically used for defense."));
        put("circus", new Lines("A place for entertainment featuring", "acrobats and clowns."));
        put("coffee", new Lines("A popular beverage brewed from roasted", "beans."));
        put("comet", new Lines("A celestial object with a glowing tail", "that orbits the sun."));
        put("cottage", new Lines("A small simple house typically one near", "a lake or beach."));
        put("desert", new Lines("A dry, arid region with little or no", "vegetation."));
        put("diamond", new Lines("A precious gemstone known for its", "brilliant sparkle."));
        put("elephant", new Lines("A large, grey mammal known for its long", "trunk and tusks."));
        put("flower", new Lines("The reproductive part of a plant known for", "its colorful and fragrant petals."));
        put("guitar", new Lines("A stringed musical instrument with a", "soundboard and frets."));
        put("harbor", new Lines("A sheltered coastal area for docking ships", "and boats."));
        put("island", new Lines("A piece of land completely surrounded by", "water."));
        put("library", new Lines("A place where books and other reading", "materials are stored and accessed."));
        put("mountain", new Lines("A large landform rising prominently above", "its surroundings"));
        put("orchard", new Lines("An area of land devoted to the cultivation", "of fruit or nut trees."));
        put("rainbow", new Lines("A meteorological phenomenon featuring a", "spectrum of colors in the sky."));
        put("river", new Lines("A natural watercourse that flows towards", "an ocean or sea."));
        put("robot", new Lines("A machine capable of performing tasks", "autonomously."));
        put("sailboat", new Lines("A boat propelled by sails;", "often used for pleasure trips or racing."));
        put("tiger", new Lines("A large, striped big cat known for its", "strength and agility."));
        put("violin", new Lines("A musical instrument with strings that", "are played with a bow."));
        put("wombat", new Lines("An Australian marsupial that resembles", "a small bear with short legs."));
        put("zoo", new Lines("An establishment where various animals", "are exhibited to the public."));
    }};

    private Dictionary() {
    }

    public static String getRandomWord() {
        var random = new Random();
        var keys = new ArrayList<>(Dictionary.WORDS.keySet());

        return keys.get(random.nextInt(keys.size()));
    }

    public static String getFirstWord() {
        var keys = new ArrayList<>(Dictionary.WORDS.keySet());

        return keys.getFirst();
    }

    static class Lines {
        String firstLine;
        String secondLine;

        Lines(String firstLine, String secondLine) {
            this.firstLine = firstLine;
            this.secondLine = secondLine;
        }

        String getFirstLine() {
            return firstLine;
        }

        String getSecondLine() {
            return secondLine;
        }
    }
}
