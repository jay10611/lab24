package co.grandcircus.Lab24;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PartyController {

	
	@Autowired 
	private PartyRepo dao1;
	
	@Autowired
	private PartyOptionRepo dao2;
	
	@Autowired
	private RsvpRepo rsvpDao;
	
	@RequestMapping("/")
	public String index() {
		
		return "redirect:/party";
	}
	
	@RequestMapping("/party")
	public String listParty(Model model) {
		List<Party> party=dao1.findAll();
		model.addAttribute("party",party);
		
		return "home";
		
	}
	
	@RequestMapping("/save-rsvp")
	public String submitRSVP(Rsvp rsvp) {
		  rsvpDao.save(rsvp);
		  return "redirect:/party";
	}
	
	@RequestMapping("search-name")
	public String listPartyByName(Model model,@RequestParam("name") String name) {
		List<Party> party=dao1.findPartyByName(name);
		model.addAttribute("party",party);
		return "home";
		}
	
	@RequestMapping("/partyoption")
	public String listPizzaOptions(Model model) {
		List<PartyOption> pizzaOption=dao2.findAll();
		model.addAttribute("pizzaOption",pizzaOption);
		
		return "votes";
	}
	
	@RequestMapping("/addMoreVotes")
	public String addMoreVote(Model model,@RequestParam("id") Long id) {
	  PartyOption pizza=dao2.findById(id).get();
	  pizza.setVotes(pizza.getVotes()+1);
	  dao2.save(pizza);
		  
		 return "redirect:/partyoption";
	}
	
	@PostMapping("/addNewPizza")
	public String addPizzaOption(PartyOption pizza,Model model) {
		dao2.save(pizza);
		model.addAttribute("pizza",pizza);
		return "redirect:/partyoption";
	}
	
	@RequestMapping("/review-page")
	public String showReviewPage(Model model) {
		List<PartyOption> pizza=dao2.findAll(Sort.by(Direction.DESC,"votes"));
		model.addAttribute("pizza",pizza);
		return "/reviews";
	}
	
	@RequestMapping("/admin-page")
	public String showAdminPage(Model model) {
		List<Party> party=dao1.findAll(Sort.by(Direction.DESC,"date"));
		model.addAttribute("party",party);
		return "/admin";
	}
	
	@RequestMapping("add-party")
	public String addParty() {
		return "add-party";
	}
	
	@RequestMapping("submit-party")
	public String submitParty(Model model,Party party) {
		dao1.save(party);
		model.addAttribute("party",party);
		return "redirect:/admin-page";
	}
	
	@RequestMapping("/delete")
	public String removeParty(@RequestParam("id") Long id) {
		dao1.deleteById(id);
		return "redirect:/admin-page";
	}
	
	@RequestMapping("/edit")
	public String editParty(@RequestParam("id") Long id,Model model) {
		Party party=dao1.findById(id).get();
		model.addAttribute("party",party);
		return "edit";
	}
	
	@PostMapping("/edit")
	public String submitEdit(@RequestParam("id") Long id,Party party) {
		dao1.save(party);
		return "redirect:/admin-page?id="+id;
		
	}
	
	@RequestMapping("/{id}")
	public String showRSVP(Model model,@PathVariable("id") Party party)
	{
		model.addAttribute("party",party);
		return "rsvp-show";
	}
	
}