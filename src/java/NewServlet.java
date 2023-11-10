import Clases.Producto;
import Clases.ProductoController;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/NewServlet")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String control = request.getParameter("control");
        ProductoController productoController = new ProductoController();

        if ("GUARDAR".equals(control)) {
            int codigo_producto = Integer.parseInt(request.getParameter("codigo_producto"));
            String nombre_producto = request.getParameter("nombre_producto");
            double precio = Double.parseDouble(request.getParameter("precio"));
            int existencia = Integer.parseInt(request.getParameter("existencia"));
            String fecha_vencimiento = request.getParameter("fecha_vencimiento");
            int id_marca = Integer.parseInt(request.getParameter("id_marca"));
            int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));

            Producto producto = new Producto(codigo_producto, nombre_producto, precio, existencia, fecha_vencimiento, id_marca, id_categoria);

            String resultado = productoController.guardarProducto(producto);
            out.print(resultado);
        } else if ("Mostrar".equals(control)) {
            StringBuffer respuesta = new StringBuffer();
            productoController.getProductos(respuesta);
            out.print(respuesta.toString());
        } else if ("ELIMINAR".equals(control)) {
            int codigo_producto = Integer.parseInt(request.getParameter("codigo_producto"));
            String resultado = productoController.eliminarProducto(codigo_producto);
            out.print(resultado);
        }
    }
}
