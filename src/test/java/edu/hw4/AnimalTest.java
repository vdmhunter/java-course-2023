package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnimalTest {

    @Test
    @DisplayName("Animal constructor test")
    void animal_Test() {
        // Arrange
        Animal cat = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 3, 25, 5, false);
        Animal dog = new Animal("Max", Animal.Type.CAT, Animal.Sex.M, 3, 25, 5, false);
        Animal bird = new Animal("Swan", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal fish = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 2, 5, 0, false);
        Animal spider = new Animal("Silk", Animal.Type.SPIDER, Animal.Sex.M, 1, 3, 0, false);

        //Act & Assert
        Assertions.assertAll(
            () -> Assertions.assertEquals(4, cat.paws()),
            () -> Assertions.assertEquals(4, dog.paws()),
            () -> Assertions.assertEquals(2, bird.paws()),
            () -> Assertions.assertEquals(0, fish.paws()),
            () -> Assertions.assertEquals(8, spider.paws())
        );
    }
}
