package planegame;

public class tempRank {
	
	String date;
	int time;
	int level;
	/**
	 * @param date
	 * @param time
	 * @param level
	 */
	public tempRank(String date, int time, int level) {
		this.date = date;
		this.time = time;
		this.level = level;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	
	
}
