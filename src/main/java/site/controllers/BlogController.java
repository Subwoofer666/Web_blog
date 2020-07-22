package site.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.models.Post;
import site.repo.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("blog")                        /*-------------------находит все блоги*/
    public String blog(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog";
    }

    @GetMapping("blog/redi")            //redirect:/blog - перенаправляетс просто по ссылке, forward перенаправляет в контроллер
    public String redi(Model model) {
        return "forward:/blog";
    }

    @GetMapping("blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model/*, byte[] image*/) {
        Post post = new Post(title, anons, full_text/*, image*/);
        postRepository.save(post);
        return "redirect:/blog";
    }

    /*@PostMapping("filter") *//*нихуя не понял, не работает поиск*//*
    public String filter(@RequestParam String title, Model model) {
        List<Post> post = postRepository.findByTitle(title);
        model.addAttribute("filter", post);
        return "redirect:/blog_open";
    }*/
    @GetMapping("filter")
    public String filter(@RequestParam String filter, Model model) {
        if (!postRepository.existsByTitle(filter)) {
            return "redirect:/blog";
        }
        Post post = postRepository.findByTitle(filter);
        model.addAttribute("filter", post);
        return "blog";
    }

    @GetMapping("/blog/{id}")
    public String blogOpen(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        Post post1 = post.get();
        ++post1.views;                  /*------счетчик */
        postRepository.save(post1);     /*------просмотров*/
        model.addAttribute("post", post1);
        return "blog-open";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> result = new ArrayList<>();
        post.ifPresent(result::add);
        ++result.get(0).views;                  /*------счетчик */
        postRepository.save(result.get(0));     /*------просмотров через лист (если выводить несколько статей сразу)*/
        model.addAttribute("post", result);  /*------сейчас в листе всего один объект*/
        return "blog-edit";
    }

    @PostMapping("blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model/*, byte[] image*/) {
        Post post = postRepository.findById(id).orElseThrow(IllegalAccessError::new);
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("blog/{id}/remove")
    public String blogPostRemove(@PathVariable(value = "id") long id, Model model/*, byte[] image*/) {
        Post post = postRepository.findById(id).orElseThrow(IllegalAccessError::new);
        postRepository.delete(post);
        return "redirect:/blog";
    }

    @PostMapping("filter/delete_title")
    public String filterPostRemove(@RequestParam String title, Model model/*, byte[] image*/) {
        Post post = postRepository.findByTitle(title);
        postRepository.delete(post);
        return "redirect:/blog";
    }
}
