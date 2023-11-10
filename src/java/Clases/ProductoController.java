package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoController {
    private ConexionBaseDeDatos conectorBD;

    public ProductoController() {
        conectorBD = new ConexionBaseDeDatos();
    }

    public String guardarProducto(Producto producto) {
        String sql = "INSERT INTO producto (codigo_producto, nombre_producto, precio, existencia, fecha_vencimiento, id_marca, id_categoria)";
                sql += "VALUES (?,?,?,?,?,?,?)";

        try (Connection conexion = conectorBD.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, producto.getCodigo_producto());
            statement.setString(2, producto.getNombre_producto());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getExistencia());
            statement.setString(5, producto.getFecha_vencimiento());
            statement.setInt(6, producto.getId_marca());
            statement.setInt(7, producto.getId_categoria());

            int resultado = statement.executeUpdate();
            if (resultado > 0) {
                return "Producto guardado exitosamente";
            } else {
                return "Error al guardar el producto";
            }
        } catch (SQLException e) { 
            e.printStackTrace();
            return "Error en la base de datos: " + e.getMessage();
        }
    }

    public void getProductos(StringBuffer respuesta) {
        String sql = "SELECT codigo_producto, nombre_producto, precio, existencia, fecha_vencimiento, id_marca, id_categoria FROM producto";

        try (Connection conexion = conectorBD.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            if (result != null) {
                while (result.next()) {
                    respuesta.append("<tr>");
                    respuesta.append("<td>").append(result.getString("codigo_producto")).append("</td>");
                    respuesta.append("<td>").append(result.getString("nombre_producto")).append("</td>");
                    respuesta.append("<td>").append(result.getDouble("precio")).append("</td>");
                    respuesta.append("<td>").append(result.getInt("existencia")).append("</td>");
                    respuesta.append("<td>").append(result.getString("fecha_vencimiento")).append("</td>");
                    respuesta.append("<td>").append(result.getInt("id_marca")).append("</td>");
                    respuesta.append("<td>").append(result.getInt("id_categoria")).append("</td>");
                    respuesta.append("<td><button id=\"").append(result.getString("codigo_producto"))
                    .append("\" onclick=\"eliminarProducto(this.id);\">Eliminar</button></td>");
                    respuesta.append("</tr>");
                }
            } else {
                respuesta.append("No se encontraron productos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            respuesta.append("Error al consultar la base de datos: " + e.getMessage());
        }
    }

    public String eliminarProducto(int codigo_producto) {
        String sql = "DELETE FROM producto WHERE codigo_producto = ?";

        try (Connection conexion = conectorBD.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, codigo_producto);
            int resultado = statement.executeUpdate();
            if (resultado > 0) {
                return "Producto eliminado exitosamente";
            } else {
                return "Error al eliminar el producto";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error en la base de datos: " + e.getMessage();
        }
    }
}


