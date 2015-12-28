package model;

import interfaces.Writable;
import server.ServerSettings;

import java.util.List;

/**
 * Created by jonathan on 1-11-15.
 */
public interface Model {


    List<User> getActiveUsers();

    void addActiveUser();





}
