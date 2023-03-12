package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainController {
    private JSONArray data;
    @FXML
    private TextField titleField;
    @FXML
    private TextField abstractField;
    @FXML
    private TextField fromYear;
    @FXML
    private TextField toYear;

    public void init() throws IOException, ParseException {
        this.data =  (JSONArray) new JSONParser().parse(new FileReader("src/main/resources/data.json"));
    }

    @SuppressWarnings("unchecked")
    public void search() throws IOException {
        JSONArray results = new JSONArray();
        String[] titleKeyWords = null;
        String[] abstractKeyWords = null;
        int fromYearInt = 0;
        int toYearInt = 2023;

        if(!"".equals(titleField.getText())){titleKeyWords = titleField.getText().split(",");}
        if(!"".equals(abstractField.getText())){abstractKeyWords = abstractField.getText().split(",");}
        if(!"".equals(fromYear.getText())){fromYearInt = Integer.parseInt(fromYear.getText());}
        if(!"".equals(toYear.getText())){toYearInt = Integer.parseInt(toYear.getText());}

        for(Object each : data){
            JSONObject paper = (JSONObject) each;
            int paperYear = Integer.parseInt(paper.get("year").toString());

            // if year is not invalid
            if(!(paperYear>=fromYearInt && paperYear<=toYearInt)){
                //Check Paper Year
                continue;
            }

            // if title is not valid
            if(titleKeyWords !=null && !Arrays.stream(titleKeyWords).allMatch(paper.get("title").toString()::contains)){
                continue;
            }


            if(abstractKeyWords!=null && paper.get("abstract")==null){
                continue;
            }
            // if abstract is not valid
            if(abstractKeyWords !=null && !Arrays.stream(abstractKeyWords).allMatch(paper.get("abstract").toString()::contains) ){
                continue;
            }

            results.add(paper);
        }



        //Write JSON file
        FileWriter file = new FileWriter("result.json");
        //We can write any JSONArray or JSONObject instance to the file

        file.write(results.toJSONString().replaceAll("\\/",""));
        file.flush();
        file.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Successfully");
        alert.setContentText("The search Result has been exported successfully");
        clearAllField();
        alert.showAndWait();
    }

    public void clearAllField(){
        titleField.setText("");
        abstractField.setText("");
        fromYear.setText("");
        toYear.setText("");
    }


}
