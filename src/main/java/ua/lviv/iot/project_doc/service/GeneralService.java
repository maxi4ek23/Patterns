package ua.lviv.iot.project_doc.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GeneralService<T> {
	
	private final JpaRepository<T, Integer> repository;

    public GeneralService(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public T saveToDatabase(T newObject) {
        return repository.save(newObject);
    }

    public boolean deleteById(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public T update(Integer id, T newObject, T oldObject)  {
        if (repository.existsById(id)) {
        	try {
				BeanUtils.copyProperties(repository.findById(id).get(), oldObject);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
            repository.save(newObject);
        }
        return oldObject;
    }

    abstract public T mapCsvToObject(String[] objectCsv);
}
