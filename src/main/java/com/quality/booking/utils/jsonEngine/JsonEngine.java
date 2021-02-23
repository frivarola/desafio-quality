package com.quality.booking.utils.jsonEngine;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.quality.booking.exceptions.JsonEngineException;
import org.springframework.lang.Nullable;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * this class contains methods for support json manipulation
 */
public class JsonEngine {

    /**
     * this method allow read json file and get list of objects
     * @param path json file
     * @param tf type to map
     * @param <T> object generalize
     * @return List of T
     * @throws JsonEngineException
     */
    public static <T> List<T> readFileDB(String path, TypeReference tf) throws JsonEngineException{
        List<T> result = new ArrayList<>();
        File fileDb = null;
        try{
            fileDb = ResourceUtils.getFile(path);
            ObjectMapper mapper = new ObjectMapper();
            result = (List<T>) mapper.readValue(fileDb, tf);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * this method allow overwrite json file with a List of T objects
     * @param pathDB json file
     * @param list objets to save
     * @param <T>
     * @throws JsonEngineException
     */
    public static <T> void writeDatabase(String pathDB, List<T> list) throws JsonEngineException {
        File fileDb = null;

        try{
            fileDb = ResourceUtils.getFile(pathDB);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(fileDb, list);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * this method allow append objects to a json file
     * @param pathDB json file
     * @param objectJson to write on json file
     * @param <T>
     * @throws JsonEngineException
     */
    public static <T> void appendDatabase(String pathDB, T objectJson) throws JsonEngineException {
        File fileDb = null;


        try{
            fileDb = ResourceUtils.getFile(pathDB);

            FileWriter fw = new FileWriter(fileDb, true);
            ObjectMapper mapper = new ObjectMapper();
            List<T> arrObjectsJson = readFileDB(pathDB, new TypeReference<List<T>>(){});
            arrObjectsJson.add(objectJson);
            writeDatabase(pathDB, arrObjectsJson);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
