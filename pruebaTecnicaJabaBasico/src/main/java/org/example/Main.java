package org.example;

import jakarta.persistence.*;
import jakarta.persistence.Persistence;
import org.hibernate.type.descriptor.sql.internal.Scale6IntervalSecondDdlType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc = new Scanner(System.in);
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");

    public static void main(String[] args) {

        int opcion = 0;
        while (true) {
            System.out.println("\n1. Agregar nuevo cliente.");
            System.out.println("2. Listar todos los clientes.");
            System.out.println("3. Actualizar información de un cliente.");
            System.out.println("4. Eliminar un cliente.");
            System.out.println("5. Buscar cliente por ciudad.");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    agregarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    actualizarCliente();
                    break;
                case 4:
                    eliminarCliente();
                    break;
                case 5:
                    consultarCiudad();
                    break;
                default:
                    return;


            }

        }


    }

    private static void agregarCliente() {
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
        System.out.println("Ingrese el sexo (hombre, mujer, otro: ");
        String sexo = sc.nextLine();
        System.out.println("Ingrese la ciudad: ");
        String ciudad = sc.nextLine();
        System.out.println("Ingrese la fecha nacimiento aaaa-mm-dd: ");
        String fechaTexto = sc.nextLine();
        LocalDate fechaNacimeinto = LocalDate.parse(fechaTexto);
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

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(new Cliente(nombre, apellidos, sexo, ciudad, fechaNacimeinto, telefono, email));
        em.getTransaction().commit();
        em.close();
        System.out.println("Cliente añadido con exito.");
        em.close();
    }

    private static void listarClientes() {
        EntityManager em = emf.createEntityManager();
        List<Cliente> clientes = em.createQuery("FROM Cliente", Cliente.class).getResultList();
        clientes = em.createQuery("FROM Cliente", Cliente.class).getResultList();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes para listar");
        } else {
            System.out.println("Listado de Clientes:");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);

            }
        }
        em.close();
    }

    private static void actualizarCliente() {
        System.out.println("Ingrese la id del cliente a actualizar: ");
        Long id = sc.nextLong();
        sc.nextLine();
        EntityManager em = emf.createEntityManager();
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
                System.out.println("8 - Salir");

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
                        System.out.println("Ingrese un sexo nuevo:");
                        cliente.setSexo(sc.nextLine());
                        break;
                    case 4:
                        System.out.println("Ingrese una ciudad nueva:");
                        cliente.setCiudad(sc.nextLine());
                        break;
                    case 5:
                        System.out.println("Ingrese una fecha de nacimiento nueva:");
                        String fechaTexto = sc.nextLine();
                        LocalDate fechaNacimeinto = LocalDate.parse(fechaTexto);
                        cliente.setFechaNacimiento(fechaNacimeinto);
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
                    default:
                        return;
                }

                em.getTransaction().begin();

                cliente.setNombre(cliente.getNombre());
                cliente.setApellidos(cliente.getApellidos());
                cliente.setSexo(cliente.getSexo());
                cliente.setCiudad(cliente.getCiudad());
                cliente.setFechaNacimiento(cliente.getFechaNacimiento());
                cliente.setTelefono(cliente.getTelefono());
                cliente.setEmail(cliente.getEmail());

                em.getTransaction().commit();
                System.out.println("Datos actualizados correctamente.");
            }
        }
        em.close();
    }

    private static void eliminarCliente() {
        System.out.println("Ingrese la id del cliente a eliminar: ");
        Long id = sc.nextLong();
        sc.nextLine();
        EntityManager em = emf.createEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) {

            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
            System.out.println("Cliente eliminado correctamente.");
        } else {
            System.out.println("Cliente no encontrado");
        }
        em.close();
    }

    private static void consultarCiudad() {
        System.out.println("Ingrese la ciudad a consultar: ");
        String ciudad = sc.nextLine();
        EntityManager em = emf.createEntityManager();
        List<Cliente> clientes = em.createQuery(
                        "SELECT c FROM Cliente c WHERE c.ciudad = :ciudad", Cliente.class)
                .setParameter("ciudad", ciudad)
                .getResultList();
               if (clientes.isEmpty()) {
            System.out.println("No se encontraron clientes en esa ciudad.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

}