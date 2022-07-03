package org.example.app.manager;

import lombok.RequiredArgsConstructor;
import org.example.app.dto.UserDTO;
import org.example.app.exception.UserNotFoundException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserManager {
    private final NamedParameterJdbcOperations template;
    private long nextId = 1;

    public List<UserDTO> getAll() {
        return template.query(
                // language=PostgreSQL
                """
                                SELECT id, login FROM users
                                ORDER BY id
                        """,
                Map.of(),
                BeanPropertyRowMapper.newInstance(UserDTO.class)
        );
    }

    public UserDTO getById(final long id) {

        return template.queryForObject(
                // language=PostgreSQL
                """
                        SELECT id, login FROM users
                        WHERE id = :id
                        """,
                Map.of("id", id),
                BeanPropertyRowMapper.newInstance(UserDTO.class)
        );


//        throw new UserNotFoundException();
    }

//    public UserDTO create(final String login) {
//        final UserDTO user = new UserDTO(nextId++, login);
//        users.add(user);
//        return user;
//    }
//
//    public void deleteById(long id) {
//        for (UserDTO user : users) {
//            if (user.getId() == id) {
//                users.remove(user);
//                return;
//            }
//        }
//        throw new UserNotFoundException();
//
//    }
}
