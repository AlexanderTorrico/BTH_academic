/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.UserRoles;
import dto.infoGrupos;
import java.util.ArrayList;

/**
 *
 * @author merxdev
 */
public abstract class UserRolesDao {
    
    public abstract ArrayList<UserRoles> getList(UserRoles obj)throws Exception;

    public abstract void save(UserRoles obj)throws Exception;
    
    
    public abstract ArrayList<UserRoles> getIdDocente(UserRoles obj)throws Exception;
}
