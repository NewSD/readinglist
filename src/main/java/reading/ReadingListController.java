package reading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by ami on 2019/2/14.
 */
@Controller
@RequestMapping("/")
@ConfigurationProperties(prefix="amazon")
public class ReadingListController {

    private String associateId;

    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository){
        this.readingListRepository = readingListRepository;
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }


    @RequestMapping(value="/{reader}", method = RequestMethod.GET)
    public String readerBooks(
            @PathVariable("reader") String reader,
            Model model
    ){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if(readingList != null){
            model.addAttribute("books",readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", associateId);

        }
        return "readingList";
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadingList(
            @PathVariable("reader") String reader, Book book
    ){
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }
}
