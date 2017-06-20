package tw.iii.org;

import org.json.JSONStringer;
import org.json.JSONWriter;

public class DBtoJSONMethod {

	public static void main(String[] args) {
		String json =  new JSONStringer().object().key("key1")
										.value("value1")
										.endObject().toString();
		System.out.println(json);
		
		//array()與endArray()代表一個做出一陣列,轉換成JSON模式
		//object()與endObject()之間視為一個元素,轉換成JSON模式
		String json2 =  new JSONStringer().array()
				.object()
				.key("key1")
				.value("value1")
				.endObject()
				.object()
				.key("key2")
				.value("value2")
				.endObject()
				.endArray()
				.toString();
		System.out.println(json2);
		
		JSONWriter jw = new JSONStringer().array();
		
		jw.object().key("key1").value("value1").endObject().endArray();
		System.out.println(jw);
	}

}
