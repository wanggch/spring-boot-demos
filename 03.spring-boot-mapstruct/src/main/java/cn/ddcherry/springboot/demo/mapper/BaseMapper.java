package cn.ddcherry.springboot.demo.mapper;

import java.util.List;

public interface BaseMapper<D, E> {
	D toDto(E entity);
	E toEntity(D dto);
	List<D> toDto(List<E> entityList);
	List<E> toEntity(List<D> dtoList);
}
