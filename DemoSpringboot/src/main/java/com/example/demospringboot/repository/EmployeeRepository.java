package com.example.demospringboot.repository;

import com.example.demospringboot.entity.Employee;
import com.example.demospringboot.query.CountEmployeeByRoleName;
import com.example.demospringboot.query.CountEmployeeRole;
import com.example.demospringboot.query.ICountEmployeeRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "select e.*, r.name as role_name from employee e inner join role r on e.role_id = r.id where e.role_id = ?1", nativeQuery = true)
    List<Employee> getByRoleId(Integer roleId);

    @Query(value = "select e.*, p.name as project_name from employee e inner join project p on e.project_id = p.id where e.project_id = ?1", nativeQuery = true)
    List<Employee> getByProjectId(Integer projecId);

    @Query(value = "select e.*, p.name as project_name from employee e inner join project p on e.project_id = p.id where p.name = ?1", nativeQuery = true)
    List<Employee> getByProjectName(String str);

    @Query(value = "select e.*, r.name as role_name from employee e inner join role r on e.role_id = r.id where r.name = ?1", nativeQuery = true)
    List<Employee> getByRoleName(String str);

    @Query(value = "select e.*, r.name as role_name, p.name as project_name from employee as e inner join project as p on e.project_id = p.id inner join role r on e.role_id = r.id where e.name = ?1", nativeQuery = true)
    List<Employee> getEmployeeByName(String str);

    Optional<Employee> findEmployeeByUsername(String Username);

    @Query(value = "select e from Employee e order by e.id")
    Page<Employee> getAllEmployeeWithPaging(Pageable pageable);

    @Query(value = "select e from Employee e")
    Slice<Employee> getAllEmployeeWithSlice(Pageable pageable);

    @Query(value = "SELECT new com.example.demospringboot.query.CountEmployeeByRoleName(r.name, e.id) from Role r left outer join Employee e on r.id = e.role.id")
    List<CountEmployeeByRoleName> getEmployeeByRoleName();

    @Query("select r.id as typeRole, count(e.role) as total from Employee e, Role r where e.role.id = r.id group by r.id")
    List<ICountEmployeeRole> countTotalEmployeeByRoleInterface();

    @Query("SELECT new com.example.demospringboot.query.CountEmployeeRole(r.id, COUNT (e.role)) "
            + "FROM Employee e, Role r Where e.role.id = r.id GROUP BY r.id ORDER BY r.id DESC")
    List<CountEmployeeRole> countTotalEmployeeByRole();


//    List<Employee> findAllByNameLike(String name, Pageable pageable);

//    findByName

}
