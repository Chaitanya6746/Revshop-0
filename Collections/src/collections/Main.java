package collections;

import java.util.*;
import java.util.Map.Entry;

public class Main {
	
	public static void reverseList(List<Integer> arlst) {
		Collections.reverse(arlst);
		System.out.println("ArrayList: "+arlst);
		
	}
	public static void maxElement(List<Integer> arlst) {
		System.out.println("Max of arraylist: "+Collections.max(arlst));
	}
	
	public static void rmvdupsfromArrayList(List<Integer> arlst) {
		ArrayList<Integer> ar = new ArrayList<>();
		for(int i=0;i<arlst.size();i++) {
			if(!ar.contains(arlst.get(i))) {
				ar.add(arlst.get(i));
			}
		}
		System.out.println("ArrayList after removing dups "+ar);
	}
	
	public static void AddelementsHashset(List<Integer> arlst) {
		Set<Integer> hashset =  new HashSet<>();
		hashset.addAll(arlst);
		System.out.println("Hashset "+hashset);
	}
	
	public static void PriorityQueue(List<Integer> arlst) {
		PriorityQueue<Integer> prq = new PriorityQueue<>();
		prq.addAll(arlst);
		System.out.println("Priority order in order :");
		while(!prq.isEmpty()) {
			System.out.print(prq.poll()+" ");
		}
	}
	
	public static void HashMap() {
		HashMap<Integer,String> hm = new HashMap<>();
		hm.put(1, "Chaitanya");
		hm.put(2, "Kalyan");
		hm.put(3, "Ankith");
		hm.put(4, "Venkat");
		hm.put(5, "Sai");
		
		for(Entry<Integer, String> entry : hm.entrySet()) {
			System.out.println("\nKey "+entry.getKey()+", Values "+entry.getValue());
		}
		
	}
	
	public static void frequency(List<Integer> arlst) {
		HashMap<Integer,Integer> hm = new HashMap<>();
		for(int i=0;i<arlst.size();i++) {
			hm.put(arlst.get(i),hm.getOrDefault(arlst.get(i),0)+1);
		}
		for(Entry<Integer, Integer> entry : hm.entrySet()) {
			System.out.println("Element "+entry.getKey()+", Frequency of Element "+entry.getValue());
		}
		
	}
	
	public static void main(String[] args) {
		List<Integer> arlst =  new ArrayList<>();
		arlst.add(10);
		arlst.add(20);
		arlst.add(30);
		arlst.add(40);
		arlst.add(50);
		arlst.add(20);
		arlst.add(50);
		arlst.add(20);
		arlst.add(60);
		arlst.add(70);
		frequency(arlst);
		reverseList(arlst);
		maxElement(arlst);
		rmvdupsfromArrayList(arlst);
		AddelementsHashset(arlst);
		PriorityQueue(arlst);
		HashMap();
		frequency(arlst);
	}
}
