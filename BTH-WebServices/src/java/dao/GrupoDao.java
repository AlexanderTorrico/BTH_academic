/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.infoGrupos;
import java.util.ArrayList;

/**
 *
 * @author merxdev
 */
public abstract class GrupoDao {
    public abstract ArrayList<infoGrupos> grupoInfo(infoGrupos obj)throws Exception;
}