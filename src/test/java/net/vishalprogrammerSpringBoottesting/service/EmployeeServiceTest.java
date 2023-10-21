package net.vishalprogrammerSpringBoottesting.service;

import net.vishalprogrammerSpringBoottesting.exception.ResourceNotFoundException;
import net.vishalprogrammerSpringBoottesting.model.Employee;
import net.vishalprogrammerSpringBoottesting.repository.EmployeeRepository;
import net.vishalprogrammerSpringBoottesting.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
  @Mock
    private EmployeeRepository employeeRepository;
  @InjectMocks
    private EmployeeServiceImpl employeeService;

    private  Employee employee;


    @BeforeEach
public void setup(){
//         employeeRepository =Mockito.mock(EmployeeRepository.class);
//          employeeService  =new EmployeeServiceImpl(employeeRepository);
        employee=Employee.builder()
                .id(1L)
                .firstName("vishal")
                .lastName("shetkar")
                .email("vishal@gmail.com")
                .build();
}

        //junit test for saveEmployee method

@DisplayName("junit test for saveEmployee method")
 @Test
  public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
   //given -precondition or setup


     given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());

     given(employeeRepository.save(employee)).willReturn(employee);
    System.out.println(employeeRepository);
    System.out.println(employeeService);

   //when-action o behavior that we are going to test
    Employee  savedEmployee=employeeService.saveEmployee(employee);

    System.out.println(employeeService);
   //then-verify the output
     Assertions.assertThat(savedEmployee).isNotNull();

  }

//junit test for saveEmployee method which throw exception

    @DisplayName("junit test for saveEmployee method which throw exception")
    @Test
    public void givenExitingEmail_whenSaveEmployee_thenThrowsException(){
        //given -precondition or setup


        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));

       // given(employeeRepository.save(employee)).willReturn(employee);


        System.out.println(employeeRepository);
        System.out.println(employeeService);

        //when-action o behavior that we are going to test

        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class,()->{
            employeeService.saveEmployee(employee);
        });



        //then-verify the output
       verify(employeeRepository,never()).save(any(Employee.class));

    }

    //junit test for getAllEmployeemethod
    @DisplayName("junit test for getAllEmployeemethod")
     @Test
      public void givenEmployeeList_whenFindAll_thenReturnEmployeeList(){
       //given -precondition or setup
        Employee employee1=Employee.builder()
                .id(1L)
                .firstName("vishal")
                .lastName("shetkar")
                .email("vishal@gmail.com")
                .build();
        given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));


       //when-action o behavior that we are going to test
        List<Employee>    getallEmpolyee =employeeService.getAllEmployee();

       //then-verify the output
        Assertions.assertThat(getallEmpolyee).isNotNull();
        Assertions.assertThat(getallEmpolyee.size()).isEqualTo(2);

      }

    //junit test for getAllEmployeemethod
    @DisplayName("junit test for getAllEmployeemethod(navgetive senario)")
    @Test
    public void givenEmptyEmployeeList_whenFindAll_thenReturnEmptyEmployeeList(){
        //given -precondition or setup
        Employee employee1=Employee.builder()
                .id(1L)
                .firstName("vishal")
                .lastName("shetkar")
                .email("vishal@gmail.com")
                .build();
        given(employeeRepository.findAll()).willReturn(Collections.emptyList());


        //when-action o behavior that we are going to test
        List<Employee>    getallEmpolyee =employeeService.getAllEmployee();

        //then-verify the output
        Assertions.assertThat(getallEmpolyee).isEmpty();
        Assertions.assertThat(getallEmpolyee.size()).isEqualTo(0);

    }

    //junit test for Empolyee findByid
    @DisplayName("junit test for Empolyee findByid")
     @Test
      public void givenEmployeeId_whenFindById_thenReturnEmployeeObject(){
       //given -precondition or setup
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

       //when-action o behavior that we are going to test
        Employee employeeByid=employeeService.getEmployeeById(employee.getId()).get();

       //then-verify the output
         Assertions.assertThat(employeeByid).isNotNull();


      }

      //Junit test for update employee method
        @DisplayName("unit test for update employee method")
       @Test
        public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdateEmployeeObject(){
         //given -precondition or setup
            given(employeeRepository.save(employee)).willReturn(employee);
            employee.setEmail("rahul@gmail.com");
            employee.setFirstName("vilas");
         //when-action o behavior that we are going to test
           Employee updateEmployee=employeeService.updateEmployee(employee);


         //then-verify the output
           Assertions.assertThat(updateEmployee.getEmail()).isEqualTo("rahul@gmail.com");
           Assertions.assertThat(updateEmployee.getFirstName()).isEqualTo("vilas");


        }

        // junit test for delete employee
    @DisplayName("junit test for delete employee")
     @Test
      public void givenEmployeeId_whenDeleteEmployee_thenReturnDeleteEmployee(){
        long employeeId=1L;
       //given -precondition or setup
        willDoNothing().given(employeeRepository).deleteById(1L);

       //when-action o behavior that we are going to test
        employeeService.deleteEmployee(employee.getId());

       //then-verify the output
        verify(employeeRepository,times(1)).deleteById(employeeId);

      }



}
