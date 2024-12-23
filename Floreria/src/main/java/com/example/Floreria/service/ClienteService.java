package com.example.Floreria.service;

import com.example.Floreria.dao.ClienteDAO;
import com.example.Floreria.model.Cliente;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteDAO clienteDAO;

    // El constructor ya no necesita @Autowired
    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    /**
     * Agrega un cliente con los detalles proporcionados.
     *
     * @param nombre     El nombre del cliente.
     * @param apellido   El apellido del cliente.
     * @param direccion  La dirección del cliente.
     * @param telefono   El teléfono del cliente.
     * @param dni        El DNI único del cliente.
     * @param correo     El correo único del cliente.
     * @return Verdadero si se agrega correctamente, falso en caso contrario.
     */
    public boolean agregarCliente(String nombre, String apellido, String direccion, String telefono, String dni, String correo) throws SQLException, ClassNotFoundException {
        return clienteDAO.insertarCliente(nombre, apellido, direccion, telefono, dni, correo);
    }

    /**
     * Obtiene una lista de todos los clientes.
     *
     * @return Lista de clientes.
     */
    public List<Cliente> obtenerTodosLosClientes() throws SQLException {
        return clienteDAO.obtenerClientes();
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param id El ID del cliente a eliminar.
     * @return Verdadero si se elimina correctamente, falso en caso contrario.
     */
    public boolean eliminarCliente(int id) throws SQLException, ClassNotFoundException {
        return clienteDAO.eliminarCliente(id);
    }

    /**
     * Obtiene un cliente por su ID.
     *
     * @param id El ID del cliente.
     * @return Un objeto Cliente si se encuentra, null en caso contrario.
     */
    public Cliente obtenerClientePorId(int id) {
        return clienteDAO.obtenerClientePorId(id);
    }

    /**
     * Actualiza los detalles de un cliente existente.
     *
     * @param cliente El objeto Cliente con los datos actualizados.
     * @return Verdadero si se actualiza correctamente, falso en caso contrario.
     */
    public boolean actualizarCliente(Cliente cliente) {
        return clienteDAO.actualizarCliente(cliente);
    }
}
