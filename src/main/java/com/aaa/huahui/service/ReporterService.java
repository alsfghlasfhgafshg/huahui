package com.aaa.huahui.service;

import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.ReporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ReporterService {

    @Autowired
    ReporterRepository reporterRepository;

    boolean canOperate(User user, int reporterid) {
        if (user == null) {
            return false;
        }

        if (reporterRepository.selectCountByReporteridAndShopId(user.getId(), reporterid) == 1) {
            return true;
        }
        return false;
    }

    //删除一个reporter
    boolean deleteReporter(int reporterid) {
        if (reporterRepository.deleteReporter(reporterid) == 1) {
            return true;
        }
        return false;
    }

    //删除所有reporter
    boolean deleteAAllRAeporter(int shopid) {
        ArrayList<Integer> allReporterId = reporterRepository.selectAllReporter(shopid);
        for (Integer i : allReporterId) {
            deleteReporter(i);
        }
        return true;
    }

    //添加一个reporter
    boolean addReposter(int shopid, String name) {
        if (reporterRepository.insertReporter(name, shopid) == 1) {
            return true;
        }
        return false;
    }

}
