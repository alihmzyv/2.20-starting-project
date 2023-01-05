package com.luv2code.component.dao.impl;

import com.luv2code.component.dao.ApplicationDao;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Repository("dao")
public class ApplicationDaoImpl implements ApplicationDao {

    public ApplicationDaoImpl() {
        System.out.println("ApplicationDaoImpl() constructor called.");
    }

    public double addGradeResultsForSingleClass(List<Double> grades) {
        double result = 0;
        for (double i : grades) {
            result += i;
        }
        return result;
    }

    public double findGradePointAverage (List<Double> grades) {
        int lengthOfGrades = grades.size();
        double sum = addGradeResultsForSingleClass(grades);
        double result = sum / lengthOfGrades;

        // add a round function
        BigDecimal resultRound = BigDecimal.valueOf(result);
        resultRound = resultRound.setScale(2, RoundingMode.HALF_UP);
        return resultRound.doubleValue();

    }

    public boolean checkNull(Object obj) {
        return obj == null;
    }
}
