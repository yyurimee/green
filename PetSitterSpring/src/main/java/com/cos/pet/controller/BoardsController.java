package com.cos.pet.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cos.pet.dao.BoardsDAO;
import com.cos.pet.model.Boards;

@Controller
@RequestMapping("/pet")
public class BoardsController {
	final BoardsDAO dao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//프로퍼티파일로부터 저장 경로 참조
	@Value("${pet.imgdir}")
	String fdir;
	
	@Autowired
	public BoardsController(BoardsDAO dao) {
		this.dao = dao;
	}
		
	@PostMapping("/add")
	public String addBoards(@ModelAttribute Boards boards, Model m, @RequestParam(value="file", required=false) MultipartFile file) throws Exception {
		try {
			
			File dest = new File(fdir+"/"+file.getOriginalFilename());
			
			file.transferTo(dest);
			
			//boards.setBoard_img("/img/"+dest.getName());
			dao.addBoards(boards);
		} catch(Exception e) {
			e.printStackTrace();
			logger.info("게시글 추가 과정에서 문제 발생!!");
			m.addAttribute("error", "게시글이 정상적으로 등록되지 않았습니다!!");
		}
		return "redirect:/pet/list";
	}
	
	@GetMapping("/delete/{board_id}")
	public String deleteBoards(@PathVariable int board_id, Model m) {
		try {
			dao.delBoards(board_id);
		} catch(SQLException e) {
			e.printStackTrace();
			logger.warn("게시글 삭제 과정에서 문제 발생!!");
			m.addAttribute("error", "게시글이 정상적으로 삭제되지 않았습니다!!");
		}
		return "redirect:/pet/list";
	}
	
	@GetMapping("/list")
	public String listBoards(Model m) {
		List<Boards> list;
		try {
			list = dao.getAll();	//리스트를 가져와서
			m.addAttribute("boardslist", list);	//저장
		} catch(Exception e) {
			e.printStackTrace();
			logger.warn("게시글 목록 생성 과정에서 문제 발생!!");
			m.addAttribute("error", "게시글 목록이 정상적으로 처리되지 않았습니다!!");
		}
		return "pet/boardList";
	}
	
	@GetMapping("/{board_id}")
	public String getBoards(@PathVariable int board_id, Model m) {
		try {
			Boards b = dao.getBoards(board_id);	//경로 파라미터 방식으로 요청 진행
			m.addAttribute("boards", b);
		} catch(SQLException e) {
			e.printStackTrace();
			logger.warn("게시글을 가져오는 과정에서 문제 발생!!");
			m.addAttribute("error", "게시글을 정상적으로 가져오지 못했습니다!!");
		}
		return "pet/boardView";
	}
	
	@GetMapping("/write")
	public String writeBoards() {
		return "pet/boardWrite";
	}
	
	
	
}
