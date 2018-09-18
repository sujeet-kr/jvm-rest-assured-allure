package HelperLibrary;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class DataReader {

    public JsonNode getTestData(String fileName){

        String path = new File("src/test/resources").getAbsolutePath();
        path = path.concat("/Datafiles/"+ fileName + ".yaml");

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        JsonNode json = null;
        try {
            json = mapper.readTree(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }


    public JsonNode getEnvironmentData(){

        String path = new File("src/test/resources").getAbsolutePath();
        path = path.concat("/config.yaml");

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        JsonNode json = null;
        try {
            json = mapper.readTree(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
