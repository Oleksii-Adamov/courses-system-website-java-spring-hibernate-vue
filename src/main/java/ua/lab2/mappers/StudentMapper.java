package ua.lab2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.lab2.dto.StudentDTO;
import ua.lab2.entities.Student;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    List<StudentDTO> map(List<Student> students);
}
