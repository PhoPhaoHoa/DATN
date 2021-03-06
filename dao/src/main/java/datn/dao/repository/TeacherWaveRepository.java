package datn.dao.repository;

import datn.dao.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TeacherWaveRepository extends JpaRepository<TeacherWave, String> {

    @Query("select st from TeacherWave st where st.projectWave=:wave")
    ArrayList<TeacherWave> findByProjectWave(@Param("wave") ProjectWave wave);

    @Query("select st from TeacherWave st where st.projectWave=:wave and st.teacher=:teacher")
    ArrayList<TeacherWave> findByTeacherAndProjectWave(@Param("teacher")Teacher teacher, @Param("wave") ProjectWave wave);

    @Query("select st from TeacherWave st where st.id=:id")
    TeacherWave findOne(@Param("id") String id);

    @Query("select st from TeacherWave st where st.teacher = :teacher")
    ArrayList<TeacherWave> findByTeacher(@Param("teacher")Teacher teacher);
}
