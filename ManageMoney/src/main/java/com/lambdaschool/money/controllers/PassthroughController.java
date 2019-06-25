package com.lambdaschool.money.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.money.models.Dog;
import com.lambdaschool.money.models.User;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class PassthroughController {
    @ApiOperation(value = "TEST FOR RETURNING SOME DATA VIA ANOTHER API, WILL BE DELETED, DON'T WORRY ABOUT IT", response = Dog.class, responseContainer = "List")
    @GetMapping(value = "/dogs")
    public ResponseEntity<?> getAllDogs()
    {

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try
        {
            URL restAPIUrl = new URL("https://bobbyad-java-dogs.herokuapp.com/dogs/dogs/dogs");
            connection = (HttpURLConnection) restAPIUrl.openConnection();
            connection.setRequestMethod("GET");

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                jsonData.append(line);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ArrayList<Dog> dogList =
                    objectMapper.readValue(jsonData.toString(), new TypeReference<ArrayList<Dog>>(){});

            return new ResponseEntity<>(dogList, HttpStatus.OK);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly(reader);
        }

        return null;
    }
}
