package com.cg.service;

import java.util.List;

import com.cg.bean.FeedbackParameters;
import com.cg.bean.FeedbackReport;
import com.cg.dao.FeedbackReportDao;
import com.cg.dao.FeedbackReportDaoImpl;
import com.cg.exception.FeedbackNotFoundException;

public class FeedbackReportServiceImpl implements FeedbackReportService {
	
	private FeedbackReportDao feedbackDao;
	
	public FeedbackReportServiceImpl() {
		feedbackDao = new FeedbackReportDaoImpl();
	}
	
	@Override
	public List<FeedbackReport> getByMonth(int month) throws FeedbackNotFoundException {
		return feedbackDao.byMonth(month);
	}

	@Override
	public List<FeedbackReport> getByFacultyForMonth(int facultyCode, int month) throws FeedbackNotFoundException {
		return feedbackDao.byFacultyForMonth(facultyCode, month);
	}

	@Override
	public List<FeedbackReport> getDefaultersByMonth(int month) throws FeedbackNotFoundException {
		return feedbackDao.feedbackDefaultersByMonth(month);
	}

	@Override
	public FeedbackParameters getAvgByMonth(int month) {
		return feedbackDao.getAvgByMonth(month);
	}

	@Override
	public FeedbackParameters getAvgByMonthAndFaculty(int month, int faculty) {
		return feedbackDao.getAvgByMonthAndFaculty(month, faculty);
	}

}
