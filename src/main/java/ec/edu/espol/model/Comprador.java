/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author rsgar
 */
public class Comprador implements Serializable{

    int ID;
    private String nombres;
    private String apellidos;
    private String organizacion;
    private String correo;
    private String clave;
    private static final long serialVersionUID = 8799656478674716638L;


    public Comprador(int ID, String nombres, String apellidos, String organizacion, String correo, String clave) {
        this.ID = ID;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo = correo;
        this.clave = clave;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOrganizacion() {
        return this.organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public static Comprador validarUsuario(String correo, String clave, ArrayList<Comprador> compradores) throws PasswordException, NoSuchAlgorithmException{
        boolean validarcorreo = false;
        boolean validarclave = false;
        Comprador comprador = null;
        for (int i = 0; i < compradores.size(); i++) {
            String clave_i = compradores.get(i).getClave();//clave del comprador que estamos tomando
            String correo_i = compradores.get(i).getCorreo();//correo del comprador que estamos tomando            
            if (correo_i.equals(correo)) {
                validarcorreo = true;
                if (clave_i.equals(Util.toHexString(Util.getSHA(clave)))){
                    validarclave = true;
                    comprador = compradores.get(i);
                } 
            } 
        }
        if(validarcorreo == true){
            if(validarclave == true){
                return comprador;
            } else {
                throw new PasswordException("PasswordException");
            }
        } else {
            return null;
            //throw new UserException("UserException");
        }
    }
    
    public static boolean validarRegistro(String correo, ArrayList<Comprador> compradores) throws  UserException, NoSuchAlgorithmException{
        for (int i = 0; i < compradores.size(); i++) {
            String correo_i = compradores.get(i).getCorreo();//correo del comprador que estamos tomando            
            if (correo_i.equals(correo)) {
                throw new UserException("UserException");
            } 
        }
        return false;
    }
    
    public static Comprador searchByID(ArrayList<Comprador> compradores, int id) {
        for (Comprador c : compradores) {
            if (c.ID == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Comprador<" + this.ID + ">{Nombres=" + this.nombres + ", Apellidos=" + this.apellidos + ", Organizacion=" + this.organizacion + ", Correo=" + this.correo + ", Clave=" + this.clave + "}";
    }
    
    public static void eliminarComprador(ArrayList<Comprador> compradores, Comprador comprador) throws IOException
    {
        for(int i = 0; i < compradores.size(); i++)
        {
            if (compradores.get(i).equals(comprador))
            {
                compradores.remove(i);
            }
        }
        Comprador.saveListToFileSer("src\\main\\resources\\doc\\compradores.ser", compradores);
    }      

    public static int nextID(ArrayList<Comprador> compradores){
        int max = 0;
        for(Comprador comprador: compradores){
            if (comprador.getID() > max) {
                max = comprador.getID();
            }
        }
        return (max+1);        
    }
    
    public static void registrarNuevoComprador(String nombres, String apellidos, String organizacion, String correo, String clave, ArrayList<Comprador> compradores, String nomfile) throws CompradorException{
        int id = Comprador.nextID(compradores); 
        Comprador c = new Comprador(id, nombres, apellidos, organizacion, correo, clave);
        if (compradores.contains(c) == false) {
            compradores.add(c);
            Comprador.saveListToFileSer("src\\main\\resources\\doc\\compradores.ser", compradores);
        } else {
            throw new CompradorException("");
        }

    }
    
    public static void saveListToFileSer(String nomfile, ArrayList<Comprador> compradores){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(nomfile)))){
            out.writeObject(compradores);
            out.flush();
            out.close();
        } catch (FileNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se encontró el archivo.");
            a.show();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo.");
            a.show();
        } 
    }
    
    public static ArrayList<Comprador> readListFromFileSer(String nomfile){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(nomfile)))){
            ArrayList<Comprador> compradores = (ArrayList<Comprador>)in.readObject();
            in.close();
            return compradores;
        } catch (FileNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se encontró el archivo.");
            a.show();
        } catch (IOException | ClassNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo.");
            a.show();
        }
        return null;
    }

}

