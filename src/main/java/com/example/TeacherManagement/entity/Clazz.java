package com.example.TeacherManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@SqlResultSetMapping(
        name = "ClassesThatHaveNotBeenAssigned",
        classes = {
                @ConstructorResult(
                        targetClass = com.example.TeacherManagement.service.dto.ClazzHaveNotBeenAssignedDto.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "classId", type = String.class),
                                @ColumnResult(name = "startDate", type = LocalDate.class)
                        }
                )
        }
)

@NamedNativeQuery(
        name = Clazz.FIND_CLASSES_THAT_HAVE_NOT_BEEN_ASSIGNED,
        query = "SELECT c.id as id, c.class_id as classId, c.start_date as startDate FROM Clazz c " +
                "WHERE c.id NOT IN " +
                "(SELECT clazz_id FROM assignment_detail)",
        resultSetMapping = "ClassesThatHaveNotBeenAssigned"
)


public class Clazz {
    public static final String FIND_CLASSES_THAT_HAVE_NOT_BEEN_ASSIGNED = "Clazz.findClassesThatHaveNotBeenAssigned";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotNull
    private String classId;

    @NotNull
    @Max(25)
    private Integer numberOfStudent;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer totalCourseHours;

    private String courseBook;

}
