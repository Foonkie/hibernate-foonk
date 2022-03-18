package com.foonk.service;

import com.foonk.dao.UserRepository;
import com.foonk.dto.UserReadDto;
import com.foonk.entity.User;
import com.foonk.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.graph.GraphSemantic;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    public Optional<UserReadDto> findById(Long id){
        Map<String, Object> properties= Map.of(
                GraphSemantic.LOAD.getJpaHintName(), userRepository.getEntityManager().getEntityGraph("WithCompany")
        )
        return userRepository.findById(id).map(userReadMapper::mapFrom);
    }
    public boolean delete(Long id){
        var maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(user.getId()));
        return  maybeUser.isPresent();
    }

}
