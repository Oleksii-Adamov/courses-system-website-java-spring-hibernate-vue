package ua.lab2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.lab2.dto.CourseDTO;
import ua.lab2.entities.Course;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    List<CourseDTO> map(List<Course> courses);
}
