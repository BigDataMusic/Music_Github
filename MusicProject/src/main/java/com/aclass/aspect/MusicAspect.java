package com.aclass.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aclass.mgr.*;
import com.aclass.mongodb.MusicDAO;;

@Aspect
@Component
public class MusicAspect {
	@Autowired
	private BugsManager bfm;
	@Autowired
	private MusicDAO dao;
}
