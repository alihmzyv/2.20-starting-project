package com.luv2code.component.service;

import com.luv2code.component.dao.impl.ApplicationDaoImpl;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.impl.ApplicationServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationServiceImplBestApproachTest {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private CollegeStudent student;

    @Autowired
    private StudentGrades studentGrades;

    @MockBean(name = "dao")
    /*
    From docs:
    Mocks can be registered by type or by bean name.
    Any existing single bean of the same type defined in the context will be replaced by the mock.
    If no existing bean is defined a new one will be added.
     */
    private ApplicationDaoImpl mockDao;

    @Autowired
    private ApplicationServiceImpl service;

    @BeforeEach
    void beforeEach() {
        /*System.out.println("All bean names:");
        Arrays.stream(context.getBeanDefinitionNames())
                        .forEach(System.out::println);*/
        studentGrades.setMathGradeResults(List.of(55.5, 55.5, 50.5));
        student.setStudentGrades(studentGrades);
    }

    @Test
    @DisplayName("Test @MockBean Same With Bean By Context") //replaces same type of bean existing
    void testMockBeanSameWithBean() {
        assertSame(context.getBean(ApplicationDaoImpl.class), mockDao);
    }

    @Test
    @DisplayName("Test @Autowired Same With Bean By Context") //replaces the previous bean
    void testAutowiredSameWithBean() {
        assertSame(context.getBean(ApplicationServiceImpl.class), service);
    }


    @Test
    @DisplayName("Test addGradeResultsForSingleClass()")
    void testAddGradeResultsForSingleClass() {
        when(mockDao.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults())).thenReturn(161.5);
        assertEquals(161.5, service.addGradeResultsForSingleClass(studentGrades.getMathGradeResults()));
        assertNotEquals(162.5, service.addGradeResultsForSingleClass(studentGrades.getMathGradeResults()));
        verify(mockDao, times(2)).addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults());
    }

    @Test
    @DisplayName("Test doThrow")
    void testThrows() {
        doThrow(new IllegalStateException("Cannot be callled")).when(mockDao).checkNull(null);
        assertThrowsExactly(IllegalStateException.class, () -> service.checkNull(null));
        verify(mockDao, times(1)).checkNull(null);
    }

    @Test
    @DisplayName("Test Ongoing Stubbing")
    void testOngoingStubbing() {
        when(mockDao.checkNull(null))
                .thenReturn(false)
                .thenThrow(new IllegalStateException("Cannot pass null in twice."));
        assertFalse(service.checkNull(null));
        assertThrowsExactly(IllegalStateException.class, () -> service.checkNull(null));
        verify(mockDao, times(2)).checkNull(null);
    }
}
