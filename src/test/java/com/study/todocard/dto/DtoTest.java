package com.study.todocard.dto;

import com.study.todocard.security.dto.SignupRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DtoTest {
    private static ValidatorFactory factory;
    private static Validator validator;


    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    @DisplayName("SingupDto 테스트")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class SignupRequestDtoTest {
        private static SignupRequestDto requestDto;

        @BeforeAll
        public static void init() {
            requestDto = new SignupRequestDto();
            requestDto.setUsername("user");
            requestDto.setPassword("1234567890");
        }

        @Order(1)
        @Test
        @DisplayName("SingupDto 이메일 공백")
        void test1() {
            // given

            // when
            Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(requestDto);

            // then
            assertEquals(1, violations.size());
            for (ConstraintViolation<SignupRequestDto> violation : violations) {
                assertEquals("공백일 수 없습니다", violation.getMessage());
            }
        }

        @Order(2)
        @Test
        @DisplayName("SingupDto 이메일 형식")
        void test2() {
            // given
            requestDto.setEmail("abcde");

            // when
            Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(requestDto);

            // then
            assertEquals(1, violations.size());
            for (ConstraintViolation<SignupRequestDto> violation : violations) {
                assertEquals("올바른 형식의 이메일 주소여야 합니다", violation.getMessage());
            }

        }

        @Order(3)
        @Test
        @DisplayName("SingupDto username 형식")
        void test3() {
            // given
            requestDto.setEmail("abcde@aga.com");
            requestDto.setUsername("AAAAbb");

            // when
            Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(requestDto);

            // then
            assertEquals(1, violations.size());
            for (ConstraintViolation<SignupRequestDto> violation : violations) {
                assertEquals("\"^[a-z0-9]*\"와 일치해야 합니다", violation.getMessage());
            }

        }

        @Order(4)
        @Test
        @DisplayName("SingupDto password 형식")
        void test4() {
            // given
            requestDto.setUsername("user");
            requestDto.setPassword("223");

            // when
            Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(requestDto);

            // then
            assertEquals(1, violations.size());
            for (ConstraintViolation<SignupRequestDto> violation : violations) {
                assertEquals("크기가 8에서 16 사이여야 합니다", violation.getMessage());
            }

        }
    }
}