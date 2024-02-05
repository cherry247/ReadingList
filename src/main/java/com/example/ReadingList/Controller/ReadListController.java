package com.example.ReadingList.Controller;


import com.example.ReadingList.Domain.Book;
import com.example.ReadingList.Repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReadListController {
    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }
    @RequestMapping(value = "/{reader}",method = RequestMethod.GET)
    public String readersBook(@PathVariable("reader") String reader, Model model){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if(readingList!=null){
            model.addAttribute("books",readingList);
        }
        return "readingList";
    }

    @RequestMapping(value = "/{reader}",method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }
}
