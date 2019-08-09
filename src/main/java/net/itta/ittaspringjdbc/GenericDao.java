/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.ittaspringjdbc;

import java.util.Collection;

public interface GenericDao <K,T> {
  
    public T findOne(K id);
 
    public Collection<T> findAll() ;
 
    public int create(T entity);
 
    public int update(T entity);
 
    public int deleteById(K entityId);
    
     public int saveOrUpdate(T entity);
}
