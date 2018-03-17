import java.time.Duration;
import java.time.LocalDateTime;

public class MonitoredData {

	public String startTime;
	public String endTime;
	public String activity;

	public MonitoredData(String startTime, String endTime, String activity) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.activity = activity;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getActivity() {
		return activity;
	}

	public int getZi() {
		String[] split = getStartTime().split(" ");
		String[] splits = split[0].split("-");
		System.out.println(splits[2]);
		int a = Integer.parseInt(splits[2]);

		return a;
	}

	public double getTimp() {
		double period = 0;

		String[] splite = getEndTime().split("\t");
		String[] splitee = splite[0].split(" ");
		String[] spliteee = splitee[0].split("-");
		String[] spliteeee = splitee[1].split(":");
		String[] splits = getStartTime().split("\t");
		String[] splitss = splits[0].split(" ");
		String[] splitsss = splitss[0].split("-");
		String[] splitssss = splitss[1].split(":");

		LocalDateTime oldDate = LocalDateTime.of(Integer.parseInt(splitsss[0]), Integer.parseInt(splitsss[1]),
				Integer.parseInt(splitsss[2]), Integer.parseInt(splitssss[0]), Integer.parseInt(splitssss[1]),
				Integer.parseInt(splitssss[2]));
		LocalDateTime newDate = LocalDateTime.of(Integer.parseInt(spliteee[0]), Integer.parseInt(spliteee[1]),
				Integer.parseInt(spliteee[2]), Integer.parseInt(spliteeee[0]), Integer.parseInt(spliteeee[1]),
				Integer.parseInt(spliteeee[2]));

		System.out.println("Start Time:"+oldDate);
		System.out.println("End Time:"+newDate);
		
		
		Duration duration = Duration.between(oldDate, newDate);

		period = duration.getSeconds();

		return period;

	}

	public int sub() {
		String[] splite = getEndTime().split("\t");
		String[] splitee = splite[0].split(" ");
		String[] spliteee = splitee[0].split("-");
		String[] spliteeee = splitee[1].split(":");
		String[] splits = getStartTime().split("\t");
		String[] splitss = splits[0].split(" ");
		String[] splitsss = splitss[0].split("-");
		String[] splitssss = splitss[1].split(":");

		LocalDateTime oldDate = LocalDateTime.of(Integer.parseInt(splitsss[0]), Integer.parseInt(splitsss[1]),
				Integer.parseInt(splitsss[2]), Integer.parseInt(splitssss[0]), Integer.parseInt(splitssss[1]),
				Integer.parseInt(splitssss[2]));
		LocalDateTime newDate = LocalDateTime.of(Integer.parseInt(spliteee[0]), Integer.parseInt(spliteee[1]),
				Integer.parseInt(spliteee[2]), Integer.parseInt(spliteeee[0]), Integer.parseInt(spliteeee[1]),
				Integer.parseInt(spliteeee[2]));

		Duration duration = Duration.between(oldDate, newDate);

		double period = duration.getSeconds();

		if (period < 300) {
			System.out.println(period);
			return 1;
		}
		return 0;

	}

	public String toString() {
		String[] splita = getActivity().split("\t");

		setActivity(splita[0]);
		return "Activitatea " + getActivity() + " a inceput la " + getStartTime() + " si s-a sfarsit la "
				+ getEndTime();
	}

}