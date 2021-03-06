/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo;
import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.model.Tour;
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
@CommandRequestMapping(url = "/admin/add-tour")
public class AddTourCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			AddTourCommand.class.getName());

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executePost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executePost(final HttpServletRequest request, 
			final HttpServletResponse response) throws IOException, 
				ServletException, ServiceException {
		final String name = request.getParameter("name");
		final long price = Long.parseLong(request.getParameter("price"));
		final String typeOfHolidayName = request.getParameter("holidaytype");
		final int numberOfPeople = Integer.parseInt(request.getParameter(
				"numberofpeople"));
		final String hotelTypeName = request.getParameter("hoteltype");
		final String tourStatusName = request.getParameter("status");
		final boolean isHot = (request.getParameter("ishot") != null);
		final int maxDiscount = Short.parseShort(request.getParameter("maxdiscount"));
		final int stepDiscount = Short.parseShort(request.getParameter("stepdiscount"));
		final int incrementDiscount = Short.parseShort(request.
				getParameter("incrementdiscount"));
		final AbstractBoFactory boFactory = AbstractBoFactory.getBoFactory(AbstractBoFactory.
				SIMPLEDEFAULTFAC);
		final TourBo tourBo = boFactory.getTourBo();
		final long holidayTypeId = tourBo.getHolidayTypeId(typeOfHolidayName);
		final long hotelTypeId = tourBo.getHotelTypeId(hotelTypeName);
		final int tourStatusId = tourBo.getStatusId(tourStatusName);
		LOGGER.info(holidayTypeId + " " + typeOfHolidayName);
		final Tour tour = new Tour(0, name, price, holidayTypeId, 
				typeOfHolidayName, numberOfPeople, hotelTypeId, 
				hotelTypeName, tourStatusId, tourStatusName, isHot,
				maxDiscount, stepDiscount, incrementDiscount);
		tourBo.insert(tour);
		return "add-tour";
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executeGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executeGet(final HttpServletRequest request, 
			final HttpServletResponse response) {
		return FilesPathsStorage.COMMAND_TOUR_MENU;
	}

}
