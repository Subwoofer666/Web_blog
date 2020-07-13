package site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;  //если это поле будет где-то подставляться в UserService то спринг д
                             // останет из контекста бин и вставит в то место где упоминается это поле

    public String getAllUsers() {
        return userDAO.getUsers(); //тут //в итоге если надо будет что-то расширять, то класс ДАО не будет трогаться. будет расширяться этот метод
    }
}

