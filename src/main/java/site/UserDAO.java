package site;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    private String users = "asdasdddddddddddddddddddddd";  //это типа БД

    public String getUsers() {   // это типо получение из БД
        return users;
    }

}
