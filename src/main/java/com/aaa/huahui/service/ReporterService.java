package com.aaa.huahui.service;

import com.aaa.huahui.repository.ReporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ReporterService {

    @Autowired
    ReporterRepository reporterRepository;


    @Transactional
    boolean deleteReporter(int reporterid) {
        if (reporterRepository.deleteReporter(reporterid) == 1) {
            return true;
        }
        return false;
    }

    boolean deleteAAllRAeporter(int shopid) {
        ArrayList<Integer> allReporterId = reporterRepository.selectAllReporter(shopid);
        for (Integer i : allReporterId) {
            deleteReporter(i);
        }
        return true;
    }

}
