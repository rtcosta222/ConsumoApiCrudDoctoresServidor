/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.List;
import models.Doctor;
import services.ServiceDoctores;

/**
 *
 * @author lscar
 */
public class ControllerDoctor {
    
    ServiceDoctores service;
    
    public ControllerDoctor(){
        this.service = new ServiceDoctores();
    }
    
    public String getDoctores() throws IOException{
        List<Doctor> doctores = this.service.getDoctores();
        String html = "";
        for(Doctor d: doctores){
            html += "<tr>";
            html += "<td>" + d.getHcod() + "</td>";
            html += "<td>" + d.getDcod() + "</td>";
            html += "<td>" + d.getApe() + "</td>";
            html += "<td>" + d.getEspec() + "</td>";
            html += "<td>" + d.getSal()+ "</td>";
            html += "<td>" + "<button type='submit' name='eliminar' value='" + 
                    d.getDcod() + "'>Eliminar</button></td>";
            html += "</tr>";
        }
        return html;
    }
    
    public String insertDoctor(int hcod, int dcod, String ape, String espec, int sal) throws IOException{
        int code = this.service.insertDoctor(hcod, dcod, ape, espec, sal);
        return "<h1>Doctor insertado. Código" + code + "</h1>";
    }
    
    public String deleteDoctor(String iddoc){
        int code = this.service.eliminarDoctor(iddoc);
        return "<h1>Doctor eliminado. Code(" + code + "</h1>";
    }
}
