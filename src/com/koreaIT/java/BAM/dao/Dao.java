package com.koreaIT.java.BAM.dao;

public class Dao {
	public int lastId;

	public int setArticleId() {
		return lastId + 1;
	}
}
