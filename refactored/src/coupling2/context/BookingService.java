package coupling2.context;

import java.util.Date;

import coupling.BookingException;
import coupling.BookingResult;
import coupling2.ConferencingServer;
import coupling2.MeetingCalendar;

class BookingService {

  private final BigFatContext context;

  public BookingService(BigFatContext context) {
    this.context = context;
  }

  public BookingResult bookConference(String topic) {
    MeetingCalendar calendar = context.getMeetingCalendar();
    Date startDate = calendar.nextPossibleDate();
    try {
      ConferencingServer server = context.getConferencingServer();
      server.bookConference(topic, startDate);
      return BookingResult.forSuccess(startDate);
    }
    catch (BookingException e) {
      return BookingResult.forFailure(e);
    }
  }
}
