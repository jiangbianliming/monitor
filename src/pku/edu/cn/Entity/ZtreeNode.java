package pku.edu.cn.Entity;

public class ZtreeNode {
	private String id;
	private String pId;
	private String name;
	private Boolean open;
	private String stdname;
	private String icon;
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getStdname() {
		return stdname;
	}
	public void setStdname(String stdname) {
		this.stdname = stdname;
	}
	public ZtreeNode(String id, String pId, String name, boolean open,String stdname,String icon) {
		// TODO Auto-generated constructor stub
		this.id =id;
		this.pId = pId;
		this.name =name;
		this.open = open;
		this.stdname = stdname;
		this.icon = icon;
	}
	public ZtreeNode() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
}
