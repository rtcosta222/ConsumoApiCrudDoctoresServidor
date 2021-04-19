/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import models.Doctor;

/**
 *
 * @author lscar
 */
public class ServiceDoctores {
    String urlapi;
    
    public ServiceDoctores(){
        this.urlapi = "https://apidoctoresrosselli.azurewebsites.net/";
    }
    
    private String readStreamHttp(InputStream stream) throws IOException{
        BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
        StringBuffer data = new StringBuffer();
        String linea = "";
        String separator = "";
        while((linea = buffer.readLine()) != null){
            data.append(separator + linea);
            separator= "\n";
        }
        return data.toString();
    }
    
    private String getHttpRequest(String request) throws MalformedURLException, IOException{
        URL url = new URL(this.urlapi + request);
        HttpURLConnection cn = (HttpURLConnection) url.openConnection();
        cn.setRequestMethod("GET");
        cn.setRequestProperty("Accept", "application/json");
        if(cn.getResponseCode() == 200){
            InputStream stream = cn.getInputStream();
            String data = this.readStreamHttp(stream);
            cn.disconnect();
            return data;
        } else{
            cn.disconnect();
            return null;
        }
    }
    
    public List<Doctor> getDoctores() throws IOException{
        String request = "api/cruddoctor";
        String datosjson = this.getHttpRequest(request);
        Gson converter = new Gson();
        List<Doctor> doctores = converter.fromJson(datosjson,
                        new TypeToken<List<Doctor>>(){}.getType());
        return doctores;
    }
    
    public Doctor getDoctor(String id) throws IOException{
        String request = "api/cruddoctor/" + id;
        String datosjson = this.getHttpRequest(request);
        Gson converter = new Gson();
        Doctor doctor = converter.fromJson(datosjson, Doctor.class);
        return doctor;
    }
    
    public int insertDoctor(int hcod, int dcod, String ape, String espec, int sal) throws MalformedURLException, IOException{
        String request = "api/cruddoctor/post";
        URL url = new URL(this.urlapi + request);
        
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("POST");
        conexion.setRequestProperty("Content-Type", "application/json");
        Doctor doc = new Doctor(hcod, dcod, ape, espec, sal);
        Gson converter = new Gson();
        String doctorjson = converter.toJson(doc);
        // Indicamos al servicio que enviamos información.
        conexion.setDoOutput(true);
        OutputStream writer = conexion.getOutputStream();
        writer.write(doctorjson.getBytes());
        writer.flush();
        writer.close();
        int statuscode = conexion.getResponseCode();
        conexion.disconnect();
        return statuscode;
    }
    
    public int updateDoctor(int hcod, int dcod, String ape, String espec, int sal) throws MalformedURLException, IOException{
        String request = "api/cruddoctor/put";
        URL url = new URL(this.urlapi + request);
        
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("PUT");
        conexion.setRequestProperty("Content-Type", "application/json");
        Doctor doc = new Doctor(hcod, dcod, ape, espec, sal);
        Gson converter = new Gson();
        String doctorjson = converter.toJson(doc);
        // Indicamos al servicio que enviamos información.
        conexion.setDoOutput(true);
        OutputStream writer = conexion.getOutputStream();
        writer.write(doctorjson.getBytes());
        writer.flush();
        writer.close();
        int statuscode = conexion.getResponseCode();
        conexion.disconnect();
        return statuscode;
    }

    public int eliminarDoctor(String id) throws MalformedURLException, IOException{
        // Como en Ajax
        String request = "api/cruddoctor/delete/" + id;
        URL url = new URL(this.urlapi + request);
        HttpURLConnection conexion = (HttpURLConnection)url.openConnection();
        conexion.setRequestMethod("DELETE");
        // Analisa la respuesta
        int statuscode = conexion.getResponseCode();
        conexion.disconnect();
        return statuscode;
    }
}





