package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {3, 5, 2, 9, 1};
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		hm.put(2, "a");
		hm.put(1, "b");
		hm.put(3, "c");
		Set<Integer> set = hm.keySet();
//		SortedSet<Integer> keySet = new TreeSet<Integer>(hm.keySet());
		List<Integer> sortedCollection = new ArrayList<Integer>(hm.keySet());
		System.out.println(sortedCollection);
		
	}

}
