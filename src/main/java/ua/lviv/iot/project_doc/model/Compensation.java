package ua.lviv.iot.project_doc.model;

import javax.persistence.*;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "compensation", schema = "employee_managment")
@Data
@NoArgsConstructor
public class Compensation {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    @Column(name = "id")
    private Integer id;
	
	@Basic
	@Column(name = "amount")
	private String amount;
	
	@Basic
	@Column(name = "type")
	private String type;
	
	@ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
	
	public Compensation(String amount, String type, Company company) {
        this.amount = amount;
        this.type = type;
        this.company = company;
    }
	
	public String[] toCsvFormat() {
		Integer companyId = generateRandomId();
        return new String[]{ "Compensation", this.amount, this.type,
                companyId.toString() };
    }
	
	private Integer generateRandomId() {
		EasyRandomParameters parameters = new EasyRandomParameters().seed(System.currentTimeMillis());
		EasyRandom generator = new EasyRandom(parameters);
		return generator.nextInt(1, 501);
	}
}
