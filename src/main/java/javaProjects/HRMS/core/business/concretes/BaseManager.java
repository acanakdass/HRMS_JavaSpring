package javaProjects.HRMS.core.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProjects.HRMS.core.business.abstracts.BaseService;
import javaProjects.HRMS.core.constants.Messages;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.ErrorDataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.core.utilities.results.SuccessDataResult;
import javaProjects.HRMS.core.utilities.results.SuccessResult;

public abstract class BaseManager<TEntityDao extends JpaRepository<TEntity, TEntityId>, TEntity, TEntityId>
		implements BaseService<TEntity, TEntityId> {
	private TEntityDao entityDao;
	private String entityName;

	public BaseManager(TEntityDao entityDao, String entityName) {
		super();
		this.entityDao = entityDao;
		this.entityName = entityName;
	}
	
	@Override
	public Result add(TEntity entity) {
		entityDao.save(entity);
		return new SuccessResult(Messages.added(entityName));
	}

	@Override
	public Result delete(TEntityId id) {
		 Optional<TEntity> entity = entityDao.findById(id);
		if (entity.isEmpty())
			return new ErrorDataResult<TEntity>(Messages.notFound(entityName));

		entityDao.delete(entity.get());
		return new SuccessResult(Messages.deleted(entityName));
	}

	@Override
	public DataResult<List<TEntity>> getAll() {
		 List<TEntity> entities = entityDao.findAll();
		return new SuccessDataResult<List<TEntity>>(entities);
	}

	@Override
	public DataResult<TEntity> getById(final TEntityId id) {
		 Optional<TEntity> entity = entityDao.findById(id);

		if (entity.isEmpty())
			return new ErrorDataResult<TEntity>(Messages.notFound(entityName));

		return new SuccessDataResult<TEntity>(entity.get());
	}

	@Override
	public Result update(final TEntity entity) {
		entityDao.save(entity);
		return new SuccessResult(Messages.updated(entityName));
	}
}
