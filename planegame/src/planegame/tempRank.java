package planegame;

public class tempRank {
	
	String date;
	String time;
	int playTime;
	int level;
	/**
	 * @param date
	 * @param time
	 * @param level
	 */
	public tempRank(String date, String time, int playTime,int level) {
		this.date = date;
		this.time = time;
		this.playTime=playTime;
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
	public String getTime() {
		return time;
	}
	

	@Override
	public String toString() {
		//return "tempRank [date=" + date + ", time=" + time + ", playTime=" + playTime + ", level=" + level + "]";
		//return "Time: "+playTime+" second "+" Level: "+level+" Date: "+date+" "+time+".";
		return "Time: "+playTime+" second "+" Level: "+level+" Date: "+date+".";
		
	}
	public int getPlayTime() {
		return playTime;
	}
	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + playTime;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof tempRank))
			return false;
		tempRank other = (tempRank) obj;
		if (playTime != other.playTime)
			return false;
		return true;
	}
	
	
}
