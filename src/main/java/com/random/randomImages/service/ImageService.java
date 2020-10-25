package com.random.randomImages.service;

import com.google.common.io.ByteStreams;
import com.random.randomImages.model.RandomLinks;
import com.random.randomImages.repository.RandomImageDao;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.amqp.RabbitMetrics;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.net.HttpURLConnection;

@Service
public class ImageService {
    @Autowired
    private RandomImageDao randomImageDao;

    @Autowired
    private Environment environment;

    public RandomLinks getImageURL(Integer id){
        return randomImageDao.getLink(id);
    }

    public ResponseEntity<byte[]> getImage(Integer id){
        ResponseEntity<byte[]> responseEntity = null;
        try{
            String uri = environment.getProperty("url");
            int a = (int)(Math.random()*1000);
            int b = (int)(Math.random()*1000);
            uri = uri.concat(String.valueOf(a)).concat("/").concat(String.valueOf(b));
            java.net.URL url = new java.net.URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream in = connection.getInputStream();
            byte[] media = ByteStreams.toByteArray(in);
            responseEntity = new ResponseEntity<>(media, HttpStatus.OK);
            RandomLinks randomLinks = new RandomLinks();
            randomLinks.setId(id);
            randomLinks.setLinks(uri);
            saveData(randomLinks);

        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return responseEntity;
    }

    @Transactional
    private void saveData(RandomLinks randomLinks){
        randomImageDao.save(randomLinks);
    }


    public ResponseEntity<byte[]> getExistingImageUrl(Integer id) {
        RandomLinks randomLinks = randomImageDao.getLink(id);
        ResponseEntity<byte[]> responseEntity = null;
        try{
            java.net.URL url = new java.net.URL(randomLinks.getLinks());
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream in = connection.getInputStream();
            byte[] media = ByteStreams.toByteArray(in);
            responseEntity = new ResponseEntity<>(media, HttpStatus.OK);

        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return responseEntity;
    }
}
