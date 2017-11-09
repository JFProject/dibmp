package cn.mldn.member.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Member;

public class ConvertToMap  {
	public static Map<String,Object> ListToMap(List<Member> all){
		Map<String,Object> map = new HashMap<>();
		Iterator<Member> it = all.iterator();
		while(it.hasNext()){
			Member vo = it.next();
			map.put(vo.getMid(), vo.getName());
		}
		return map;
	}
}