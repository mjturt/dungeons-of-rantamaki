package combat;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MonsterGenerator {
	private ArrayList<Monster> monsterList;
	
	public MonsterGenerator() {
		monsterList = new ArrayList<Monster>();
		String path = Paths.get(".").toAbsolutePath().toString();
		path = path.substring(0, path.length()-1) + "src\\combat\\";
		Path mPath = Paths.get(path, "monsterlist");
		try {
			List<String> monsters = Files.readAllLines(mPath); //monsterlist is read as utf-8 and autoclosed
			for(String s: monsters) {
				monsterList.add(parseLine(s));
			}
		}
		catch(IOException ioe) {
			System.out.println("Something went wrong :(");
			ioe.printStackTrace();
		}
	}
	//returns the monster by it's line number in a file
	//with level that is inputted, could use randomization?
	public Monster getMonster(int i, int lvl) { 
		Monster prototype = monsterList.get(i);
		for(int j=1;j<lvl;j++) {
			prototype.LevelUp();
		}
		return prototype;
	}
	
	private Monster parseLine(String line) {
		String[] monsterArray = line.split(",");
		return new Monster(Integer.valueOf(monsterArray[0]), monsterArray[1], Integer.valueOf(monsterArray[2]), Integer.valueOf(monsterArray[3]), Integer.valueOf(monsterArray[4]));
		}
}
