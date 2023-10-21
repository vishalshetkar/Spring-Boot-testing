package net.vishalprogrammerSpringBoottesting.repository;

import net.vishalprogrammerSpringBoottesting.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmail(String email);

    //define Custom query using JPQL with index params
    @Query("select e from Employee e where e.firstName= ?1 and e.lastName= ?2")
    Employee findByJPQL(String firstName,String lastName);


    @Query("select e from Employee e where e.firstName= :firstName and e.lastName= :lastName")
    Employee findByJPQLParam(@Param("firstName") String firstName,@Param("lastName") String lastName);


    //define custom query using Native sql with indext param
    @Query(value = "select * from employee e where e.first_name=?1 and e.last_name=?2",nativeQuery = true)
    Employee findByNativeSql(String firstName,String lastName);



    //define custom query using Native sql with named param
    @Query(value = "select * from employee e where e.first_name=:firstName and e.last_name=:lastName",nativeQuery = true)
    Employee findByNativeSqlNamed(@Param("firstName") String firstName,@Param("lastName") String lastName);
}
