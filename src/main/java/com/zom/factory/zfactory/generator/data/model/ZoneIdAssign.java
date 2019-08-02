package com.zom.factory.zfactory.generator.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> ZoneIdAssign <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华-airson <p>
* <strong> 编写时间：</strong> 2017年8月30日12:20:28 <p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都零壹众科技有限公司 http://www.01more.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
*/
public class ZoneIdAssign implements Serializable {

	private static final long serialVersionUID = -5625452958621348805L;

	private int		id;
	private String	name;
	private Long	maxUid;
	private Long	maxTgid;
	private Long	curUid;
	private Long	curTgid;
	private Integer	num;
	
	private List<Long>	userList	= new ArrayList<Long>(2000000);
	private List<Long>	con1List	= new ArrayList<Long>(100);
	private List<Long> groupList = new ArrayList<Long>(200000);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMaxUid() {
		return maxUid;
	}

	public void setMaxUid(Long maxUid) {
		this.maxUid = maxUid;
	}

	public Long getMaxTgid() {
		return maxTgid;
	}

	public void setMaxTgid(Long maxTgid) {
		this.maxTgid = maxTgid;
	}

	public Long getCurUid() {
		return curUid;
	}

	public void setCurUid(Long curUid) {
		this.curUid = curUid;
	}

	public Long getCurTgid() {
		return curTgid;
	}

	public void setCurTgid(Long curTgid) {
		this.curTgid = curTgid;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<Long> getUserList() {
		return userList;
	}

	public void setUserList(List<Long> userList) {
		this.userList = userList;
	}

	public List<Long> getCon1List() {
		return con1List;
	}

	public void setCon1List(List<Long> con1List) {
		this.con1List = con1List;
	}
	

	public List<Long> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Long> groupList) {
		this.groupList = groupList;
	}

}
