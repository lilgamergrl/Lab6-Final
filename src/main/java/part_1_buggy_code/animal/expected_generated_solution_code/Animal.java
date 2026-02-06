package part_1_buggy_code.animal.expected_generated_solution_code;

public class Animal {
    private String name;
    private AnimalType type;
    private AnimalStatus status;
    private AnimalSize size;
    private int age;
    private int daysInShelter;

    public Animal(String name, AnimalType type, AnimalSize size, int age) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.age = age;
        this.status = AnimalStatus.AVAILABLE;
        this.daysInShelter = 0;
    }

    public String getName() { return name; }
    public AnimalType getType() { return type; }
    public AnimalStatus getStatus() { return status; }
    public AnimalSize getSize() { return size; }
    public int getAge() { return age; }
    public int getDaysInShelter() { return daysInShelter; }

    public void setStatus(AnimalStatus status) { this.status = status; }
    public void incrementDays() { daysInShelter++; }
}
