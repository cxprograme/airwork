package active;

import java.sql.Date;

public class acentity {
	private int id;
	private String activename;
	private String acontent;
	private Date acdate;
	private int u_id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivename() {
		return activename;
	}

	public void setActivename(String activename) {
		this.activename = activename;
	}

	public String getAcontent() {
		return acontent;
	}

	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}

	public Date getAcdate() {
		return acdate;
	}

	public void setAcdate(Date acdate) {
		this.acdate = acdate;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	@Override
	public String toString() {
		return "acentity [id=" + id + ", activename=" + activename + ",acontent=" + acontent + ", acdate=" + acdate + ", u_id=" + u_id + "]";
	}
	
	
}
