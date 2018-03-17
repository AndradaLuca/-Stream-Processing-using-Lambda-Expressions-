
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Operations o = new Operations();
		List<MonitoredData> mm = new ArrayList<>();
		List<MonitoredData> mmm = new ArrayList<>();
		mm=o.citire();
		mmm=o.citire();	
			mm.forEach(System.out::println);
		
			int days=o.countDays(mm);
			System.out.println("Sunt " + days + " zile");
			
			o.distinctActivity(mm);
			
			o.zile(mm);
			mmm.forEach(System.out::println);
			o.durata(mmm);
			o.durataPeste(mmm);
			o.f(mmm);
			mmm.forEach(System.out::println);
		
	}
}
