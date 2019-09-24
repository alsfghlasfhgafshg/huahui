package com.aaa.huahui.service;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.Staff;
import com.aaa.huahui.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AvatarService {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ReporterRepository reporterRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    FileService fileService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;



    public String updateAvatar(int id, MultipartFile file) {
        String role = userRoleRepository.queryRoleNameByUserId(id);
        if (role == null) {
            return null;
        }
        String filepath = fileService.uploadImage(file);
        int a = 0;
        switch (role) {
            case ROLE.BRAND:
                a = brandRepository.updateBrandAvatar(id, filepath);
                break;
            case ROLE.REPORTER:
                a = reporterRepository.updateReporterAvatar(id, filepath);
                break;
            case ROLE.STAFF:
                a = staffRepository.updateStaffAvatar(id, filepath);
                break;
            default:
                break;
        }
        if (a == 1) {
            return filepath;
        } else {
            fileService.deleteImage(filepath.split("/")[filepath.split("/").length - 1]);
            return null;
        }
    }
}
