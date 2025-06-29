package org.example.controllers;

import com.mysql.cj.xdevapi.Client;
import jakarta.persistence.EntityManager;
import org.example.Sexo;
import org.example.entities.Cliente;
import org.example.persistence.ClienteJPA;
import org.example.persistence.ConfigJPA;

import java.time.LocalDate;
import java.util.Scanner;

public class ClienteController {
    static Scanner sc = new Scanner(System.in);
    static ClienteJPA clienteJPA = new ClienteJPA();

    public static void agregarCliente() {
        String nombre;
        do {
            System.out.println("Ingrese el nombre: ");
            nombre = sc.nextLine();
            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacio.");
            }
        } while (nombre.isEmpty());


        System.out.println("Ingrese los apellidos: ");
        String apellidos = sc.nextLine();

        System.out.println("Ingres el sexo (1) HOMBRE, (2) MUJER, (3) OTRO.");
        int opcion = sc.nextInt();
        sc.nextLine();
        Sexo sexo;
        switch (opcion){
            case 1:
                sexo = Sexo.HOMBRE;
                break;
            case 2:
                sexo = Sexo.MUJER;
                break;
            default:
                sexo = Sexo.OTRO;
        }


        System.out.println("Ingrese la ciudad: ");
        String ciudad = sc.nextLine();

        LocalDate fechaNacimiento = null;
        while (fechaNacimiento == null) {
            System.out.println("Ingrese la fecha nacimiento aaaa-mm-dd: ");
            String fechaTexto = sc. nextLine();
            try{
                fechaNacimiento = LocalDate.parse(fechaTexto);
            }  catch (Exception e)  {
                System.out.println("Formato invalido, el formato tiene que ser aaaa-mm-dd.");
            }
        }

        String telefono;
        do {
            System.out.println("Ingrese el teléfono: ");
            telefono = sc.nextLine();
            if (telefono.length() != 9) {
                System.out.println("El teléfono tiene que tener exactamente 9 números");
            }
        } while (telefono.length() != 9);

        System.out.println("Ingrese la coreo electrónico: ");
        String email = sc.nextLine();

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellidos(apellidos);
        cliente.setSexo(sexo);
        cliente.setCiudad(ciudad);
        cliente.setFechaNacimiento(fechaNacimiento);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);

        clienteJPA.agregarCliente(cliente);
    }

    public void listarClientes() {
        ClienteJPA.listarClientes();
    }

    public static void actualizarCliente() {
        System.out.println("Ingrese la id del cliente a actualizar: ");
        Long id = sc.nextLong();
        sc.nextLine();

        EntityManager em = ConfigJPA.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
        } else {
            while (true) {
                System.out.println("Ingrese el valor que desea modificar:");
                System.out.println("1 - Nombre");
                System.out.println("2 - Apellidos");
                System.out.println("3 - Sexo");
                System.out.println("4 - Ciudad");
                System.out.println("5 - Fecha nacimiento");
                System.out.println("6 - Telefono");
                System.out.println("7 - Correo electrónico");
                System.out.println("8 - Salir y actulizar cambios");
                System.out.print("Opción: ");

                int modificar = 0;
                modificar = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                switch (modificar) {
                    case 1:
                        String nombreNuevo;
                        do {
                            System.out.println("Ingrese un nombre nuevo: ");
                            nombreNuevo = sc.nextLine();
                            if (nombreNuevo.isEmpty()) {
                                System.out.println("El nombre no puede estar vacio.");
                            }
                        } while (nombreNuevo.isEmpty());
                        cliente.setNombre(nombreNuevo);
                        break;
                    case 2:
                        System.out.println("Ingrese unos apellidos nuevos:");
                        cliente.setApellidos(sc.nextLine());
                        break;
                    case 3:
                        System.out.println("Ingres el sexo (1) HOMBRE, (2) MUJER, (3) OTRO.");
                        Sexo sexo = null;
                        int opcion = sc.nextInt();
                        sc.nextLine();
                        switch (opcion){
                            case 1:
                                sexo = Sexo.HOMBRE;
                                break;
                            case 2:
                                sexo = Sexo.MUJER;
                                break;
                            default:
                                sexo = Sexo.OTRO;
                        }
                        break;
                    case 4:
                        System.out.println("Ingrese una ciudad nueva:");
                        cliente.setCiudad(sc.nextLine());
                        break;
                    case 5:
                        LocalDate fechaNacimiento = null;
                        while (fechaNacimiento == null) {
                            System.out.println("Ingrese la nueva fecha nacimiento aaaa-mm-dd: ");
                            String fechaTexto = sc. nextLine();
                            try{
                                fechaNacimiento = LocalDate.parse(fechaTexto);
                            }  catch (Exception e)  {
                                System.out.println("Formato invalido, el formato tiene que ser aaaa-mm-dd.");
                            }
                        }
                        cliente.setFechaNacimiento(fechaNacimiento);
                        break;
                    case 6:
                        String telefonoNuevo;
                        do {
                            System.out.println("Ingrese un teléfono nuevo: ");

                            telefonoNuevo = sc.nextLine();
                            if (telefonoNuevo.length() != 9) {
                                System.out.println("El teléfono tiene que tener exactamente 9 números");
                            }
                        } while (telefonoNuevo.length() != 9);
                        cliente.setTelefono(telefonoNuevo);
                        break;
                    case 7:
                        System.out.println("Ingrese un correo electrónico nuevo:");
                        cliente.setEmail(sc.nextLine());
                        break;
                    case 8:
                        clienteJPA.actualizarCliente(cliente);
                        return;
                    default:
                        System.out.println("Opción no valida");
                        return;
                }
            }

        }

        em.close();
    }

    public void eliminarCliente() {
        System.out.println("Ingrese la id del cliente a eliminar: ");
        Long id = sc.nextLong();
        sc.nextLine();
        EntityManager em = ConfigJPA.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) {
            em.close();
            ClienteJPA.eliminarCliente(cliente);

            } else {
            System.out.println("Cliente no encontrado");
            em.close();
            }

    }
    public void listarPorCiudad(){
        clienteJPA.consultarCiudad();

    }
}
