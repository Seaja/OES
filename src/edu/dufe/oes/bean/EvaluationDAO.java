package edu.dufe.oes.bean;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Evaluation entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.dufe.oes.bean.Evaluation
 * @author MyEclipse Persistence Tools
 */
public class EvaluationDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EvaluationDAO.class);
	// property constants
	public static final String EVALUATION_TITLE = "evaluationTitle";
	public static final String EVALUATION_STATUS = "evaluationStatus";
	public static final String IS_GROUP_EVALUATION = "isGroupEvaluation";
	public static final String EVALUATION_ID = "evaluationID";
	public void save(Evaluation transientInstance) {
		log.debug("saving Evaluation instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Evaluation persistentInstance) {
		log.debug("deleting Evaluation instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Evaluation findById(java.lang.Integer id) {
		log.debug("getting Evaluation instance with id: " + id);
		try {
			Evaluation instance = (Evaluation) getSession().get(
					"edu.dufe.oes.bean.Evaluation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Evaluation instance) {
		log.debug("finding Evaluation instance by example");
		try {
			List results = getSession()
					.createCriteria("edu.dufe.oes.bean.Evaluation")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Evaluation instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Evaluation as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEvaluationTitle(Object evaluationTitle) {
		return findByProperty(EVALUATION_TITLE, evaluationTitle);
	}

	public List findByEvaluationStatus(Object evaluationStatus) {
		return findByProperty(EVALUATION_STATUS, evaluationStatus);
	}

	public List findByIsGroupEvaluation(Object isGroupEvaluation) {
		return findByProperty(IS_GROUP_EVALUATION, isGroupEvaluation);
	}

	public List findAll() {
		log.debug("finding all Evaluation instances");
		try {
			String queryString = "from Evaluation";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Evaluation merge(Evaluation detachedInstance) {
		log.debug("merging Evaluation instance");
		try {
			Evaluation result = (Evaluation) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Evaluation instance) {
		log.debug("attaching dirty Evaluation instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Evaluation instance) {
		log.debug("attaching clean Evaluation instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}