package pack.util;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class ResponseFormat {
	final static JsonNodeFactory factory = JsonNodeFactory.instance;
	static ObjectNode node;
	static ObjectNode child;

	@PostConstruct
	public void init() {
		node = factory.objectNode();
		child = factory.objectNode();

	}

	public static ObjectNode setResponse(String json) {
		node.removeAll();
		child.removeAll();

		if (json.equals("null")) {
			child.put("status", "Failure");
			child.put("reason", HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
			child.put("data", "No content or null values");
		} else {
			child.put("status", "Success");
			child.put("data", json);
		}
		node.set("result", child);

		return node;
	}

}
