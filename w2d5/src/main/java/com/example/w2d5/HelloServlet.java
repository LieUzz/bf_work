package com.example.w2d5;

import java.io.*;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "aboutMe", value = "/about-me")
public class HelloServlet extends HttpServlet {
    private String name;
    private String interests;

    public void init() {
        name = "Jiayu Zheng";
        interests = "game dev, watching movie, skiing";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + name + "</h1>");
        out.println("<h1>" + interests + "</h1>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/plain");

        PrintWriter out = response.getWriter();
        out.println(request.getParameter("lyrics"));
    }

    public void destroy() {
    }
}