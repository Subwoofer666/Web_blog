package site.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.models.Post;
import site.repo.PostRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("blog")
    public String blog(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog";
    }

    @GetMapping("blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("blog/add")
    public String blogPostAdd(@RequestParam String title,@RequestParam String anons, @RequestParam String full_text, Model model/*, byte[] image*/) {
        Post post = new Post(title, anons, full_text/*, image*/);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogOpen(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog"; }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> result = new ArrayList<>();
        post.ifPresent(result::add);
        ++result.get(0).views;
        postRepository.save(result.get(0));
        model.addAttribute("post", result);
        return "blog-open";
    }
}
