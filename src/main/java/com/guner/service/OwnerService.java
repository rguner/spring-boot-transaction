package com.guner.service;

import com.guner.entity.Owner;
import com.guner.entity.Product;
import com.guner.repository.OwnerRepository;
import com.guner.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerService {

    private OwnerRepository ownerRepository;


     // failed to lazily initialize a collection of role: com.guner.entity.Owner.blogList: could not initialize proxy - no Session] with root cause
     // bu hata nedeniyle transactional annotation ekledik
     @Transactional
    public Owner findById(int id) {
         Owner ownerOut = ownerRepository.findById(id).get();
         System.out.println("\nOwner details :: \n" + ownerOut);

         // getBlogList için select bu aşamada yapılır
         System.out.println("\nList of Blogs :: \n" + ownerOut.getBlogList());

         System.out.println("\nDone!!!");
         return ownerOut;

    }

}
