package survey;

import employees.Employee;
import employees.SurveyObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Main;

import java.util.ArrayList;

/**
 * Main class for survey management for each interaction connected with surveys.
 */
public class SurveyManager {
    private final ArrayList<Survey> surveysArr = new ArrayList<>();
    private ObservableList<Survey> surveys = FXCollections.observableList(surveysArr);
    private ArrayList<SurveyObserver> surveySubscribers = new ArrayList<>();
    private final Main main;

    /**
     * Constructor for a SurveyManager with the specified main application instance.
     *
     * @param main The main instance.
     */
    public SurveyManager(Main main) {
        this.main = main;
    }

    /**
     * Subscribes a user for surveys update.
     *
     * @param subscriber The subscriber to be added.
     */
    public void subscribe(SurveyObserver subscriber) {
        surveySubscribers.add(subscriber);
    }

    /**
     * Unsubscribes a user from surveys update.
     *
     * @param subscriber The subscriber to be removed.
     *                   This method is not used now due to non-existence of user deletion.
     */
    public void unsubscribe(SurveyObserver subscriber) {
        surveySubscribers.remove(subscriber);
    }

    /**
     * Retrieves surveys available for a specific employee to vote.
     *
     * @param user The employee for whom to retrieve surveys.
     * @return The surveys available for the employee to vote.
     */
    public ArrayList<Survey> getEmployeeSurveys(Employee user) {
        ArrayList<Survey> employeeSurveys = new ArrayList<>();
        surveys.forEach(survey -> {
            if (survey.containEmployee(user)) employeeSurveys.add(survey);
        });

        return employeeSurveys;
    }

    /**
     * Retrieves surveys where a specific employee is the owner.
     *
     * @param user The employee for whom to retrieve personal surveys.
     * @return The surveys where the employee is the owner.
     */
    public ArrayList<Survey> getPersonalSurveys(Employee user) {
        ArrayList<Survey> personalSurveys = new ArrayList<>();
        surveys.forEach(survey -> {
            if (survey.checkOwner(user)) personalSurveys.add(survey);
        });

        return personalSurveys;
    }

    /**
     * Creates a new survey with the specified parameters.
     *
     * @param name             The name of the survey.
     * @param teams            The teams' names eligible to participate in the survey.
     * @param options          The options available in the survey.
     * @param isMultipleChoice Indicates if the survey is multiple choice or single choice.
     */
    public void createNewSurvey(String name, ArrayList<String> teams, ArrayList<String> options, boolean isMultipleChoice) {
        Employee owner = main.getCurrentUser();
        Survey newSurvey;
        if (isMultipleChoice) newSurvey = new MultipleChoiceSurvey(name, teams, options, owner);
        else newSurvey = new SingleChoiceSurvey(name, teams, options, owner);
        surveys.add(newSurvey);

        inform(newSurvey);
    }

    /**
     * Deletes a survey.
     *
     * @param survey The survey to be deleted.
     */
    public void deleteSurvey(Survey survey) {
        surveys.remove(survey);
        inform(survey);
    }

    /**
     * Loads a survey with the specified ID.
     *
     * @param id The ID of the survey to be loaded.
     */
    public void loadSurvey(String id) {
        surveys.forEach(survey -> {
            if (survey.getSurveyId().equals(id)) main.currentSurvey = survey;
        });
    }

    /**
     * Processes the vote in the current survey.
     *
     * @param ids The IDs of the options selected by the user.
     */
    public void voteInSurvey(ArrayList<String> ids) {
        Survey survey = main.currentSurvey;
        Employee user = main.getCurrentUser();
        survey.vote(user, ids);
    }

    /**
     * Informs subscribers about changes in surveys.
     *
     * @param survey The survey that triggered the update.
     */
    private void inform(Survey survey) {
        for (SurveyObserver subscriber : surveySubscribers) {
            if (survey.containEmployee((Employee) subscriber) || survey.checkOwner((Employee) subscriber)) {
                ((Employee) subscriber).update();
            }
        }
    }
}