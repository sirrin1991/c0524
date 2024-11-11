package com.example.ss9;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        System.out.println("init run");
        message = "Hello C0524M1!";
    }
    /*
    * init(): tự động thực thi khi servlet được gọi đến
    * service: doGet, doPost, ....
    * destroy(): tự động thực thi khi serlet được phá hủy đi*/
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<p>" + message + "</p>");
        out.println("</body></html>");
    }

    public void destroy() {
        System.out.println("destroy run");

    }
}