package com.sankholin.mpjapp;

import mpi.MPI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class ProgramMainEntryPoint {

    public static void main(String[] args) {
        ProgramMainEntryPoint p = new ProgramMainEntryPoint();
        p.start(args);
    }

    private void start(String[] args) {

        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        if (rank == 0) {
            System.out.println("Size is: " + size);
            System.out.println("Rank " + rank + " is reading JSON file!");
            readJson();
        }

        System.out.println("Hello from rank [ " + rank + " ]");

        MPI.Finalize();
    }

    private void readJson() {
        JSONParser parser = new JSONParser();

        try {
            InputStream is = getClass().getResourceAsStream("/test.json");
            Object obj = parser.parse(new InputStreamReader(is));

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);

            String name = (String) jsonObject.get("feature");
            System.out.println(name);

            long age = (Long) jsonObject.get("type");
            System.out.println(age);

            JSONArray msg = (JSONArray) jsonObject.get("point");
            for (Object aMsg : msg) {
                System.out.println(aMsg);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
