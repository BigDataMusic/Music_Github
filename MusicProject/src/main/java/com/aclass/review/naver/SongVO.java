package com.aclass.review.naver;
import java.io.File;

// 몽고디비 project3 - song테이블 VO
public class SongVO {

 private String no;
 private String song;
 private String singer;
 
 private String naverReview;

public String getNaverReview() {
	return naverReview;
}

public void setNaverReview(String naverReview) {
	this.naverReview = naverReview;
}

public String getSong() {
	return song;
}

public void setSong(String song) {
	this.song = song;
}

public String getSinger() {
	return singer;
}

public void setSinger(String singer) {
	this.singer = singer;
}

public String getNo() {
	return no;
}

public void setNo(String no) {
	this.no = no;
}



 
 
 
 
}
