package survey;

import employees.Employee;
import employees.SurveyObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Main;

import java.util.ArrayList;

public class SurveyManager {
    private Main main;
    public SurveyManager(Main main) {
        this.main = main;
    }
    private final ArrayList<Survey> surveysArr = new ArrayList<>();
    ObservableList<Survey> surveys = FXCollections.observableList(surveysArr);
    ArrayList<SurveyObserver> surveySubscribers = new ArrayList<>();

    // subscribe a user for surveys update;
    public void subscribe(SurveyObserver subscriber) {
        surveySubscribers.add(subscriber);
    }
    // unsubscribe a user for surveys update;
    // this method is not used now due to non-existence of user deletion.
    public void unsubscribe(SurveyObserver subscriber) {
        surveySubscribers.remove(subscriber);
    }

    // send to a user his surveys (surveys where he can vote)
    public ArrayList<Survey> getEmployeeSurveys(Employee user) {
        if (surveys == null) return null;

        ArrayList<Survey> employeeSurveys = new ArrayList<>();
        surveys.forEach(survey -> {
            if (survey.containEmployee(user)) employeeSurveys.add(survey);
        });

        return employeeSurveys;
    }

    // send to a user his personal surveys (surveys where he is owner)
    public ArrayList<Survey> getPersonalSurveys(Employee user) {
        if (surveys == null) return null;

        ArrayList<Survey> personalSurveys = new ArrayList<>();
        surveys.forEach(survey -> {
            if (survey.checkOwner(user)) personalSurveys.add(survey);
        });

        return personalSurveys;
    }

    public void createNewSurvey(String name, ArrayList<String> teams, ArrayList<String> options, boolean isMultipleChoice) {
        Employee owner = main.getCurrentUser();
        Survey newSurvey;
        if (isMultipleChoice) newSurvey = new MultipleChoiceSurvey(name, teams, options, owner);
        else newSurvey = new SingleChoiceSurvey(name, teams, options, owner);
        surveys.add(newSurvey);

        inform(newSurvey);
    }

    public void deleteSurvey(Survey survey) {
        surveys.remove(survey);
        inform(survey);
    }

    public void loadSurvey(String id) {
        surveys.forEach(survey -> {
            if (survey.getSurveyId().equals(id)) main.currentSurvey = survey;
        });
    }

    public void voteInSurvey(ArrayList<String> ids) {
        Survey survey = main.currentSurvey;
        Employee user = main.getCurrentUser();
        survey.vote(user, ids);
    }

    // inform each subscribes user about changes in surveys
    private void inform(Survey survey) {
        for (SurveyObserver subscriber: surveySubscribers) {
            if (survey.containEmployee((Employee) subscriber) || survey.checkOwner((Employee) subscriber)) {
                ((Employee) subscriber).update();
            };
        }
    }
}
