/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.model.Venta;
import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;
import javafx.scene.control.Alert;

/**
 *
 * @author rsgar
 */
public class Vendedor implements Serializable{

    int ID;
    private String nombres;
    private String apellidos;
    private String organizacion;
    private String correo;
    private String clave;
    private ArrayList<Vehiculo> vehiculos;
    private static final long serialVersionUID = 8799656478674716638L;

    public Vendedor() {
    }

    public Vendedor(int ID, String nombres, String apellidos, String organizacion, String correo, String clave) {
        this.ID = ID;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo = correo;
        this.clave = clave;
        this.vehiculos = new ArrayList<>();
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

    public ArrayList<Vehiculo> getVehiculos() {
        return this.vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.correo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vendedor other = (Vendedor) obj;
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        return true;
    }
        
    public static Vendedor searchByID(ArrayList<Vendedor> vendedores, int id) {
        for (Vendedor v : vendedores) {
            if (v.ID == id) {
                return v;
            }
        }
        return null;
    }
    public static Vendedor validarUsuario(String correo, String clave, ArrayList<Vendedor> vendedores) throws PasswordException, NoSuchAlgorithmException{
         boolean validarcorreo = false;
        boolean validarclave = false;
        Vendedor vendedor = null;
        for (int i = 0; i < vendedores.size(); i++) {
            String clave_i = vendedores.get(i).getClave();//clave del comprador que estamos tomando
            String correo_i = vendedores.get(i).getCorreo();//correo del comprador que estamos tomando            
            if (correo_i.equals(correo)) {
                validarcorreo = true;
                if (clave_i.equals(Util.toHexString(Util.getSHA(clave)))){
                    validarclave = true;
                    vendedor = vendedores.get(i);
                } 
            } 
        }
        if(validarcorreo == true){
            if(validarclave == true){
                return vendedor;
            } else {
                throw new PasswordException("PasswordException");
            }
        } else {
            return vendedor;
        }
    }
    public ArrayList<Oferta> getOfertas(){
        ArrayList<Oferta> ofertas = new ArrayList<>();
        for(Vehiculo vehiculo: this.getVehiculos()){
            if(!vehiculo.getOfertas().isEmpty()){
                for(Oferta o: vehiculo.getOfertas()){
                    ofertas.add(o);
                }
            }
        }
        return ofertas;
    }
    
    public static boolean validarRegistro(String correo, ArrayList<Vendedor> vendedores) throws  UserException, NoSuchAlgorithmException{
        for (int i = 0; i < vendedores.size(); i++) {
            String correo_i = vendedores.get(i).getCorreo();//correo del comprador que estamos tomando            
            if (correo_i.equals(correo)) {
                throw new UserException("UserException");
            } 
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Vendedor<" + this.ID + ">{Nombres=" + this.nombres + ", Apellidos=" + this.apellidos + ", Organizacion=" + this.organizacion + ", Correo=" + this.correo + ", Clave=" + this.clave + "}";
    }

public static void eliminarVendedor(ArrayList<Vendedor> vendedores, Vendedor vendedor) throws IOException
    {
        for(int i = 0; i < vendedores.size(); i++)
        {
            if (vendedores.get(i).equals(vendedor))
            {
                vendedores.remove(i);
            }
        }
        Vendedor.saveListToFileSer("src\\main\\resources\\doc\\vendedores.ser", vendedores);

    }
    
    public static int nextID(ArrayList<Vendedor> vendedores){
        int max = 0;
        for(Vendedor vendedor: vendedores){
            if (vendedor.getID() > max) {
                max = vendedor.getID();
            }
        }
        return (max+1);        
    }
    
    public static void registrarNuevoVendedor(String nombres, String apellidos, String organizacion, String correo, String clave, ArrayList<Vendedor> vendedores) throws VendedorException{
        int id = Vendedor.nextID(vendedores); 
        Vendedor v = new Vendedor(id, nombres, apellidos, organizacion, correo, clave);
        if (vendedores.contains(v) == false) {
            vendedores.add(v);
            Vendedor.saveListToFileSer("src\\main\\resources\\doc\\vendedores.ser", vendedores);
        } else {
            throw new VendedorException("");
        }

    }
    
    public static void saveListToFileSer(String nomfile, ArrayList<Vendedor> vendedores){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(nomfile)))){
            out.writeObject(vendedores);
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
        
    public static ArrayList<Vendedor> readListFromFileSer(String nomfile) throws FileNotFoundException{
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(nomfile)))){
            ArrayList<Vendedor> vendedores = (ArrayList<Vendedor>)in.readObject();
            in.close();
            return vendedores;
        } catch (IOException | ClassNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo.");
            a.show();
        }
        return null;
    }

    public void cambiarClave(ArrayList<Vendedor> vendedores, String clave) throws NoSuchAlgorithmException{
        for(int i = 0; i < vendedores.size(); i++)
        {
            if (vendedores.get(i).equals(this))
            {
                vendedores.get(i).setClave(Util.toHexString(Util.getSHA(clave)));
            }
        }
        Vendedor.saveListToFileSer("src\\main\\resources\\doc\\compradores.ser", vendedores);
    }
}
