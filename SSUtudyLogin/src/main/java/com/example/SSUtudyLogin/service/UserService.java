package com.example.SSUtudyLogin.service;

import com.example.SSUtudyLogin.DTO.*;
import com.example.SSUtudyLogin.domain.Category;
import com.example.SSUtudyLogin.domain.CategoryUser;
import com.example.SSUtudyLogin.domain.User;

import com.example.SSUtudyLogin.repository.CategoryRepository;
import com.example.SSUtudyLogin.repository.CategoryUserRepository;
import com.example.SSUtudyLogin.repository.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.SSUtudyLogin.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {
    @Lazy
    private  final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    @Lazy
    private final CategoryUserRepository categoryUserRepository;
    @Lazy
    private final PasswordEncoder passwordEncoder;
    @Lazy
    private final JwtTokenProvider jwtTokenProvider;

    @Lazy
    private final EntityManager entityManager;

    @Transactional
    public Long join(UserJoinDTO userJoinDTO) {
        List<CategoryUser> categoryUserList = new ArrayList<>();

        User newUser =User.builder()
                .studentId(userJoinDTO.getStudent_Id())
                .password(passwordEncoder.encode(userJoinDTO.getPassword()))
                .name(userJoinDTO.getName())
                .grade(userJoinDTO.getGrade())
                .department(userJoinDTO.getDepartment())
                .categoryUsers(categoryUserList)
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build();




        for(String i: userJoinDTO.getCategories()){

            Category category =  categoryRepository.findById(Long.parseLong(i)).orElseThrow(() -> new IllegalArgumentException("잘못된 카테고리 넘버를 입력하셨습니다."));
            CategoryUser m = new CategoryUser(newUser,category);
            categoryUserRepository.save(m);

            categoryUserList.add(m);
        }
        newUser.setCategoryUsers(categoryUserList);

        userRepository.save(newUser);

        return  newUser.getId();


    }

    @Transactional
    public UserModifyDTO Update(UserModifyDTO userModifyDTO){
        Optional.ofNullable(userModifyDTO).orElseThrow(()->{
            return new NullPointerException("건네준 DTO가 존재하지 않음");
        });

        User persistance = userRepository.findByStudentId(userModifyDTO.getStudent_Id()).orElseThrow(()->{
            return new IllegalArgumentException("회원 찾기 실패");
        });

        //password 필드 수정
        Optional.ofNullable(userModifyDTO.getPassword())
                .ifPresentOrElse(password->{
                            String encodedPassword = passwordEncoder.encode(password);
                            persistance.setPassword(encodedPassword);
                        }
                        ,()->userModifyDTO.setPassword(persistance.getPassword()));

        Optional.ofNullable(userModifyDTO.getName())
                .ifPresentOrElse(persistance::setName,()->userModifyDTO.setName(persistance.getName()));


        Optional.ofNullable(userModifyDTO.getGrade())
                .ifPresentOrElse(grade->persistance.setGrade(grade),()->userModifyDTO.setGrade(persistance.getGrade()));

        Optional.ofNullable(userModifyDTO.getDepartment())
                .ifPresentOrElse(persistance::setDepartment,()->userModifyDTO.setDepartment(persistance.getDepartment()));



            if(userModifyDTO.getCategories()!=null&&!userModifyDTO.getCategories().stream().anyMatch(element -> element == null)){

                persistance.getCategoryUsers().clear();
                categoryUserRepository.deleteByUserId(persistance.getId());
                for(String i: userModifyDTO.getCategories()){

                    Category category =  categoryRepository.findById(Long.parseLong(i)).orElseThrow(() -> new IllegalArgumentException("잘못된 카테고리 넘버를 입력하셨습니다."));
                    CategoryUser m = new CategoryUser(persistance,category);
                    categoryUserRepository.save(m);
                    persistance.getCategoryUsers().add(m);
                }
        }
            else{
                List<CategoryUser> CUlist= persistance.getCategoryUsers();
                List<String> Clist = new ArrayList<>();
                for (CategoryUser i: CUlist) {
                    Clist.add(Long.toString(i.getCategory().getId()));

                }
                userModifyDTO.setCategories(Clist);
            }

        return userModifyDTO;


//        private String student_Id;
//
//
//        private String password;
//
//
//        private String name;
//
//        private int grade;
//
//
//        private String department;
//
//
//        private List<String> categories = new ArrayList<>();

    }

    @Transactional
    public Long delete(UserDeleteDTO userDeleteDTO){
        User member = userRepository.findByStudentId(userDeleteDTO.getStudent_Id())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 id 입니다."));
        if (!passwordEncoder.matches(userDeleteDTO.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        userRepository.delete(member);
        return 1L;
    }


    @Transactional
    public String login(UserLogInDTO userLogInDTO) {
        User member = userRepository.findByStudentId(userLogInDTO.getStudent_Id())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 id 입니다."));
        if (!passwordEncoder.matches(userLogInDTO.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        return  jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }


    public UserFindDTO findByStudentId(String student_id) {
        User entity = userRepository.findByStudentId(student_id).orElseThrow(()->new IllegalArgumentException("해당 유저가 없습니다. student_Id="+student_id));
        List<String> categories_String = new ArrayList<>();

        for(CategoryUser i: entity.getCategoryUsers()){
            categories_String.add(Long.toString(i.getId()));
        }
        return new UserFindDTO(entity,categories_String);
    }



}
