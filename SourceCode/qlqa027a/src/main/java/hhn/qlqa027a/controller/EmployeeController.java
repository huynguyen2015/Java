/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.controller;

import hhn.qlqa027a.GUI.EmployeeForm;
import hhn.qlqa027a.GUI.EmployeePage;
import hhn.qlqa027a.entities.Certificate;
import hhn.qlqa027a.entities.Employee;
import hhn.qlqa027a.entities.Role;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.framework.AbstractDataPageController;
import hhn.qlqa027a.framework.DataPageView;
import hhn.qlqa027a.service.AbstractService;
import hhn.qlqa027a.service.CertificateService;
import hhn.qlqa027a.service.EmployeeService;
import static hhn.qlqa027a.service.QueryParameter.with;
import hhn.qlqa027a.service.RoleService;
import hhn.qlqa027a.service.StatusService;
import java.util.List;

/**
 * Dashboard Controller.
 *
 * @author Cem Ikta
 */
public class EmployeeController extends AbstractDataPageController<Employee> {

    @Override
    protected AbstractService<Employee> createService() {
        return new EmployeeService();
    }

    @Override
    protected DataPageView<Employee> createDataPageView() {
        return new EmployeePage();
    }

    @Override
    public void openFormView(Employee employee) {
        new EmployeeForm(this, employee).showDialog();
    }

    @Override
    public void onAddNew() {
        Employee employee = new Employee();        
        openFormView(employee);
    }

    @Override
    public List<Employee> getData(String filter, int start, int end) {
        if (filter.equals("")) {
            return getService().getListWithNamedQuery(getNamedQuery(), start, end);
        } else {
            
            return getService().getListWithNamedQuery(getNamedQueryWithFilter(),
                    with("fullName", "%" + filter + "%")
                    .and("email", "%" +filter+ "%")
                    .and("phone", "%" +filter+ "%")
                    .and("identityNumber", "%" +filter+ "%").parameters(), start, end);
        }
    }

    @Override
    public int getDataSize(String filter) {
        if (filter.equals("")) {
            return getService().getListWithNamedQuery(getNamedQuery()).size();
        } else {
            return getService().getListWithNamedQuery(getNamedQueryWithFilter(),
                     with("fullName", "%" + filter + "%")
                    .and("email", "%" +filter+ "%")
                    .and("phone", "%" +filter+ "%")
                    .and("identityNumber", "%" +filter+ "%").parameters()).size();
        }
    }

    @Override
    public String getNamedQuery() {
        return Employee.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return Employee.FIND_BY_FILTER;
    }

    @Override
    public String getName() {
        return "EmployeeController";
    }

    /**
     * Huy Nguyen, 2016-05-29, Gets roles from database.
     *
     * @return role list
     */
    public List<Role> getRoleList() {
        return new RoleService().getListWithNamedQuery(Role.FIND_ALL);
    }
    
    /**
     * Huy Nguyen, 2016-05-29, Gets status from database.
     *
     * @return status list
     */
    public List<Status> getStatusList() {
        return new StatusService().getListWithNamedQuery(Status.FIND_ALL);
    }
    
    /**
     * Huy Nguyen, 2016-05-29, Check is email in used.
     *
     * @param email
     * @param employeeId
     * @return status list
     */
    public boolean isEmailInUsed(String email, int employeeId) {
        return new EmployeeService().isUsedEmail(email, employeeId);
    }
    
    /**
     * Huy Nguyen, 2016-06-01, Gets certificates from database.
     *
     * @return certificate list
     */
    public List<Certificate> getCertificateList() {
        return new CertificateService().getListWithNamedQuery(Certificate.FIND_ALL);
    }
    
    /**
     * Huy Nguyen, 2016-06-01, Gets certificates from database by names.
     *
     * @param names
     * @return certificate list
     */
    public List<Certificate> getCertificateListByNames(List<String> names) {        
        return new CertificateService().getListWithNamedQuery(Certificate.FIND_BY_NAMES, 
                with("names", names ).parameters());
    }
}
