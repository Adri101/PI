package es.unex.pi.model;

public class Route {

	private long id;
	private String title;
	private String description;
	private String date;
	private float distance;
	private String time;
	private String difficulty;
	private int nUser;
	
	private int elevation;
	private int kudos;
	private int blocked;
	
	private long idu;
	
	public int getnUser() {
		return nUser;
	}
	public void setnUser(int nUser) {
		this.nUser=nUser;
	}
	

	
	public int getBlocked() {
		return blocked;
	}
	public void setBlocked(int blocked) {
		this.blocked = blocked;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public int getElevation() {
		return elevation;
	}
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	public long getIdu() {
		return idu;
	}
	public void setIdu(long idu) {
		this.idu = idu;
	}
	public int getKudos() {
		return kudos;
	}
	public void setKudos(int kudos) {
		this.kudos = kudos;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	
}
