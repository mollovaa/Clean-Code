package fmi.clean.code.project.mappers;

import fmi.clean.code.project.models.dtos.UserRegisterDto;
import fmi.clean.code.project.models.entities.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

  User registerDtoToUser(UserRegisterDto userRegisterDto);
}