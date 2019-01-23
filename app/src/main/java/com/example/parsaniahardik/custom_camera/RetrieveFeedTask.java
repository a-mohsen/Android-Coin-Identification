package com.example.parsaniahardik.custom_camera;

import android.os.AsyncTask;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

class RetrieveFeedTask extends AsyncTask {

    private String result;
    private String img;

    public RetrieveFeedTask(String img) {
        this.img = img;
    }

    @Override
    protected String doInBackground(Object[] objects) {
        try {
            IamOptions options = new IamOptions.Builder()
                    .apiKey("R7IoaDm47TiC6k-XqpT9FZXvGtUGT2Z26VoZafOuJX0B").url("https://gateway.watsonplatform.net/visual-recognition/api")
                    .build();
            VisualRecognition service = new VisualRecognition("2018-03-19", options);
            InputStream imagesStream = new FileInputStream(img);
            ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                    .imagesFile(imagesStream)
                    .imagesFilename(img)
                    .threshold((float) 0.6)
                    .classifierIds(Arrays.asList("DefaultCustomModel_758436311"))
                    .build();

            ClassifiedImages output = service.classify(classifyOptions).execute();
            result = output.toString();
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getResult() {
        return result;
    }
}