package ua.lviv.iot.project_doc.model;


import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "company", schema = "employee_managment")
@Data
@NoArgsConstructor
public class Company {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    @Column(name = "id")
    private Integer id;
	
	@Basic
	@Column(name = "name")
	private String name;
	
	@Basic
	@Column(name = "location")
	private String location;
	
	public Company(String name, String location) {
        this.name = name;
        this.location = location;
    }
	
	public String[] toCsvFormat() {
        return new String[]{ "Company", this.name, this.location};
    }
}
