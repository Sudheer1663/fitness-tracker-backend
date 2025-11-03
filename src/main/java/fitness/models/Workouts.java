package fitness.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="workouts")
public class Workouts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Long userId;
private String type;
private int duration;
private int calories;
private LocalDate date;

public Workouts() {
	
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

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public int getDuration() {
	return duration;
}

public void setDuration(int duration) {
	this.duration = duration;
}

public int getCalories() {
	return calories;
}

public void setCalories(int calories) {
	this.calories = calories;
}

public LocalDate getDate() {
	return date;
}

public void setDate(LocalDate date) {
	this.date = date;
}

public Workouts(Long id, Long userId, String type, int duration, int calories, LocalDate date) {
	super();
	this.id = id;
	this.userId = userId;
	this.type = type;
	this.duration = duration;
	this.calories = calories;
	this.date = date;
}



}
