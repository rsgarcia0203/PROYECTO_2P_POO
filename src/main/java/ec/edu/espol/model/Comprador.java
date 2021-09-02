/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.Alert;

/**
 *
 * @author rsgar
 */
public class Comprador {

    int ID;
    String nombres;
    String apellidos;
    String organizacion;
    String correo;
    String clave;

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

    public void saveFile(String nomFile) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))) {
            pw.println(this.ID + "|" + this.nombres + "|" + this.apellidos + "|" + this.organizacion + "|" + this.correo + "|" + Util.toHexString(Util.getSHA(this.clave)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Comprador> readFile(String nomFile) throws FileNotFoundException {

        ArrayList<Comprador> comprador = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nomFile))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                Comprador c = new Comprador(Integer.parseInt(arreglo[0]), arreglo[1], arreglo[2], arreglo[3], arreglo[4], arreglo[5]);
                comprador.add(c);
            }
        } 
        return comprador;
    }
    
    public static Comprador validarUsuario(String correo, String clave, ArrayList<Comprador> compradores) throws PasswordException, UserException, NoSuchAlgorithmException{
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
            throw new UserException("UserException");
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
        Util.limpiarArchivo("comprador.txt");
        for(Comprador c: compradores)
        {
            c.saveFile("comprador.txt");
        }
    }      

    
    public static void registrarNuevoComprador(String nombres, String apellidos, String organizacion, String correo, String clave, ArrayList<Comprador> compradores, String nomfile) throws CompradorException{
        int id = Util.nextID(nomfile); 
        Comprador c = new Comprador(id, nombres, apellidos, organizacion, correo, clave);
        if (compradores.contains(c) == false) {
            c.saveFile(nomfile);
        } else {
            throw new CompradorException("");
        }

    }

    public static void OfertarVehiculo(Scanner sc, ArrayList<Vehiculo> vehiculos, ArrayList<Comprador> compradores) throws NoSuchAlgorithmException {
        System.out.println("\n=OFERTAR VEHICULO=");
        System.out.println("Ingrese correo: ");
        String correo = sc.next();
        System.out.println("Ingrese clave: ");
        String clave = sc.next();
        boolean validarCorreo = false;
        for (int i = 0; i < compradores.size(); i++) {
            String clave_i = compradores.get(i).getClave();//clave del comprador que estamos tomando
            String correo_i = compradores.get(i).getCorreo();//correo del comprador que estamos tomando

            if (correo_i.equals(correo)) {
                validarCorreo = true;
                if (clave_i.equals(Util.toHexString(Util.getSHA(clave)))) {
                    System.out.println("\nBienvenido " + compradores.get(i).getNombres() + " " + compradores.get(i).getApellidos() + " de la organización " + compradores.get(i).getOrganizacion());
                    System.out.println("\n=OFERTAR=");
                    System.out.println("Ingrese el tipo de vehiculo: (si desea revisar todos los tipos, ingrese <todos>)");
                    String tipo = sc.next();
                    if (tipo.equals("automovil") || tipo.equals("motocicleta") || tipo.equals("camioneta") || tipo.equals("todos")) {
                        System.out.println("Ingrese el rango del recorrido: (si desea revisar por todos los recorridos, ingrese 0 en ambas opciones)");
                        System.out.println("Desde: ");
                        double recorridoInicial = sc.nextDouble();
                        System.out.println("Hasta: ");
                        double recorridoFinal = sc.nextDouble();
                        System.out.println("Ingrese el rango del año: (si desea revisar por todos los años, ingrese 0 en ambas opciones)");
                        System.out.println("Desde: ");
                        int añoInicial = sc.nextInt();
                        System.out.println("Hasta: ");
                        int añoFinal = sc.nextInt();
                        System.out.println("Ingrese el rango del precio:");
                        System.out.println("Desde: ");
                        double precioInicial = sc.nextDouble();
                        System.out.println("Hasta: ");
                        double precioFinal = sc.nextDouble();
                        if (recorridoInicial != 0 && recorridoFinal != 0) {
                            vehiculos = Vehiculo.vehiculosxRecorrido(vehiculos, recorridoInicial, recorridoFinal);
                        }

                        if (añoInicial != 0 && añoFinal != 0) {
                            vehiculos = Vehiculo.vehiculosxAño(vehiculos, añoInicial, añoFinal);
                        }

                        if (precioInicial != 0 && precioFinal != 0) {
                            vehiculos = Vehiculo.vehiculosxPrecio(vehiculos, precioInicial, precioFinal);
                        }
                        double precioOfertado;
                        if (!vehiculos.isEmpty()) {
                            int cont = 0;
                            ArrayList<Vehiculo> vehicles = new ArrayList<>();
                            switch (tipo) {
                                case "automovil":
                                    for (int e = 0; e < vehiculos.size(); e++) {
                                        if (vehiculos.get(e) instanceof Automovil) {
                                            cont++;
                                            vehicles.add(vehiculos.get(e));
                                        }
                                    }
                                    if (cont == 0) {
                                        System.out.println("No existen vehiculos con esos parametros de busqueda.");
                                    } else {
                                        for (int e = 0; e < vehicles.size(); e++) {
                                            System.out.println("\n" + vehicles.get(e));
                                            String op = Util.ofertarVehiculos(sc, e, vehicles.size() - 1);
                                            if (op.equals("anterior")) {
                                                i -= 2;
                                            } else if (op.equals("ofertar")) {
                                                System.out.println("Ingrese el precio a ofertar por el vehiculo: ");
                                                precioOfertado = sc.nextDouble();
                                                Oferta.registrarNuevaOferta(vehicles.get(e), compradores.get(i), precioOfertado, "oferta.txt");
                                                break;
                                            } else if (op.equals("regresar")) {
                                                break;
                                            }
                                        }
                                    }
                                    break;

                                case "camioneta":
                                    for (int e = 0; e < vehiculos.size(); e++) {
                                        if (vehiculos.get(e) instanceof Camioneta) {
                                            cont++;
                                            vehicles.add(vehiculos.get(e));
                                        }
                                    }
                                    if (cont == 0) {
                                        System.out.println("No existen vehiculos con esos parametros de busqueda.");
                                    } else {
                                        for (int e = 0; e < vehicles.size(); e++) {
                                            System.out.println("\n" + vehicles.get(e));
                                            String op = Util.ofertarVehiculos(sc, e, vehicles.size() - 1);
                                            if (op.equals("anterior")) {
                                                i -= 2;
                                            } else if (op.equals("ofertar")) {
                                                System.out.println("Ingrese el precio a ofertar por el vehiculo: ");
                                                precioOfertado = sc.nextDouble();
                                                Oferta.registrarNuevaOferta(vehicles.get(e), compradores.get(i), precioOfertado, "oferta.txt");
                                                break;
                                            } else if (op.equals("regresar")) {
                                                break;
                                            }
                                        }
                                    }
                                    break;

                                case "motocicleta":
                                    for (int e = 0; e < vehiculos.size(); e++) {
                                        if (vehiculos.get(i) instanceof Motocicleta) {
                                            cont++;
                                            vehicles.add(vehiculos.get(e));
                                        }
                                    }
                                    if (cont == 0) {
                                        System.out.println("No existen vehiculos con esos parametros de busqueda.");
                                    } else {
                                        for (int e = 0; e < vehicles.size(); e++) {
                                            System.out.println("\n" + vehicles.get(e));
                                            String op = Util.ofertarVehiculos(sc, e, vehicles.size() - 1);
                                            if (op.equals("anterior")) {
                                                i -= 2;
                                            } else if (op.equals("ofertar")) {
                                                System.out.println("Ingrese el precio a ofertar por el vehiculo: ");
                                                precioOfertado = sc.nextDouble();
                                                Oferta.registrarNuevaOferta(vehicles.get(e), compradores.get(i), precioOfertado, "oferta.txt");
                                                break;
                                            } else if (op.equals("regresar")) {
                                                break;
                                            }
                                        }
                                    }
                                    break;

                                default:
                                    for (int e = 0; e < vehiculos.size(); e++) {
                                        System.out.println("\n" + vehiculos.get(e));
                                        String op = Util.ofertarVehiculos(sc, e, vehiculos.size() - 1);
                                        if (op.equals("anterior")) {
                                            e -= 2;
                                        } else if (op.equals("ofertar")) {
                                            System.out.println("Ingrese el precio a ofertar por el vehiculo: ");
                                            precioOfertado = sc.nextDouble();
                                            Oferta.registrarNuevaOferta(vehiculos.get(e), compradores.get(i), precioOfertado, "oferta.txt");
                                            break;
                                        } else if (op.equals("regresar")) {
                                            break;
                                        }
                                    }
                                    break;
                            }
                        } else {
                            System.out.println("No existen vehiculos con esos parametros de busqueda.");
                        }

                    } else {
                        System.out.println("Tipo de vehiculo no valido");
                    }
                }

            } else {
                System.out.println("Clave incorrecta");
            }
        }
        if (validarCorreo == false) {
            System.out.println("Correo incorrecto");
        }
    }
}
