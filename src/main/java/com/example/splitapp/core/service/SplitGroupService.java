package com.example.splitapp.core.service;

import com.example.splitapp.core.domain.SplitGroup;
import com.example.splitapp.core.domain.User;
import com.example.splitapp.core.port.in.SplitGroupServicePort;
import com.example.splitapp.core.port.in.command.CreateSplitGroupCommand;
import com.example.splitapp.core.port.in.command.UpdateSplitGroupCommand;
import com.example.splitapp.core.port.out.SplitGroupRepositoryPort;
import com.example.splitapp.core.port.out.UserRepositoryPort;
import com.example.splitapp.exception.ObjectNotFoundException;
import com.example.splitapp.exception.RemovalOfSplitGroupWithUserException;
import com.example.splitapp.exception.UserAlreadyInSplitGroupException;
import com.example.splitapp.exception.UserNotFoundInSplitGroupException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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
    public List<SplitGroup> findSplitGroups(String title, String description, String userLogin, String sortBy, String sortOrder) {
        Specification<SplitGroup> spec = (root, query, cb) -> cb.conjunction();
        if (!title.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(root.get("title"), "%" + title + "%"));
        }
        if (!description.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(root.get("description"), "%" + description + "%"));
        }
        if (!userLogin.isEmpty()) {
            User user = getUserByLogin(userLogin);
            spec = spec.and((root, query, cb) ->
                    cb.isMember(user, root.get("user")));
        }
        return splitGroupRepository.findAll(spec, sortBy, sortOrder);
    }

    @Override
    @Transactional
    public SplitGroup add(CreateSplitGroupCommand createCommand) {
        SplitGroup splitGroup = new SplitGroup(createCommand.title(), createCommand.description());
        return splitGroupRepository.save(splitGroup);
    }

    @Override
    @Transactional
    public SplitGroup update(Long id, UpdateSplitGroupCommand updateRequest) {
        SplitGroup existingSplitGroup = getGroupById(id);
        if (updateRequest.title() != null) {
            existingSplitGroup.setTitle(updateRequest.title());
        }
        if (updateRequest.description() != null) {
            existingSplitGroup.setDescription(updateRequest.description());
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
