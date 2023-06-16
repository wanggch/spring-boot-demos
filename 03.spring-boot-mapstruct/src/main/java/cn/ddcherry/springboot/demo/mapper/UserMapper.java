package cn.ddcherry.springboot.demo.mapper;

import cn.ddcherry.springboot.demo.dto.UserDto;
import cn.ddcherry.springboot.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {
}
