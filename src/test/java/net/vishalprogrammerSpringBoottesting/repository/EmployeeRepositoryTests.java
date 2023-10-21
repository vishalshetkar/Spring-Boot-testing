package net.vishalprogrammerSpringBoottesting.repository;

import net.vishalprogrammerSpringBoottesting.model.Employee;
import static org.assertj.core.api.Assertions.assertThat;

import org.aspectj.apache.bcel.classfile.Module;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {
 @Autowired
private     EmployeeRepository employeeRepository;

 private Employee employee;
 //it is execute each test cases

 @BeforeEach
 public void setUp(){
      employee=Employee.builder()
             .firstName("vishal")
             .lastName("shetkar")
             .email("vishal@121")
             .build();
 }




 // jUnit test for save  Employee Operation

@DisplayName("junit Test for save Employee oparation")
 @Test
 public void  givenEmployeeObject_whensave_thenReturnSavedEmployee(){
     //given-precondition or setup
//  Employee employee=Employee.builder()
//          .firstName("vishal")
//          .lastName("shetkar")
//          .email("vishal@121")
//          .build();

     //when-action or behavior that we are going to test
               Employee saveEmployee=employeeRepository.save(employee);

     //then-verify the output
 assertThat(saveEmployee).isNotNull();
  assertThat(saveEmployee.getId()).isGreaterThan(0);
 }


 // junit test for getAllEmployee operation

 @DisplayName(" junit test for getAllEmployee operation")
 @Test
 public void givenEmployeeList_whenFindAll_thenEmployeeList(){
  //given -precondition or setup
//  Employee employee=Employee.builder()
//          .firstName("vishal")
//          .lastName("shetkar")
//          .email("vishal@121")
//          .build();
  Employee employee1=Employee.builder()
          .firstName("rahul")
          .lastName("sharma")
          .email("rahul@121")
          .build();
  employeeRepository.save(employee);
  employeeRepository.save(employee1);
  //when-action o behavior that we are going to test
  List<Employee> employeeList=employeeRepository.findAll();



  //then-verify the output
   assertThat(employeeList).isNotNull();
   assertThat(employeeList.size()).isEqualTo(2);

 }


 // junit test get employee id operation

@DisplayName("junit test get employee id operation")
@Test
public void givenEmployeeObject_whenFindbyId_thenReturnEmployeeObject(){
    //given -precondition or setup
//   Employee employee=Employee.builder()
//           .firstName("vishal")
//           .lastName("shetkar")
//           .email("vishal@121")
//           .build();

    employeeRepository.save(employee);

    //when-action o behavior that we are going to test
        Employee  employeeId=employeeRepository.findById(employee.getId()).get();

    //then-verify the output
  assertThat(employeeId).isNotNull();


   }



   //Junit test get Employee By email operation


    @DisplayName("unit test get Employee By email operation")
    @Test
     public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject(){
      //given -precondition or setup

//        Employee employee=Employee.builder()
//                .firstName("vishal")
//                .lastName("shetkar")
//                .email("vishal@121")
//                .build();

        employeeRepository.save(employee);

      //when-action o behavior that we are going to test
       Employee   employeeByEmail =employeeRepository.findByEmail(employee.getEmail()).get();

      //then-verify the output
     assertThat(employeeByEmail).isNotNull();


     }



     //Junit test for Update Employee operation
    @DisplayName("Junit test for Update Employee operation")
     @Test
      public void givenEmployeeObject_whenUpdateEmployee_thenreturnupdateEmployee(){
       //given -precondition or setup
//         Employee employee=Employee.builder()
//                 .firstName("vishal")
//                 .lastName("shetkar")
//                 .email("vishal@121")
//                 .build();
         employeeRepository.save(employee);

       //when-action o behavior that we are going to test
         Employee findByEmployee= employeeRepository.findById(employee.getId()).get();
         findByEmployee.setEmail("ravi@gmail.com");
         findByEmployee.setFirstName("ravi");
        Employee saveEmployee=employeeRepository.save(findByEmployee);

       //then-verify the output
        assertThat(saveEmployee.getEmail()).isEqualTo("ravi@gmail.com");
         assertThat(saveEmployee.getFirstName()).isEqualTo("ravi");

      }


    //  Junit test for delete employee oparation


    @DisplayName("Junit test for delete employee oparation")
     @Test
      public void givenEmployeeObject_whenDeleteEmployee_thenRemoveEmployee(){
       //given -precondition or setup
//        Employee employee=Employee.builder()
//                .firstName("vishal")
//                .lastName("shetkar")
//                .email("vishal@121")
//                .build();
        employeeRepository.save(employee);

       //when-action o behavior that we are going to test
       employeeRepository.deleteById(employee.getId());
       Optional<Employee> optionalEmployee =employeeRepository.findById(employee.getId());


       //then-verify the output

        assertThat(optionalEmployee).isEmpty();


      }

      //Junit test custom query using JPQL index params


        @DisplayName("Junit test custom query using JPQL index params")
       @Test
        public void givenFirstNameAndLastName_whenFindByJpql_thenReturnEmployeeObject(){
         //given -precondition or setup
//           Employee employee=Employee.builder()
//                   .firstName("vishal")
//                   .lastName("shetkar")
//                   .email("vishal@121")
//                   .build();
           employeeRepository.save(employee);
           String firstName="vishal";
           String lastName="shetkar";

         //when-action o behavior that we are going to test
            Employee  employeefind= employeeRepository.findByJPQL(firstName,lastName);

         //then-verify the output
           assertThat(employeefind).isNotNull();



        }


        //Junit test custom query using JPQL named params

    @DisplayName("Junit test custom query using JPQL named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJpqlparam_thenReturnEmployeeObject(){
        //given -precondition or setup
//        Employee employee=Employee.builder()
//                .firstName("vishal")
//                .lastName("shetkar")
//                .email("vishal@121")
//                .build();
        employeeRepository.save(employee);
        String firstName="vishal";
        String lastName="shetkar";

        //when-action o behavior that we are going to test
        Employee  employeefind= employeeRepository.findByJPQLParam(firstName,lastName);

        //then-verify the output
        assertThat(employeefind).isNotNull();



    }


    // JUNIT test custom quary using Nativesql with index param
    @DisplayName("JUNIT test custom quary using Nativesql with index param")
     @Test
      public void givenFirstNameandLastName_whenNativesql_thenReturnEmployeeObject(){
       //given -precondition or setup
//        Employee employee=Employee.builder()
//                .firstName("vishal")
//                .lastName("shetkar")
//                .email("vishal@121")
//                .build();
        employeeRepository.save(employee);

       //when-action o behavior that we are going to test
                 Employee     employeeFind = employeeRepository.findByNativeSql(employee.getFirstName(),employee.getLastName());


       //then-verify the output
        assertThat(employeeFind).isNotNull();


      }


    // JUNIT test custom quary using Nativesql with named param
    @DisplayName("JUNIT test custom quary using Nativesql with named param")
    @Test
    public void givenFirstNameandLastName_whenNativesqlNamed_thenReturnEmployeeObject(){
        //given -precondition or setup
//        Employee employee=Employee.builder()
//                .firstName("vishal")
//                .lastName("shetkar")
//                .email("vishal@121")
//                .build();
        employeeRepository.save(employee);

        //when-action o behavior that we are going to test
        Employee     employeeFind = employeeRepository.findByNativeSqlNamed(employee.getFirstName(),employee.getLastName());


        //then-verify the output
        assertThat(employeeFind).isNotNull();


    }






}
