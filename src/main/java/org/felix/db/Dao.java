package org.felix.db;

public interface Dao<T>
{
	abstract void update(T t);

	abstract void insert(T t);

	abstract void delete(T t);

	abstract void query(T t);
}
