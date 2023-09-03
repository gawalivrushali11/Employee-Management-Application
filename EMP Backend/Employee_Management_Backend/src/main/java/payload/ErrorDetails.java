package payload;

import java.util.Date;

public class ErrorDetails {
	private Date timestamp;
	private String msg;
	private String details;

	
	public ErrorDetails(Date timestamp, String msg, String details ) {
		super();
		this.timestamp = timestamp;
		this.msg = msg;
		this.details = details;
	
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMsg() {
		return msg;
	}

	public String getDetails() {
		return details;
	}

	

	
}
