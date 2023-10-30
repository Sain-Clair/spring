package kr.co.ictedu.mvc.controller.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ictedu.mvc.dto.JsonDTO;

@RestController
public class TestRestController {
	
	@GetMapping(value="/restHello", produces = "application/json; charset=UTF-8")
	public String viewMessage() { 
		return "ㅎㅇ";
	}

	// VO를 반환하는 JSON -> {"key1": val 1, "key2":val 2}
	@GetMapping(value="/restJsonDTO", produces = "application/json; charset=UTF-8")
	public JsonDTO viewJsonDto() {
		JsonDTO vo = new JsonDTO("바다", 1000);
		return vo;
	}

	// List<JSON> -> [{"key1": val 1, "key2":val 2},{"key1": val 1, "key2":val 2}]
	@GetMapping(value="/restListJson", produces = "application/json; charset=UTF-8")
	public List<JsonDTO> ListJson() {
		
		List<JsonDTO> list = new ArrayList<JsonDTO>();
		// List - 순서 o, 중복 o / MAP, SET - 순서 x, 중복 x	
		list.add(new JsonDTO("한강",1)); 
		list.add(new JsonDTO("강2",2));
		list.add(new JsonDTO("강3",3));
		
		return list;
	}
	@GetMapping(value="/restListJson2", produces = "application/json; charset=UTF-8")
	public String surveyDetail_title() {
		List<JsonDTO> list = new ArrayList<JsonDTO>();
		String[] area = {"산","바다","강","영화관","집"};
		int[] cnts = {1100, 2000, 100, 22500, 10};
		for(int i = 0; i < area.length; i++) {
			JsonDTO vo = new JsonDTO(area[i], cnts[i]);
			list.add(vo);
		}
		Map<String, Integer> map = new HashMap<>();
		for(JsonDTO e: list) {
			map.put(e.getTitle(), e.getCnt());
		}
		System.out.println("Size: " + list.size());
		String result = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			//map을 문자열 json object의 값으로 변경 시켜주는 역할한다.
			result = mapper.writeValueAsString(map);
			System.out.println(result);
			result = "[{\"sub\":\"좋하는 장소는?\"},"+result+"]";
		}catch (JsonProcessingException e) {
			e.printStackTrace(); 
		}
		return result ;
	}
}
