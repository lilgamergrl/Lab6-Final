package part_1_buggy_code.animal.expected_generated_solution_code;

import java.util.List;

// Test class
public class ShelterTest {
    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter();

        // Add animals
        shelter.addAnimal(new Animal("Buddy", AnimalType.DOG, AnimalSize.LARGE, 3));
        shelter.addAnimal(new Animal("Whiskers", AnimalType.CAT, AnimalSize.SMALL, 2));
        shelter.addAnimal(new Animal("Max", AnimalType.DOG, AnimalSize.MEDIUM, 5));
        shelter.addAnimal(new Animal("Fluffy", AnimalType.RABBIT, AnimalSize.SMALL, 1));

        // Test finding available dogs
        System.out.println("\nAvailable dogs:");
        List<Animal> availableDogs = shelter.findAvailableByType(AnimalType.DOG);
        for (Animal dog : availableDogs) {
            System.out.println("- " + dog.getName());
        }

        // Test adoption
        System.out.println("\nAttempting to adopt Buddy:");
        shelter.adoptAnimal("Buddy");

        // Test getting animals by size
        System.out.println("\nSmall animals:");
        List<Animal> smallAnimals = shelter.getAnimalsBySize(AnimalSize.SMALL);
        for (Animal animal : smallAnimals) {
            System.out.println("- " + animal.getName());
        }
    }
}
