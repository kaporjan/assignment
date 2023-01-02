package com.an.assignment;

import com.an.assignment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ConstraintsTest extends IntegrationTest {
    @Autowired
    private CustomerRepository commentRepository;
/*
    @Test
    void cannotCreateCommentForNonExistingPost() throws Exception {
        mvc.perform(post("/posts/{postId}/comments", 44)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"comment\":\"my comment\"}")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void whenDeletingAPostAllRelatedCommentsDeleted() throws Exception {
        mvc.perform(delete("/posts/1"))
                .andExpect(status().isOk());
        Page<Comment> comments = commentRepository.findByPostId(1L, Pageable.unpaged());
        assertThat(comments.stream().count()).isEqualTo(0L);
        mvc.perform((get("/posts/{postId}/comments", 1)))
                .andExpect(status().isNotFound());

    }

    @Test
    void whenDeletedTheLastCommentPostWillExist() throws Exception {
        mvc.perform(delete("/posts/1/comments/4"))
                .andExpect(status().isOk());
        mvc.perform(delete("/posts/1/comments/5"))
                .andExpect(status().isOk());
        mvc.perform((get("/posts/{postId}/comments", 1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].comment", empty()));
    }

 */
}
