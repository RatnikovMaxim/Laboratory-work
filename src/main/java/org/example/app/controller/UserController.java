package org.example.app.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.app.dto.UserDTO;
import org.example.app.manager.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserManager manager;
    Gson gson = new Gson();
    String login = "petya";
    @RequestMapping("/users.getAll")
    public void getAll(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException, IOException {
        List<UserDTO> responseDTO = manager.getAll();
        res.getWriter().write(gson.toJson(responseDTO));
    }

    @RequestMapping("/users.getById")
    public void getById(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final long id = Long.parseLong(req.getParameter("id"));
        UserDTO responseDTO = manager.getById(id);
        res.getWriter().write(gson.toJson(responseDTO));
    }

//    public void create(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
//        UserDTO responseDTO = manager.create(req.getParameter("login"));
//        res.getWriter().write(gson.toJson(responseDTO));
//    }
//    public void deleteById(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
//        manager.deleteById(Integer.parseInt(req.getParameter("id")));
//    }
}