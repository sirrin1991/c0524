package com.example.ss9.controiller;

import com.example.ss9.model.Category;
import com.example.ss9.model.Product;
import com.example.ss9.repository.BaseRepository;
import com.example.ss9.repository.CategoryRepository;
import com.example.ss9.repository.ICategoryRepository;
import com.example.ss9.service.IProductService;
import com.example.ss9.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "")
public class ProductServlet extends HttpServlet {
    private final IProductService iProductService = new ProductService();
    private final ICategoryRepository iCategoryRepository = new CategoryRepository();
    // Nơi xử lý các HTTP GET CRUDS
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "show_create_form":
                showCreateForm(request,response);
                break;
            case "show_update_form":
                showUpdateForm(request,response);
                break;
            case "show_delete_form":
                showDeleteForm(request,response);
                break;
            default:
                showListProduct(request, response);
        }
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("delete_form.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("update_form.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create_form.jsp");
        List<Category> categories = iCategoryRepository.findAll();
        try {
            request.setAttribute("categories", categories);
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = iProductService.findAll();
        request.setAttribute("productList", productList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }


    // Nơi xử lý các HTTP POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create_product":
                createProduct(request,response);
                break;
            case "delete":
                deleteProduct(request,response);
                break;
//            case "show_delete_form":
//                showDeleteForm(request,response);
//                break;
//            default:
//                showListProduct(request, response);
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idDel"));
        System.out.println(id);
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("category"));
        Category category = new Category();
        category.setId(categoryId);
        Product product = new Product(name,price);
        product.setCategory(category);
        iProductService.save(product);
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
