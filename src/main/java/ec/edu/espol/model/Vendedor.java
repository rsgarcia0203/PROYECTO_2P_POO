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

    public void saveFile(String nomFile) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))) {
            pw.println(this.ID + "|" + this.nombres + "|" + this.apellidos + "|" + this.organizacion + "|" + this.correo + "|" + Util.toHexString(Util.getSHA(this.clave)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Vendedor> readFile(String nomFile) throws FileNotFoundException {

        ArrayList<Vendedor> vendedor = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nomFile))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                Vendedor v = new Vendedor(Integer.parseInt(arreglo[0]), arreglo[1], arreglo[2], arreglo[3], arreglo[4], arreglo[5]);
                vendedor.add(v);
            }
        }
        return vendedor;
    }

    public static Vendedor searchByID(ArrayList<Vendedor> vendedores, int id) {
        for (Vendedor v : vendedores) {
            if (v.ID == id) {
                return v;
            }
        }
        return null;
    }
    public static Vendedor validarUsuario(String correo, String clave, ArrayList<Vendedor> vendedores) throws PasswordException, UserException, NoSuchAlgorithmException{
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
            //throw new UserException("UserException");
        }
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
        Util.limpiarArchivo("vendedor.txt");
        for(Vendedor v: vendedores)
        {
            v.saveFile("vendedor.txt");
        }
    }      
    
    public static void registrarNuevoVendedor(String nombres, String apellidos, String organizacion, String correo, String clave, ArrayList<Vendedor> vendedores, String nomfile) throws VendedorException{
        int id = Util.nextID(nomfile); 
        Vendedor v = new Vendedor(id, nombres, apellidos, organizacion, correo, clave);
        if (vendedores.contains(v) == false) {
            v.saveFile(nomfile);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Usuario registrado.");
            a.show();
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
    

}
