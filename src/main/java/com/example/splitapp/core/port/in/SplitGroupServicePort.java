package com.example.splitapp.core.port.in;

import com.example.splitapp.core.domain.SplitGroup;
import com.example.splitapp.core.port.in.command.CreateSplitGroupCommand;
import com.example.splitapp.core.port.in.command.UpdateSplitGroupCommand;

import java.util.List;

public interface SplitGroupServicePort {

    SplitGroup getById(Long id);

    List<SplitGroup> findSplitGroups(String title, String description, String userLogin, String sortBy, String sortOrder);

    SplitGroup add(CreateSplitGroupCommand createCommand);

    SplitGroup update(Long id, UpdateSplitGroupCommand updateRequest);

    void deleteById(Long id);

    void addUserToGroup(Long id, String login);

    void deleteUserFromGroup(Long id, String login);
}
