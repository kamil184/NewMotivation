package com.kamil184.newmotivate.util;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kamil184.newmotivate.model.Tag;

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
import java.util.List;

public class TagStoreRetrieveData {
    private Context context;
    private String fileName;

    public TagStoreRetrieveData(Context context, String filename) {
        this.context = context;
        fileName = filename;
    }

    public static JSONArray toJSONArray(List<Tag> tags) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Tag tag : tags) {
            Gson gson = new GsonBuilder().create();
            JSONObject jsonObject = new JSONObject(gson.toJson(tag));
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    public void saveToFile(List<Tag> items) throws JSONException, IOException {
        FileOutputStream fileOutputStream;
        OutputStreamWriter outputStreamWriter;
        fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        outputStreamWriter.write(toJSONArray(items).toString());
        outputStreamWriter.close();
        fileOutputStream.close();
    }

    public List<Tag> loadFromFile() throws IOException, JSONException {
        List<Tag> tags = new ArrayList<>();
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
                Tag tag = gson.fromJson(jsonArray.getJSONObject(i).toString(), Tag.class);
                tags.add(tag);
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
        return tags;
    }

    public static List<Tag> getLocallyStoredData(TagStoreRetrieveData storeRetrieveData) {
        List<Tag> items = null;

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
