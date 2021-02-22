package com.quality.booking.utils.jsonEngine;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quality.booking.exceptions.JsonEngineException;
import org.springframework.lang.Nullable;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class JsonEngine {

    public static <T> List<T> readFileDB(String path, TypeReference tf) throws JsonEngineException{
        List<T> result = null;
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

    public static <T> void writeDatabase(String pathDB, List<T> list) throws JsonEngineException {
        File fileDb = null;

        try{
            fileDb = ResourceUtils.getFile(pathDB);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(fileDb, list);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
