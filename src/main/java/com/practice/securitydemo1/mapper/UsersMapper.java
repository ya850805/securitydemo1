package com.practice.securitydemo1.mapper;

import com.practice.securitydemo1.domain.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
  Users findByUsername(String username);
}
