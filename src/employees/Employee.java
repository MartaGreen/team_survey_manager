package employees;

import java.util.Collection;
import java.util.UUID;

public abstract class Employee{
    private String name;
    private String surname;
    private float experience;
    private String team;
    private String id;

    protected Employee(String name, String surname, float experience, String team) {
        this.name = name;
        this.surname = surname;
        this.experience = experience;
        this.team = team;
        this.id = UUID.randomUUID().toString();
    }
    public String getId() {
        return id;
    }
    public void setId(String newId) {
        this.id = newId;
    }

    public String getName() {
        return name;
    }
    protected void setName(String newName) {
        name = newName;
    }

    public String getSurname() {
        return surname;
    }
    protected void setSurname(String newSurname) {
        surname = newSurname;
    }

    public float getExperience() {
        return experience;
    }
    protected void setExperience(float newExperience) {
        experience = newExperience;
    }

    public String getTeam() {
        return team;
    }
    protected void setTeam(String newTeam){
        team = newTeam;
    }

    //return 1 if it is current employee
    // return 0 if it isn't current employee
    public boolean compareEmployee(String name, String surname) {
        return (this.getName().equals(name) && this.getSurname().equals(surname));
    }
    public boolean compareEmployee(String fullName) {
        return ((this.getName() + " " + this.getSurname()).equals(fullName));
    }
}
