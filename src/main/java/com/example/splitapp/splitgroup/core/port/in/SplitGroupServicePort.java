package com.example.splitapp.splitgroup.core.port.in;

import com.example.splitapp.splitgroup.core.domain.model.SplitGroup;
import com.example.splitapp.splitgroup.core.port.in.command.CreateSplitGroupCommand;
import com.example.splitapp.splitgroup.core.port.in.command.UpdateSplitGroupCommand;

import java.util.List;

public interface SplitGroupServicePort {

    SplitGroup getById(Long id);

    List<SplitGroup> findSplitGroups(String sortBy, String sortOrder);

    List<SplitGroup> findByUserLogin(String login);

    SplitGroup add(CreateSplitGroupCommand createCommand);

    SplitGroup update(Long id, UpdateSplitGroupCommand updateRequest);

    void deleteById(Long id);

    void addUserToGroup(Long id, String login);

    void deleteUserFromGroup(Long id, String login);
}
