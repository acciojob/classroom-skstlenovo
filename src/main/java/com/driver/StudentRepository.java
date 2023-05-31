package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    private HashMap<String,Student> studentHashMap=new HashMap<>();
    private HashMap<String,Teacher> teacherHashMap=new HashMap<>();
    private HashMap<String, List<String>> pairHashMap=new HashMap<>();

    public void addStudent(Student student){
        studentHashMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher){
        teacherHashMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacher(String student,String teacher)
    {
        if(pairHashMap.containsKey(teacher))
        {
            pairHashMap.get(teacher).add(student);
        }
        else{
            List<String> list=new ArrayList<>();
            list.add(student);
            pairHashMap.put(teacher,list);
        }
    }
    public Student getStudentByName(String name){

        return studentHashMap.get(name);
    }

    public Teacher getTeacherByName(String name){
        return teacherHashMap.get(name);
    }

    public List<String> getStudentsByTeacherName(String name)
    {
        return pairHashMap.get(name);
    }

    public List<String> getAllStudents(){
        List<String> list=new ArrayList<>();
        for(String s: studentHashMap.keySet())
        {
            list.add(s);
        }
        return list;
    }

    public void deleteTeacherByName(String name)
    {
        List<String> studentlist=pairHashMap.get(name);
        for(String i:studentlist)
        {
            studentHashMap.remove(i);
        }
        pairHashMap.remove(name);
        teacherHashMap.remove(name);
    }
    public void deleteAllTeachers(){
        for(String teacherName:pairHashMap.keySet())
        {
            deleteTeacherByName(teacherName);
        }
    }
}
