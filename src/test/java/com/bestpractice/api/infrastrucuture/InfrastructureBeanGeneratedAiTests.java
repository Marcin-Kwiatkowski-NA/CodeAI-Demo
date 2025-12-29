package com.bestpractice.api.infrastrucuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com. bestpractice. api. infrastructure. entity. Info; 
import com. bestpractice. api. infrastructure. persistent. InfoPersistentRepository; 
import org. junit. jupiter. api. Test; 
import org. springframework. beans. factory. annotation. Autowired; 
import org. springframework. boot. test. context. SpringBootTest; 

import static org. junit. jupiter. api. Assertions. assertNotNull; 

@ SpringBootTest 
public class InfoPersistentRepositoryGeneratedAiTests { 

    @ Autowired 
    private InfoPersistentRepository infoPersistentRepository; 

    @ Test 
    public void testFindAll() { 
        assertNotNull( infoPersistentRepository. findAll()); 
    } 

    @ Test 
    public void testFindById() { 
        Info info = infoPersistentRepository. findById(1L); 
        assertNotNull( info); 
    } 
}
