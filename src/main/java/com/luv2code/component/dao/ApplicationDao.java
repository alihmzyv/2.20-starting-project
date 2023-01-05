package com.luv2code.component.dao;

import java.util.List;

public interface ApplicationDao {
    double addGradeResultsForSingleClass(List<Double> grades);
    double findGradePointAverage (List<Double> grades);
    boolean checkNull(Object obj);
}
