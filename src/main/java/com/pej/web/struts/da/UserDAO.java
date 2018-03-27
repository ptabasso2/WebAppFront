package com.pej.web.struts.da;

import com.pej.web.struts.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getUsers();
    public void insertBatch();
}
