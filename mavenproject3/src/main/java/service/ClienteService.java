package service;

import dao.ClienteDAO;
import java.sql.SQLException;
import model.Cliente;

public class ClienteService {

    private final ClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
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
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean agregarCliente(String nombre, String apellido, String direccion, String telefono, String dni, String correo) throws SQLException, ClassNotFoundException {
        // Llamar al DAO para insertar el cliente
        return clienteDAO.insertarCliente(nombre, apellido, direccion, telefono, dni, correo);
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param id El ID del cliente a eliminar.
     * @return Verdadero si se elimina correctamente, falso en caso contrario.
     * @throws Exception
     */
    public boolean eliminarCliente(int id) throws Exception {
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
