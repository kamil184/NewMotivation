package com.kamil184.newmotivate.util;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kamil184.newmotivate.model.Tag;
import com.kamil184.newmotivate.model.ToDoItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class TodoStoreRetrieveData {
    private Context context;
    private String fileName;

    public TodoStoreRetrieveData(Context context, String filename) {
        this.context = context;
        fileName = filename;
    }

    public static JSONArray toJSONArray(ArrayList<ToDoItem> items) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (ToDoItem item : items) {
            Gson gson = new GsonBuilder().create();
            JSONObject jsonObject = new JSONObject(gson.toJson(item));
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    public void saveToFile(ArrayList<ToDoItem> items) throws JSONException, IOException {
        FileOutputStream fileOutputStream;
        OutputStreamWriter outputStreamWriter;
        fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        outputStreamWriter.write(toJSONArray(items).toString());
        outputStreamWriter.close();
        fileOutputStream.close();
    }

    public ArrayList<ToDoItem> loadFromFile() throws IOException, JSONException {
        ArrayList<ToDoItem> items = new ArrayList<>();
        BufferedReader bufferedReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(fileName);
            StringBuilder builder = new StringBuilder();
            String line;
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }

            JSONArray jsonArray = (JSONArray) new JSONTokener(builder.toString()).nextValue();
            for (int i = 0; i < jsonArray.length(); i++) {
                Gson gson = new GsonBuilder().create();
                ToDoItem item = gson.fromJson(jsonArray.getJSONObject(i).toString(), ToDoItem.class);
                items.add(item);
            }


        } catch (FileNotFoundException fnfe) {
            //do nothing about it
            //file won't exist first time app is run
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }

        }
        return items;
    }

    public static ArrayList<ToDoItem> getLocallyStoredData(TodoStoreRetrieveData storeRetrieveData) {
        ArrayList<ToDoItem> items = null;

        try {
            items = storeRetrieveData.loadFromFile();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        if (items == null) {
            items = new ArrayList<>();
        }
        return items;

    }
}
