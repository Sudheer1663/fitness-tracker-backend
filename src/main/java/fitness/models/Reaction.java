//package fitness.models;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name="reaction")
//public class Reaction {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//private  int fire;
//private int muscle;
//private int clap;
//private int heart;
//public Reaction() {
//}
//public int getFire() {
//	return fire;
//}
//public void setFire(int fire) {
//	this.fire = fire;
//}
//public int getMuscle() {
//	return muscle;
//}
//public void setMuscle(int muscle) {
//	this.muscle = muscle;
//}
//public int getClap() {
//	return clap;
//}
//public void setClap(int clap) {
//	this.clap = clap;
//}
//public int getHeart() {
//	return heart;
//}
//public void setHeart(int heart) {
//	this.heart = heart;
//}
//public Reaction(int fire, int muscle, int clap, int heart) {
//	super();
//	this.fire = fire;
//	this.muscle = muscle;
//	this.clap = clap;
//	this.heart = heart;
//}
//
//}



package fitness.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Reaction {
    private int fire;
    private int muscle;
    private int clap;
    private int heart;

    public Reaction() {}

    public int getFire() { return fire; }
    public void setFire(int fire) { this.fire = fire; }

    public int getMuscle() { return muscle; }
    public void setMuscle(int muscle) { this.muscle = muscle; }

    public int getClap() { return clap; }
    public void setClap(int clap) { this.clap = clap; }

    public int getHeart() { return heart; }
    public void setHeart(int heart) { this.heart = heart; }
}

