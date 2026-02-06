package part_1_TA_SOLUTION.expected_generated_solution_code;// animal.AnimalShelter.java
// This program manages an animal shelter with different types of animals
// Expected behavior: Track animals, their status, and manage adoptions

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalShelter {
    private final List<Animal> animals;
    private final Map<AnimalType, Integer> adoptionCounts;

    public AnimalShelter() {
        animals = new ArrayList<>();
        adoptionCounts = new HashMap<>();
    }

    // Add a new animal to the shelter
    public void addAnimal(Animal animal) {
        animals.add(animal);
        System.out.println(animal.getName() + " has been added to the shelter.");
    }

    // Find all animals of a specific type that are available
    public List<Animal> findAvailableByType(AnimalType type) {
        List<Animal> result = new ArrayList<>();
        for (int i = 0; i <= animals.size(); i++) {  // BUG: Off-by-one error
            Animal animal = animals.get(i);
            if (animal.getType() == type && animal.getStatus() == AnimalStatus.AVAILABLE) {
                result.add(animal);
            }
        }
        return result;
    }

    // Adopt an animal by name
    public boolean adoptAnimal(String name) {
        for (Animal animal : animals) {
            if (animal.getName() == name) {  // BUG: String comparison with ==
                if (animal.getStatus() != AnimalStatus.AVAILABLE) {  // BUG: Logic error - should be ==
                    animal.setStatus(AnimalStatus.ADOPTED);

                    // Update adoption counts
                    AnimalType type = animal.getType();
                    adoptionCounts.put(type, adoptionCounts.get(type) + 1);  // BUG: Null pointer if type not in map

                    System.out.println(animal.getName() + " has been adopted!");
                    return true;
                }
            }
        }
        return false;
    }

    // Get count of animals by status
    public int getCountByStatus(AnimalStatus status) {
        int count = 0;
        for (Animal animal : animals) {
            if (animal.getStatus() == status) {
                count++;
            }
        }
        return count;
    }

    // Find animals that have been in shelter longer than specified days
    public List<Animal> findLongTermResidents(int minDays) {
        List<Animal> longTerm = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getDaysInShelter() > minDays) {  // BUG: Should be >= for "longer than or equal to"
                longTerm.add(animal);
            }
        }
        return longTerm;
    }

    // Update all animals - increment days and check for special handling
    public void dailyUpdate() {
        for (Animal animal : animals) {
            animal.incrementDays();

            // Animals on medical hold for more than 7 days should become available
            if (animal.getStatus() == AnimalStatus.MEDICAL_HOLD) {
                if (animal.getDaysInShelter() > 7) {
                    animal.setStatus(AnimalStatus.AVAILABLE);
                }
            }
        }
    }

    // Get animals by size - switch statement practice
    public List<Animal> getAnimalsBySize(AnimalSize size) {
        List<Animal> result = new ArrayList<>();

        for (Animal animal : animals) {
            switch (size) {  // BUG: Should switch on animal.getSize(), not parameter
                case SMALL:
                    result.add(animal);
                    break;
                case MEDIUM:
                    result.add(animal);
                    break;
                // BUG: Missing LARGE case
            }
        }
        return result;
    }

    // Get most popular animal type for adoption
    public AnimalType getMostAdoptedType() {
        AnimalType mostPopular = null;
        int maxAdoptions = 0;

        for (Map.Entry<AnimalType, Integer> entry : adoptionCounts.entrySet()) {
            if (entry.getValue() >= maxAdoptions) {  // BUG: Should be > to get first maximum, not last
                maxAdoptions = entry.getValue();
                mostPopular = entry.getKey();
            }
        }
        return mostPopular;
    }
}

