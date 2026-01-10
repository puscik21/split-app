package com.example.splitapp.splitgroup.core.service;

import com.example.splitapp.splitgroup.core.domain.model.SplitGroup;
import com.example.splitapp.user.core.domain.model.User;
import com.example.splitapp.splitgroup.core.port.in.SplitGroupServicePort;
import com.example.splitapp.splitgroup.core.port.in.command.CreateSplitGroupCommand;
import com.example.splitapp.splitgroup.core.port.in.command.UpdateSplitGroupCommand;
import com.example.splitapp.splitgroup.core.port.out.SplitGroupRepositoryPort;
import com.example.splitapp.user.core.port.out.UserRepositoryPort;
import com.example.splitapp.common.domain.exception.ObjectNotFoundException;
import com.example.splitapp.splitgroup.core.domain.exception.RemovalOfSplitGroupWithUserException;
import com.example.splitapp.splitgroup.core.domain.exception.UserAlreadyInSplitGroupException;
import com.example.splitapp.splitgroup.core.domain.exception.UserNotFoundInSplitGroupException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SplitGroupService implements SplitGroupServicePort {

    private final SplitGroupRepositoryPort splitGroupRepository;
    private final UserRepositoryPort userRepository;

    @Override
    @Transactional(readOnly = true)
    public SplitGroup getById(Long id) {
        return getGroupById(id);
    }

    @Override
    @Transactional
    public List<SplitGroup> findSplitGroups(String sortBy, String sortOrder) {
        return splitGroupRepository.findAll(sortBy, sortOrder);
    }

    @Override
    public List<SplitGroup> findByUserLogin(String login) {
        return splitGroupRepository.findByUserLogin(login);
    }

    @Override
    @Transactional
    public SplitGroup add(CreateSplitGroupCommand createCommand) {
        SplitGroup splitGroup = new SplitGroup(createCommand.title(), createCommand.description());
        return splitGroupRepository.save(splitGroup);
    }

    @Override
    @Transactional
    public SplitGroup update(Long id, UpdateSplitGroupCommand updateCommand) {
        SplitGroup existingSplitGroup = getGroupById(id);
        if (updateCommand.title() != null) {
            existingSplitGroup.setTitle(updateCommand.title());
        }
        if (updateCommand.description() != null) {
            existingSplitGroup.setDescription(updateCommand.description());
        }
        return splitGroupRepository.save(existingSplitGroup);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        SplitGroup splitGroup = getGroupById(id);
        if (splitGroup.hasUsers()) {
            throw new RemovalOfSplitGroupWithUserException(splitGroup.getTitle());
        }
        splitGroupRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addUserToGroup(Long id, String login) {
        if (splitGroupRepository.existsByIdAndUserLogin(id, login)) {
            throw new UserAlreadyInSplitGroupException(id, login);
        }
        SplitGroup splitGroup = getGroupById(id);
        User user = getUserByLogin(login);
        splitGroup.addUser(user);
        splitGroupRepository.save(splitGroup);
    }

    @Override
    @Transactional
    public void deleteUserFromGroup(Long id, String login) {
        if (!splitGroupRepository.existsByIdAndUserLogin(id, login)) {
            throw new UserNotFoundInSplitGroupException(id, login);
        }
        SplitGroup splitGroup = getGroupById(id);
        User user = getUserByLogin(login);
        splitGroup.removeUser(user);
        splitGroupRepository.save(splitGroup);
    }

    private SplitGroup getGroupById(Long id) {
        return splitGroupRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("SplitGroup with id '%d' not found".formatted(id)));
    }

    private User getUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new ObjectNotFoundException("User with login '%s' not found".formatted(login)));
    }
}
