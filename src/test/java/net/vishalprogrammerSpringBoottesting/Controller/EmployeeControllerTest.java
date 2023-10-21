package net.vishalprogrammerSpringBoottesting.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.vishalprogrammerSpringBoottesting.model.Employee;
import net.vishalprogrammerSpringBoottesting.service.EmployeeService;

import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static  org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest

public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;


//     @Test
//      public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception {
//       //given -precondition or setup
//         Employee employee=Employee.builder()
//                 .firstName("vishal")
//                 .lastName("shetkar")
//                 .email("vishal121@gmail.com")
//                 .build();
//         BDDMockito.given(employeeService.saveEmployee(ArgumentMatchers.any(Employee.class)))
//                 .willAnswer((invocation -> invocation.getArgument(0)));
//       //when-action o behavior that we are going to test
//         ResultActions response =mockMvc.perform(post("/api/Employees")
//                                    .contentType(MediaType.APPLICATION_JSON)
//                                            .content(objectMapper.writeValueAsString(employee))
//                                    );
//
//       //then-verify the output
//               response.andDo(MockMvcResultHandlers.print())
//                       .andExpect(MockMvcResultMatchers.status().isCreated())
//                       .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(employee.getFirstName())))
//                       .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(employee.getLastName())))
//                       .andExpect(MockMvcResultMatchers.jsonPath("$.email",CoreMatchers.is(employee.getEmail())));
//
//
//      }


// it is same upper code but we call directly static method in this method

    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception {
        //given -precondition or setup
        Employee employee=Employee.builder()
                .firstName("vishal")
                .lastName("shetkar")
                .email("vishal121@gmail.com")
                .build();
        given(employeeService.saveEmployee(any(Employee.class)))
                .willAnswer((invocation -> invocation.getArgument(0)));
        //when-action o behavior that we are going to test
        ResultActions response =mockMvc.perform(post("/api/Employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
        );

        //then-verify the output
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName",is(employee.getLastName())))
                .andExpect(jsonPath("$.email",is(employee.getEmail())));


    }


      //junit test for getAllEmployee\

     @Test
      public void givenlistOfEmployee_whengetAllEmployee_thenReturnListOfEmployee() throws Exception {
       //given -precondition or setup
         List<Employee> listOfEmployee=new ArrayList<>();
         listOfEmployee.add(Employee.builder().firstName("vishal").lastName("shetkar").email("vishal121@gmail.com").build());
         listOfEmployee.add(Employee.builder().firstName("rahul").lastName("sharma").email("rahul@gmail.com").build());
         given(employeeService.getAllEmployee()).willReturn(listOfEmployee);
       //when-action o behavior that we are going to test
          ResultActions response= mockMvc.perform(get("/api/Employees"));

       //then-verify the output
         response.andDo(print())
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.size()",is(listOfEmployee.size())));


      }


      // positive scenorio - valid employee id
    //junit testing  for getEmployee by id Rest Api

     @Test
      public void givenEmployeeID_whenGetByIdEmployee_thenReturnEmployeeObject() throws Exception {
       //given -precondition or setup
        long employeeId=1L;
         Employee employee=Employee.builder()
                 .firstName("vishal")
                 .lastName("shetkar")
                 .email("vishal121@gmail.com")
                 .build();
         given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));

       //when-action o behavior that we are going to test
         ResultActions  response= mockMvc.perform(get("/api/Employees/{id}",employeeId));

       //then-verify the output
                        response.andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(jsonPath("$.firstName",is(employee.getFirstName())))
                                .andExpect(jsonPath("$.lastName",is(employee.getLastName())))
                                . andExpect(jsonPath("$.email",is(employee.getEmail())));
      }


    // Negative scenorio - valid employee id
    //junit testing  for getEmployee by id Rest Api

    @Test
    public void givenInvaildEmployeeID_whenGetByIdEmployee_thenReturnEmpty() throws Exception {
        //given -precondition or setup
        long employeeId=1L;
        Employee employee=Employee.builder()
                .firstName("vishal")
                .lastName("shetkar")
                .email("vishal121@gmail.com")
                .build();
        given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());

        //when-action o behavior that we are going to test
        ResultActions  response= mockMvc.perform(get("/api/Employees/{id}",employeeId));

        //then-verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());

    }

        //positive senerio
    //junit test for updateEmployee Rest Api

     @Test
      public void givenupdateEmployee_whenUpdateEmployee_thenReturnUpdateEmployeeObject()throws Exception{
       //given -precondition or setup
        long employeeId=1L;
         Employee savedemployee=Employee.builder()
                 .firstName("vishal")
                 .lastName("shetkar")
                 .email("vishal121@gmail.com")
                 .build();
         Employee updateEmployee=Employee.builder()
                 .firstName("shubham")
                 .lastName("shetkar")
                 .email("shubham@gmail.com")
                 .build();
         given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(savedemployee));
         given(employeeService.updateEmployee(any(Employee.class))).willAnswer((e)->e.getArgument(0));

       //when-action o behavior that we are going to test
             ResultActions  response= mockMvc.perform(put("/api/Employees/{id}",employeeId)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(updateEmployee))
                                );


       //then-verify the output
                    response.andExpect(status().isOk())
                            .andDo(print())
                            .andExpect(jsonPath("$.firstName",is(updateEmployee.getFirstName())))
                            .andExpect(jsonPath("$.lastName",is(updateEmployee.getLastName())))
                            .andExpect(jsonPath("$.email",is(updateEmployee.getEmail())));

      }


      //Junit test for Negative senerio
      @Test
      public void givenupdateEmployee_whenUpdateEmployee_thenRetur404()throws Exception{
          //given -precondition or setup
          long employeeId=1L;
          Employee savedemployee=Employee.builder()
                  .firstName("vishal")
                  .lastName("shetkar")
                  .email("vishal121@gmail.com")
                  .build();
          Employee updateEmployee=Employee.builder()
                  .firstName("shubham")
                  .lastName("shetkar")
                  .email("shubham@gmail.com")
                  .build();
          given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
          given(employeeService.updateEmployee(any(Employee.class))).willAnswer((e)->e.getArgument(0));

          //when-action o behavior that we are going to test
          ResultActions  response= mockMvc.perform(put("/api/Employees/{id}",employeeId)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(updateEmployee))
          );


          //then-verify the output
          response.andExpect(status().isNotFound())
                  .andDo(print());


      }

      // Junit test for Delete Employee Rest Api

     @Test
      public void givenEmplyeeId_whenDeleteById_thenReturn200() throws Exception{
       //given -precondition or setup
        long employeeId=1L;

      willDoNothing().given(employeeService).deleteEmployee(employeeId);



       //when-action o behavior that we are going to test
                    ResultActions response=   mockMvc.perform(delete("/api/Employees/{id}",employeeId));

       //then-verify the output
                response.andExpect(status().isOk())
                        .andDo(print());

      }

}
