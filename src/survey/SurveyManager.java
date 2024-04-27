package survey;

import employees.Employee;
import employees.SurveyObserver;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import main.Main;

import java.util.ArrayList;

public class SurveyManager {
    private final ArrayList<Survey> surveysArr = new ArrayList<>();
    ObservableList<Survey> surveys = FXCollections.observableList(surveysArr);
    ArrayList<SurveyObserver> surveySubscribers = new ArrayList<>();

    public SurveyManager() {
//        surveys.addListener((ListChangeListener<? super survey.Survey>) change -> {
//            System.out.print("Surveys was changed\nUpdate user\nUpdate current survey\n");
//            Main.getCurrentUser().update();
//            loadSurvey(Main.currentSurvey.getSurveyId());
//        });
    }

    public void subscribe(SurveyObserver subscriber) {
        surveySubscribers.add(subscriber);
    }
    public void unsubscribe(SurveyObserver subscriber) {
        surveySubscribers.remove(subscriber);
    }

    public ArrayList<Survey> getEmployeeSurveys(Employee user) {
        if (surveys == null) return null;

        ArrayList<Survey> employeeSurveys = new ArrayList<>();

        for (Survey survey: surveys) {
            if (survey.containEmployee(user)) employeeSurveys.add(survey);
        }

        return employeeSurveys;
    }

    public ArrayList<Survey> getPersonalSurveys(Employee user) {
        if (surveys == null) return null;

        ArrayList<Survey> personalSurveys = new ArrayList<>();
        for (Survey survey: surveys) {
            if (survey.checkOwner(user)) {
                personalSurveys.add(survey);
            }
        }

        return personalSurveys;
    }

    public void createNewSurvey(String name, ArrayList<String> teams, ArrayList<String> options, boolean isMultipleChoice) {
        Employee owner = Main.getCurrentUser();
        Survey newSurvey;
        if (isMultipleChoice) newSurvey = new MultipleChoiceSurvey(name, teams, options, owner);
        else newSurvey = new SingleChoiceSurvey(name, teams, options, owner);
        surveys.add(newSurvey);

        inform(newSurvey);
    }

    public void loadSurvey(String id) {
        surveys.forEach(survey -> {
            if (survey.getSurveyId().equals(id)) Main.currentSurvey = survey;
        });
    }

    public void voteInSurvey(String optionId) {
        Employee user = Main.getCurrentUser();
        Main.currentSurvey.vote(user, optionId);
    }
    public void voteInSurvey(ArrayList<String> ids) {
        Employee user = Main.getCurrentUser();
        ((MultipleChoiceSurvey)Main.currentSurvey).vote(user, ids);
    }

    private void inform(Survey survey) {
        for (SurveyObserver subscriber: surveySubscribers) {
            if (survey.containEmployee((Employee) subscriber)) {
                ((Employee) subscriber).update();
            };
        }
    }
}
