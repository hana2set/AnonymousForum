package com.study.todocard.controller;

import com.study.todocard.dto.CommentRequestDto;
import com.study.todocard.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(controllers = {CommentController.class})
class CommentControllerTest extends CommonControllerTest{
    @MockBean
    CommentService commentService;

    @Test
    @DisplayName("comment 업데이트")
    void test1() throws Exception {
        // given
        Long commentId = 10L;

        CommentRequestDto requestDto = new CommentRequestDto();

        String content = objectMapper.writeValueAsString(requestDto);

        // when - then
        mvc.perform(put("/api/comments/" + commentId)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

}