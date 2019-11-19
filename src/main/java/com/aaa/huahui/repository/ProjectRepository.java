package com.aaa.huahui.repository;

import com.aaa.huahui.model.Project;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Optional;

@Mapper
public interface ProjectRepository {

    @Insert("insert into project(factoryid,projectname)values(#{factoryid},#{projectname})")
    @Options(useGeneratedKeys = true, keyProperty = "projectid")
    int insertProject(Project project);

    @Delete("delete from project where projectid=#{projectid}")
    int deleteProjectById(@Param("projectid") int projectid);

    @Delete("delete from project where projectid=#{projectid}")
    int deleteProject(Project project);

    @Select("select * from project where factoryid=#{projectid}")
    ArrayList<Project> selectAllProject(@Param("projectid") int projectid);

    @Update("update project set projectname=#{projectname} where projectid=#{projectid}")
    int updateProject(@Param("projectid") int projectid, @Param("projectname") String projectname);
}
