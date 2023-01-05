package com.luv2code.component.service.impl;

import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("service")
public class ApplicationServiceImpl implements ApplicationService {
    private ApplicationDao applicationDao;

    @Autowired
    public ApplicationServiceImpl(ApplicationDao applicationDao) {
        System.out.printf("ApplicationServiceImpl() constructor called. Injected: %s\n", applicationDao);
        this.applicationDao = applicationDao;
    }

    public double addGradeResultsForSingleClass(List<Double> numbers) {
        return applicationDao.addGradeResultsForSingleClass(numbers);
    }

    public double findGradePointAverage (List<Double> grades) {
        return applicationDao.findGradePointAverage(grades);
    }

    public boolean checkNull(Object obj) {
        return applicationDao.checkNull(obj);
    }

    @Override
    public ApplicationDao getDao() {
        return applicationDao;
    }

}
