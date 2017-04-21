package com.aclass.mgr;
import java.util.*;
public class AlbumVO {
	private int alNo;
	private int alRank;
	private String alType;
	private String alTitle;
	private String alPoster;
	private String alArtist;
	private String alRegdate;
	private String alGenre;
	private String saleCo;
	private String entertainment;
	private double alPoint;
	private int alLike;
	private String alInfo;
	private String alIncrement;
	
	List<MusicVO> mList= new ArrayList<MusicVO>();
	
	public String getAlType() {
		return alType;
	}
	public void setAlType(String alType) {
		this.alType = alType;
	}
	public int getAlRank() {
		return alRank;
	}
	public void setAlRank(int alRank) {
		this.alRank = alRank;
	}
	public int getAlNo() {
		return alNo;
	}
	public void setAlNo(int alNo) {
		this.alNo = alNo;
	}
	public double getAlPoint() {
		return alPoint;
	}
	public void setAlPoint(double alPoint) {
		this.alPoint = alPoint;
	}
	public int getAlLike() {
		return alLike;
	}
	public void setAlLike(int alLike) {
		this.alLike = alLike;
	}
	public String getAlInfo() {
		return alInfo;
	}
	public void setAlInfo(String alInfo) {
		this.alInfo = alInfo;
	}
	public String getAlTitle() {
		return alTitle;
	}
	public void setAlTitle(String alTitle) {
		this.alTitle = alTitle;
	}
	public String getAlPoster() {
		return alPoster;
	}
	public void setAlPoster(String alPoster) {
		this.alPoster = alPoster;
	}
	public String getAlArtist() {
		return alArtist;
	}
	public void setAlArtist(String alArtist) {
		this.alArtist = alArtist;
	}
	public String getAlRegdate() {
		return alRegdate;
	}
	public void setAlRegdate(String alRegdate) {
		this.alRegdate = alRegdate;
	}
	public String getAlGenre() {
		return alGenre;
	}
	public void setAlGenre(String alGenre) {
		this.alGenre = alGenre;
	}
	public String getSaleCo() {
		return saleCo;
	}
	public void setSaleCo(String saleCo) {
		this.saleCo = saleCo;
	}
	public String getEntertainment() {
		return entertainment;
	}
	public void setEntertainment(String entertainment) {
		this.entertainment = entertainment;
	}
	public List<MusicVO> getmList() {
		return mList;
	}
	public void setmList(List<MusicVO> mList) {
		this.mList = mList;
	}
	public String getAlIncrement() {
		return alIncrement;
	}
	public void setAlIncrement(String alIncrement) {
		this.alIncrement = alIncrement;
	}
	
}