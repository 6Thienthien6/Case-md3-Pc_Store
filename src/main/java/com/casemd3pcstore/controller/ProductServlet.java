package com.casemd3pcstore.controller;

import com.casemd3pcstore.dao.CategoryDAO;
import com.casemd3pcstore.dao.ICategoryDAO;
import com.casemd3pcstore.dao.ProductDAO;
import com.casemd3pcstore.model.Product;
import com.casemd3pcstore.utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    ProductDAO productDAO;
    ICategoryDAO categoryDAO;
//    private String img;

    public void init() {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
        if (this.getServletContext().getAttribute("categories") == null) {
            this.getServletContext().setAttribute("categories", categoryDAO.selectAllCategories());
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(req, resp);
                break;
            case "edit":
                try {
                    showEditForm(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    showInfoDeleteForm(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "category":
                try {
                    showListByCategory(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "sortasc":
                try {
                    listNumberPageSortPriceASC(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "sortdesc":
                try {
                    listNumberPageSortPriceDESC(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "logout":
                try {
                    logout(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    listNumberPage(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    createProduct(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    updateProduct(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    deleteProduct(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void listProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        List<Product> products = this.productDAO.findAll();
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/product/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void listNumberPage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        System.out.println("numberPage");
        int page = 1;
        int recordsPerPage = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        ;
        String name = "";
        if (req.getParameter("searchproduct") != null) {
            name = req.getParameter("searchproduct");
        }
        List<Product> listProduct = productDAO.getNumberPage((page - 1) * recordsPerPage, recordsPerPage, name);
        int noOfRecords = productDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//        System.out.println("noOfPages" + noOfPages);
//        System.out.println(noOfRecords);
        req.setAttribute("products", listProduct);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("searchproduct", name);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/product/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void listNumberPageSortPriceASC(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        System.out.println("numberPage");
        int page = 1;
        int recordsPerPage = 1000;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        ;
        String name = "";
        if (req.getParameter("searchproduct") != null) {
            name = req.getParameter("searchproduct");
        }
        List<Product> listProduct = productDAO.getNumberPageSortPriceASC((page - 1) * recordsPerPage, recordsPerPage, name);
        int noOfRecords = productDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//        System.out.println("noOfPages" + noOfPages);
//        System.out.println(noOfRecords);
        req.setAttribute("products", listProduct);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("searchproduct", name);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/product/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void listNumberPageSortPriceDESC(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        System.out.println("numberPage");
        int page = 1;
        int recordsPerPage = 1000;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        ;
        String name = "";
        if (req.getParameter("searchproduct") != null) {
            name = req.getParameter("searchproduct");
        }
        List<Product> listProduct = productDAO.getNumberPageSortPriceDESC((page - 1) * recordsPerPage, recordsPerPage, name);
        int noOfRecords = productDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//        System.out.println("noOfPages" + noOfPages);
//        System.out.println(noOfRecords);
        req.setAttribute("products", listProduct);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("searchproduct", name);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/product/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showListByCategory(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        int category_id = Integer.parseInt(req.getParameter("id"));
        List<Product> products = this.productDAO.getListByCategory(category_id);
//        Category category = categoryDAO.findById(category_id);
        RequestDispatcher dispatcher;
        if (products.size() == 0) {
            List<Product> products1 = this.productDAO.findAll();
            req.setAttribute("error", "There are no products in this category");
            req.setAttribute("products", products1);
        } else {
            req.setAttribute("products", products);
        }
        dispatcher = req.getRequestDispatcher("/WEB-INF/product/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/product/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String img = req.getParameter("image");
        String name = req.getParameter("name");
        String quantity = req.getParameter("quantity");
        String price = req.getParameter("price");
        int categoryId = Integer.parseInt(req.getParameter("category"));
        Date createAt = Date.valueOf(LocalDate.now());
        String createdBy = req.getParameter("");// đổi thành  người tạo là user hiện tại đang đăng nhập
        List<String> errors = new ArrayList<>();
        boolean isQuantity = ValidateUtils.isNumberVailid(quantity);
        boolean isPrice = ValidateUtils.isNumberVailid(price);
//        Double isPrice = Double.parseDouble(String.valueOf(price));

        Product product = new Product(0, img, name, quantity, price);
        // bat đau validateeeeeeeeeeeeeeeeeeeeeeeeee
//        if (
//                img.isEmpty() || name.isEmpty()
//        ) {
//            errors.add("enter your information");
//        }
        if (img.isEmpty()) {
            errors.add("get your image");
        }
        if (name.isEmpty()) {
            errors.add("Name cannot be left blank ");
        }
        if (quantity.isEmpty()) {
            errors.add("enter quantity");
        }
        if (price.isEmpty()) {
            errors.add("enter your price");
        }
        double checkPrice = 1.0;
        try {
            if (isPrice)
                checkPrice = Double.parseDouble(price);
        } catch (Exception e) {
            errors.add("Invalid price format");
        }
        if (!isPrice || checkPrice <= 30000 || checkPrice > 20000000) {
            errors.add("Price must be real numbers greater than 30000 and less than 20000000");
        }

        int checkQuantity = 1;
        try {
            if (isQuantity)
                checkQuantity = Integer.parseInt(String.valueOf(quantity));
        } catch (Exception e) {
            errors.add("Invalid number format");
        }
        if (checkQuantity < 0 || checkQuantity > 500) {
            errors.add("Quantity must be real number >= 0 and <500");
        }
//LỖIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
        if (errors.size() == 0) {
            product = new Product(img, name, quantity, price, categoryId, createAt, createdBy);
            boolean success = false;
            success = productDAO.insertProduct(product);
            if (success) {
                req.setAttribute("message", true);
            } else {
                req.setAttribute("errors", true);
                errors.add("Invalid data, Please check again!");
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/product/create.jsp");
            req.setAttribute("error", "New Product was created");

        }
        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
            req.setAttribute("inserProduct", product);
        }
        // validate phía trênnnnnnnnnnnnnnnnnnnnnnnnnnnnn


        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/product/create.jsp");
        req.setAttribute("error", "New Product was created");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    // LỖIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = this.productDAO.findById(id);
        RequestDispatcher dispatcher;
        try {
            if (product == null) {
                dispatcher = req.getRequestDispatcher("error-404.jsp");
            } else {
                req.setAttribute("product", product);
                dispatcher = req.getRequestDispatcher("/WEB-INF/product/edit.jsp");
            }
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        List<String> errors = new ArrayList<>();
        Product product;
        int id = Integer.parseInt(req.getParameter("id").trim());
        boolean isId = ValidateUtils.isIntValid(String.valueOf(id));

        String img = req.getParameter("img");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String quantity = req.getParameter("quantity");

        int cate = Integer.parseInt(req.getParameter("category"));
        boolean isPrice = ValidateUtils.isNumberVailid(price);
        boolean isQuantity = ValidateUtils.isNumberVailid(quantity);
        product = new Product(img, name, price, quantity, cate);
        if (
                name.isEmpty() ||
                        price.isEmpty() ||
                        quantity.isEmpty()
        ) {
            errors.add("Please enter all information");
        }
        if (name.isEmpty()) {
            errors.add("Name cannot be left blank");
        }
        if (price.isEmpty()) {
            errors.add("Price cannot be left blank");
        }
        if (quantity.isEmpty()) {
            errors.add("Quantity cannot be left blank");
        }

        double checkPrice = 1.0;
        try {
            if (isPrice)
                checkPrice = Double.parseDouble(price);
        } catch (Exception e) {
            errors.add("Invalid price format !");
        }
        if (!isPrice || checkPrice <= 30000 || checkPrice > 20000000) {
            errors.add("Price must be real numbers greater than 30.000 VND and less than 20.000.000 VND");
        }

        double checkQuantity = 1.0;
        try {
            if (isQuantity)
                checkQuantity = Double.parseDouble(quantity);
        } catch (Exception e) {
            errors.add("Invalid price format !");
        }
        if (!isQuantity || checkQuantity < 0 || checkQuantity > 500) {
            errors.add("Quantity must be real number\n >= 0 và <500");
        }

        if (errors.size() == 0) {
            product = new Product(id, img, name, quantity, price, cate);
            boolean success = false;
            success = productDAO.update(product);
            if (success) {
                req.setAttribute("message", true);
                req.setAttribute("product", product);
            } else {
                req.setAttribute("errors", true);
                errors.add("Invalid data, Please check again!");
            }
        } else {
            req.setAttribute("errors", errors);
            Product product1 = productDAO.findById(id);
            req.setAttribute("product", product1);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/product/edit.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showInfoDeleteForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = this.productDAO.findById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            req.setAttribute("product", product);
            dispatcher = req.getRequestDispatcher("/WEB-INF/product/delete.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        RequestDispatcher dispatcher;
        Product product = this.productDAO.findById(id);

        if (product == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");

        } else {
            this.productDAO.delete(id);
            List<Product> products = this.productDAO.findAll();
            req.setAttribute("products", products);
            req.setAttribute("message", "Product has been deleted");
            dispatcher = req.getRequestDispatcher("/WEB-INF/product/list.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
//                resp.sendRedirect("/products");
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
//                resp.sendRedirect("/products");
    }
}
