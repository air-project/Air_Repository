package other;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileExploreUtils {
	private static Logger logger = LoggerFactory.getLogger(FileExploreUtils.class);
	private static int id = 0;
	private static int pid = 0;
	private static List<Tree> listt = new ArrayList<Tree>();
	private static List<Tree> sortlist = new ArrayList<Tree>();
	private static Map<Integer, List<Tree>> sortMap = new HashMap<Integer, List<Tree>>();
	private static Map<Integer, Tree> allMap = new HashMap<Integer, Tree>();

	private static void exploreFile(File file, int pid, final String regex) {
		if (file.exists()) {
			File f[] = file.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					// if(name.matches("lib|CVS")){
					// return false;
					// }
					// else
					if (!name.contains(".")) {
						return true;
					} else
						// return name.endsWith(".txt");
						return name.matches(regex);
				}
			});
			for (File f1 : f) {
				Tree t = new Tree();
				id++;
				t.setLeaf(f1.isDirectory());
				t.setId(id);
				t.setPid(pid);
				t.setText(f1.getName());
				listt.add(t);
				allMap.put(t.getId(), t);
				if (f1.isDirectory()) {
					exploreFile(f1, id, regex);
				} else {
					t.setPath(f1.getPath());
				}
			}
		} else {
			logger.info("not exists");
		}
	}

	private static void fifter(int pid) {
		Tree t = allMap.get(pid);
		if (t == null) {
			return;
		}
		if (t.isLeaf()) {
			if (!sortlist.contains(t)) {
				sortlist.add(t);
			}
		} else {
			fifter(t.getPid());
		}
	}

	private static void group(String regex) {
		for (int i = listt.size() - 1; i >= 0; i--) {
			Tree t = listt.get(i);
			if (!t.isLeaf() && t.text.matches(regex)) {
				sortlist.add(t);
				fifter(t.getPid());
			}
		}

		for (Tree t : sortlist) {
			if (t.isLeaf()) {
				List<Tree> tt = new ArrayList<Tree>();
				sortMap.put(t.getId(), tt);
			}
		}
		for (Tree t : sortlist) {
			if (sortMap.containsKey(t.getPid())) {
				sortMap.get(t.getPid()).add(t);
			} else {
				List<Tree> tt = new ArrayList<Tree>();
				tt.add(t);
				sortMap.put(0, tt);
			}
		}
	}

	public static Map<Integer, List<Tree>> getSortMap(String filepath, String regex) {
		if (sortMap.size() == 0) {
			exploreFile(new File(filepath), pid, regex);
			group(regex);
		}
		return sortMap;
	}

	public static String readFile(int id) {
		String content = null;
		try {
			Tree t = allMap.get(id);
			File f = new File(t.getText());
			content = FileUtils.readFileToString(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String readFile(String path) {
		String content = null;
		try {
			File f = new File(path);
			content = FileUtils.readFileToString(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	public static void main(String[] args) {
//		 String s = "F:\\howard\\ntc\\webcontent";
//		 for (Tree t : FileExploreUtils.getSortMap(s, "^.+\\.jsp$").get(0)) {
//		 // if(t.getPid()==8){
//		 logger.info("id={},pid={},text={},path={},isParent={}", t.getId(),
//		 t.getPid(), t.getText(), t.getPath(), t.isLeaf());
//		 // }
//		 }
		
		try {
			String name = "F:\\howard\\ntc\\webcontent\\403.jsp2014.01.03_10.13.20.737";
			File fc = new File(name);
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss.SSS");
			String f=sdf.format(new Date());
//			FileUtils.copyFile(fc, new File(name+f));
			FileUtils.writeStringToFile(fc, f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
