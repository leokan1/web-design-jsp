/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.servlet;

import it.bean.ItemBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 1
 */
@WebServlet(name = "handleOrder", urlPatterns = {"/handleOrder"})
public class handleOrder extends HttpServlet {

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
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        String image = request.getParameter("image");
        ArrayList orders;
        if (session.getAttribute("orders") != null) {
            orders = (ArrayList) session.getAttribute("orders");
        } else {
            orders = new ArrayList();
        }
        ItemBean ib = new ItemBean();
        ib.setId(id);
        ib.setName(name);
        ib.setPrice(price);
        ib.setImg(image);
        orders.add(ib);

        session.setAttribute("orders", orders);
        RequestDispatcher rd;
        String action = request.getParameter("action");
        if ("apple".equalsIgnoreCase(action)) {
            rd = getServletContext().getRequestDispatcher("/apple.jsp");
            rd.forward(request, response);
        } else if ("samsung".equalsIgnoreCase(action)) {
            rd = getServletContext().getRequestDispatcher("/samsung.jsp");
            rd.forward(request, response);
        } else if ("htc".equalsIgnoreCase(action)) {
            rd = getServletContext().getRequestDispatcher("/htc.jsp");
            rd.forward(request, response);
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
