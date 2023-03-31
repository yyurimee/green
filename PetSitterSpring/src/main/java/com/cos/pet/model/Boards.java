package com.cos.pet.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Boards {
	private int board_id;
	private String board_category;
	private String board_content;
	private String board_created_date;
	private String board_title;
	private String board_writer;
	
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getBoard_category() {
		return board_category;
	}
	public void setBoard_category(String board_category) {
		this.board_category = board_category;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_created_date() {
		return board_created_date;
	}
	public void setBoard_created_date(String board_created_date) {
		this.board_created_date = board_created_date;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	
}
