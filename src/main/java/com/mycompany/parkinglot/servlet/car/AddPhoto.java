/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parkinglot.servlet.car;

import com.mycompany.parkinglot.common.CarDetails;
import com.mycompany.parkinglot.ejb.CarBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Alex
 */
@MultipartConfig
@WebServlet(name = "AddPhoto", urlPatterns = {"/Cars/AddPhoto"})
public class AddPhoto extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddPhoto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddPhoto at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Inject
    CarBean carBean;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer carId = Integer.parseInt(request.getParameter("id"));
        CarDetails car = carBean.findById(carId);
        request.setAttribute("car", car);
        
        request.getRequestDispatcher("/WEB-INF/pages/addPhoto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String carIdAsString = request.getParameter("car_id");
        Integer carId = Integer.parseInt(carIdAsString);
        
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String fileType = filePart.getContentType();
        long fileSize = filePart.getSize();
        byte[] fileContent = new byte[(int) fileSize];
        filePart.getInputStream().read(fileContent);
        
        carBean.addPhotoToCar(carId, fileName, fileType, fileContent);
        response.sendRedirect(request.getContextPath()+ "/Cars");
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
