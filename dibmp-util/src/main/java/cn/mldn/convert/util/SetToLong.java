package cn.mldn.convert.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetToLong {
	public static Set<Long> ObjectValToLong(Set<Object> set){
		Set<Long> all = new HashSet<>();
		Iterator<Object> it = set.iterator();
		while(it.hasNext()){
			all.add(Long.parseLong(it.next().toString()));
		}
		return all;
	}
}
