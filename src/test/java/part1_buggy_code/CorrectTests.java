//package part1_buggy_code;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.DisplayName;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.List;
//
///**
// * These tests should PASS with the buggy code.
// * They test the actual buggy behavior to confirm bugs exist.
// */
//class CorrectTests {
//    private AnimalShelter shelter;
//
//    @BeforeEach
//    void setUp() {
//        shelter = new AnimalShelter();
//    }
//
//    // Bug 1: Off-by-one error causes ArrayIndexOutOfBoundsException
//    @Test
//    @DisplayName("Bug 1: Confirms ArrayIndexOutOfBoundsException is thrown")
//    void testBug1_OffByOneErrorThrowsException() {
//        shelter.addAnimal(new Animal("Buddy", AnimalType.DOG, AnimalSize.LARGE, 3));
//        shelter.addAnimal(new Animal("Max", AnimalType.DOG, AnimalSize.MEDIUM, 5));
//
//        // Bug: i <= animals.size() causes ArrayIndexOutOfBoundsException
//        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
//            shelter.findAvailableByType(AnimalType.DOG);
//        });
//    }
//
//    // Bug 2: String comparison with == fails (when strings are different objects)
//    @Test
//    @DisplayName("Bug 2: Confirms == comparison fails for different String objects")
//    void testBug2_StringComparisonWithEqualsFails() {
//        shelter.addAnimal(new Animal(new String("Buddy"), AnimalType.DOG, AnimalSize.LARGE, 3));
//
//        // Bug: Uses == instead of .equals(), fails with different String objects
//        boolean result = shelter.adoptAnimal(new String("Buddy"));
//
//        assertFalse(result, "Bug confirmed: == fails for different String objects");
//        assertEquals(0, shelter.getCountByStatus(AnimalStatus.ADOPTED));
//    }
//
//    // Bug 3: Inverted logic only adopts non-available animals
//    @Test
//    @DisplayName("Bug 3: Confirms inverted logic rejects available animals")
//    void testBug3_InvertedLogicRejectsAvailableAnimals() {
//        Animal buddy = new Animal("Buddy", AnimalType.DOG, AnimalSize.LARGE, 3);
//        shelter.addAnimal(buddy);
//
//        // Bug: Checks status != AVAILABLE, so rejects available animals
//        boolean result = shelter.adoptAnimal("Buddy");
//
//        assertFalse(result, "Bug confirmed: Cannot adopt available animals");
//        assertEquals(AnimalStatus.AVAILABLE, buddy.getStatus());
//    }
//
//    @Test
//    @DisplayName("Bug 3: Confirms inverted logic allows adopting non-available animals")
//    void testBug3_InvertedLogicAcceptsNonAvailableAnimals() {
//        Animal reserved = new Animal("Reserved", AnimalType.DOG, AnimalSize.LARGE, 3);
//        reserved.setStatus(AnimalStatus.RESERVED);
//        shelter.addAnimal(reserved);
//
//        // Bug: status != AVAILABLE is true for RESERVED, so it proceeds
//        // However, it will hit Bug 4 (NullPointerException) next
//        assertThrows(NullPointerException.class, () -> {
//            shelter.adoptAnimal("Reserved");
//        });
//    }
//
//    // Bug 4: NullPointerException when adopting first animal of a type
//    @Test
//    @DisplayName("Bug 4: Confirms NullPointerException on first adoption")
//    void testBug4_NullPointerExceptionOnFirstAdoption() {
//        Animal reserved = new Animal("Reserved", AnimalType.DOG, AnimalSize.LARGE, 3);
//        reserved.setStatus(AnimalStatus.RESERVED); // Make it pass the inverted logic check
//        shelter.addAnimal(reserved);
//
//        // Bug: adoptionCounts.get(type) returns null, then tries to add 1
//        assertThrows(NullPointerException.class, () -> {
//            shelter.adoptAnimal("Reserved");
//        });
//    }
//
//    // Bug 5: > operator excludes animals at exactly minDays
//    @Test
//    @DisplayName("Bug 5: Confirms > excludes animals at exact threshold")
//    void testBug5_ExactThresholdExcluded() {
//        Animal buddy = new Animal("Buddy", AnimalType.DOG, AnimalSize.LARGE, 3);
//        shelter.addAnimal(buddy);
//
//        // Set to exactly 30 days
//        for (int i = 0; i < 30; i++) {
//            buddy.incrementDays();
//        }
//
//        // Bug: Uses >, so excludes exactly 30
//        List<Animal> longTerm = shelter.findLongTermResidents(30);
//
//        assertEquals(0, longTerm.size(), "Bug confirmed: Excludes animal at exactly 30 days");
//    }
//
//    @Test
//    @DisplayName("Bug 5: Confirms > only includes animals over threshold")
//    void testBug5_OverThresholdIncluded() {
//        Animal buddy = new Animal("Buddy", AnimalType.DOG, AnimalSize.LARGE, 3);
//        shelter.addAnimal(buddy);
//
//        // Set to 31 days (over threshold)
//        for (int i = 0; i < 31; i++) {
//            buddy.incrementDays();
//        }
//
//        List<Animal> longTerm = shelter.findLongTermResidents(30);
//
//        assertEquals(1, longTerm.size(), "Animals over threshold are included");
//    }
//
//    // Bug 6: Switch on parameter instead of animal.getSize()
//    @Test
//    @DisplayName("Bug 6: Confirms switch checks parameter, returns wrong results")
//    void testBug6_SwitchOnWrongVariable() {
//        shelter.addAnimal(new Animal("Tiny", AnimalType.CAT, AnimalSize.SMALL, 2));
//        shelter.addAnimal(new Animal("Big", AnimalType.DOG, AnimalSize.LARGE, 5));
//        shelter.addAnimal(new Animal("Mid", AnimalType.DOG, AnimalSize.MEDIUM, 3));
//
//        // Bug: switch(size) instead of switch(animal.getSize())
//        // When size=SMALL, adds ALL animals (because case SMALL is hit)
//        List<Animal> result = shelter.getAnimalsBySize(AnimalSize.SMALL);
//
//        // All animals get added because switch matches parameter, not animal size
//        assertEquals(3, result.size(), "Bug confirmed: Returns all animals");
//    }
//
//    @Test
//    @DisplayName("Bug 6: Confirms MEDIUM parameter returns all animals")
//    void testBug6_MediumReturnsAll() {
//        shelter.addAnimal(new Animal("Tiny", AnimalType.CAT, AnimalSize.SMALL, 2));
//        shelter.addAnimal(new Animal("Big", AnimalType.DOG, AnimalSize.LARGE, 5));
//        shelter.addAnimal(new Animal("Mid", AnimalType.DOG, AnimalSize.MEDIUM, 3));
//
//        List<Animal> result = shelter.getAnimalsBySize(AnimalSize.MEDIUM);
//
//        assertEquals(3, result.size(), "Bug confirmed: Returns all animals for MEDIUM");
//    }
//
//    // Bug 7: Missing LARGE case in switch
//    @Test
//    @DisplayName("Bug 7: Confirms LARGE case is missing, returns empty list")
//    void testBug7_MissingLargeCase() {
//        shelter.addAnimal(new Animal("Big", AnimalType.DOG, AnimalSize.LARGE, 5));
//        shelter.addAnimal(new Animal("Huge", AnimalType.DOG, AnimalSize.LARGE, 7));
//
//        // Bug: No case LARGE in switch, falls through to default (nothing)
//        List<Animal> largeAnimals = shelter.getAnimalsBySize(AnimalSize.LARGE);
//
//        assertEquals(0, largeAnimals.size(), "Bug confirmed: LARGE case missing, returns empty");
//    }
//
//    // Bug 8: >= returns last maximum instead of first
//    @Test
//    @DisplayName("Bug 8: Confirms >= returns last when there's a tie")
//    void testBug8_ReturnsLastMaximum() {
//        // We can't fully test this due to other bugs preventing adoption
//        // But we can verify the logic with null case
//
//        AnimalType result = shelter.getMostAdoptedType();
//
//        assertNull(result, "No adoptions yet");
//
//        // Note: Full test would require working adoption to show that
//        // with DOG=3, CAT=3, >= returns CAT (last), > would return DOG (first)
//    }
//
//    // Additional tests confirming working features with the bugs
//    @Test
//    @DisplayName("Working: getCountByStatus works correctly")
//    void testWorkingFeature_GetCountByStatus() {
//        shelter.addAnimal(new Animal("Buddy", AnimalType.DOG, AnimalSize.LARGE, 3));
//        shelter.addAnimal(new Animal("Max", AnimalType.DOG, AnimalSize.MEDIUM, 5));
//
//        assertEquals(2, shelter.getCountByStatus(AnimalStatus.AVAILABLE));
//        assertEquals(0, shelter.getCountByStatus(AnimalStatus.ADOPTED));
//    }
//
//    @Test
//    @DisplayName("Working: dailyUpdate increments days")
//    void testWorkingFeature_DailyUpdate() {
//        Animal buddy = new Animal("Buddy", AnimalType.DOG, AnimalSize.LARGE, 3);
//        shelter.addAnimal(buddy);
//
//        assertEquals(0, buddy.getDaysInShelter());
//        shelter.dailyUpdate();
//        assertEquals(1, buddy.getDaysInShelter());
//        shelter.dailyUpdate();
//        assertEquals(2, buddy.getDaysInShelter());
//    }
//
//    @Test
//    @DisplayName("Working: Medical hold release after 7 days")
//    void testWorkingFeature_MedicalHoldRelease() {
//        Animal sick = new Animal("Sick", AnimalType.CAT, AnimalSize.SMALL, 2);
//        sick.setStatus(AnimalStatus.MEDICAL_HOLD);
//        shelter.addAnimal(sick);
//
//        // Run daily update 8 times
//        for (int i = 0; i < 8; i++) {
//            shelter.dailyUpdate();
//        }
//
//        assertEquals(AnimalStatus.AVAILABLE, sick.getStatus(),
//            "Animal should be available after 8 days (>7)");
//    }
//
//    @Test
//    @DisplayName("Working: addAnimal adds to shelter")
//    void testWorkingFeature_AddAnimal() {
//        shelter.addAnimal(new Animal("Buddy", AnimalType.DOG, AnimalSize.LARGE, 3));
//
//        assertEquals(1, shelter.getCountByStatus(AnimalStatus.AVAILABLE));
//    }
//
//    @Test
//    @DisplayName("Edge case: Empty shelter operations work")
//    void testEdgeCase_EmptyShelter() {
//        assertEquals(0, shelter.getCountByStatus(AnimalStatus.AVAILABLE));
//        assertEquals(0, shelter.findLongTermResidents(30).size());
//        assertNull(shelter.getMostAdoptedType());
//
//        // These would throw exceptions due to Bug 1, but with empty list:
//        List<Animal> dogs = shelter.findAvailableByType(AnimalType.DOG);
//        assertEquals(0, dogs.size());
//    }
//
//    @Test
//    @DisplayName("Confirms Bug 1 only happens with non-empty list")
//    void testBug1_OnlyWithAnimals() {
//        // Empty list: no exception
//        List<Animal> dogs = shelter.findAvailableByType(AnimalType.DOG);
//        assertEquals(0, dogs.size());
//
//        // Add one animal: exception
//        shelter.addAnimal(new Animal("Buddy", AnimalType.DOG, AnimalSize.LARGE, 3));
//        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
//            shelter.findAvailableByType(AnimalType.DOG);
//        });
//    }
//}