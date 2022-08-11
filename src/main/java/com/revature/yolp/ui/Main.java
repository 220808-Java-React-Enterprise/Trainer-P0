package com.revature.yolp.ui;

import com.revature.yolp.daos.UserDAO;
import com.revature.yolp.services.UserService;

public class Main {
    public static void main(String[] args) {
        /* dependency injection */
//        UserDAO userDAO = new UserDAO();
//        UserService userService = new UserService(userDAO);
//        new LoginMenu(userService).start();

        new LoginMenu(new UserService(new UserDAO())).start();
    }
}
