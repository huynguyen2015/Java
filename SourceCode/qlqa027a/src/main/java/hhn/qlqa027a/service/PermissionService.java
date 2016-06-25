/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.service;

import hhn.qlqa027a.entities.Permission;
import static hhn.qlqa027a.service.QueryParameter.with;
import hhn.qlqa027a.util.I18n;
import java.util.List;

/**
 * Customer Service
 *
 * @author Cem Ikta
 */
public class PermissionService extends AbstractService<Permission> {
    
    public PermissionService() {
        super(Permission.class);
    }
    
    @Override
    public boolean remove(Permission permission) throws Exception {
        System.out.println(permission.getName());
        System.out.println(I18n.CUSTOMERS.getString("App.Session.HasMemberPermission"));
        if((permission.getName() == null || permission.getName().equals(I18n.CUSTOMERS.getString("App.Session.HasMemberPermission")))
                || (permission.getName().equals(I18n.CUSTOMERS.getString("App.Session.HasMenuPermission")))
                || (permission.getName().equals(I18n.CUSTOMERS.getString("App.Session.HasTablePermission")))
                || (permission.getName().equals(I18n.CUSTOMERS.getString("App.Session.HasCategoryPermission")))
                || (permission.getName().equals(I18n.CUSTOMERS.getString("App.Session.HasFoodPermission")))
                || (permission.getName().equals(I18n.CUSTOMERS.getString("App.Session.HasRolePermission")))
                || (permission.getName().equals(I18n.CUSTOMERS.getString("App.Session.HasPermissionPermission")))
                || (permission.getName().equals(I18n.CUSTOMERS.getString("App.Session.HasItemPermission")))
                || (permission.getName().equals(I18n.CUSTOMERS.getString("App.Session.HasShiftPermission")))
                || (permission.getName().equals(I18n.CUSTOMERS.getString("App.Session.HasSchedulePermission")))
                || (permission.getName().equals(I18n.CUSTOMERS.getString("App.Session.HasCPPermission")))
                || (permission.getName().equals(I18n.CUSTOMERS.getString("App.Session.HasBillPermission"))))
            throw new Exception("You can not delete default permission.");

        removeFromDatabase(permission);
        return true;          
    }
    
    public Permission findByName(String name){
        List<Permission> permissions = getListWithNamedQuery(Permission.FIND_BY_NAME, with("name", name).parameters());
        return permissions.isEmpty() ? null : permissions.get(0);
    }
}
