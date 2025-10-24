package fitness.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	 private int fitnessGoalId;
	private String title;
	private String type;
	private String level;
	private int duration;
	private Double cost;
	@Lob
	private byte[] imageData;
	
	
	public Cart() {
	
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public Double getCost() {
		return cost;
	}


	public void setCost(Double cost) {
		this.cost = cost;
	}




	public byte[] getImageData() {
		return imageData;
	}


	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}


	public int getFitnessGoalId() {
		return fitnessGoalId;
	}


	public void setFitnessGoalId(int fitnessGoalId) {
		this.fitnessGoalId = fitnessGoalId;
	}


	public Cart(Long id, Long userId, int fitnessGoalId, String title, String type, String level, int duration,
			Double cost, byte[] imageData) {
		super();
		this.id = id;
		this.userId = userId;
		this.fitnessGoalId = fitnessGoalId;
		this.title = title;
		this.type = type;
		this.level = level;
		this.duration = duration;
		this.cost = cost;
		this.imageData = imageData;
	}
	
}
