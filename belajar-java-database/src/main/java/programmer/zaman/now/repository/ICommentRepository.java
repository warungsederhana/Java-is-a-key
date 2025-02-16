package programmer.zaman.now.repository;

import programmer.zaman.now.entity.Comment;

import java.util.List;

public interface ICommentRepository {

  void insert(Comment comment);

  Comment findById(Integer id);

  List<Comment> findAll();

  List<Comment> findAllByEmail(String email);
}
