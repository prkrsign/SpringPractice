package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.User;

public class UserDao implements CrudRepository<User, Long> {
	
    public User findByUsername(String username);

    public User findByMailAddress(String mailAddress);

	@Override
	public <S extends User> S save(S entity) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Optional<User> findById(Long id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public Iterable<User> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Iterable<User> findAllById(Iterable<Long> ids) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public long count() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void delete(User entity) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void deleteAll(Iterable<? extends User> entities) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void deleteAll() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
