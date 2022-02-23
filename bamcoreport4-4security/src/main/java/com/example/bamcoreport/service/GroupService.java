package com.example.bamcoreport.service;

import com.example.bamcoreport.model.entity.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroup();

    Group createGroup(Group group);

    Group updateGroup(long id, Group group);

    void deleteGroup(long id);

    Group getGroupById(long id);
}
