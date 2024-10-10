package com.guner.controller;

import com.guner.entity.Blog;
import com.guner.entity.Owner;
import com.guner.repository.BlogRepository;
import com.guner.repository.OwnerRepository;
import com.guner.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerRepository ownerRepository;

    private final BlogRepository blogRepository;

    private final OwnerService ownerService;

    @PostMapping("/saveOwner")
    public String saveOwner(@RequestBody Owner owner) {
        System.out.println("Owner save called...");

        // a new Owner
        Owner ownerIn = new Owner(owner.getName(), owner.getEmail());

        // list of Blog
        List<Blog> blogs = new ArrayList<>();
        for (Blog blogIn : owner.getBlogList()) {
            // new Blog
            Blog blog = new Blog(blogIn.getTitle(), blogIn.getCategory(), blogIn.getContent());
            // set owner to Blog
            blog.setOwner(ownerIn);
            // add blog to list
            blogs.add(blog);
        }

        // add blog list to Owner
        ownerIn.setBlogList(blogs);

        // save Owner
        Owner ownerOut = ownerRepository.save(ownerIn);
        System.out.println("Owner out :: " + ownerOut);

        System.out.println("Saved!!!");
        return "Owner saved!!!";
    }

    @PostMapping("/saveBlog")
    @Transactional
    public String saveBlog(@RequestParam(name = "id") String id) {
        System.out.println("Blog save called...");

        // fetch Ower
        Owner ownerTemp = ownerRepository.getById(Integer.valueOf(id));

        // list of Blog
        List<Blog> blogs = new ArrayList<>();

        // new Blog
        Blog blog = new Blog("Build application server using NodeJs", "nodeJs",
                "We will build REStful api using nodeJs.");
        // set owner to blog
        blog.setOwner(ownerTemp);
        // add Blog to list
        blogs.add(blog);

        blog = new Blog("Single Page Application using Angular", "Angular",
                "We can build robust application using Angular framework.");
        // set owner to blog
        blog.setOwner(ownerTemp);
        blogs.add(blog);

        // add Blog list to Owner
        ownerTemp.setBlogList(blogs);

        // save Owner
        ownerRepository.save(ownerTemp);

        System.out.println("Saved!!!");
        return "Blog saved!!!";
    }

    @PostMapping("/saveBlogWithNewOwner")
    @Transactional
    public String saveBlogWithNewOwner(@RequestBody Owner owner) {
        // a new Owner
        Owner ownerIn = new Owner(owner.getName(), owner.getEmail());

        // list of Blog
        List<Blog> blogs = new ArrayList<>();
        for (Blog blogIn : owner.getBlogList()) {
            // new Blog
            Blog blog = new Blog(blogIn.getTitle(), blogIn.getCategory(), blogIn.getContent());
            // set owner to Blog
            blog.setOwner(ownerIn);
            // add blog to list
            blogs.add(blog);
        }

        // add blog list to Owner
        ownerIn.setBlogList(blogs);

        // blogs.forEach(blogRepository::save); ConcurrentModificationException] with root cause

        for (int i = 0; i < blogs.size(); i++) {
            blogRepository.save(blogs.get(i));
        }

        System.out.println("Saved!!!");
        return "Blog saved!!!";
    }

    @PostMapping("/saveBlogWithReference")
    @Transactional
    public String saveBlogWithReference(@RequestParam(name = "id") String id) {
        System.out.println("Blog save called...");

        // fetch Ower
        Owner ownerTemp = ownerRepository.getReferenceById(Integer.valueOf(id));

        // list of Blog
        List<Blog> blogs = new ArrayList<>();

        // new Blog
        Blog blog = new Blog("Build application server using NodeJs", "nodeJs",
                "We will build REStful api using nodeJs.");
        // set owner to blog
        blog.setOwner(ownerTemp);
        // add Blog to list
        blogs.add(blog);

        blog = new Blog("Single Page Application using Angular", "Angular",
                "We can build robust application using Angular framework.");
        // set owner to blog
        blog.setOwner(ownerTemp);
        blogs.add(blog);

        /* bu yöntem ownerRepository veya blogRepository save olabilir.
        // add Blog list to Owner
        ownerTemp.setBlogList(blogs);

        // save Owner
        //ownerRepository.save(ownerTemp);
         */

        blogs.forEach(blogRepository::save);


        System.out.println("Saved!!!");
        return "Blog saved!!!";
    }

    @GetMapping("/getOwner/{id}")
    public String getOwner(@PathVariable(name = "id") String id) {
        System.out.println("Owner get called...");

        // fetch Owner
        Owner ownerOut = ownerService.findById(Integer.valueOf(id));
        return "Owner fetched...";
    }

    @GetMapping("/getBlog/{id}")
    public String getBlog(@PathVariable(name = "id") String id) {
        System.out.println("Blog get called...");

        // fetch Blog
        Blog blogOut = blogRepository.findById(Integer.valueOf(id)).get();
        System.out.println("\nBlog details :: \n" + blogOut);
        System.out.println("\nOwner details :: \n" + blogOut.getOwner());

        System.out.println("\nDone!!!");
        return "Blog fetched...";
    }
}