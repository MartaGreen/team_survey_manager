package pages;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import survey.Survey;
import survey.SurveyOption;

import java.util.ArrayList;

public class GraphController {
    public GraphController(Survey survey) {
        ArrayList<SurveyOption> options = survey.getSurveyOption();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> chart =
                new BarChart<String, Number>(xAxis,yAxis);
        xAxis.setLabel("Options");
        yAxis.setLabel("Vote %");

        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName(survey.getSurveyName());
        for (SurveyOption option: options) {
            series.getData().add(new XYChart.Data<String, Number>(option.getName(), option.percentage * 100));
        }

        Scene scene  = new Scene(chart,800,600);
        chart.getData().addAll(series);

        start(scene);
    }

    private void start(Scene scene) {
        Stage graphStage = new Stage();
        graphStage.setScene(scene);
        graphStage.show();
    }
}
