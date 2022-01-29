
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Producto;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Se utiliza este validar para el empleado, el usario y el registrarse.
 * @author EMANUEL ORTIZ
 */
@WebServlet(name = "Validar", urlPatterns = {"/Validar"})
public class Validar extends HttpServlet {
    //Se instancia para hacer la validacion la base de datos
    EmpleadoDAO edao=new EmpleadoDAO();
    Empleado em=new Empleado();
    ClienteDAO cdao=new ClienteDAO();
    Cliente cl= new Cliente();
    String direccion="./img/login.jpeg";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Validar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Validar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String accion=request.getParameter("menu");
      if(accion.equalsIgnoreCase("Ingresar Empleado")){
          String user=request.getParameter("textuser");
          String pass=request.getParameter("textpass");
          try {
              
              em=edao.validar(user, pass);
              if(em.getUser()!=null){
                  request.setAttribute("usuario", em);
                
                  request.getRequestDispatcher("./vistas/Principal.jsp").forward(request, response);
              }else{
                 
                  request.getRequestDispatcher("./vistas/validarEmpleado.jsp").forward(request, response);
              }
          } catch (SQLException ex) {
              Logger.getLogger(Validar.class.getName()).log(Level.SEVERE, null, ex);
          }
      }else if(accion.equalsIgnoreCase("Salir")){
          request.setAttribute("redirigir",direccion);
          request.setAttribute("validar","./Validar");
          request.getRequestDispatcher("vistas/validarEmpleado.jsp").forward(request, response);
      }else if(accion.equalsIgnoreCase("Ingresar")){
          String correo=request.getParameter("textcorreo");
          String pass=request.getParameter("textpass");
          try {
              
              cl=cdao.validar(correo, pass);
              if(cl.getCorreo()!=null){
                  request.setAttribute("cliente", cl);
                
                  request.getRequestDispatcher("./vistas/principalCliente.jsp").forward(request, response);
              }else{
                 
                  request.getRequestDispatcher("./vistas/validarCliente.jsp").forward(request, response);
              }
          } catch (SQLException ex) {
              Logger.getLogger(Validar.class.getName()).log(Level.SEVERE, null, ex);
          }
      }else if(accion.equalsIgnoreCase("Cerrar Sesion")){
          request.setAttribute("redirigir",direccion);
          request.setAttribute("validar","./Validar");
          request.getRequestDispatcher("./vistas/validarCliente.jsp").forward(request, response);
      }else if(accion.equalsIgnoreCase("Registrar")){
         String dni=request.getParameter("dni");
         String nombre=request.getParameter("nombre");
         String direccion=request.getParameter("direccion");
         String telefono=request.getParameter("telefono");
         String correo=request.getParameter("email");
         String password=request.getParameter("password");
         cl.setDni(dni);
         cl.setNombre(nombre);
         cl.setDireccion(direccion);
         cl.setTelefono(telefono);
         cl.setCorreo(correo);
         cl.setPassword(password);
         cdao.Agregar(cl);
          request.getRequestDispatcher("./vistas/validarCliente.jsp").forward(request, response);
      }
        
    }

   
}
