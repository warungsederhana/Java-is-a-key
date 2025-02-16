package programmer.zaman.now;

import org.junit.jupiter.api.*;
import programmer.zaman.now.entity.Comment;
import programmer.zaman.now.repository.CommentRepository;
import programmer.zaman.now.repository.ICommentRepository;

import java.util.List;

public class RepositoryTest {
  private ICommentRepository commentRepository;

  @BeforeEach
  void setUp() {
    commentRepository = new CommentRepository();
  }

  @Test
  @Disabled // comment this to run testInsert
  void testInsert() {
    Comment comment = new Comment("eko@test.com", "hi");
    commentRepository.insert(comment);

    Assertions.assertNotNull(comment.getId());
    System.out.println(comment.getId());
  }

  @Test

  void testFindById() {
    Comment comment = commentRepository.findById(504);
    Assertions.assertNotNull(comment);
    System.out.println(comment);

    Comment commentNotFound = commentRepository.findById(1000);
    Assertions.assertNull(commentNotFound);
  }

  @Test
  void testFindAll() {
    List<Comment> comments = commentRepository.findAll();
    Assertions.assertNotNull(comments);
    System.out.println(comments.size());
    System.out.println(comments);
  }

  @Test
  void testFindByEmail() {
    List<Comment> comments = commentRepository.findAllByEmail("eko@test.com");
    Assertions.assertNotNull(comments);
    System.out.println(comments.size());
    System.out.println(comments);
  }
}
