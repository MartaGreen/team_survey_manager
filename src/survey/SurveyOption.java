package survey;

import employees.Employee;

import java.util.ArrayList;
import java.util.UUID;

public class SurveyOption {
   private final ArrayList<Employee> voters = new ArrayList<>();
    private String text;

    private String name;
    private String type;
    private String optionId;
    public double percentage;

    public SurveyOption(String name, String type) {
        this.name = name;
        this.type = type;
        this.optionId = UUID.randomUUID().toString();
    }

    public String getOptionId() {
        return optionId;
    }

    public String getName() {
        return name;
    }

    public void addVoter(Employee voter) {
        voters.add(voter);
    }

    public void removeVoter(Employee voterToRemove) {
        voters.removeIf(voter -> voter.getId().equals(voterToRemove.getId()));
    }

    public int getVotersSize() {
        return voters.size();
    }

    public void recalculate(int participantsCount) {
        percentage = participantsCount == 0 ? 0 : (double) voters.size() / participantsCount;
    }

    public boolean containsVoter(Employee user) {
        for (Employee voter: voters) {
            if (voter.getId().equals(user.getId())) return true;
        }

        return false;
    }
}
