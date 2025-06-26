package org.example.persistence;

import jakarta.persistence.*;
import org.example.entities.Cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ClienteJPA {

    public void agregarCliente(Cliente cliente) {
        EntityManager em = ConfigJPA.getEntityManager();
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.close();
    }

    public static void listarClientes() {
        EntityManager em = ConfigJPA.getEntityManager();
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

    public static void actualizarCliente(Cliente cliente) {
        EntityManager em = ConfigJPA.getEntityManager();
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
        em.close();
    }

    public static void eliminarCliente(Cliente cliente) {
        EntityManager em = ConfigJPA.getEntityManager();
            em.getTransaction().begin();
            cliente = em.merge(cliente);
            em.remove(cliente);
            em.getTransaction().commit();
            em.close();
    }

    public static void consultarCiudad() {
        System.out.println("Ingrese la ciudad a consultar: ");
        Scanner sc = new Scanner(System.in);
        String ciudad = sc.nextLine();
        EntityManager em = ConfigJPA.getEntityManager();
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