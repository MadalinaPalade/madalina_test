package pack.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

public class Response {
	private HttpStatus status;
	private String reason;
	private List<Object> data;

	public Response() {
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public Response getResponse(Object data) {
		this.data = new ArrayList<>();
		this.data.add(data);
		if (this.data != null) {
			setStatus(HttpStatus.OK);
			setReason("No reason to fail");
		} else {
			setStatus(HttpStatus.CONFLICT);
			setReason("No content or null values");
			setData(new ArrayList<>());
		}
		return this;
	}
}
