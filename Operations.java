import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Operations {

	public Operations() {

	}

	public List<MonitoredData> citire() {
		List<MonitoredData> mo = new ArrayList<>();
		List<String> dates = new ArrayList<>();
		Path path = Paths.get("Activities.txt.txt");

		List<String[]> list;
		try {
			list = Files.lines(path).map(line -> line.split("\t\t")).collect(Collectors.toList());

			list.stream().forEach((strings) -> {
				Arrays.stream(strings).forEach(str -> {
					String[] splits = str.split("\t\t");
					for (String a : splits) {
						dates.add(a);
					}
				});
			});

			for (int i = 0; i < dates.size(); i += 3) {
				MonitoredData m = new MonitoredData(dates.get(i), dates.get(i + 1), dates.get(i + 2));
				mo.add(m);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mo;

	}

	public int countDays(List<MonitoredData> m) {
		int days = 0;
		List<String> list = new ArrayList<>();
		for (MonitoredData b : m) {
			String[] splits = b.getStartTime().split(" ");

			list.add(splits[0]);
			System.out.println(splits[0] + "\n");
		}

		for (int i = 1; i < list.size(); i++) {
			int j = 2;
			if (list.get(i).equals(list.get(j)) == true) {
				days++;
			}
			j++;
		}
		
		Set<Integer> s = m.stream().map(t->t.getZi()).collect(Collectors.toSet());
		
		


		return s.size();

	}

	public void distinctActivity(List<MonitoredData> m) throws FileNotFoundException {
		List<String> list = new ArrayList<>();
		
		for (MonitoredData o : m) {
			list.add(o.getActivity());
		}

		List<String> result1 = list.stream().sorted(Comparator.comparing(n -> n.toString()))
				.collect(Collectors.toList());

		Map<String, Long> result = result1.stream().collect(Collectors.toList()).stream()
				.sorted((e1, e2) -> e2.compareTo(e1))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		System.out.println(result);

		Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(new File("2.FrecventaActivitati.txt")), StandardCharsets.UTF_8));
		result.forEach((value, key) -> {
			try {
				writer.write(key + " - " + value + System.lineSeparator());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void zile(List<MonitoredData> m) throws FileNotFoundException {
		List<MonitoredData> mm = m;
		for (MonitoredData b : mm) {
			String[] splita = b.getActivity().split("\t");
			
			b.setActivity(splita[0]);
			
		}

		Map<String, Long> result2 = mm.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
		// System.out.println(result2);

		Map<Integer, Map<String, Long>> r = mm.stream().collect(Collectors.groupingBy(MonitoredData::getZi,
				Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting())));
		r.forEach((k,v)->System.out.println( k + " " + v));

		Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(new File("3.FrecventaActivitatiZile.txt")), StandardCharsets.UTF_8));
		r.forEach((key, value) -> {
			try {
				writer.write(key + " - " + value + System.lineSeparator());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void durata(List<MonitoredData> m) throws FileNotFoundException {

		Map<String, Double> d = m.stream().collect(
				(Collectors.groupingBy(MonitoredData::getActivity, Collectors.summingDouble(MonitoredData::getTimp))));

		Map<String, Double> dd = d.entrySet().stream().filter(map -> map.getValue() >= 36000)
				.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

		dd.forEach((k,v)->System.out.println( k + " " + v));

		Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(new File("4.Durata.txt")), StandardCharsets.UTF_8));
		dd.forEach((key, value) -> {
			try {value=value/3600;
				
				writer.write(key + " - " + value.intValue()+ System.lineSeparator());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int durataPeste(List<MonitoredData> m) {
		List<String> result = new ArrayList<>();
		
		
		
		Map<String, Integer> d = m.stream().collect(
				(Collectors.groupingBy(MonitoredData::getActivity, Collectors.summingInt(MonitoredData::sub))));

		Map<String, Long> r = m.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));

		List<Integer> rr = d.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());

		List<Long> r1 = r.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());

		List<Integer> rb = new ArrayList<>();
		r1.forEach(item -> {
			item = (item * 9) / 10;
			int a = item.intValue();
			rb.add(a);
		});

		
		int a = 0;

		for (int i = 0; i < rr.size(); i++) {
			if (rb.get(i) == rr.get(i) * 9 / 10) {
				a = rr.get(i);
			}
		}

		return a;

	}

	public void f(List<MonitoredData> m) throws FileNotFoundException {
		List<String> result = new ArrayList<>();
		Map<String, Integer> d = m.stream().collect(
				(Collectors.groupingBy(MonitoredData::getActivity, Collectors.summingInt(MonitoredData::sub))));
		int a = durataPeste(m);
		System.out.println(a);
		result = d.entrySet().stream().filter(x -> x.getValue() == durataPeste(m)).map(x -> x.getKey())
				.collect(Collectors.toList());

		result.forEach(System.out::println);
		
		Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(new File("5.Final.txt")), StandardCharsets.UTF_8));
		result.forEach(( value) -> {
			try {
				writer.write( value + System.lineSeparator());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
