package App.models.department;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DepartmentRegisterModel {
    @NotEmpty(message = "Please provide: Department Name")
    private String departmentName;
}
