package App.service;

import App.entity.Department;
import App.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Optional<Department> findDepartmentByDepartmentName(String departmentName) {
        return this.departmentRepository.findDepartmentByDepartmentName(departmentName);
    }

    public Optional<Department> findById(UUID id) {
        return this.departmentRepository.findById(id);
    }

    public List<Department> findAll() {
        return this.departmentRepository.findAll();
    }

    public Department createOrUpdate(Department department) {
        return this.departmentRepository.save(department);
    }

    public void delete(Department department) {
        this.departmentRepository.delete(department);
    }
}
