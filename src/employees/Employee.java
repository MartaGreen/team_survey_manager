package employees;

import java.util.Collection;

public abstract class Employee{
    private String name;
    private String surname;
    private float experience;
    private String team;

    protected Employee(String name, String surname, float experience, String team) {
        this.name = name;
        this.surname = surname;
        this.experience = experience;
        this.team = team;
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
    public static boolean compareEmployee(Employee checkedEmployee, String name, String surname) {
        return (checkedEmployee.getName().equals(name) && checkedEmployee.getSurname().equals(surname));
    }
}
