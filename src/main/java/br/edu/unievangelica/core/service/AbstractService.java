package br.edu.unievangelica.core.service;


import br.edu.unievangelica.core.exception.CustomNotFoundException;
import br.edu.unievangelica.core.exception.ExceptionMessageCode;
import br.edu.unievangelica.core.exception.GenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public class AbstractService<T> implements IService<T> {

    @Autowired
    protected CrudRepository<T, Long> repository;

    @Override
    public T save(T obj) throws GenericException {
        try {
            return repository.save(obj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException(ExceptionMessageCode.MENSAGEM_REGISTRO_DUPLICADO);
        }
    }

    @Override
    public boolean delete(long id) throws GenericException {
        T obj = repository.findOne(id);
        if (obj != null) {
            try {
                repository.delete(id);
                return true;
            } catch (Exception e) {
                throw new GenericException(ExceptionMessageCode.MENSAGEM_REGISTRO_COM_DEPENDENCIA);
            }
        }

        throw new CustomNotFoundException(ExceptionMessageCode.MENSAGEM_NOT_FOUND);
    }

    @Override
    public List<T> findAll() {

        //throw new CustomNotFoundException(ExceptionMessageCode.MENSAGEM_NOT_FOUND);

        return (List<T>) repository.findAll();
    }

    @Override
    public T findOnde(long id) throws GenericException {
        T obj = repository.findOne(id);
        if (obj == null) {
            throw new GenericException(ExceptionMessageCode.MENSAGEM_NOT_FOUND);
        }
        return obj;
    }

    @Override
    public boolean exists(long id) throws GenericException {
        T obj = repository.findOne(id);
        if (obj == null) {
            return false;
        }
        return true;
    }
}
