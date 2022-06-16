package com.cg.employee.tests;

import com.cg.employee.utils.IdUtils;
import com.cg.employee.utils.TestSetup;
import com.cg.employee.beans.Employee;
import com.cg.employee.mapper.EmployeeResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

public class EmployeeServiceTests extends TestSetup {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void addNonExistingEmployeeTest() throws Exception {
        String uri = "/api/addEmployee";
        Employee employee = new Employee();
        employee.setFirst_name("ABCD");
        int id = IdUtils.randomIdGenerator();
        System.setProperty("id", String.valueOf(id));
        employee.setId(id);
        employee.setLast_name("Paul");
        employee.setPhone_number("987654210");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(employee))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(201, status);
        EmployeeResponse employeeResponse = super.mapFromJson(mvcResult.getResponse().getContentAsString(), EmployeeResponse.class);
        Assert.assertEquals(employeeResponse.getMsg(), "Employee created");
        Assert.assertEquals(employeeResponse.getId(), id);
    }

    @Test
    public void addExistingEmployeeTest() throws Exception {
        String uri = "/api/addEmployee";
        Employee employee = new Employee();
        employee.setFirst_name("ABCD");
        employee.setId(123456);
        employee.setLast_name("Paul");
        employee.setPhone_number("987654210");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(employee))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(202, status);
        EmployeeResponse employeeResponse = super.mapFromJson(mvcResult.getResponse().getContentAsString(), EmployeeResponse.class);
        Assert.assertEquals(employeeResponse.getMsg(), "Employee exists in database");
    }

    @Test
    public void getExistingEmployeeByIdTest() throws Exception {
        String uri = "/api/getEmployee/id";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .queryParam("id", "123458")).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        Employee employee = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Employee.class);
        Assert.assertEquals("TEST2", employee.getFirst_name());
        Assert.assertEquals("TEST3", employee.getLast_name());
        Assert.assertEquals("876543210", employee.getPhone_number());
    }

    @Test
    public void getExistingEmployeeByFirstNameTest() throws Exception {
        String uri = "/api/getEmployee/firstName";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .queryParam("first_name", "TEST5")).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        List<Employee> employees = super.mapFromJson(mvcResult.getResponse().getContentAsString(), List.class);
        Assert.assertTrue(employees.size() > 0);
    }

    @Test
    public void getExistingEmployeeByLastNameTest() throws Exception {
        String uri = "/api/getEmployee/lastName";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .queryParam("last_name", "TEST3")).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        List<Employee> employees = super.mapFromJson(mvcResult.getResponse().getContentAsString(), List.class);
        Assert.assertTrue(employees.size() > 0);
    }

    @Test
    public void employeeNotFoundTest() throws Exception {
        String getEmployeeByFNURI = "/api/getEmployee/firstName";
        String getEmployeeByIdURI = "/api/getEmployee/id";
        String getEmployeeByLNURI = "/api/getEmployee/lastName";
        MvcResult mvcResultByID = mvc.perform(MockMvcRequestBuilders.get(getEmployeeByIdURI)
                .queryParam("id", "9887")).andReturn();
        Assert.assertEquals(404, mvcResultByID.getResponse().getStatus());
        MvcResult mvcResultByFN = mvc.perform(MockMvcRequestBuilders.get(getEmployeeByFNURI)
                .queryParam("first_name", "XYZ")).andReturn();
        Assert.assertEquals(404, mvcResultByFN.getResponse().getStatus());
        MvcResult mvcResultByLN = mvc.perform(MockMvcRequestBuilders.get(getEmployeeByLNURI)
                .queryParam("last_name", "ABC")).andReturn();
        Assert.assertEquals(404, mvcResultByLN.getResponse().getStatus());

    }

    @Test
    public void deleteExistingEmployeeTest() throws Exception {
        String uri = "/api/deleteEmployee";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .queryParam("id", System.getProperty("id"))).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        EmployeeResponse employeeResponse = super.mapFromJson(mvcResult.getResponse().getContentAsString(), EmployeeResponse.class);
        Assert.assertEquals("Employee deleted", employeeResponse.getMsg());
        Assert.assertEquals(Integer.parseInt(System.getProperty("id")), employeeResponse.getId());

    }

    @Test
    public void deleteNonExistingEmployeeTest() throws Exception {
        String uri = "/api/deleteEmployee";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .queryParam("id", "123")).andReturn();
        Assert.assertEquals(404, mvcResult.getResponse().getStatus());
        EmployeeResponse employeeResponse = super.mapFromJson(mvcResult.getResponse().getContentAsString(), EmployeeResponse.class);
        Assert.assertEquals("Employee do not exist in database", employeeResponse.getMsg());
        Assert.assertEquals(123, employeeResponse.getId());
    }

    @Test
    public void updateExistingEmployeeTest() throws Exception {
        String uri = "/api/updateEmployee";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .queryParam("id", "123456")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "    \"first_name\" : \"Test1234\",\n" +
                        "    \"last_name\" : \"Test9876\",\n" +
                        "    \"phone_number\" : \"6543234\"\n" +
                        "}")).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        Employee employee = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Employee.class);
        Assert.assertEquals(123456, employee.getId());
        Assert.assertEquals("Test1234", employee.getFirst_name());
        Assert.assertEquals("Test9876", employee.getLast_name());
        Assert.assertEquals("6543234", employee.getPhone_number());
    }

    @Test
    public void updateNonExistingEmployeeTest() throws Exception {
        String uri = "/api/updateEmployee";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .queryParam("id", "123")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "    \"first_name\" : \"ABCD\",\n" +
                        "    \"last_name\" : \"XYZ\",\n" +
                        "    \"phone_number\" : \"986876\"\n" +
                        "}")).andReturn();
        Assert.assertEquals(404, mvcResult.getResponse().getStatus());
    }

}
