package com.aaa.huahui.repository;

import com.aaa.huahui.model.Project;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Optional;

@Mapper
public interface ProjectRepository {

    @Insert("insert into project(factoryid,projectname,category,pinpai)values(#{factoryid},#{projectname},#{category},#{pinpai})")
    @Options(useGeneratedKeys = true, keyProperty = "projectid")
    int insertProject(Project project);

    @Delete("delete from project where projectid=#{projectid}")
    int deleteProjectById(@Param("projectid") int projectid);

    @Delete("delete from project where projectid=#{projectid}")
    int deleteProject(Project project);

    @Select("select * from project where factoryid=#{factoryid}")
    ArrayList<Project> selectAllProject(@Param("factoryid") int factoryid);

    @Update("update project set projectname=#{projectname} where projectid=#{projectid}")
    int updateProject(@Param("projectid") int projectid, @Param("projectname") String projectname);

    @Select("select factoryid from project where category=#{category}")
    ArrayList<Integer> findFactoryidByCategory(String category);

    @Select("select pinpai from project where projectid=#{projectid}")
    String findPinpaiByProjectid(@Param("projectid")int projectid);

    @Select("select project.category from project,factory where project.projectname=#{projectname} and project.factoryid=factory.id and factory.brandid=#{brandid}")
    String findCategoryByProjectName(@Param("projectname") String projectname, @Param("brandid") int brandid);

}
