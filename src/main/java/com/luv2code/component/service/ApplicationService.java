package com.luv2code.component.service;

import com.luv2code.component.dao.ApplicationDao;

import java.util.List;

public interface ApplicationService {
    double addGradeResultsForSingleClass(List<Double> grades);
    double findGradePointAverage (List<Double> grades);
    boolean checkNull(Object obj);
    ApplicationDao getDao();
}
