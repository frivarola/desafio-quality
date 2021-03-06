package com.quality.booking.utils.jsonEngine;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * this class contains methods for support json manipulation
 * @author frivarola
 */
public class JsonEngine {

    /**
     * this method allow read json file and get list of objects
     * @param path json file
     * @param tf type to map
     * @param <T> object generalize
     * @return List of T
     * @throws Exception json exception
     */
    public static <T> List<T> readFileDB(String path, TypeReference tf) throws Exception{
        List<T> result = new ArrayList<>();
        File fileDb;
        try{
            fileDb = ResourceUtils.getFile(path);
            ObjectMapper mapper = new ObjectMapper();
            result = (List<T>) mapper.readValue(fileDb, tf);

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("No se pudo escribir los datos en la base de datos");
        }

        return result;
    }

    /**
     * this method allow overwrite json file with a List of T objects
     * @param pathDB json file
     * @param list objets to save
     * @param <T> Object of list
     * @throws Exception json fail
     */
    public static <T> void writeDatabase(String pathDB, List<T> list) throws Exception {
        File fileDb;

        try{
            fileDb = ResourceUtils.getFile(pathDB);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(fileDb, list);

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("No se pudo escribir los datos en la base de datos");
        }

    }

    /**
     * this method allow append objects to a json file
     * @param pathDB json file
     * @param objectJson to write on json file
     * @param <T> object to persist
     * @throws Exception exception for json fail
     */
    public static <T> void appendDatabase(String pathDB, T objectJson) throws Exception {

            List<T> arrObjectsJson = readFileDB(pathDB, new TypeReference<List<T>>(){});
            arrObjectsJson.add(objectJson);
            writeDatabase(pathDB, arrObjectsJson);


    }
}
