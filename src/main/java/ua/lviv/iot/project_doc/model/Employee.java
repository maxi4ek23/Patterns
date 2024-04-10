package ua.lviv.iot.project_doc.model;

import javax.persistence.*;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee", schema = "employee_managment")
@Data
@NoArgsConstructor
public class Employee {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    @Column(name = "id")
    private Integer id;
	
	@Basic
	@Column(name = "name")
	private String name;
	
	@Basic
	@Column(name = "surname")
	private String surname;
	
	@Basic
	@Column(name = "salary")
	private String salary;
	
	@Basic
	@Column(name = "position")
	private String position;
	
	@ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
	
	public Employee( String name, String surname, String salary, String position, Company company) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.position = position;
        this.company = company;
    }
	
	public String[] toCsvFormat() {
		Integer companyId = generateRandomId();
        return new String[]{ "Employee", this.name, this.surname, this.salary, this.position,
                companyId.toString() };
    }
	
	private Integer generateRandomId() {
		EasyRandomParameters parameters = new EasyRandomParameters().seed(System.currentTimeMillis());
		EasyRandom generator = new EasyRandom(parameters);
		return generator.nextInt(1, 501);
	}
}
