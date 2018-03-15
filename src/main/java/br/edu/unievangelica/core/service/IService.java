package br.edu.unievangelica.core.service;


import br.edu.unievangelica.core.exception.CustomDuplicatedException;
import br.edu.unievangelica.core.exception.GenericException;

import java.util.List;

public interface IService<T> {

    public T save(T obj);


    public boolean delete(long id);

    public List<T> findAll();

    public T findOne(long id);

    public boolean exists(long id);

}
