package com.example.splitapp.service;

import com.example.splitapp.dto.splitgroup.CreateSplitGroupRequest;
import com.example.splitapp.dto.splitgroup.SplitGroupDTO;
import com.example.splitapp.dto.splitgroup.UpdateSplitGroupRequest;
import com.example.splitapp.exception.ObjectNotFoundException;
import com.example.splitapp.exception.RemovalOfSplitGroupWithUserException;
import com.example.splitapp.exception.UserAlreadyInSplitGroupException;
import com.example.splitapp.exception.UserNotFoundInSplitGroupException;
import com.example.splitapp.mapper.SplitGroupMapper;
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
    private final SplitGroupMapper splitGroupMapper;

    @Transactional(readOnly = true)
    public SplitGroupDTO getById(Long id) {
        return splitGroupMapper.toDto(getGroupById(id));
    }

    public List<SplitGroupDTO> findSplitGroups(String title, String description, String userLogin, String sortBy, Sort.Direction sortOrder) {
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
            User user = userService.getUserByLogin(userLogin);
            spec = spec.and((root, query, cb) ->
                    cb.isMember(user, root.get("user")));
        }
        Sort sort = Sort.by(sortOrder, sortBy);
        return splitGroupRepository.findAll(spec, sort).stream()
                .map(splitGroupMapper::toDto)
                .toList();
    }

    @Transactional
    public SplitGroupDTO add(CreateSplitGroupRequest splitGroupRequest) {
        SplitGroup splitGroup = new SplitGroup(splitGroupRequest.title(), splitGroupRequest.description());
        return splitGroupMapper.toDto(splitGroupRepository.save(splitGroup));
    }

    @Transactional
    public void deleteById(Long id) {
        SplitGroup splitGroup = getGroupById(id);
        if (!splitGroup.getUsers().isEmpty()) {
            throw new RemovalOfSplitGroupWithUserException(splitGroup.getTitle());
        }
        splitGroupRepository.deleteById(id);
    }

    @Transactional
    public void addUser(Long id, String login) {
        if (splitGroupRepository.existsByIdAndUsers_Login(id, login)) {
            throw new UserAlreadyInSplitGroupException(id, login);
        }
        SplitGroup splitGroup = getGroupById(id);
        User user = userService.getUserByLogin(login);
        splitGroup.addUser(user);
    }

    @Transactional
    public void deleteUser(Long id, String login) {
        if (!splitGroupRepository.existsByIdAndUsers_Login(id, login)) {
            throw new UserNotFoundInSplitGroupException(id, login);
        }
        SplitGroup splitGroup = getGroupById(id);
        User user = userService.getUserByLogin(login);
        splitGroup.removeUser(user);
    }

    @Transactional
    public SplitGroupDTO update(Long id, UpdateSplitGroupRequest updateRequest) {
        SplitGroup existingSplitGroup = getGroupById(id);
        if (updateRequest.title() != null) {
            existingSplitGroup.setTitle(updateRequest.title());
        }
        if (updateRequest.description() != null) {
            existingSplitGroup.setDescription(updateRequest.description());
        }
        return splitGroupMapper.toDto(splitGroupRepository.save(existingSplitGroup));
    }

    private SplitGroup getGroupById(Long id) {
        return splitGroupRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("SplitGroup with id '%d' not found".formatted(id)));
    }
}
