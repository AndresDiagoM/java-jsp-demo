/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.spacex.control;

import com.mycompany.spacex.persistence.entities.Rockets;
import com.mycompany.spacex.persistence.jpa.RocketsJpaController;
import com.mycompany.spacex.persistence.jpa.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author felip
 */
@WebServlet("/")
public class ServerletSpaceX extends HttpServlet {
    
    private RocketsJpaController rocketsJPA;
    private final static String PU = "com.mycompany_spacex_war_1.0-SNAPSHOTPU";
    
    @Override 
    public void init() throws ServletException {
        super.init();
        //creamos una instancia de la clase EntityManagerFactory
        //esta clase se encarga de gestionar la construcción de entidades y
        //permite a los controladores JPA ejecutar las operaciones CRUD
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        //creamos una instancia del controldor JPA para la clase clients y le
        //pasamos el gestor de entidades
        rocketsJPA = new RocketsJpaController(emf);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new": 
                    showNewForm(request, response);
                    break;
                case "/insert": 
                    insertRocket(request, response);
                    break;
                case "/delete": 
                    deleteRocket(request, response);
                    break;
                case "/edit": 
                    showEditForm(request, response);
                    break;
                case "/update": 
                    updateRocket(request, response);
                    break;
                default:
                    listRockets(request, response);
                    break;
            }
        } catch (SQLException ex) {
        throw new ServletException(ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ServerletSpaceX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private void listRockets(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Rockets> listRockets = rocketsJPA.findRocketsEntities();
        request.setAttribute("listRockets", listRockets);

        RequestDispatcher dispatcher = request.getRequestDispatcher("list-rockets.jsp");

        dispatcher.forward(request, response);
    }
    
    //muestra el formulario para crear un nuevo usuario
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("rocket-form.jsp");

        dispatcher.forward(request, response);
    }
    
    //muestra el formulario para editar un usuario
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        //toma el id del cliente a ser editaro
        String id = request.getParameter("id");
        //busca al cliente en la base de datos
        Rockets existingRocket = rocketsJPA.findRockets(id);
        RequestDispatcher dispatcher = null;
        if (existingRocket != null) {
            //si lo encuentra lo envía al formulario
            dispatcher = request.getRequestDispatcher("rocket-form.jsp");
            request.setAttribute("rocket", existingRocket);
        } else {
            //si no lo encuentra regresa a la página con la lista de los clientes
            dispatcher = request.getRequestDispatcher("list-rocketss.jsp");
        }
        dispatcher.forward(request, response);
    }
    
    //método para crear un cliente en la base de datos
    private void insertRocket(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //toma los datos enviados por el formulario
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String active = request.getParameter("active");
        String country = request.getParameter("country");
        String company = request.getParameter("company");
        String costPerLaunch = request.getParameter("costPerLaunch");
        
        //crea un objeto vacío y lo llena con los datos del cohete
        Rockets r1 = new Rockets();
        r1.setRocketId(String.valueOf(rocketsJPA.getRocketsCount()+1));
        r1.setName(name);
        r1.setType(type);
        r1.setActive(active);
        r1.setCountry(country);
        r1.setCompany(company);
        r1.setCostPerLaunch(Integer.parseInt(costPerLaunch));
        try {
            //Crea el cliente utilizando el objeto controlador JPA
            rocketsJPA.create(r1);
        } catch (Exception ex) {
            Logger.getLogger(ServerletSpaceX.class.getName()).log(Level.SEVERE, null, ex);
        }
        //solicita al Servlet que muestre la página actualizada con la lista de clientes 
        response.sendRedirect("list");
    }
    
    //Método para editar un cliente
    private void updateRocket(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //toma los datos enviados por el formulario
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String active = request.getParameter("active");
        String country = request.getParameter("country");
        String company = request.getParameter("company");
        String costPerLaunch = request.getParameter("costPerLaunch");
        
        //crea un objeto vacío y lo llena con los datos del cohete
        Rockets r1 = new Rockets();
        r1.setRocketId(id);
        r1.setName(name);
        r1.setType(type);
        r1.setActive(active);
        r1.setCountry(country);
        r1.setCompany(company);
        r1.setCostPerLaunch(Integer.parseInt(costPerLaunch));
        try {
            //Edita el cliente en la BD
            rocketsJPA.edit(r1);
        } catch (Exception ex) {
            Logger.getLogger(ServerletSpaceX.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("list");
    }
    
    //Elimina un cliente de la BD
    private void deleteRocket(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, NonexistentEntityException {
        //Recibe el ID del cliente que se espera eliminar de la BD
        String id = request.getParameter("id");
        try {
            //Elimina el cliente con el id indicado
            rocketsJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ServerletSpaceX.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("list");
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
