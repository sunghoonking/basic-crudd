package com.example.basiccrud.domain;

import com.example.basiccrud.domain.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ActiveProfiles("test")
class SubjectTest {


    @Nested
    @DisplayName("과목 객체 생성")
    class CreatTime {

        private String subject1;


        @BeforeEach
        void setup() {
            subject1 = "컴퓨터공학과";
        }

        @Test
        @DisplayName("정상 케이스")
        void create_Normal() {

            //given

            //when
            Subject subject = new Subject(subject1);


            //then
            assertThat(subject.getSubjectName()).isEqualTo("컴퓨터공학과");


        }

        @Nested
        @DisplayName("실패 케이스")
        class FailCases {

            @Nested
            @DisplayName("과목 유효성 검사")
            class gap_subject {

                @Test
                @DisplayName("null")
                void invalid_subject(){
                    //given
                    subject1 = null;
                    //when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> new Subject(subject1));

                    //then
                    assertThat(exception.getMessage()).isEqualTo("입력된 과목이 없습니다");
                }
                @Test
                @DisplayName("공백")
                void gap_username() {
                    // given
                    subject1 = "";
                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> new Subject(subject1));

                    // then
                    assertThat(exception.getMessage()).isEqualTo("입력된 과목이 없습니다");
                }

            }


        }

        }
    }
