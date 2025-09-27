package com.example.splitapp.service;

import com.example.splitapp.exception.ObjectNotFoundException;
import com.example.splitapp.exception.RemovalOfSplitGroupWithUserException;
import com.example.splitapp.exception.SplitGroupAlreadyExistsException;
import com.example.splitapp.exception.UserAlreadyInSplitGroupException;
import com.example.splitapp.exception.UserNotFoundInSplitGroupException;
import com.example.splitapp.model.SplitGroup;
import com.example.splitapp.model.User;
import com.example.splitapp.repository.SplitGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SplitGroupService {

    private final UserService userService;
    private final SplitGroupRepository splitGroupRepository;

    @Transactional(readOnly = true)
    public SplitGroup getById(Long id) {
        return splitGroupRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("SplitGroup with id '%d' not found".formatted(id)));
    }

    public List<SplitGroup> findSplitGroups(String title, String description, String userLogin, String sortBy, Sort.Direction sortOrder) {
        Specification<SplitGroup> spec = Specification.where(null); // TODO: fix depracated

        if (!title.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(root.get("title"), "%" + title + "%"));
        }
        if (!description.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(root.get("description"), "%" + description + "%"));
        }
        if (!userLogin.isEmpty()) {
            User user = userService.getByLogin(userLogin);
            spec = spec.and((root, query, cb) ->
                    cb.isMember(user, root.get("user")));
        }
        Sort sort = Sort.by(sortOrder, sortBy);
        return splitGroupRepository.findAll(spec, sort);
    }

    @Transactional
    public SplitGroup add(SplitGroup splitGroup) {
        splitGroupRepository.findByTitleAndDate(splitGroup.getTitle(), splitGroup.getDate())
                .ifPresent(m -> {
                    throw new SplitGroupAlreadyExistsException(m.getTitle());
                });
        splitGroup.setId(null);
        return splitGroupRepository.save(splitGroup);
    }

    @Transactional
    public void deleteById(Long id) {
        SplitGroup splitGroup = getById(id);
        if (!splitGroup.getUsers().isEmpty()) {
            throw new RemovalOfSplitGroupWithUserException(splitGroup.getTitle());
        }
        splitGroupRepository.deleteById(id);
    }

    @Transactional
    public void addUser(Long id, String login) {
        SplitGroup splitGroup = getById(id);
        User user = userService.getByLogin(login);
        if (splitGroup.getUsers().contains(user)) {
            throw new UserAlreadyInSplitGroupException(id, login);
        }
        splitGroup.addUser(user);
    }

    @Transactional
    public void deleteUser(Long id, String login) {
        SplitGroup splitGroup = getById(id);
        User user = userService.getByLogin(login);
        if (!splitGroup.getUsers().contains(user)) {
            throw new UserNotFoundInSplitGroupException(id, login);
        }
        splitGroup.removeUser(user);
    }


    @Transactional
    public SplitGroup update(Long id, SplitGroup splitGroup) {
        SplitGroup existingSplitGroup = getById(id);
        splitGroup.setId(existingSplitGroup.getId());
        return splitGroupRepository.save(splitGroup);
    }
}
