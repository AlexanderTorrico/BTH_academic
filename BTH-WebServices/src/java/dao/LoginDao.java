/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Login;
import dto.infoGrupos;
import java.util.ArrayList;

/**
 *
 * @author merxdev
 */
public abstract class LoginDao {
    public abstract Login docente(Login obj)throws Exception;
}
