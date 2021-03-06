package ua.nure.dominov.SummaryTask4.web.controller.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo;
import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.model.HotelType;
import ua.nure.dominov.SummaryTask4.db.model.Tour;
import ua.nure.dominov.SummaryTask4.db.model.TourStatus;
import ua.nure.dominov.SummaryTask4.db.model.TypeOfHoliday;
import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.web.controller.command.AbstractCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;

/**
 * @author calango
 *
 */
@CommandUserRoleCoefficient(roleCoefficient = 100)
@CommandRequestMapping(url = "/admin/tour-menu")
public class TourMenuCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			TourMenuCommand.class.getName());
	

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executePost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executePost(final HttpServletRequest request, 
			final HttpServletResponse response) throws IOException, 
				ServletException, ServiceException {
		LOGGER.info("Tour menu command begins");
		final HttpSession session = request.getSession();
		final AbstractBoFactory boFactory = AbstractBoFactory.getBoFactory(
				AbstractBoFactory.SIMPLEDEFAULTFAC);
		final TourBo tourBo = boFactory.getTourBo();
		final List<Tour> freeTours = tourBo.getFreeTours();
		final List<Tour> bookedTours = tourBo.getBookedTours();
		final List<TypeOfHoliday> holTypes = tourBo.getAllTypesOfHoliday();
		final List<HotelType> hotelTypes = tourBo.getAllHotelTypes();
		final List<TourStatus> toursStatuses = tourBo.getAllTourStatuses();
		session.setAttribute("freetours", freeTours);
		session.setAttribute("bookedtours", bookedTours);
		session.setAttribute("holidayType", holTypes);
		session.setAttribute("hotelType", hotelTypes);
		session.setAttribute("tourStatus", toursStatuses);
		LOGGER.info("Tour menu command finished");
		return "tour-menu";
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executeGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executeGet(final HttpServletRequest request, 
			final HttpServletResponse response) {
		return FilesPathsStorage.PAGE_TOUR_MENU;
	}

}
