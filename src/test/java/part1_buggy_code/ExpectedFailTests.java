package part1_buggy_code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * These tests should ALL FAIL with the buggy code.
 * They test the correct expected behavior.
 */
class ExpectedFailTests {
    private AnimalShelter shelter;

    @BeforeEach
    void setUp() {
        shelter = new AnimalShelter();
    }

    // Bug 1: Off-by-one error in findAvailableByType
    @Test
    @DisplayName("Bug 1: Should find available dogs without ArrayIndexOutOfBoundsException")
    void testFindAvailableByType_ShouldNotThrow() {
        shelter.addAnimal(new Animal("Buddy", AnimalType.DOG, AnimalSize.LARGE, 3));
        shelter.addAnimal(new Animal("Max", AnimalType.DOG, AnimalSize.MEDIUM, 5));
        shelter.addAnimal(new Animal("Whiskers", AnimalType.CAT, AnimalSize.SMALL, 2));

        // Should successfully return 2 dogs without throwing exception
        List<Animal> dogs = shelter.findAvailableByType(AnimalType.DOG);
        assertEquals(2, dogs.size(), "Should find 2 available dogs");
        assertTrue(dogs.stream().allMatch(a -> a.getType() == AnimalType.DOG));
    }

    // Bug 2: String comparison using == instead of .equals()
    @Test
    @DisplayName("Bug 2: Should find animal by name with .equals()")
    void testAdoptAnimal_ShouldUseEqualsForStringComparison() {
        // Create string in a way that ensures it's not interned
        String name = new String("Buddy");
        shelter.addAnimal(new Animal(name, AnimalType.DOG, AnimalSize.LARGE, 3));

        // Even with different String object, should find by content
        boolean result = shelter.adoptAnimal(new String("Buddy"));
        
        assertTrue(result, "Should successfully adopt animal using .equals()");
        assertEquals(1, shelter.getCountByStatus(AnimalStatus.ADOPTED));
    }

    // Bug 3: Inverted logic in adoption check
    @Test
    @DisplayName("Bug 3: Should adopt AVAILABLE animals, not non-available ones")
    void testAdoptAnimal_ShouldAdoptAvailableAnimals() {
        Animal buddy = new Animal("Buddy", AnimalType.DOG, AnimalSize.LARGE, 3);
        shelter.addAnimal(buddy);

        // Animal is AVAILABLE, so adoption should succeed
        boolean result = shelter.adoptAnimal("Buddy");

        assertTrue(result, "Should successfully adopt available animal");
        assertEquals(AnimalStatus.ADOPTED, buddy.getStatus(), "Animal should be adopted");
        assertEquals(1, shelter.getCountByStatus(AnimalStatus.ADOPTED));
    }

    @Test
    @DisplayName("Bug 3: Should NOT adopt non-available animals")
    void testAdoptAnimal_ShouldNotAdoptReservedAnimals() {
        Animal reserved = new Animal("Reserved", AnimalType.CAT, AnimalSize.SMALL, 2);
        reserved.setStatus(AnimalStatus.RESERVED);
        shelter.addAnimal(reserved);

        boolean result = shelter.adoptAnimal("Reserved");

        assertFalse(result, "Should not adopt reserved animal");
        assertEquals(AnimalStatus.RESERVED, reserved.getStatus(), "Status should remain reserved");
    }

    // Bug 4: NullPointerException in adoption counts
    @Test
    @DisplayName("Bug 4: Should handle first adoption of each type without NullPointerException")
    void testAdoptAnimal_ShouldHandleFirstAdoptionOfType() {
        Animal dog = new Animal("Buddy", AnimalType.DOG, AnimalSize.LARGE, 3);
        Animal cat = new Animal("Whiskers", AnimalType.CAT, AnimalSize.SMALL, 2);
        shelter.addAnimal(dog);
        shelter.addAnimal(cat);

        // First adoption of DOG type should not throw NullPointerException
        assertDoesNotThrow(() -> shelter.adoptAnimal("Buddy"));
        
        // First adoption of CAT type should also work
        assertDoesNotThrow(() -> shelter.adoptAnimal("Whiskers"));
        
        assertEquals(2, shelter.getCountByStatus(AnimalStatus.ADOPTED));
    }

    // Bug 5: Wrong comparison operator in findLongTermResidents
    @Test
    @DisplayName("Bug 5: Should include animals at exactly minDays threshold")
    void testFindLongTermResidents_ShouldIncludeExactThreshold() {
        Animal buddy = new Animal("Buddy", AnimalType.DOG, AnimalSize.LARGE, 3);
        Animal max = new Animal("Max", AnimalType.DOG, AnimalSize.MEDIUM, 5);
        shelter.addAnimal(buddy);
        shelter.addAnimal(max);

        // Set buddy to exactly 30 days
        for (int i = 0; i < 30; i++) {
            buddy.incrementDays();
        }
        
        // Set max to 31 days
        for (int i = 0; i < 31; i++) {
            max.incrementDays();
        }

        // Should find animals with >= 30 days (both buddy at 30 and max at 31)
        List<Animal> longTerm = shelter.findLongTermResidents(30);

        assertEquals(2, longTerm.size(), "Should include animal at exactly 30 days");
        assertTrue(longTerm.contains(buddy), "Should include Buddy at 30 days");
        assertTrue(longTerm.contains(max), "Should include Max at 31 days");
    }

    // Bug 6: Switch on wrong variable in getAnimalsBySize
    @Test
    @DisplayName("Bug 6: Should check animal.getSize() not the parameter")
    void testGetAnimalsBySize_ShouldCheckAnimalSize() {
        shelter.addAnimal(new Animal("Tiny", AnimalType.CAT, AnimalSize.SMALL, 2));
        shelter.addAnimal(new Animal("Big", AnimalType.DOG, AnimalSize.LARGE, 5));
        shelter.addAnimal(new Animal("Mid", AnimalType.DOG, AnimalSize.MEDIUM, 3));

        List<Animal> smallAnimals = shelter.getAnimalsBySize(AnimalSize.SMALL);
        assertEquals(1, smallAnimals.size(), "Should find 1 small animal");
        assertEquals("Tiny", smallAnimals.get(0).getName());

        List<Animal> mediumAnimals = shelter.getAnimalsBySize(AnimalSize.MEDIUM);
        assertEquals(1, mediumAnimals.size(), "Should find 1 medium animal");
        assertEquals("Mid", mediumAnimals.get(0).getName());
    }

    // Bug 7: Missing LARGE case in switch statement
    @Test
    @DisplayName("Bug 7: Should handle LARGE case in switch")
    void testGetAnimalsBySize_ShouldHandleLargeCase() {
        shelter.addAnimal(new Animal("Big", AnimalType.DOG, AnimalSize.LARGE, 5));
        shelter.addAnimal(new Animal("Huge", AnimalType.DOG, AnimalSize.LARGE, 7));
        shelter.addAnimal(new Animal("Tiny", AnimalType.CAT, AnimalSize.SMALL, 2));

        List<Animal> largeAnimals = shelter.getAnimalsBySize(AnimalSize.LARGE);

        assertEquals(2, largeAnimals.size(), "Should find 2 large animals");
        assertTrue(largeAnimals.stream().allMatch(a -> a.getSize() == AnimalSize.LARGE));
    }

    // Bug 8: Wrong comparison operator in getMostAdoptedType (line 108)
    @Test
    @DisplayName("Bug 8: Should return first maximum, not last")
    void testGetMostAdoptedType_ShouldReturnFirstMaximum() {
        // Add animals of different types
        shelter.addAnimal(new Animal("Dog1", AnimalType.DOG, AnimalSize.LARGE, 3));
        shelter.addAnimal(new Animal("Dog2", AnimalType.DOG, AnimalSize.MEDIUM, 5));
        shelter.addAnimal(new Animal("Dog3", AnimalType.DOG, AnimalSize.SMALL, 2));
        shelter.addAnimal(new Animal("Cat1", AnimalType.CAT, AnimalSize.SMALL, 2));
        shelter.addAnimal(new Animal("Cat2", AnimalType.CAT, AnimalSize.MEDIUM, 4));
        shelter.addAnimal(new Animal("Cat3", AnimalType.CAT, AnimalSize.LARGE, 6));
        shelter.addAnimal(new Animal("Rabbit1", AnimalType.RABBIT, AnimalSize.SMALL, 1));

        // Adopt 3 dogs
        shelter.adoptAnimal("Dog1");
        shelter.adoptAnimal("Dog2");
        shelter.adoptAnimal("Dog3");

        // Adopt 3 cats
        shelter.adoptAnimal("Cat1");
        shelter.adoptAnimal("Cat2");
        shelter.adoptAnimal("Cat3");

        // Adopt 1 rabbit
        shelter.adoptAnimal("Rabbit1");

        // Both DOG and CAT have 3 adoptions
        // With >, should return first one encountered (DOG, assuming insertion order)
        // With >=, returns last one (CAT)
        AnimalType mostAdopted = shelter.getMostAdoptedType();

        assertEquals(AnimalType.DOG, mostAdopted, 
            "Should return first type when there's a tie, not last");
    }

    // Comprehensive test combining multiple functionalities
    @Test
    @DisplayName("Comprehensive: Complete adoption workflow should work")
    void testCompleteAdoptionWorkflow() {
        // Add various animals
        shelter.addAnimal(new Animal("Buddy", AnimalType.DOG, AnimalSize.LARGE, 3));
        shelter.addAnimal(new Animal("Max", AnimalType.DOG, AnimalSize.MEDIUM, 5));
        shelter.addAnimal(new Animal("Whiskers", AnimalType.CAT, AnimalSize.SMALL, 2));
        shelter.addAnimal(new Animal("Fluffy", AnimalType.RABBIT, AnimalSize.SMALL, 1));

        // Find available dogs
        List<Animal> availableDogs = shelter.findAvailableByType(AnimalType.DOG);
        assertEquals(2, availableDogs.size());

        // Adopt a dog
        assertTrue(shelter.adoptAnimal("Buddy"));
        assertEquals(1, shelter.getCountByStatus(AnimalStatus.ADOPTED));
        assertEquals(3, shelter.getCountByStatus(AnimalStatus.AVAILABLE));

        // Find available dogs again
        availableDogs = shelter.findAvailableByType(AnimalType.DOG);
        assertEquals(1, availableDogs.size());
        assertEquals("Max", availableDogs.get(0).getName());

        // Get small animals
        List<Animal> smallAnimals = shelter.getAnimalsBySize(AnimalSize.SMALL);
        assertEquals(2, smallAnimals.size());
    }

    @Test
    @DisplayName("Edge case: Empty shelter operations")
    void testEmptyShelter() {
        assertEquals(0, shelter.getCountByStatus(AnimalStatus.AVAILABLE));
        assertEquals(0, shelter.findAvailableByType(AnimalType.DOG).size());
        assertEquals(0, shelter.getAnimalsBySize(AnimalSize.LARGE).size());
        assertEquals(0, shelter.findLongTermResidents(30).size());
        assertNull(shelter.getMostAdoptedType());
    }

    @Test
    @DisplayName("Test all animal sizes work correctly")
    void testAllAnimalSizes() {
        shelter.addAnimal(new Animal("Small1", AnimalType.CAT, AnimalSize.SMALL, 1));
        shelter.addAnimal(new Animal("Medium1", AnimalType.DOG, AnimalSize.MEDIUM, 3));
        shelter.addAnimal(new Animal("Large1", AnimalType.DOG, AnimalSize.LARGE, 5));

        assertEquals(1, shelter.getAnimalsBySize(AnimalSize.SMALL).size());
        assertEquals(1, shelter.getAnimalsBySize(AnimalSize.MEDIUM).size());
        assertEquals(1, shelter.getAnimalsBySize(AnimalSize.LARGE).size());
    }
}