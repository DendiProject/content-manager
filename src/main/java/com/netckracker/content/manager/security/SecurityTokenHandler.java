/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.security;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.HttpURLConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author ArtemShevelyukhin
 */

@Component
@Profile("prod")
public class SecurityTokenHandler {

  private String token;
  
  @Value("${service.id}")
  String clientId;
  @Value("${service.secret}")
  String clientSecret;
  @Value("${idp.url}")
  String idpURL;
  
  @Autowired
  SecurityTokenHandler tokenHandler;

  public SecurityTokenHandler() {
    this.token = null;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

//  public void updateAccessToken() {
//    try {
//      String client = clientId + ":" + clientSecret;
//      byte[] encodedBytes = Base64.getEncoder().encode(client.getBytes());
//      String str = new String(encodedBytes, StandardCharsets.UTF_8);
//      String encoded = "Basic " + str;
//
//      HttpClient httpclient = HttpClients.createDefault();
//      HttpPost httppost = new HttpPost("http://"+idpURL+"/oauth/token");
//
//      httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
//      httppost.addHeader("Authorization", encoded);
//      httppost.addHeader("Cache-Control", "no-cache");
//
//      List<NameValuePair> params = new ArrayList<NameValuePair>();
//      params.add(new BasicNameValuePair("grant_type", "client_credentials"));
//      params.add(new BasicNameValuePair("scope", "read"));
//      httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//      HttpResponse response = httpclient.execute(httppost);
//      HttpEntity entity = response.getEntity();
//
//      if (entity != null) {
//
//        InputStream instream = entity.getContent();
//        try {
//
//          Gson gson = new Gson();
//          String responseString = EntityUtils.toString(entity, "UTF-8");
//          JSONObject jsonObj = new JSONObject(responseString);
//
//          tokenHandler.setToken(jsonObj.get("access_token").toString());
//
//          System.out.println("UpdatedToken =  " + tokenHandler.getToken());
//
//        } finally {
//          instream.close();
//        }
//      }
//
//    } catch (UnsupportedEncodingException ex) {
//      Logger.getLogger(StartupHousekeeper.class.getName()).log(Level.SEVERE, null, ex);
//    } catch (IOException ex) {
//      Logger.getLogger(StartupHousekeeper.class.getName()).log(Level.SEVERE, null, ex);
//    }
// }

  public int verifySecureToken(String secureToken) {
    int res = 0;
    if (secureToken != null){
    try {
      URL url = new URL(
              "http://"+idpURL+"/idpsecure/verifyToken" + "?access_token=" + tokenHandler.getToken());
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("POST");
      con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
      con.setRequestProperty("secureToken", secureToken);
      con.setDoOutput(true);

      int responseCode = con.getResponseCode();
      System.out.println(" verifySecureToken responseCode =  " + responseCode);
      res = con.getResponseCode();
    
    } catch (MalformedURLException ex) {
      Logger.getLogger(SecurityTokenHandler.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(SecurityTokenHandler.class.getName()).log(Level.SEVERE, null, ex);
    } } else {
      res = 401;
    }
    return res;
  }
}
