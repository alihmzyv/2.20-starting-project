package com.luv2code.component.service;

import com.luv2code.component.dao.impl.ApplicationDaoImpl;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.impl.ApplicationServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationServiceImplTest {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private CollegeStudent student;

    @Autowired
    private StudentGrades studentGrades;

    @Mock
    private ApplicationDaoImpl mockDao;

    @InjectMocks
    private ApplicationServiceImpl service;

    @BeforeEach
    void beforeEach() {
        studentGrades.setMathGradeResults(List.of(55.5, 55.5, 50.5));
        student.setStudentGrades(studentGrades);
    }

    @Test
    @DisplayName("Test @Mock Not Same With Bean By Context")
    void testMockNotSameWithBean() {
        assertNotSame(context.getBean("dao", ApplicationDaoImpl.class), mockDao);
    }

    @Test
    @DisplayName("Test @InjectMocks Not Same With Bean By Context")
    void testInjectMocksNotSameWithBean() {
        assertNotSame(context.getBean("service", ApplicationServiceImpl.class), service);
    }


    @Test
    @DisplayName("Test addGradeResultsForSingleClass()")
    void testAddGradeResultsForSingleClass() {
        when(mockDao.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults())).thenReturn(161.5);
        assertEquals(161.5, service.addGradeResultsForSingleClass(studentGrades.getMathGradeResults()));
        assertNotEquals(162.5, service.addGradeResultsForSingleClass(studentGrades.getMathGradeResults()));
        verify(mockDao, times(2)).addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults());
    }
}
