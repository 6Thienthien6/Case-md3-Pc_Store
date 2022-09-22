package com.casemd3pcstore.controller;

import com.casemd3pcstore.dao.UserDAO;
import com.casemd3pcstore.model.User;
import com.casemd3pcstore.utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    private  UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertUser(request, response);
                    break;
                case "edit":
                    updateUser(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                case "login":
                    login(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                default:
                    listNumberPage(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    private void listUser(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//        List<User> listUser = userDAO.selectAllUsers();
//        request.setAttribute("listUser", listUser);
//        System.out.println(listUser);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/list.jsp");
//        dispatcher.forward(request, response);
//    }

    private void listNumberPage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        System.out.println("numberPage");
        int page = 1;
        int recordsPerPage = 7;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        };
        String name = "";
        if (req.getParameter("searchuser") != null) {
            name = req.getParameter("searchuser");
        }
        List<User> listUser = userDAO.getNumberPage((page - 1) * recordsPerPage, recordsPerPage, name);
        int noOfRecords = userDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        req.setAttribute("listUser", listUser);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("searchuser" , name);



        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/list.jsp");
        requestDispatcher.forward(req, resp);

    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/create.jsp");
        request.setAttribute("user", user);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/edit.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

//    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        User existingUser = userDAO.selectUser(id);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user/delete.jsp");
//        request.setAttribute("user", existingUser);
//        dispatcher.forward(request, response);
//
//    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {


        User user;
        String userName = request.getParameter("username").replaceAll(" ", "").toLowerCase();
        String password = request.getParameter("password").trim();
        String fullName = request.getParameter("fullname").trim();
        String phone = request.getParameter("phone").trim();
        String email = request.getParameter("email").trim();
        String address = request.getParameter("address").trim();
        List<String> errors = new ArrayList<>();
        boolean isPassword = ValidateUtils.isPasswordVailid(password);
        boolean isPhone = ValidateUtils.isNumberPhoneVailid(phone);
        boolean isEmail = ValidateUtils.isEmailValid(email);
//        boolean isUserName = ValidateUtils.isUserNameVailid(userName);

        user = new User(userName, password, fullName, phone, email, address);
        if (userName.isEmpty() ||
                password.isEmpty() ||
                fullName.isEmpty() ||
                phone.isEmpty() ||
                email.isEmpty() ||
                address.isEmpty() ){
            errors.add("enter your information");
        }
        if (userName.isEmpty()) {
            errors.add("UserName is not empty");
        }
        if (password.isEmpty()) {
            errors.add("Password is not empty");
        }
        if (fullName.isEmpty()) {
            errors.add("Fullname is not empty");
        }
        if (phone.isEmpty()) {
            errors.add("Phone is not correct");
        }
        if (!isPhone) {
                errors.add("Phone number is not in the correct format, begin 0 and has 10 number");
        }
        if (userDAO.existByPhone(phone)) {
            errors.add("Phone already exist!");
        }
        if (email.isEmpty()) {
            errors.add("Email is not correct");
        }
        if (!isEmail) {
            errors.add("Email is not in the correct format");
        }
        if (address.isEmpty()) {
            errors.add("Address is not empty");
        }

        if (userDAO.existsByEmail(email)) {
            errors.add("Email already exists");
        }
//        if (!isUserName) {
//            errors.add("UserName không đúng định dạng (không có khoảng cách) ");
//        }
        if (userDAO.existByUsername(userName)) {
            errors.add("Username already exists!");
        }
        if (!isPassword) {
            errors.add("Password is not in the correct format");
        }

        if (errors.size() == 0) {
            user = new User(userName, password, fullName, phone, email, address);
            boolean success = false;
            success = userDAO.insertUser(user);

            if (success) {
                request.setAttribute("message", true);
            } else {
                request.setAttribute("errors", true);
                errors.add("Invalid data, Please check again!");
            }

        }
        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            request.setAttribute("user", user);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/create.jsp");
//        request.setAttribute("user", user);
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String fullName = request.getParameter("fullname");
//        String phone = request.getParameter("phone");
//        String email = request.getParameter("email");
//        String address = request.getParameter("address");
//        User newUser = new User(id,username, password, fullName,phone,email,address);
//        userDAO.updateUser(newUser);
////        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/list.jsp");
////        dispatcher.forward(request, response);
//        response.sendRedirect("/users");
        List<String> errors = new ArrayList<>();
        User user = null;
        String id= request.getParameter("id");
        boolean isId = ValidateUtils.isIntValid(id);
        int checkId=0;
        if (isId) {
            checkId=Integer.parseInt(id);
        }else {
            errors.add("ID must be a positive integer !");
        }
        if (!userDAO.existByUserId(checkId)) {
            errors.add("ID must be real!");
        }


        String fullName = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        boolean isPhone = ValidateUtils.isNumberPhoneVailid(phone);
        boolean isEmail = ValidateUtils.isEmailValid(email);
        user = new User(id, fullName, phone,email, address);
        User userEmail = userDAO.selectUser(id);
        String checkEmail = userEmail.getEmail();
        User userPhone = userDAO.selectUser(id);
        String checkPhone = userPhone.getPhone();
        if (fullName.isEmpty() ||
                phone.isEmpty() ||
                email.isEmpty() ||
                address.isEmpty()) {
            errors.add("enter your information");
        }

        if (fullName.isEmpty()) {
            errors.add("Fullname is not empty");
        }
        if (phone.isEmpty()) {
            errors.add("Phone is not empty");
        }
        if (email.isEmpty()) {
            errors.add("Emailis not empty");
        }
        if (!isEmail) {
            errors.add("Email is not in the correct format");
        }

        if (userDAO.existsByEmail(email) && !email.equals(checkEmail)){
            errors.add("Email already exists");
        }
        if (!isPhone) {
            errors.add("Phone is not in the correct format, must be 10 number and begins 0");
        }
        if (userDAO.existByPhone(phone) && !phone.equals(checkPhone)){
            errors.add("Phone already exists");
        }
        if (address.isEmpty()) {
            errors.add("Address is not empty");
        }


        if (errors.size() == 0) {
            user = new User(id, fullName, phone,email, address);
            boolean success = false;
            success = userDAO.updateUser(user);
            if (success) {
                User newUser = userDAO.selectUser(id);
                request.setAttribute("message", true);
                request.setAttribute("user", newUser);

            } else {
                request.setAttribute("errors", true);
                errors.add("Invalid data, Please check again!");
            }
        }
        else {
            request.setAttribute("errors", errors);
            request.setAttribute("user", user);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/edit.jsp");
//        request.setAttribute("user", user);
        dispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("/users");

    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> listUser = null;
        String name = "";
        if (req.getParameter("searchuser") != null) {
            name = req.getParameter("searchuser");

            listUser = userDAO.searchUser(name);
        } else {
            listUser = userDAO.selectAllUsers();
        }
        req.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/list.jsp");
        dispatcher.forward(req, resp);
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean checkUser = userDAO.existByUsernameLogin(username, password);
        if (username.isEmpty() && password.isEmpty()) {
            req.setAttribute("error", "Please enter username and password");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(req, resp);
        }
        if (username.isEmpty()) {
            req.setAttribute("error", "Please enter username");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(req, resp);
        }
        if (password.isEmpty()) {
            req.setAttribute("error", "Please enter password");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(req, resp);
        }
        if (checkUser) {
            resp.sendRedirect("/users");
        }
        else {
            req.setAttribute("error", "Account not exist");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(req, resp);
        }

    }
}
