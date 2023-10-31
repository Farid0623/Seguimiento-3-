package Banco;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Banco {
    private BlockingQueue<Cliente> filaPreferencial;
    private BlockingQueue<Cliente> filaComunitaria;

    public Banco() {
        filaPreferencial = new ArrayBlockingQueue<>(50);
        filaComunitaria = new ArrayBlockingQueue<>(50);
    }

    public void agregarCliente(Cliente cliente) {
        try {
            if (cliente.esPreferencial()) {
                filaPreferencial.put(cliente);
                System.out.println("Cliente preferencial " + cliente.getId() + " en la fila preferencial.");
            } else {
                filaComunitaria.put(cliente);
                System.out.println("Cliente comunitario " + cliente.getId() + " en la fila comunitaria.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Cliente atenderClientePreferencial() throws InterruptedException {
        return filaPreferencial.take();
    }

    public Cliente atenderClienteComunitario() throws InterruptedException {
        return filaComunitaria.take();
    }
